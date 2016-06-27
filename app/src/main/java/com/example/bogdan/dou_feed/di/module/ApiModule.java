package com.example.bogdan.dou_feed.di.module;

import android.content.Context;

import com.example.bogdan.dou_feed.DouApp;
import com.example.bogdan.dou_feed.api.DouApi;
import com.example.bogdan.dou_feed.api.DouConverterFactory;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    DouConverterFactory provideDouConverterFactory() {
        return DouConverterFactory.create();
    }

    @Singleton
    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Singleton
    @Provides
    OkHttpClient provideHttpClient(Context app) {
        return new OkHttpClient
                .Builder()
                .cache(new Cache(app.getCacheDir(), 10 * 1024 * 1024)) // 10 MB
                .addInterceptor(new Interceptor() {
                    @Override public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        if (((DouApp)app).isNetworkAvailable()) {
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build();
                        } else {
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();
                        }
                        return chain.proceed(request);
                    }
                })
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(DouConverterFactory douConverterFactory,
                             RxJavaCallAdapterFactory rxJavaCallAdapterFactory,
                             OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(douConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    DouApi provideApi(Retrofit retrofit) {
        return retrofit.create(DouApi.class);
    }
}
