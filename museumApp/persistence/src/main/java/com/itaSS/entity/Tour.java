package com.itaSS.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "id")
public class Tour extends Job{

    @Column(nullable = false)
    private String tour_name;

    @Column
    private Date begin_Date;

    @Column
    private Date end_Date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="tour_hall",
        joinColumns={@JoinColumn(name="tour_id")},
        inverseJoinColumns={@JoinColumn(name="hall_id")})
    Set<Hall> hall= new HashSet<>();

    public String getTour_name() {
        return tour_name;
    }

    public void setTour_name(String tour_name) {
        this.tour_name = tour_name;
    }

    public Date getBegin_Date() {
        return begin_Date;
    }

    public void setBegin_Date(Date begin_Date) {
        this.begin_Date = begin_Date;
    }

    public Date getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(Date end_Date) {
        this.end_Date = end_Date;
    }

    public Set<Hall> getHall() {
        return hall;
    }

    public void setHall(Set<Hall> hall) {
        this.hall = hall;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tour_name).append(" ");
        if (begin_Date != null) {
            stringBuilder.append("begin date: ").append(begin_Date.toString()).append(" ");
        }
        if (end_Date != null) {
            stringBuilder.append("end date: ").append(end_Date.toString());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

}
