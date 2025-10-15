package com.rest.proj.global.initData;

import com.rest.proj.domain.article.service.ArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// 배포할 땐 필요없음 : NotProd
@Configuration
@Profile({"dev", "test"})
public class NotPord {
    @Bean
    CommandLineRunner initData(ArticleService articleService) {
        return args -> {
            articleService.create("제목 1", "내용1");
            articleService.create("제목 2", "내용2");
            articleService.create("제목 3", "내용3");
            articleService.create("제목 4", "내용4");
            articleService.create("제목 5", "내용5");
        };
    }
}
