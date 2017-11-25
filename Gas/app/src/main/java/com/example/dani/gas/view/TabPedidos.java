package com.example.dani.gas.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dani.gas.R;
import com.example.dani.gas.connection.RestClient;
import com.example.dani.gas.controlview.CardArrayAdapterPedido;
import com.example.dani.gas.entity.Pedido;
import com.example.dani.gas.entity.Produto;
import com.example.dani.gas.entity.Vendedor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by dani on 26/03/16.
 */
public class TabPedidos extends Fragment {


    SwipeRefreshLayout swipeLayout;
     public static CardArrayAdapterPedido cardArrayAdapter;
    private ListView listView;

    static List<Pedido> pedidos = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_pedidos, container, false);

        listarPedidos();

        listView = (ListView) v.findViewById(R.id.listviewpedidos);

        cardArrayAdapter = new CardArrayAdapterPedido(getContext(), R.layout.list_pedidos);


        listView.setAdapter(cardArrayAdapter);

        swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_tab4);

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
                    }
                }, 2000);
            }
        });

        return v;
    }

    public static void listarPedidos() {

        pedidos.clear();


        RestClient.get("kuhn.daniel.ws.entity.pedido/listar/1", null, new JsonHttpResponseHandler() {

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

                    Type listType = new TypeToken<ArrayList<Pedido>>(){}.getType();

                    pedidos = new Gson().fromJson(response.toString(), listType);

                    for (Pedido pedido : TabPedidos.pedidos) {

                        cardArrayAdapter.add(pedido);
                    }

                    Log.d("pedidos: ", pedidos.toString());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}