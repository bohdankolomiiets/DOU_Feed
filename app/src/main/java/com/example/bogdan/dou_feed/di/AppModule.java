package com.example.bogdan.dou_feed.di;

import android.content.Context;

import com.example.bogdan.dou_feed.DouApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
@Module
public class AppModule {
    private DouApp mApplication;

    public AppModule(DouApp application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    Context provideApplication() {
        return mApplication;
    }


}
