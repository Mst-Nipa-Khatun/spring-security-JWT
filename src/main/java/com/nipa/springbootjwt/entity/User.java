package com.nipa.springbootjwt.entity;


import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class User{
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Role> roles;

    @Column(updatable = false)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;

    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private Integer status;

   
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}