package com.example.crudspringboot.repositories.entities;


import com.example.crudspringboot.base.enums.MitraTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mitra")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MitraEntity extends BaseEntity {

    @Column(name = "mitra_code", nullable = false, unique = true, updatable = false)
    private String mitra_code;

    @Column(name = "mitra_name", nullable = false)
    private String mitra_name;

    @Column(name = "mitra_phone", nullable = false)
    private String mitra_phone;

    @Column(name = "mitra_address", nullable = false)
    private String mitra_address;

    @Enumerated(EnumType.STRING)
    @Column(name = "mitra_type", nullable = false)
    private MitraTypeEnum mitra_type;

}
