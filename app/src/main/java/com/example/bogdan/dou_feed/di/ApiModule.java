package com.example.bogdan.dou_feed.di;

import com.example.bogdan.dou_feed.api.DouApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
@Module
public class ApiModule {
    private String mBaseUrl;

    public ApiModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    @Singleton
    @Provides
    DouApi provideApi(Retrofit retrofit) {
        return retrofit.create(DouApi.class);
    }
}
