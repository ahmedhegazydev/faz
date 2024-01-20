package com.aliasadi.androidmvp.data.fanz.source.remote.services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FanzService {

    private static final String URL = "https://www.google.com/";

    private FanZApi mFanZApi;

    private static FanzService singleton;

    private FanzService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL).build();
        mFanZApi = mRetrofit.create(FanZApi.class);
    }

    public static FanzService getInstance() {
        if (singleton == null) {
            singleton = new FanzService();
        }
        return singleton;
    }

    public FanZApi getFanzApi() {
        return mFanZApi;
    }

}
