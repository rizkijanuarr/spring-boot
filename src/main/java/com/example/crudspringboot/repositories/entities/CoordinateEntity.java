package com.example.crudspringboot.repositories.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coordinate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateEntity extends BaseEntity {

    @Column(name = "seq", nullable = true)
    private Integer seq;

    @Column(name = "lat", nullable = true)
    private Double lat;

    @Column(name = "lng", nullable = true)
    private Double lng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private AreaEntity area;

}
