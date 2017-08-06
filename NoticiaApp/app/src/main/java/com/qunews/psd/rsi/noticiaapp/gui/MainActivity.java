package com.qunews.psd.rsi.noticiaapp.gui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.qunews.psd.rsi.noticiaapp.ClientAPI.ConnectiontoAPI;
import com.qunews.psd.rsi.noticiaapp.ClientAPI.NoticiaAPI;
import com.qunews.psd.rsi.noticiaapp.R;
import com.qunews.psd.rsi.noticiaapp.dominio.Helloworld;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity{

    private static Context contexto;
    private TextView txtHello;
    ConnectiontoAPI connectiontoAPI = new ConnectiontoAPI();
    NoticiaAPI noticiaAPI = connectiontoAPI.CreateRetrofit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contexto = this;
        txtHello = (TextView) findViewById(R.id.txtHello);
        Call<Helloworld> call = noticiaAPI.getHelloworld("nohelloworld");
        call.enqueue(new Callback<Helloworld>() {
            @Override
            public void onResponse(Response<Helloworld> response, Retrofit retrofit) {
                Helloworld h = response.body();
                txtHello.setText(h.getMsg());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
