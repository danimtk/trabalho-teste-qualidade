package com.example.dani.gas.controlview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dani.gas.EnderecosActivity;
import com.example.dani.gas.LoginActivity;
import com.example.dani.gas.NovoEnderecoActivity;
import com.example.dani.gas.R;
import com.example.dani.gas.WelcomeActivity;
import com.example.dani.gas.connection.RestClient;
import com.example.dani.gas.entity.Produto;
import com.example.dani.gas.util.Share;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardArrayAdapterProdutos extends ArrayAdapter<Produto> {
    private static final String TAG = "CardArrayAdapter";
    private List<Produto> cardList = new ArrayList<>();


    static NovoEnderecoActivity novoenderecoactivity;

    static class CardViewHolder {
        TextView line1, line2;
        Button  btpedir;
        ImageView imgview;
    }

    public CardArrayAdapterProdutos(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public void add(Produto object) {
        cardList.add(object);
        super.add(object);
    }

    public void add(int index, Produto noticia) {
        cardList.add(index, noticia);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public Produto getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_produtos, parent, false);

            viewHolder = new CardViewHolder();

            viewHolder.line1 = (TextView) row.findViewById(R.id.username);
            viewHolder.line2 = (TextView) row.findViewById(R.id.userquote);
            viewHolder.btpedir = (Button) row.findViewById(R.id.btfollow);
            viewHolder.imgview = (ImageView) row.findViewById(R.id.userimg);

            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }

        viewHolder.btpedir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = (int) v.getTag();

                Intent secondActivity = new Intent(getContext(), EnderecosActivity.class);

                String _id =  ""+id+"";
                secondActivity.putExtra("produto_id", _id);
                getContext().startActivity(secondActivity);

            }
        });

        final Produto mCartItem =  getItem(position);

        Produto produto = getItem(position);

        viewHolder.btpedir.setTag(produto.getId());
        viewHolder.line1.setText(produto.getTitulo());
        viewHolder.line2.setText("R$ "+produto.getPreco());
        Picasso.with(getContext()).load("http://"+ RestClient.URL+"/gas.jpg").into(viewHolder.imgview);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}