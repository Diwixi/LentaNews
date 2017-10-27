package com.diwixis.lentaapi;

import com.diwixis.lentaapi.objects.RssFeed;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Diwixis on 25.10.2017.
 */

public interface LentaApiService {
    @GET(LentaUrls.TOP7)
    Call<RssFeed> getTopSevenNews();

    @GET(LentaUrls.LAST24)
    Call<RssFeed> getLastDayNews();

    @GET(LentaUrls.ALL)
    Call<RssFeed> getAllNews();
}
