package com.lyxy.crawlnovel;

import com.lyxy.crawlnovel.model.Novel;
import com.lyxy.crawlnovel.repository.NovelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CrawlNovelApplicationTests {
    @Resource
    NovelRepository novelRepository;

    @Test
    void contextLoads() {
        Novel novel = new Novel();
        novel.setTitle("aa");
        novel.setContent("bbbbbbbbbbb");
        novelRepository.save(novel);
    }

}

