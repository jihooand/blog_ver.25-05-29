package com.blog.controller;

import com.blog.domain.Article;
import com.blog.dto.AddArticleRequest;
import com.blog.dto.ArticleResponse;
import com.blog.dto.UpdateArticleRequest;
import com.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
//@Controller
public class BlogApiController {

//    @GetMapping("/")
//    public String test(){
//        return "test";
//    }

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle=blogService.save(request);

        //요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){

        List<ArticleResponse> articles=blogService.findAll()
                .stream()
                .map(ArticleResponse::new) //article을 ArticleResponse로 매핑
                .toList(); //ArticleResponse 객체들을 다시 List<ArticleResponse>로

        /*
        List<Article> article=blogService.findAll();
        List<ArticleResponse> aa=null;

        for(int i=0;i<article.size();i++){
            ArticleResponse dto=article.get(i);
            Article entity=dto.toEntity();
            aa.add(entity)
        }

         */

        return ResponseEntity.ok()
                .body(articles);
    }

    //4. 블로그 글 하나 조회
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findById(@PathVariable Long id){
        Article article=blogService.findById(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    //블로그 글 하나 삭제
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }

    //블로그 글 하나 수정
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        Article updateArticle=blogService.update(id, request);
        return ResponseEntity.ok().body(updateArticle);
    }
}

//스트림 : 데이터의 연속적인 흐름, 데이터를 저장하지 않고 처리만
//      데이터 컬렉션에 대한 다양한 연산을 함수형 스타일로 수행할 수 있도록 도와줌