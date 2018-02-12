package br.com.ejb.projetomobile.api;

import br.com.ejb.projetomobile.util.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PriceAPI {
    private static PriceAPI sInstance;
    private IPriceAPI iPriceAPI;

    private PriceAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.iPriceAPI = retrofit.create(IPriceAPI.class);

    }

    public static PriceAPI getInstance() {
        if (sInstance == null) {
            synchronized (PriceAPI.class) {
                if (sInstance == null) sInstance = new PriceAPI();
            }
        }
        return sInstance;
    }

    public IPriceAPI getAPI() {
        return iPriceAPI;
    }
}
