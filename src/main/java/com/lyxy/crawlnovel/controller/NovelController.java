package com.lyxy.crawlnovel.controller;

import com.lyxy.crawlnovel.model.Novel;
import com.lyxy.crawlnovel.repository.NovelRepository;
import com.lyxy.crawlnovel.spider.NovelSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NovelController {
    @Autowired
    NovelSpider novelSpider;
    @Autowired
    private NovelRepository novelRepository;

    @GetMapping("/start")
    public void start() {
//        String novelUrl = "https://www.znxdxs.com/xiaoshuo/22/22933/"; // 替换为小说网站的实际URL
        String novelUrl = "https://www.znxdxs.com/xiaoshuo/33/33253/"; // 替换为小说网站的实际URL
        try {
            ArrayList<Novel> list = novelSpider.getChapters(novelUrl);
            // 存储到数据库的逻辑
            novelSpider.saveToDatabase(list); // 假设已经有了标题和内容
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/novels")
    public String listNovels(Model model) {
        List<Novel> novels = novelRepository.findAll();
        model.addAttribute("novels", novels);
        return "novels";
    }

    @GetMapping("/novels/{id}")
    public String novelDetail(@PathVariable Long id, Model model) {
        Novel novel = novelRepository.findById(id).orElse(null);
        model.addAttribute("novel", novel);
        return "novelDetail";
    }
}