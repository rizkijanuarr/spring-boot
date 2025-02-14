package com.example.crudspringboot.repositories.entities;


import com.example.crudspringboot.repositories.enumaration.MitraTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mitra")
public class MitraEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MitraTypeEnum type;
}
