package com.example.crudspringboot.core.repositories.entities;

import com.example.crudspringboot.core.utils.enums.RoleEnum;
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
