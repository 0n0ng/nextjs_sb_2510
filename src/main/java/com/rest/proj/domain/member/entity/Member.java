package com.rest.proj.domain.member.entity;

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
public class Member extends BaseEntity {
    private String username;
    private String password;
}
