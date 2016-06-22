package com.example.bogdan.dou_feed.api;

import com.example.bogdan.dou_feed.model.entity.ArticleEntity;
import com.example.bogdan.dou_feed.model.entity.FeedItemEntity;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class DouConverter implements Converter<ResponseBody, Object> {
    private Type mType;

    public DouConverter(Type type) {
        mType = type;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {
        if (mType.toString().equals(new TypeToken<List<FeedItemEntity>>() {
        }.getType().toString())) {
            return DouParser.parseFeed(Jsoup.parse(value.string()));
        } else if (mType.toString().equals(new TypeToken<ArticleEntity>() {
        }.getType().toString())) {
            return DouParser.parseFeed( Jsoup.parse(value.string()));
        }
        throw new IllegalArgumentException("This type does not supported " + mType);
    }
}
