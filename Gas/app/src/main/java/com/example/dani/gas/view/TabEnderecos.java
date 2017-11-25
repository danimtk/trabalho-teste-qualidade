package com.example.dani.gas.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dani.gas.R;
import com.example.dani.gas.connection.RestClient;
import com.example.dani.gas.controlview.CardArrayAdapterEndereco;
import com.example.dani.gas.controlview.CardArrayAdapterProdutos;
import com.example.dani.gas.entity.Endereco;
import com.example.dani.gas.entity.Pedido;
import com.example.dani.gas.entity.Produto;
import com.example.dani.gas.entity.Vendedor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by dani on 26/03/16.
 */
public class TabEnderecos extends Fragment {

    SwipeRefreshLayout swipeLayout;
    public static CardArrayAdapterEndereco cardArrayAdapter;
    public static ListView listView;

    List<Endereco> enderecos = new ArrayList<>();


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // In fragment class callback
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_enderecos,container,false);

        listView = (ListView) v.findViewById(R.id.listviewenderecos);

        cardArrayAdapter = new CardArrayAdapterEndereco(getContext(), R.layout.list_enderecos);

        if (cardArrayAdapter.isEmpty())
        {
            RestClient.get("kuhn.daniel.ws.entity.endereco/listar/1", null, new JsonHttpResponseHandler() {

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d("retorno", responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Log.d("Retorno: ", response.toString());
                    try {

                        Gson gson = new Gson();
                        // https://stackoverflow.com/a/5554296

                        Type listType = new TypeToken<ArrayList<Endereco>>(){}.getType();

                        enderecos = new Gson().fromJson(response.toString(), listType);

                        for (Endereco endereco : enderecos) {
                            if(endereco.getComplemento() == "null") {
                                endereco.setComplemento("");
                            }
                            cardArrayAdapter.add(endereco);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("Retorno: ", response.toString());
                    try {

                        Gson gson = new Gson();
                        // https://stackoverflow.com/a/5554296

                        Type listType = new TypeToken<ArrayList<Endereco>>(){}.getType();

                        enderecos = new Gson().fromJson(response.toString(), listType);

                        for (Endereco endereco : enderecos) {

                            cardArrayAdapter.add(endereco);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            for (Endereco endereco : enderecos) {
                // Integer id, Vendedor vendedor, String titulo, String descricao, String preco, String timestamp
                Endereco card = new Endereco(endereco.getId(), endereco.getCidade(), endereco.getUsuario(), endereco.getCep(), endereco.getBairro(), endereco.getRua(), endereco.getNumero(), endereco.getComplemento(), endereco.getTimestamp());
                cardArrayAdapter.add(card);
            }
        }

        listView.setAdapter(cardArrayAdapter);

        return v;
    }
}

