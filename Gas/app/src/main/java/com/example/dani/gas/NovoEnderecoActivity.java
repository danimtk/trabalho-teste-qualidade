package com.example.dani.gas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dani.gas.connection.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.net.URI;

import cz.msebera.android.httpclient.Header;

public class NovoEnderecoActivity extends AppCompatActivity {

    private Bitmap bmp;
    URI imageUri;

    public String frase, livro, autor;

    private Adapter myAdapter;

    // CardArrayAdapter cardArrayAdapter;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorb));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_endereco);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Adicionar Novo Endereço");

        changeStatusBarColor();

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();

        if (Intent.ACTION_SEND.equals(intent.getAction()) && intent.getType() != null) {
            if ("text/plain".equals(intent.getType())) {
               String resposta = handleSendText(intent); // Handle text being sent

                EditText etfrase = (EditText) findViewById(R.id.etBairro);

                etfrase.setText(resposta);
            }
        }


        if(intent != null && bundle != null && !intent.ACTION_SEND.equals(intent.getAction())) {

            EditText etfrase = (EditText) findViewById(R.id.etBairro);

            etfrase.setText("Frase");
        }
    }


    String handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            Log.d("Text: ", sharedText);
            return sharedText;

            // Update UI to reflect text being shared
        }
        return " ";
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_quite, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_salvar) {

            boolean status = true;

            EditText etcep     = (EditText) findViewById(R.id.etCep);
            EditText etcidade  = (EditText) findViewById(R.id.etCidade);
            EditText etrua     = (EditText) findViewById(R.id.etRua);
            EditText etnumero  = (EditText) findViewById(R.id.etNumero);
            EditText etbairro  = (EditText) findViewById(R.id.etBairro);
            EditText etcomplemento = (EditText) findViewById(R.id.etComplemento);

            boolean cancel = false;

            if(etcep.getText().toString().isEmpty()) {

                etcep.setError("Preencha o Cep");
                cancel = true;
            }

            if (etcidade.getText().toString().isEmpty()) {
                etcidade.setError("Preencha a Cidade");
                cancel = true;
            }

            if (etrua.getText().toString().isEmpty()) {

                etrua.setError("Preencha a rua");
                cancel = true;
            }

            if (etnumero.getText().toString().isEmpty()) {
                etnumero.setError("Preencha o número");
                cancel = true;
            }

            if (etbairro.getText().toString().isEmpty()) {

                etbairro.setError("Preencha o bairro");
                cancel = true;
            }

            if (cancel == false) {

                String cidade, rua, bairro, complemento;
                Integer cep, numero;


                cidade = etcidade.getText().toString();
                rua = etrua.getText().toString();

                bairro = etbairro.getText().toString();
                if (!etcomplemento.getText().toString().isEmpty()) {
                    complemento = etcomplemento.getText().toString();
                } else {
                    complemento = null;
                }

            /*
            if (cep ==null) {
                etcep.setError(getString(R.string.error_empty_cep));
                status = false;
            }
            */

                if (cidade.isEmpty()) {
                    etcidade.setError(getString(R.string.error_empty_cidade));
                    status = false;
                }

                if (rua.isEmpty()) {
                    etrua.setError(getString(R.string.error_empty_rua));
                    status = false;
                }

            /*
            if (numero == null) {
                etnumero.setError(getString(R.string.error_empty_numero));
                status = false;
            } */

                if (bairro.isEmpty()) {
                    etbairro.setError(getString(R.string.error_empty_bairro));
                    status = false;
                }

                if (status == true) {
                    Log.d("salvar", "estaria pronto pra adicionar");
                }
                String estado = "RS";
                cep = 98200000;
                numero = 1544;

                //estado/cep/cidade/rua/numero/bairro/complemento

                Log.d("aviso", "kuhn.daniel.ws.entity.endereco/adicionar/1/" + cep + "/" + cidade + "/" + rua + "/" + numero + "/" + bairro + "/" + complemento);

                RestClient.get("kuhn.daniel.ws.entity.endereco/adicionar/1/" + cep + "/" + cidade + "/" + rua + "/" + numero + "/" + bairro + "/" + complemento, null, new JsonHttpResponseHandler() {

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("retorno", responseString);
                        Toast.makeText(getApplicationContext(), "Ops, algo deu errado!", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Log.d("Retorno: ", response.toString());
                        try {

                            // https://stackoverflow.com/a/5554296

                            JSONObject jobj = new JSONObject(response.toString());

                            String ret = jobj.getString("ret");

                            if (ret.equals("true")) {

                                Intent secondActivity = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(secondActivity);

                                Toast.makeText(getApplicationContext(), "Novo Endereço Adicionado", Toast.LENGTH_LONG).show();
                                onBackPressed();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            /*
            Connection con = new Connection();

            if (con.haveNetworkConnection(getApplicationContext())) {
                // sincronizar com o servidor
                new SynAsync().execute(this);

            } else {
                Toast.makeText(getApplicationContext(),"Você não está conectado. Vamos sincronizar futuramente.", Toast.LENGTH_LONG).show();
            }
            */

            /*

            cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);

            Card card = new Card(22, c1.getCitacao(), c1.getAutor(), c1.getLivro());

            cardArrayAdapter.add(0, card);

            cardArrayAdapter.notifyDataSetChanged();

            */

          //  onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}