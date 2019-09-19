package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

class ParserVk {
    private URL uri;
    private VkParserModel vkParserModel;
    private Element wallPost;
    private Elements post;
    private Pattern searchPattern;
    private SaveFileFromJson saveFileFromJson;

    ParserVk(String pathSaveFile) {
        vkParserModel = new VkParserModel();
        saveFileFromJson = new SaveFileFromJson(pathSaveFile);
    }

    void parsAndSave(int idPage, String search1, String search2) {
        String uri = "https://vk.com/id" + idPage;
        try {
            this.uri = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        wallPost = getDocument();
        post = getPost();
        int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
        searchPattern = Pattern.compile("(\\b" + search1 + ".*\\b)(\\b" + search2 + ".*\\b)", flags);
        parse();
    }

    private Document getDocument() {
        try {
            return Jsoup.parse(uri, 7000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Elements getPost() {
        return wallPost.getElementsByClass("post");
    }

    private void parse() {
        for (Element element : post) {
            if (searchPattern.matcher(element.text()).find()) {
                vkParserModel.setDate(element.select("span[class=rel_date]").first().text());
                vkParserModel.setIdOwner(element.select("a[class=author]").attr("data-from-id"));
                vkParserModel.setIdPost(element.select("a[class=author]").attr("data-post-id"));
                vkParserModel.setPostText(element.select("div[class=wall_post_text]").text());
                System.out.println(vkParserModel);
                saveFileFromJson.saveInFile(vkParserModel);
            }
        }
    }

    void stop() {
        saveFileFromJson.stopWrite();
    }
}
