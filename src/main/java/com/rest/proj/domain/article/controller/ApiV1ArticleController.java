package com.rest.proj.domain.article.controller;

import com.rest.proj.domain.article.entity.Article;
import com.rest.proj.domain.article.service.ArticleService;
import com.rest.proj.global.rsdata.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/articles")
public class ApiV1ArticleController {
    private final ArticleService articleService;

    @Getter
    @AllArgsConstructor
    public static class ArticlesResponse {
        private final List<Article> articles;
    }

    /**
     * 모든 게시글 목록을 조회합니다.
     * @return 게시글 목록을 포함하는 RsData 객체
     */
    @GetMapping
    public RsData<ArticlesResponse> getArticles() {
        // ArticleService를 통해 전체 게시글 리스트를 가져옵니다.
        List<Article> articles = articleService.getList();

        // 성공 응답을 생성하여 반환합니다.
        return RsData.of("S-1", "성공", new ArticlesResponse(articles));
    }

    @Getter
    @AllArgsConstructor
    public static class ArticleResponse {
        private final Article article;
    }

    /**
     * 특정 ID의 게시글을 조회합니다.
     * @param id 조회할 게시글의 ID
     * @return 해당 게시글 정보를 포함하는 RsData 객체. 게시글이 없으면 실패 응답을 반환합니다.
     */
    @GetMapping("/{id}")
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id) {
        return articleService.getArticle(id).map(article -> RsData.of(
                        "S-1",
                        "성공",
                        new ArticleResponse(article)
                ))
                .orElseGet(() -> RsData.of(
                        "F-1",
                        "%d번 게시물은 존재하지 않습니다.".formatted(id),
                        null
                ));
    }
}
