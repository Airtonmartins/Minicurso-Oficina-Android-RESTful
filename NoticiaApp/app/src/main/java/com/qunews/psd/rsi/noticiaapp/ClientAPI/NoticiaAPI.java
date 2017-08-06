package com.qunews.psd.rsi.noticiaapp.ClientAPI;

import java.util.List;

/**
 * Created by airton on 05/08/17.
 */

public interface NoticiaAPI {

    @GET("{ctrlCar}")
    Call<List<Tipo>> getTipo(@Path("ctrlCar") String ctrl);
}
