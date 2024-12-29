package com.lyxy.crawlnovel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String novelName;
    private String title;
    private String content;
    private String url;

    public Novel(String title, String novelUrl) {
        this.title = title;
        this.url = novelUrl;
    }
}