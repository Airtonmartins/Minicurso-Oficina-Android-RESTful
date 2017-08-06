package com.qunews.psd.rsi.noticiaapp.ClientAPI;

import com.qunews.psd.rsi.noticiaapp.dominio.Helloworld;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by airton on 05/08/17.
 */

public interface NoticiaAPI {

    @GET("{ctrlHello}")
    Call<Helloworld> getHelloworld(@Path("ctrlHello") String ctrl);
}
