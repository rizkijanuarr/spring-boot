package com.example.crudspringboot.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coordinate")
public class CoordinateEntity extends BaseEntity{

    @Column(nullable = true)
    private Integer seq;

    @Column(nullable = true)
    private Double lat;

    @Column(nullable = true)
    private Double lng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private AreaEntity area;
}
