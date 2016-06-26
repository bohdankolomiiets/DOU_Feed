package com.example.bogdan.dou_feed.api;

import com.example.bogdan.dou_feed.model.entity.article.Article.Type;
import com.example.bogdan.dou_feed.model.entity.article.Article;
import com.example.bogdan.dou_feed.model.entity.CommentItem;
import com.example.bogdan.dou_feed.model.entity.article.ArticleHeader;
import com.example.bogdan.dou_feed.model.entity.feed.FeedItem;
import com.example.bogdan.dou_feed.model.entity.TableEntity;
import com.example.bogdan.dou_feed.model.entity.feed.Content;
import com.example.bogdan.dou_feed.model.entity.feed.Footer;
import com.example.bogdan.dou_feed.model.entity.feed.Header;

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

    public static List<FeedItem> parseFeed(Document document) {
        List<FeedItem> feed = new ArrayList<>();

        Elements feedItems = document.select(".b-lenta article");

        for (Element item : feedItems) {

            String url = item.select("h2 a").first().attr("href");

            String imageUrl = item.select("h2 a img").first().attr("src");
            String authorName = item.select(".author").first().html();
            String date = item.select(".date").first().text();
            Header header = new Header(imageUrl, authorName, date);

            String title = item.select("h2 a").first().text().replace("&nbsp;", " ");
            String description = item.select(".b-typo").first().text();
            description = deleteCommentCount(description);
            Content content = new Content(title, description);

            int watchCount;
            try {
                watchCount = Integer.parseInt(item.select(".pageviews").first().text());
            } catch (NullPointerException e) {
                watchCount = 0;
            }
            int commentCount;
            try {
                commentCount = Integer.parseInt(item.select(".b-typo a").first().html());
            } catch (NullPointerException e) {
                commentCount = 0;
            }
            String commentUrl;
            try {
               commentUrl = item.select(".b-typo a").first().attr("href");
            } catch (NullPointerException e) {
                commentUrl = null;
            }
            Footer footer = new Footer(watchCount, commentCount, commentUrl);

            FeedItem feedItemEntity = new FeedItem.Builder()
                    .url(url)
                    .header(header)
                    .content(content)
                    .footer(footer)
                    .build();

            feed.add(feedItemEntity);
        }

        return feed;
    }

    public static Article parseArticle(Document document) {
        String authorName = document.select(".b-post-info .author .name a").first().html();
        String date = document.select(".b-post-info .date").first().text();
        String title = document.select("article.b-typo h1").first().text().replace("&nbsp;", " ");
        ArticleHeader header = new ArticleHeader(authorName, date, title);

        Article articlePage = new Article(header);

        Elements elements = document.select("article.b-typo div").first().children();
        for (Element element : elements) {
            switch (element.tagName()) {
                case "p":
                    if (element.hasText()) {
                        for (Element children : element.children()) {
                            if (children.tagName().equals("src")) {
                                articlePage.addElement(Type.IMAGE, children.attr("src"));
                            } else if (children.tagName().equals("a") &&
                                    children.children().hasAttr("src")) {
                                articlePage.addElement(Type.IMAGE, children.children().attr("src"));
                            }
                        }
                        articlePage.addElement(Type.CONTENT, element.text());
                    }
                    break;
                case "h1":
                case "h2":
                case "h3":
                case "h4":
                case "h5":
                case "h6":
                    articlePage.addElement(Type.CONTENT_HEADING, element.text());
                    break;
                case "pre":
                    articlePage.addElement(Type.CONTENT_CODE, element.text());
                    break;
                case "blockquote":
                    articlePage.addElement(Type.BLOCKQUOTE, element.text());
                    break;
                case "table":
                    TableEntity table = new TableEntity();
                    for (Element tableElements : element.children()) {
                        if (tableElements.tagName().equals("thead")) {
                            table.addTableRow();
                            for (Element head : tableElements.children().first().children()) {
                                table.addRowCell(head.text());
                            }
                        } else if (tableElements.tagName().equals("tbody")) {
                            for (Element row : tableElements.children()) {
                                table.addTableRow();
                                for (Element column : row.children()) {
                                    table.addRowCell(column.text());
                                }
                            }
                        }
                    }
                    articlePage.addTable(table);
                    break;
                case "ul":
                    for (Element listChildren : element.children()) {
                        if (listChildren.tagName().equals("li")) {
                            articlePage.addElement(Type.LIST_ELEMENT, listChildren.text());
                        }
                    }
                    break;
            }
            if (element.children().hasAttr("src")) {
                articlePage.addElement(Type.IMAGE, element.children().attr("src"));
            }
        }

        return articlePage;
    }

    public static List<CommentItem> parseComments(Document document) {
        List<CommentItem> commentsList = new ArrayList<>();

        Element commentBlock = document.getElementById("commentsList");
        for (Element commentItem : commentBlock.children()) {
            String imageUrl = commentItem.select("img.g-avatar").first().attr("src");
            String authorName;
            String date;
            try {
                authorName = commentItem.select("a").first().text();
            } catch (NullPointerException e) {
                authorName = "Unknown";
            }
            try {
                date = commentItem.select(".comment-link").first().text();
            } catch (NullPointerException e) {
                date = "";
            }
            Header header = new Header(imageUrl, authorName, date);

            String content;
            try {
                content = commentItem.select(".text.b-typo").first().text();
            } catch (NullPointerException e) {
                content = "";
            }

            CommentItem comment = new CommentItem(header, content);
            commentsList.add(comment);
        }

        return commentsList;
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
