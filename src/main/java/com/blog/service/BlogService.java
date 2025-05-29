package com.blog.service;

import com.blog.domain.Article;
import com.blog.dto.AddArticleRequest;
import com.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class BlogService {

    @Autowired
    private final BlogRepository blogRepository;

    //1. 생성자 주입
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    //2. 블로그 글 추가 메서드
    //                      dto
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    //3. 블로그 전체 목록
    public List<Article> findAll(){
        return blogRepository.findAll();
    }
}
