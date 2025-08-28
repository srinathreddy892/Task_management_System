package com.teluguskillhub.taskproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="taskname", nullable=false)
    private String taskname;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="users_id")
    private Users users;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTaskname() { return taskname; }
    public void setTaskname(String taskname) { this.taskname = taskname; }

    public Users getUsers() { return users; }
    public void setUsers(Users users) { this.users = users; }
}
