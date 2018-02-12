package br.com.ejb.projetomobile.api;

import br.com.ejb.projetomobile.model.Chart;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPriceAPI {

    //authentication
    @GET("charts/market-price?format=json")
    Call<Chart> getCharts(@Query("timespan") String timespan);
}
