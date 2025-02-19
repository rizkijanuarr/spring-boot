package com.example.crudspringboot.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "area")
public class AreaEntity extends BaseEntity{

    @Column(nullable = false)
    private String area_name;

    @Column(nullable = false)
    private BigDecimal area_land;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private FarmerEntity farmer;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoordinateEntity> coordinates;
}