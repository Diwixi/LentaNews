package com.diwixis.lentanews.news_screen;

import android.support.annotation.NonNull;

import com.diwixis.lentaapi.LentaApi;
import com.diwixis.lentaapi.objects.RssFeed;
import com.diwixis.lentanews.data.RetainFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Diwixis on 26.10.2017.
 */

public class NewsPresenter {
    private INewsView parent;
    private RetainFragment data;

    public NewsPresenter(INewsView parent, RetainFragment data) {
        this.parent = parent;
        this.data = data;
    }

    public void getAllNews(){
        if (data.getAllNews() == null) {
            getAllNewsFromApi();
        } else {
            getAllNewsFromData();
        }
    }

    private void getAllNewsFromData(){
        parent.showAllNews(data.getAllNews());
    }

    private void getAllNewsFromApi() {
        Call<RssFeed> newsList = LentaApi.getLentaService().getAllNews();
        newsList.enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(@NonNull Call<RssFeed> call, @NonNull Response<RssFeed> response) {
                if (response.isSuccessful()) {
                    RssFeed rss = response.body();
                    assert rss != null;
                    data.setAllNews(rss.getNews());
                    parent.showAllNews(rss.getNews());
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RssFeed> call, @NonNull Throwable t) {
                t.toString();
            }
        });
    }

    public void getDayNews() {
        if (data.getDayNews() == null) {
            getDayNewsFromApi();
        } else {
            getDayNewsFromData();
        }
    }

    private void getDayNewsFromApi() {
        Call<RssFeed> newsList = LentaApi.getLentaService().getLastDayNews();
        newsList.enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(@NonNull Call<RssFeed> call, @NonNull Response<RssFeed> response) {
                if (response.isSuccessful()) {
                    RssFeed rss = response.body();
                    assert rss != null;
                    data.setDayNews(rss.getNews());
                    parent.showDayNews(rss.getNews());
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RssFeed> call, @NonNull Throwable t) {
                t.toString();
            }
        });
    }

    private void getDayNewsFromData() {
        parent.showDayNews(data.getDayNews());
    }

    public void getTopNews() {
        if (data.getTopNews() == null) {
            getTopNewsFromApi();
        } else {
            getTopNewsFromData();
        }
    }

    private void getTopNewsFromApi() {
        Call<RssFeed> newsList = LentaApi.getLentaService().getTopSevenNews();
        newsList.enqueue(new Callback<RssFeed>() {
            @Override
            public void onResponse(@NonNull Call<RssFeed> call, @NonNull Response<RssFeed> response) {
                if (response.isSuccessful()) {
                    RssFeed rss = response.body();
                    assert rss != null;
                    data.setTopNews(rss.getNews());
                    parent.showTopNews(rss.getNews());
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RssFeed> call, @NonNull Throwable t) {
                t.toString();
            }
        });
    }

    private void getTopNewsFromData() {
        parent.showTopNews(data.getTopNews());
    }
}
