package com.adnan.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity {

    @Column(name = "date_created", nullable = false, updatable = false)
    protected Date dateCreated;

    @Column(name = "date_updated", nullable = false)
    protected Date dateUpdated;

    protected String status;

    public BaseEntity() {
        super();
        this.dateCreated = new Date();
        this.dateUpdated = new Date();
        this.status = "ACTIVE";
    }



}
