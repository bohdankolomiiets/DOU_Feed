package com.bogdan_kolomiets_1996.bogdan.dou_feed;

import com.bogdan_kolomiets_1996.bogdan.dou_feed.model.entity.feed.Rubric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class Constants {

  public static final class HTTP {
    public static final String BASE_URL = "https://dou.ua/lenta/";
    public static final String NET_ERROR_MSG = "Проверьте, пожалуйста, интернет соединение";
  }

  public static final class RUBRIC {
    public static final List<Rubric> RUBRICS = new ArrayList<>();
  }
}
