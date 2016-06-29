package com.example.bogdan.dou_feed;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.bogdan.dou_feed.di.module.ApiModule;
import com.example.bogdan.dou_feed.di.component.AppComponent;
import com.example.bogdan.dou_feed.di.module.AppModule;
import com.example.bogdan.dou_feed.di.component.DaggerAppComponent;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class DouApp extends Application {
    private static AppComponent mAppComponent;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        resolveDependencies();
        mContext = getApplicationContext();
    }

    private void resolveDependencies() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(Constants.HTTP.BASE_URL))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
