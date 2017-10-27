package com.diwixis.lentanews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.diwixis.lentanews.news_category_screen.NewsCategoryFragment;

public class LentaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenta);

        NewsCategoryFragment fragment = new NewsCategoryFragment();
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.lenta_fragment_container, fragment, NewsCategoryFragment.TAG)
                    .commit();
        }
    }
}
