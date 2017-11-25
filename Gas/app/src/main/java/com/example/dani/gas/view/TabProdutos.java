package com.example.dani.gas.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.dani.gas.controlview.CardArrayAdapterProdutos;
import com.example.dani.gas.entity.Produto;
import com.example.dani.gas.entity.Vendedor;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dani on 26/03/16.
 */
public class TabProdutos extends Fragment {

    SwipeRefreshLayout swipeLayout;
    public CardArrayAdapterProdutos cardArrayAdapter;
    private ListView listView;

    List<Produto> produtos = new ArrayList<>();

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
        View v =inflater.inflate(R.layout.tab_produtos,container,false);

        cardArrayAdapter = new CardArrayAdapterProdutos(getContext(), R.layout.list_produtos);

        listView = (ListView) v.findViewById(R.id.listviewprodutos);

        if(cardArrayAdapter.isEmpty())
        {
            RestClient.get("kuhn.daniel.ws.entity.produto/listar", null, new JsonHttpResponseHandler() {

                @Override
                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                    Log.d("retorno", responseString);
                }

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                    Log.d("Retorno: ", response.toString());
                    try {

                        Gson gson = new Gson();
                        // https://stackoverflow.com/a/5554296

                        Type listType = new TypeToken<ArrayList<Produto>>(){}.getType();

                        produtos = new Gson().fromJson(response.toString(), listType);

                        for (Produto produto: produtos) {
                            // Integer id, Vendedor vendedor, String titulo, String descricao, String preco, String timestamp
                            Produto card = new Produto(produto.getId(), new Vendedor(1), produto.getTitulo(), produto.getDescricao(), produto.getPreco(), produto.getTimestamp());
                            cardArrayAdapter.add(card);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        listView.setAdapter(cardArrayAdapter);


        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_tab3);

        swipeLayout.setColorSchemeResources(R.color.amarelo,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_orange_dark);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(true);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                        /*
                        Card card = new Card(22, "Citação teste", "Autor teste", "Livro teste");
                        cardArrayAdapter.add(0, card);

                        listView.setAdapter(cardArrayAdapter);
                        */
                    }
                }, 1000);

            }
        });

        return v;
    }
}

