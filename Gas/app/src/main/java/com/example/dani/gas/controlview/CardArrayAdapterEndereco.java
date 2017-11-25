package com.example.dani.gas.controlview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dani.gas.EnderecosActivity;
import com.example.dani.gas.LoginActivity;
import com.example.dani.gas.R;
import com.example.dani.gas.WelcomeActivity;
import com.example.dani.gas.connection.RestClient;
import com.example.dani.gas.entity.Endereco;
import com.example.dani.gas.entity.Pedido;
import com.example.dani.gas.entity.Produto;
import com.example.dani.gas.util.Share;
import com.example.dani.gas.view.TabEnderecos;
import com.example.dani.gas.view.TabPedidos;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.dani.gas.view.TabEnderecos.cardArrayAdapter;

public class CardArrayAdapterEndereco extends ArrayAdapter<Endereco> {
    private static final String TAG = "CardArrayAdapter";
    private List<Endereco> cardList = new ArrayList<>();

    static class CardViewHolder {
        TextView endereco;
        Button  btselecionar, bteditar, btexcluir;
        ImageView imgview;
    }

    public CardArrayAdapterEndereco(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public void add(Endereco object) {
        cardList.add(object);
        super.add(object);
    }


    public void add(int index, Endereco endereco) {
        cardList.add(index, endereco);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public Endereco getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final CardViewHolder viewHolder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_enderecos, parent, false);

            viewHolder = new CardViewHolder();

            viewHolder.endereco = (TextView) row.findViewById(R.id.tvEndereco);
            viewHolder.btexcluir = (Button) row.findViewById(R.id.btExcluir);
            viewHolder.bteditar = (Button) row.findViewById(R.id.btEditar);
            viewHolder.btselecionar = (Button) row.findViewById(R.id.btSelecionar);

            // new LoadImage().execute("http://52.24.37.49/img/min/img-perfil/0b4399eb482f818224e3d3e3703eec44.jpg");

            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }

        final Endereco mCartItem =  getItem(position);

        viewHolder.btexcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = (int) v.getTag();

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setTitle(R.string.dialog_titulo_confirmacao_excluir);
                alert.setMessage(R.string.dialog_mensagem_confirmacao_excluir);
                alert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        RestClient.get("kuhn.daniel.ws.entity.endereco/excluir/"+id, null, new JsonHttpResponseHandler() {

                            @Override
                            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                                Log.d("Retorno: ", response.toString());
                                try {
                                    JSONObject jobj = new JSONObject(response.toString());

                                    String retorno = jobj.getString("ret");

                                    Log.d("Retorno: ", retorno.toString());

                                    if (retorno.equals(true) || retorno.equals("true")) {
                                        cardList.remove(mCartItem);
                                        notifyDataSetChanged();

                                        Toast.makeText(getContext(),"Endereço Excluido", Toast.LENGTH_LONG).show();

                                    } else {
                                        Log.d("não", "não logou");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                                Log.d("retorno", responseString);
                            }
                        });
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });

        viewHolder.btselecionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = (int) v.getTag();

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setTitle(R.string.dialog_titulo_confirmacao);
                alert.setMessage(R.string.dialog_mensagem_confirmacao);
                alert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        RestClient.get("kuhn.daniel.ws.entity.pedido/adicionar/1/"+ EnderecosActivity.produto_id+"/"+id, null, new JsonHttpResponseHandler() {

                            @Override
                            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                                Log.d("Retorno: ", response.toString());
                                try {
                                    JSONObject jobj = new JSONObject(response.toString());

                                    String retorno = jobj.getString("ret");

                                    Log.d("Retorno: ", retorno.toString());

                                    if (retorno.equals(true) || retorno.equals("true")) {

                                        TabPedidos.listarPedidos();
                                        Toast.makeText(getContext(),"Pedido feito", Toast.LENGTH_LONG).show();

                                    } else {
                                        Log.d("não", "não logou");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                                Log.d("retorno", responseString);
                            }
                        });

                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });


        Endereco endereco = getItem(position);

        String lbl_endereco = endereco.getRua()+" - "+endereco.getNumero()+" - "+endereco.getBairro();

        if (endereco.getComplemento() != null ) {
            lbl_endereco += " - "+endereco.getComplemento();
        }

        lbl_endereco += " - "+endereco.getCidade();

        viewHolder.bteditar.setTag(endereco.getId());
        viewHolder.btexcluir.setTag(endereco.getId());
        viewHolder.btselecionar.setTag(endereco.getId());
        viewHolder.endereco.setText(lbl_endereco);

        // Picasso.with(getContext()).load(user.getImg()).into(viewHolder.imgview);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}


/*
    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Loading Image ....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                //.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();
                Toast.makeText(getContext(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }
    */
