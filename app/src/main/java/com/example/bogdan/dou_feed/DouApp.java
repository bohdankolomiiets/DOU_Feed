package com.example.bogdan.dou_feed;

import android.app.Application;

import com.example.bogdan.dou_feed.di.ApiModule;
import com.example.bogdan.dou_feed.di.AppComponent;
import com.example.bogdan.dou_feed.di.AppModule;
import com.example.bogdan.dou_feed.di.DaggerAppComponent;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class DouApp extends Application {
    private static AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        resolveDependencies();
    }

    private void resolveDependencies() {
        mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule(Constants.HTTP.BASE_URL))
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return mAppComponent;
    }
}
