package com.teluguskillhub.taskproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable=false)
    private String name;

    @Column(name="email", nullable=false)
    private String email;

    @Column(name="password", nullable=false)
    private String password;

    // Getters and setters
    public long getId() {
    	return id; 
    	}
    public void setId(long id) { 
    	this.id = id;
    	}

    public String getName() { 
    	return name;
    	}
    public void setName(String name) {
    	this.name = name;
    	}

    public String getEmail() {
    	return email;
    	}
    public void setEmail(String email) {
    	this.email = email;
    	}

    public String getPassword() {
    	return password;
    	}
    public void setPassword(String password) {
    	this.password = password;
    	}
}
