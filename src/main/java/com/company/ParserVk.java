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
    private String patternRegexp;
    private int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;

    ParserVk(String uri, String search1, String search2, String pathSaveFile) {
        try {
            this.uri = new URL(uri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        vkParserModel = new VkParserModel();
        patternRegexp = "(\\b" + search1 + ".*\\b)(\\b" + search2 + ".*\\b)";
        saveFileFromJson = new SaveFileFromJson(pathSaveFile);
    }

    void parsAndSave(){
        wallPost = getDocument();
        post = getPost();
        searchPattern = Pattern.compile(patternRegexp, flags);
        parse();
        saveFileFromJson.saveInFile(vkParserModel);
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
//                saveFileFromJson.saveInFile();
            }
        }
    }

    void stop() {
        saveFileFromJson.stopWrite();
    }
}
