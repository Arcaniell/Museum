package com.itaSS.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Worker {
    @Id
    @Column
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    String name;
    @Column
    BigDecimal salary;
    @Column
    String position;
    @OneToOne
    Hall hall;
    @OneToOne
    Tour tour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
