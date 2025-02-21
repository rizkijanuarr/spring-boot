package com.example.crudspringboot.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "farmer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmerEntity extends BaseEntity {

    @Column(name = "farmer_code", nullable = false, unique = true, updatable = false)
    private String farmer_code;

    @Column(name = "farmer_name", nullable = false)
    private String farmer_name;

    @Column(name = "farmer_phone", nullable = false)
    private String farmer_phone;

    @Column(name = "farmer_address", nullable = false)
    private String farmer_address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mitra_id", nullable = false)
    private MitraEntity mitra;

}
