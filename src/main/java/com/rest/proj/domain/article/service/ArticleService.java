package com.rest.proj.domain.article.service;

import com.rest.proj.domain.article.entity.Article;
import com.rest.proj.domain.article.repository.ArticleRepository;
import com.rest.proj.domain.member.entity.Member;
import com.rest.proj.global.rsdata.RsData;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
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
    @Transactional // 한 세트로 처리되는 작업의 묶음, 중간에 실패면 모두 실패
    // 없다면 중간중간 빠진 데이터가 db에 저장 될 수 있어 꼬일 수 있다.
    public RsData<Article> create(Member member, String subject, String content) {
        // Article 객체를 생성합니다.
        Article article = Article.builder()
                .author(member)
                .subject(subject)
                .content(content)
                .build();

        // 생성된 Article 객체를 저장소에 저장합니다.
        articleRepository.save(article);

        return RsData.of(
                "S-2",
                "게시물이 생성되었습니다.",
                article
        );
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

    public Optional<Article> findById(Long id) {

        return articleRepository.findById(id);
    }

    public RsData<Article> modify(Article article, @NotBlank String subject, @NotBlank String content) {
        article.setSubject(subject);
        article.setContent(content);
        articleRepository.save(article);

        return RsData.of(
                "S-3",
                "%d번 게시물이 수정되었습니다.".formatted(article.getId()),
                article
        );
    }

    public RsData<Article> delete(Long id) {
        articleRepository.deleteById(id);

        return RsData.of (
                "S-4",
                "%d번 게시물이 삭제되었습니다." .formatted(id)
        );
    }
}
