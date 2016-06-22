package com.example.bogdan.dou_feed.di.module;

import android.content.Context;

import com.example.bogdan.dou_feed.DouApp;
import com.example.bogdan.dou_feed.api.DouApi;
import com.example.bogdan.dou_feed.model.DouModel;
import com.example.bogdan.dou_feed.model.DouModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    @Singleton
    @Provides
    Observable.Transformer provideSchedulerTransformer() {
        return o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    @Singleton
    @Provides
    DouModel provideDouModel(Observable.Transformer schedulerTransformer, DouApi api) {
        return new DouModelImpl(schedulerTransformer, api);
    }
}
