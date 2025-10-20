package com.rest.proj.domain.article.entity;

import com.rest.proj.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder // extends(상속) 받으려면 Super사용
public class Article extends BaseEntity {
    private String subject;
    private String content;
}
