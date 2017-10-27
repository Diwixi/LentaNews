package com.diwixis.lentanews.news_screen;

import com.diwixis.lentaapi.objects.News;

import java.util.List;

/**
 * Created by Diwixis on 26.10.2017.
 */

public interface INewsView {
    void showAllNews(List<News> news);

    void showDayNews(List<News> news);

    void showTopNews(List<News> news);
}
