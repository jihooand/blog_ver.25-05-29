package com.blog.service;

import com.blog.domain.Article;
import com.blog.dto.AddArticleRequest;
import com.blog.dto.UpdateArticleRequest;
import com.blog.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
@Transactional
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

    //4. 블로그 글 하나 조회
    public Article findById(Long id){
        //return blogRepository.findById(id).orElse(null);
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found : "+id));
        //메서드에 잘못된 입력 값(인자)이 전달되었을 때 발생하는 예외입니다.

    }

    //5. 블로그 글 삭제
    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    //6. 블로그 글 수정
    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        // Article article=blogRepository.findById(id).orElse(null);
        Article article = blogRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found : "+id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }

}
