package com.qunews.psd.rsi.noticiaapp.gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qunews.psd.rsi.noticiaapp.ClientAPI.ConnectiontoAPI;
import com.qunews.psd.rsi.noticiaapp.ClientAPI.NoticiaAPI;
import com.qunews.psd.rsi.noticiaapp.R;
import com.qunews.psd.rsi.noticiaapp.dominio.Helloworld;
import com.qunews.psd.rsi.noticiaapp.dominio.Noticia;
import com.qunews.psd.rsi.noticiaapp.dominio.Sessao;
import com.qunews.psd.rsi.noticiaapp.dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity{

    private static Context contexto;
    private ListView listaNoticias;
    private List<Noticia> noticias;
    ConnectiontoAPI connectiontoAPI = new ConnectiontoAPI();
    NoticiaAPI noticiaAPI = connectiontoAPI.CreateRetrofit();
    private Usuario usuario = Sessao.getInstancia().getUsuarioLogado();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contexto = this;
        Toast.makeText(MainActivity.this, usuario.getUsername(), Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, usuario.getToken(), Toast.LENGTH_SHORT).show();


        listaNoticias = (ListView) findViewById(R.id.lista);
        noticias = new ArrayList<>();
        retornaNoticia();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_update:
                Toast.makeText(MainActivity.this, "Update", Toast.LENGTH_SHORT).show();
                Intent intentGoMain = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intentGoMain);
                return true;
            case R.id.item_sair:
                Toast.makeText(MainActivity.this, "Saindo", Toast.LENGTH_SHORT).show();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void retornaNoticia(){

        Call<List<Noticia>> call = noticiaAPI.getNoticia();
        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Response<List<Noticia>> response, Retrofit retrofit) {
                List<Noticia> n = response.body();
                for(Noticia noti: n){
                    noticias.add(noti);
                }
                ArrayAdapter<Noticia> adapter = new ArrayAdapter<Noticia>(contexto,
                        android.R.layout.simple_list_item_1,noticias);
                listaNoticias.setAdapter(adapter);

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
