package com.diwixis.lentanews;

import android.app.Application;

import com.diwixis.lentaapi.LentaApi;

/**
 * Created by Diwixis on 25.10.2017.
 */

public class LentaNewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LentaApi.create();
    }
}
