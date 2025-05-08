package com.learn.start.entity;

import jakarta.persistence.*;

@Entity
@Table(name="ROLE")
public class Role {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "TENROLE")
    private String tenRole;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenRole() {
        return tenRole;
    }

    public void setTenRole(String tenRole) {
        this.tenRole = tenRole;
    }
}
