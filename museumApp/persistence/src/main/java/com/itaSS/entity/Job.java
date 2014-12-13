package com.itaSS.entity;

import javax.persistence.*;

@Entity
@Table
public class Job {
    @Id
    @Column
    @GeneratedValue
    private Integer id;
    @OneToOne
    Worker worker;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}

