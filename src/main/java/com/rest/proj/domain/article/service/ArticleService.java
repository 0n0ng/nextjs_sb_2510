package com.rest.proj.domain.article.service;

import com.rest.proj.domain.article.entity.Article;
import com.rest.proj.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Article 관련 비즈니스 로직을 처리하는 서비스입니다.
 */
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    /**
     * 새로운 게시글을 생성합니다.
     * @param subject 제목
     * @param content 내용
     */
    public void create(String subject, String content) {
        // Article 객체를 생성합니다.
        Article article = Article.builder()
                .subject(subject)
                .content(content)
                .build();

        // 생성된 Article 객체를 저장소에 저장합니다.
        articleRepository.save(article);
    }

    /**
     * 모든 게시글 목록을 조회합니다.
     * @return 게시글 전체 목록
     */
    public List<Article> getList() {
        // 저장소에서 모든 Article을 찾아 반환합니다.
        return articleRepository.findAll();
    }

    /**
     * 특정 ID의 게시글을 조회합니다.
     * @param id 조회할 게시글의 ID
     * @return Optional<Article> 객체. ID에 해당하는 게시글이 없을 수도 있습니다.
     */
    public Optional<Article> getArticle(Long id) {
        // 저장소에서 ID로 Article을 찾아 Optional로 감싸 반환합니다.
        return articleRepository.findById(id);
    }
}
