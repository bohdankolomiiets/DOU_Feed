package com.bogdan_kolomiets_1996.bogdan.dou_feed;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.module.ApiModule;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.component.AppComponent;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.module.AppModule;
import com.bogdan_kolomiets_1996.bogdan.dou_feed.di.component.DaggerAppComponent;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class DouApp extends Application {
  private AppComponent mAppComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    resolveDependencies();
    FacebookSdk.sdkInitialize(getApplicationContext());
    AppEventsLogger.activateApp(this);
  }

  private void resolveDependencies() {
    mAppComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .apiModule(new ApiModule(Constants.HTTP.BASE_URL))
        .build();
  }

  public AppComponent getAppComponent() {
    return mAppComponent;
  }

  public static DouApp get(Context context) {
    return (DouApp) context.getApplicationContext();
  }
}
