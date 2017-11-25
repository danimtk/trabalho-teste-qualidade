package com.example.dani.gas.connection;

import android.os.Looper;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileNotFoundException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by dani on 24/03/16.
 */
public class RestClient {


    // String BASE_URL = "http://192.168.0.101:8080/ws-gas/webresources/";

   // String BASE_URL = "http://192.168.150.1:8080/ws-gas/webresources/";

    public static String URL = "192.168.150.1";


    private static final String BASE_URL = "http://192.168.150.1:8080/wsgas/webresources/";



    private static AsyncHttpClient client = new AsyncHttpClient();

    // A SyncHttpClient is an AsyncHttpClient
    public static AsyncHttpClient syncHttpClient= new SyncHttpClient();



    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().post(getAbsoluteUrl(url), params, responseHandler);

    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


    /**
     * @return an async client when calling from the main thread, otherwise a sync client.
     */
    private static AsyncHttpClient getClient()
    {
      //  syncHttpClient.addHeader("content-type", "application/json");
     //   syncHttpClient.addHeader("Accept", "application/json");
        syncHttpClient.addHeader("content-Type", "application/json");
       // client.addHeader("Accept", "application/json");

        client.addHeader("content-Type", "application/json");
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null)
            return syncHttpClient;
        return client;
    }

    public static void postImage(String ImageLink) {
        RequestParams params = new RequestParams();

        try {
            if(ImageLink.contains("jpeg")) {
                params.put("uploadedfile", new File(ImageLink), "image/jpeg");
            }else{
                params.put("uploadedfile", new File(ImageLink), "image/png");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        RestClient client = new RestClient();


        final File filedel = new File(ImageLink);

        client.post("upload.php", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
              //  filedel.delete();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("teste:", responseString.toString());
                //filedel.delete();
            }
        });
    }
}
