package com.itaSS.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Tour {
    @Id
    @Column
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column
    private Date beginDate;
    @Column
    private Date endDate;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="tour_hall",
        joinColumns={@JoinColumn(name="tour_id")},
        inverseJoinColumns={@JoinColumn(name="hall_id")})
    Set<Hall> hall= new HashSet<>();

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

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Hall> getHall() {
        return hall;
    }

    public void setHall(Set<Hall> hall) {
        this.hall = hall;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(" ");
        if (beginDate != null) {
            stringBuilder.append("begin date: ").append(beginDate.toString()).append(" ");
        }
        if (endDate != null) {
            stringBuilder.append("end date: ").append(endDate.toString());
        }
        return stringBuilder.toString();
    }

}
