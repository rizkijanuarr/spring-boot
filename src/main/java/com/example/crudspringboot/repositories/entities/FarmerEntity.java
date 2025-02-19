package com.example.crudspringboot.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "farmer")
public class FarmerEntity extends BaseEntity{
    @Column(nullable = false, unique = true, updatable = false)
    private String farmer_code;
    @Column(nullable = false)
    private String farmer_name;
    @Column(nullable = false)
    private String farmer_phone;
    @Column(nullable = false)
    private String farmer_address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mitra_id", nullable = false)
    private MitraEntity mitra;
}
