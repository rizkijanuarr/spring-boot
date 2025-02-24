package com.example.crudspringboot.maxxitaniapps.repositories.entities;

import com.example.crudspringboot.core.repositories.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "area")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaEntity extends BaseEntity {

    @Column(name = "area_name", nullable = false)
    private String area_name;

    @Column(name = "area_land", nullable = false)
    private BigDecimal area_land;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private FarmerEntity farmer;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoordinateEntity> coordinates;

}