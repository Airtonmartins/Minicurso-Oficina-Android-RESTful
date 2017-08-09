package com.qunews.psd.rsi.noticiaapp.ClientAPI;

import com.qunews.psd.rsi.noticiaapp.dominio.Helloworld;
import com.qunews.psd.rsi.noticiaapp.dominio.Noticia;
import com.qunews.psd.rsi.noticiaapp.dominio.Usuario;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by airton on 05/08/17.
 */

public interface NoticiaAPI {

    @GET("{ctrlHello}")
    Call<Helloworld> getHelloworld(@Path("ctrlHello") String ctrl);

    @DELETE("user/")
    Call<Usuario> deleteUsuario(@Header("Authorization") String strtoken);

    @POST("api-register/")
    Call<Usuario> saveUsuario(@Body Usuario usuario);


    @PUT("user/")
    Call<Usuario> upUsuario(@Header("Authorization") String strtoken, @Body Usuario usuario);

    @FormUrlEncoded
    @POST("login/")
    Call<Usuario> login(@Field("username") String username,
                        @Field("password") String password);

    @GET("noticia/")
    Call<List<Noticia>> getNoticia();



}
