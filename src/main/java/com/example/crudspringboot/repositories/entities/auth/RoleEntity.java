package com.example.crudspringboot.repositories.entities.auth;

import com.example.crudspringboot.repositories.entities.base.BaseEntity;
import com.example.crudspringboot.utils.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity extends BaseEntity {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

}
