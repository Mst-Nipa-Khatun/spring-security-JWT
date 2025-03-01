package com.nipa.springbootjwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NaturalId
    @Column(name = "name", length = 128)
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @Column(updatable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;

    private String updatedBy;

   
    public Long getId() {
        return id;
    }

   
    public void setId(Long id) {
        this.id = id;
    }

   
    public String getCreatedBy() {
        return createdBy;
    }

   
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

   
    public Date getCreatedAt() {
        return createdAt;
    }

   
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

   
    public String getUpdatedBy() {
        return updatedBy;
    }

   
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

   
    public Date getUpdatedAt() {
        return updatedAt;
    }

   
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

   
    public Integer getStatus() {
        return status;
    }

   
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private Integer status;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}