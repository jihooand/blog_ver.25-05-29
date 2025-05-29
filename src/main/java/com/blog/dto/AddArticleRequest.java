package com.blog.dto;

import com.blog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;
    
    //Builder 패턴 활용
    public Article toEntity(){
        //return new Article(id,title,content);
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
