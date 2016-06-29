package com.example.bogdan.dou_feed;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.06.16
 */
public class HTTPUtils {

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
