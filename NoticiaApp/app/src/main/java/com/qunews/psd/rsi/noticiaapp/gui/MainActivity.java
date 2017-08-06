package com.qunews.psd.rsi.noticiaapp.gui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qunews.psd.rsi.noticiaapp.ClientAPI.ConnectiontoAPI;
import com.qunews.psd.rsi.noticiaapp.ClientAPI.NoticiaAPI;
import com.qunews.psd.rsi.noticiaapp.R;
import com.qunews.psd.rsi.noticiaapp.dominio.Helloworld;
import com.qunews.psd.rsi.noticiaapp.dominio.Noticia;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contexto = this;

        listaNoticias = (ListView) findViewById(R.id.lista);
        noticias = new ArrayList<>();
        ArrayAdapter<Noticia> adapter = new ArrayAdapter<Noticia>(this,
                android.R.layout.simple_list_item_1,noticias);
        listaNoticias.setAdapter(adapter);
        retornaNoticia();


    }

    public void retornaNoticia(){

        Call<List<Noticia>> call = noticiaAPI.getNoticia("noticia");
        call.enqueue(new Callback<List<Noticia>>() {
            @Override
            public void onResponse(Response<List<Noticia>> response, Retrofit retrofit) {
                List<Noticia> n = response.body();
                for(Noticia noti: n){
                    noticias.add(noti);
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
