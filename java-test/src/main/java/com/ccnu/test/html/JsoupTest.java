package com.ccnu.test.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {

    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("http://www.baidu.com").get();

        Elements imgTags = doc.getElementsByTag("img");
        for (Element imgTag : imgTags) {
            String src = imgTag.attr("src");
            System.out.println(src);
        }
    }
}
