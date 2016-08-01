package com.bogdan_kolomiets_1996.bogdan.dou_feed.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class DouConverterFactory extends Converter.Factory {

    private DouConverterFactory() {
    }

    public static DouConverterFactory create() {
        return new DouConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new DouConverter(type);
    }
}
