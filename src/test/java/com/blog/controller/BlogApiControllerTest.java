package com.blog.controller;

import com.blog.domain.Article;
import com.blog.dto.AddArticleRequest;
import com.blog.repository.BlogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc; //스프링 테스트, 컨트롤러의 요청/응답 테스트 할 수 있도록 준비

    @Autowired
    protected ObjectMapper objectMapper; //직렬화 <--> 역직렬화

    @Autowired
    //스프링 웹 애플리케이션용, HTTP 요청, 세션, 서블릿 컨텍스트 정보
    protected WebApplicationContext context;
    
    @Autowired
    BlogRepository blogRepository;
    
    @BeforeEach //매 번 테스트 전에 실행
    void mockMvcSetup() {
        this.mockMvc= MockMvcBuilders.webAppContextSetup(this.context)
                .build();
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle : 블로그 글 추가에 성공한다.")
    @Test   //테스팅할 메서드
    public void addArticle() throws Exception {
        //1. given -> 준비, 객체를 준비
        final String url="/api/articles";
        final String title="title";
        final String content="content";
        final AddArticleRequest userRequest=new AddArticleRequest(title,content);   //dto

        //1.2. java 객체를 Json으로 변환 -> 직렬화
        final String requestBody=objectMapper.writeValueAsString(userRequest);

        //2. when -> 처리
        // 설정한 내용을 바탕으로 요청 전송
        ResultActions result=mockMvc.perform(post(url) //post Method
                        .contentType(MediaType.APPLICATION_JSON) //json형식으로 데이터 전송
                        .content(requestBody)); //http 요청 본문에 포함할 데이터를 설정

        //3. then -> 검증
        result.andExpect(status().isCreated());

        List<Article> articles=blogRepository.findAll();

        for(Article article:articles){
            System.out.println(article.getTitle());
            System.out.println(article.getContent());
            System.out.println("-------------------");
        }


        assertThat(articles.size()).isEqualTo(1); //크기
        assertThat(articles.get(0).getTitle()).isEqualTo(title); //제목
        assertThat(articles.get(0).getContent()).isEqualTo(content); //타이틀
    }
}