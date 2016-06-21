package com.example.bogdan.dou_feed.api;

import android.test.suitebuilder.annotation.LargeTest;

import com.example.bogdan.dou_feed.FeedItemEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public interface DouApi {

    @GET("{rubric}/page/{page-number}")
    Observable<List<FeedItemEntity>> getFeedEntityByRubric(@Path("rubric")String rubric
            , @Path("page-number") int pageNumber);

    @GET("page/{page-number}")
    Observable<List<FeedItemEntity>> getFeedEntity(@Path("page-number")String pageNumber);
}
