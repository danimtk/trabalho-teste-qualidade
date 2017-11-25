package com.example.dani.gas.controlview;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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

import com.example.dani.gas.R;
import com.example.dani.gas.connection.RestClient;
import com.example.dani.gas.util.Share;
import com.example.dani.gas.entity.Pedido;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CardArrayAdapterPedido extends ArrayAdapter<Pedido> {
    private static final String TAG = "CardArrayAdapter";
    private List<Pedido> cardList = new ArrayList<Pedido>();


    static class CardViewHolder {
        TextView titulo, descricao, status, endereco;
        Button  btcancelar;
        ImageView imgview;
    }

    public CardArrayAdapterPedido(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public void add(Pedido object) {
        cardList.add(object);
        super.add(object);
    }


    public void add(int index, Pedido pedido) {
        cardList.add(index, pedido);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public Pedido getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_pedidos, parent, false);

            viewHolder = new CardViewHolder();

            viewHolder.titulo     = (TextView) row.findViewById(R.id.tvTitulo);
            viewHolder.descricao  = (TextView) row.findViewById(R.id.tvDescricao);
            viewHolder.status     = (TextView) row.findViewById(R.id.tvStatus);
            viewHolder.endereco   = (TextView) row.findViewById(R.id.tvEndereco);
            viewHolder.btcancelar = (Button) row.findViewById(R.id.btcancelar);
            viewHolder.imgview = (ImageView) row.findViewById(R.id.ivImg);


            // new LoadImage().execute("http://52.24.37.49/img/min/img-perfil/0b4399eb482f818224e3d3e3703eec44.jpg");

            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }

        final Pedido mCartItem =  getItem(position);

        viewHolder.btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int id = (int) v.getTag();

                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

                alert.setTitle(R.string.dialog_titulo_confirmacao_excluir_pedido);
                alert.setMessage(R.string.dialog_mensagem_confirmacao_excluir_pedido);
                alert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                RestClient.get("kuhn.daniel.ws.entity.pedido/excluir/"+id, null, new JsonHttpResponseHandler() {

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("retorno", responseString);
                        Toast.makeText(getContext(),"Ops, algo deu errado!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Log.d("Retorno: ", response.toString());
                        try {

                            // https://stackoverflow.com/a/5554296

                            JSONObject jobj = new JSONObject(response.toString());

                            String ret = jobj.getString("ret");

                            if (ret.equals("true")) {
                                Log.d("sim,", "é true");
                                cardList.remove(mCartItem);
                                notifyDataSetChanged();
                                Toast.makeText(getContext(),"Pedido "+mCartItem.getProduto().getTitulo()+" excluido", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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


        Pedido pedido = getItem(position);

        viewHolder.btcancelar.setTag(pedido.getId());
        viewHolder.titulo.setText(pedido.getProduto().getTitulo());
        viewHolder.descricao.setText(pedido.getProduto().getDescricao());
        viewHolder.endereco.setText(pedido.getStrendereco());

        if (pedido.getStatus().equals("0")) {
            viewHolder.status.setText("Não visualizado");
            viewHolder.status.setTextColor(Color.parseColor("#ff0000"));
            viewHolder.btcancelar.setTag(pedido.getId());
        }

        if (pedido.getStatus().equals("3")) {
            viewHolder.status.setText("Em transporte");
            viewHolder.status.setTextColor(Color.parseColor("#b27300"));
            viewHolder.btcancelar.setVisibility(View.INVISIBLE);
        }
         Picasso.with(getContext()).load("http://"+ RestClient.URL+"/gas.jpg").into(viewHolder.imgview);

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
