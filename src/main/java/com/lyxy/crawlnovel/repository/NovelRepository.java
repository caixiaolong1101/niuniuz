package com.lyxy.crawlnovel.repository;

import com.lyxy.crawlnovel.model.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelRepository extends JpaRepository<Novel, Long> {
}