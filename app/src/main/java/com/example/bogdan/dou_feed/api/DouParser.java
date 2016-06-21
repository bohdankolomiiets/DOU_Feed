package com.example.bogdan.dou_feed.api;

import com.example.bogdan.dou_feed.FeedItemEntity;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 21.06.16
 */
public class DouParser {

    public static List<FeedItemEntity> parseFeed(Document document) {
        List<FeedItemEntity> feed = new ArrayList<>();

        Elements items = document.select(".b-lenta article");

        for (Element feedItem : items) {

            String url = feedItem.select("h2 a img").first().attr("src");
            String author = feedItem.select(".author").first().html();
            String date = feedItem.select(".date").first().text();
            int watchCount = Integer.parseInt(feedItem.select(".pageviews").first().text());
            String title = feedItem.select("h2 a").first().html().replace("&nbsp;", " ");
            String description = feedItem.select(".b-typo").first().text().replace("&nbsp;", " ");
            String topic = feedItem.select(".more .topic").first().html();
            String tags = "tags";

            FeedItemEntity newItemEntity = new FeedItemEntity(url, author, date, watchCount, title, description, topic, tags);

            feed.add(newItemEntity);
        }

        return feed;
    }
}
