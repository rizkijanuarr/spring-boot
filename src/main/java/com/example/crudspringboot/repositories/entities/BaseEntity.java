package com.example.crudspringboot.repositories.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@DynamicUpdate
@Data
public class BaseEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "active", columnDefinition = "TINYINT default 1")
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    @NotNull
    private Boolean active;

    @Column(name = "created_date")
    @NotNull
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "created_by")
    @NotNull
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.createdDate = LocalDateTime.now();
        this.createdBy = "SYSTEM";
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

    @PreRemove
    public void preRemove() {
        this.deletedDate = LocalDateTime.now();
        this.active = false;
    }
}
