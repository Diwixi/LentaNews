package com.diwixis.lentaapi;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Diwixis on 25.10.2017.
 */

public class LentaApi {
    private static volatile LentaApiService mService;

    public static LentaApiService getLentaService(){
        LentaApiService service = mService;
        if (service == null) {
            synchronized (LentaApi.class) {
                service = mService;
                if (service == null) {
                    service = mService = buildRetrofit().create(LentaApiService.class);
                }
            }
        }
        return service;
    }

    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(LentaUrls.BASE)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
    }

    public static void create() {
        mService = buildRetrofit().create(LentaApiService.class);
    }
}
