package com.example.bogdan.dou_feed;

import android.app.Application;

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
