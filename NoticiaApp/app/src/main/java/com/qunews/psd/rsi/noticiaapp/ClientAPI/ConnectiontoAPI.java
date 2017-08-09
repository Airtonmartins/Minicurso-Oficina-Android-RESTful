package com.qunews.psd.rsi.noticiaapp.ClientAPI;

import com.google.gson.Gson;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by airton on 05/08/17.
 */

public class ConnectiontoAPI {
    public static final String API = "https://apinoticiatic.herokuapp.com/";

    public NoticiaAPI CreateRetrofit() {

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        NoticiaAPI noticiaAPI = retrofit.create(NoticiaAPI.class);

        return noticiaAPI;
    }
}
