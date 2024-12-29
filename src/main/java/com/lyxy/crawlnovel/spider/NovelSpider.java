package com.lyxy.crawlnovel.spider;

import com.lyxy.crawlnovel.model.Novel;
import com.lyxy.crawlnovel.repository.NovelRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class NovelSpider {
    public static String novelUrl = "https://www.znxdxs.com";
    @Resource
    NovelRepository novelRepository;

    public ArrayList<Novel> getChapters(String url) throws IOException {
        ArrayList<Novel> list = new ArrayList<>();

        // 使用Jsoup连接到小说网站
        Document doc = Jsoup.connect(url).get();

        // 选择所有class为'bookname'的div元素中的h1标签
//        Elements titles = doc.select("div.bookname > h1");

        // 解析章节内容
        Elements select = doc.select("#at .L a");// 章节内容所在的元素

        for (int i = 0; i < select.size(); i++) {
            Element e = select.get(i);
            Novel novel = new Novel(null,"大苍守夜人",e.text(),null,novelUrl+e.attr("href"));
            list.add(novel);
        }
        return list;
    }

    public void saveToDatabase(ArrayList<Novel> list) throws InterruptedException {
        // 这里添加保存到数据库的逻辑
        for (Novel novel : list) {
            System.out.println("novel = " + novel);
            try {
//                Thread.sleep(100);
                Elements select = getElements(novel);
                while (select.text().isEmpty()){
                    Thread.sleep(100);
                    select = getElements(novel);
                }
                novel.setContent(select.text());
                novelRepository.save(novel);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(100);
        }
    }

    private static Elements getElements(Novel novel) throws IOException {
        Document chapterDoc = Jsoup.connect(novel.getUrl()).get();
        Elements select = chapterDoc.select("#htmlContent>p");// 章节内容所在的元素
        return select;
    }
}