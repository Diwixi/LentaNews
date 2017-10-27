package com.diwixis.lentanews.news_category_screen;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.diwixis.lentaapi.objects.News;
import com.diwixis.lentanews.ui.ItemDivider;
import com.diwixis.lentanews.R;
import com.diwixis.lentanews.data.RetainFragment;
import com.diwixis.lentanews.adapters.GridAdapter;
import com.diwixis.lentanews.adapters.LinearAdapter;
import com.diwixis.lentanews.news_screen.INewsView;
import com.diwixis.lentanews.news_screen.NewsFragment;
import com.diwixis.lentanews.news_screen.NewsPresenter;

import java.util.List;

/**
 * Created by Diwixis on 25.10.2017.
 */

public class NewsCategoryFragment extends Fragment implements INewsView {
    public static String TAG = "com.diwixis.lentanews.news_category_screen.NewsCategoryFragment";
    private NewsPresenter presenter;
    private RecyclerView allNewsRecycler;
    private RecyclerView dayNewsRecycler;
    private RecyclerView topSevenNewsRecycler;
    private Button showAllNewsBtn;
    private Button lastDayNewsBtn;
    private Button topSevenNewsBtn;

    private RetainFragment data;

    Point size;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_news, container, false);
        size = new Point();
        getActivity().setTitle(getResources().getString(R.string.app_name));

        instanceDataFragment();

        getActivity().getWindowManager().getDefaultDisplay().getSize(size);

        initHeaderButtons(view);

        initAllNewsRecycler(view);
        initDayNewsRecycler(view);
        initTopNewsRecycler(view);

        presenter = new NewsPresenter(this, data);
        presenter.getAllNews();
        presenter.getDayNews();
        presenter.getTopNews();

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

    private void initHeaderButtons(View view) {
        showAllNewsBtn = view.findViewById(R.id.all_news_view_btn);
        showAllNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsFragment fragment = new NewsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(NewsFragment.NEWS_TYPE, NewsFragment.ALL_NEWS);
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.lenta_fragment_container, fragment, NewsFragment.TAG);
                fragmentTransaction.commit();
            }
        });
        lastDayNewsBtn = view.findViewById(R.id.last_day_view_btn);
        lastDayNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsFragment fragment = new NewsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(NewsFragment.NEWS_TYPE, NewsFragment.DAY_NEWS);
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.lenta_fragment_container, fragment, NewsFragment.TAG);
                fragmentTransaction.commit();
            }
        });
        topSevenNewsBtn = view.findViewById(R.id.top_seven_view_btn);
        topSevenNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsFragment fragment = new NewsFragment();
                Bundle bundle = new Bundle();
                bundle.putString(NewsFragment.NEWS_TYPE, NewsFragment.TOP_NEWS);
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.lenta_fragment_container, fragment, NewsFragment.TAG);
                fragmentTransaction.commit();
            }
        });
    }

    private void initTopNewsRecycler(View view) {
        topSevenNewsRecycler = view.findViewById(R.id.top_seven_news_list);
        topSevenNewsRecycler.addItemDecoration(new ItemDivider(getActivity()));
        topSevenNewsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        topSevenNewsRecycler.setNestedScrollingEnabled(false);
    }

    private void initDayNewsRecycler(View view) {
        dayNewsRecycler = view.findViewById(R.id.last_day_news_list);
        dayNewsRecycler.addItemDecoration(new ItemDivider(getActivity()));
        dayNewsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        dayNewsRecycler.setNestedScrollingEnabled(false);
    }

    private void initAllNewsRecycler(View view) {
        allNewsRecycler = view.findViewById(R.id.all_news_list);
        allNewsRecycler.addItemDecoration(new ItemDivider(getActivity()));
        allNewsRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        allNewsRecycler.setNestedScrollingEnabled(false);
    }

    public void showAllNews(List<News> news) {
        GridAdapter adapter = new GridAdapter();
        adapter.setData(news.subList(0, 4), size.x/2);

        allNewsRecycler.setAdapter(adapter);
    }

    public void showDayNews(List<News> news) {
        GridAdapter adapter = new GridAdapter();
        adapter.setData(news.subList(0, 4), size.x/2);

        dayNewsRecycler.setAdapter(adapter);
    }

    public void showTopNews(List<News> news) {
        LinearAdapter adapter = new LinearAdapter();
        adapter.setData(news.subList(0, 4), size.x/2);

        topSevenNewsRecycler.setAdapter(adapter);
    }
}
