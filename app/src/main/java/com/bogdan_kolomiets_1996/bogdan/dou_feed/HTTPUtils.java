package com.bogdan_kolomiets_1996.bogdan.dou_feed;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class HTTPUtils {
  private static final int lentaLength = "/lenta/".length();

  private HTTPUtils() {

  }

  public static String getRubric(String url) {
    int baseLength = Constants.HTTP.BASE_URL.length();
    if (!isSecure(url)) {
      baseLength -= 1;
    }

    char[] characters = url.toCharArray();

    return getUrl(characters, baseLength);
  }

  public static String getPageUrl(String url) {
    int baseLength = Constants.HTTP.BASE_URL.length() + getRubric(url).length() + 1;
    if (!isSecure(url)) {
      baseLength -= 1;
    }
    char[] character = url.toCharArray();

    return getUrl(character, baseLength);
  }

  public static String getRubricFromPath(String path) {
    char[] character = path.toCharArray();

    return getUrl(character, lentaLength);
  }

  public static String getPageUrlFromPath(String path) {
    int baseLength = lentaLength + getRubricFromPath(path).length() + 1;
    char[] character = path.toCharArray();

    return getUrl(character, baseLength);
  }

  public static boolean isNetworkException(Throwable e) {
    return (e instanceof HttpException) || (e instanceof SocketTimeoutException);
  }

  public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  private static String getUrl(char[] characters, int baseLength) {
    StringBuilder sb = new StringBuilder();

    for (int i = baseLength; i < characters.length; i++) {
      if (characters[i] == '/') {
        break;
      }
      sb.append(characters[i]);
    }

    return sb.toString();
  }

  private static boolean isSecure(String url) {
    url = url.substring(0, 5);

    return url.equals("https");
  }
}
