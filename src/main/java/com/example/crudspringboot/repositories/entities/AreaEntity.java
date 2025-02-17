package com.example.crudspringboot.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "area")
public class AreaEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer wide;

    @ManyToOne
    @JoinColumn(name = "mitra_id", nullable = false)
    private MitraEntity mitra;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private FarmerEntity farmer;
}
