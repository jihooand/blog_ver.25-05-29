package com.blog.dto;

import com.blog.domain.Article;
import lombok.Getter;

@Getter
public class ArticleResponse {
    private final String title;
    private final String content;


    //생성자
    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();


    }

}
