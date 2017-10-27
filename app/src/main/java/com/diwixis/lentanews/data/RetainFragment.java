package com.diwixis.lentanews.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.diwixis.lentaapi.objects.News;

import java.util.List;

/**
 * Created by Diwixis on 27.10.2017.
 */

public class RetainFragment extends Fragment {
    public static String TAG = "com.diwixis.lentanews.RetainFragment";
    private List<News> allNewsList;
    private List<News> dayNewsList;
    private List<News> topNewsList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    public void setAllNews(List<News> newsList){
        this.allNewsList = newsList;
    }

    public void setDayNews(List<News> dayNewsList) {
        this.dayNewsList = dayNewsList;
    }

    public void setTopNews(List<News> topNewsList) {
        this.topNewsList = topNewsList;
    }

    public List<News> getAllNews() {
        return allNewsList;
    }

    public List<News> getDayNews() {
        return dayNewsList;
    }

    public List<News> getTopNews() {
        return topNewsList;
    }
}
