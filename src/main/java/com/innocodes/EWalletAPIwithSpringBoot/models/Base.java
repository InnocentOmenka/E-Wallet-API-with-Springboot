package com.innocodes.EWalletAPIwithSpringBoot.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    Base(){
        this.createdDate= new Date();
        this.updatedDate = new Date();
    }

    @PrePersist
    public void setCreatedAt() {
        createdDate = new Date();
    }
    @PreUpdate
    public void setUpdatedAt() {
        updatedDate = new Date();
    }
}
