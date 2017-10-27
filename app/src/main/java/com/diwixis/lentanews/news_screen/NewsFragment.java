package com.diwixis.lentanews.news_screen;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diwixis.lentaapi.objects.News;
import com.diwixis.lentanews.ui.ItemDivider;
import com.diwixis.lentanews.R;
import com.diwixis.lentanews.data.RetainFragment;
import com.diwixis.lentanews.adapters.LinearAdapter;

import java.util.List;

/**
 * Created by Diwixis on 26.10.2017.
 */

public class NewsFragment extends Fragment implements INewsView{
    public static String TAG = "com.diwixis.lentanews.news_screen.NewsFragment";

    public final static String NEWS_TYPE = "NEWS_TYPE_MSG";
    public final static String TOP_NEWS = "FRAGMENT_CATEGORY_TOP_NEWS";
    public final static String DAY_NEWS = "FRAGMENT_CATEGORY_DAY_NEWS";
    public final static String ALL_NEWS = "FRAGMENT_CATEGORY_ALL_NEWS";

    private NewsPresenter presenter;
    private RecyclerView newsRecycler;
    private RetainFragment data;
    Point size;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        instanceDataFragment();
        size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        initRecycler(view);

        presenter = new NewsPresenter(this, data);
        getItems();

        return view;
    }

    private void instanceDataFragment() {
        FragmentManager fm = getFragmentManager();
        data = (RetainFragment) fm.findFragmentByTag(RetainFragment.TAG);
        if (data == null) {
            data = new RetainFragment();
            fm.beginTransaction().add(data, RetainFragment.TAG).commit();
        }
    }

    private void initRecycler(View view) {
        newsRecycler = view.findViewById(R.id.news_list);
        newsRecycler.addItemDecoration(new ItemDivider(getActivity()));
        newsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void showAllNews(List<News> news) {
        showNews(news);
    }

    @Override
    public void showDayNews(List<News> news) {
        showNews(news);
    }

    @Override
    public void showTopNews(List<News> news) {
        showNews(news);
    }

    public void showNews(List<News> news){
        LinearAdapter adapter = new LinearAdapter();
        adapter.setData(news, size.x/2);

        newsRecycler.setAdapter(adapter);
    }

    public void getItems() {
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        switch (bundle.getString(NEWS_TYPE)){
            case TOP_NEWS:{
                getActivity().setTitle(getResources().getString(R.string.top_7_news));
                presenter.getTopNews();
                break;
            }
            case DAY_NEWS:{
                getActivity().setTitle(getResources().getString(R.string._24_news));
                presenter.getDayNews();
                break;
            }
            case ALL_NEWS:{
                getActivity().setTitle(getResources().getString(R.string.all_news));
                presenter.getAllNews();
                break;
            }
        }
    }
}
