package com.example.bogdan.dou_feed.api;

import com.example.bogdan.dou_feed.model.entity.FeedItemEntity;

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

            String url = feedItem.select("h2 a").first().attr("href");
            String imageUrl = feedItem.select("h2 a img").first().attr("src");
            String author = feedItem.select(".author").first().html();
            String date = feedItem.select(".date").first().text();
            int watchCount;
            try {
                watchCount = Integer.parseInt(feedItem.select(".pageviews").first().text());
            } catch (NullPointerException e) {
                watchCount = 0;
            }
            String title = feedItem.select("h2 a").first().text().replace("&nbsp;", " ");
            String description = feedItem.select(".b-typo").first().text();
            description = deleteCommentCount(description);
            int commentCount;
            try {
                commentCount = Integer.parseInt(feedItem.select(".b-typo a").first().html());
            } catch (NullPointerException e) {
                commentCount = 0;
            }
            String topic = feedItem.select(".more .topic").first().html();
            String commentUrl;
            try {
               commentUrl = feedItem.select(".b-typo a").first().attr("href");
            } catch (NullPointerException e) {
                commentUrl = null;
            }
            FeedItemEntity newItemEntity = new FeedItemEntity(url, imageUrl,  author, date,
                    watchCount, commentCount, title, description, topic, commentUrl);

            feed.add(newItemEntity);
        }

        return feed;
    }

    private static String deleteCommentCount(String text) {
        char[] charText = text.toCharArray();
        int end = charText.length - 1;

        for (int i = end; i >= 0; i--) {
            try {
                Integer.parseInt(String.valueOf(charText[i]));
                end--;
            } catch (NumberFormatException e) {
                break;
            }
        }

        return String.valueOf(charText).substring(0, end + 1);
    }
}
