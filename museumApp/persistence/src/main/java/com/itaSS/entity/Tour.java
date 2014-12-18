package com.itaSS.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "id")
public class Tour extends Job{

    @Column(name = "", nullable = false)
    private String tourName;

    @Column(name = "begin_date", nullable = false)
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="tour_hall",
        joinColumns={@JoinColumn(name="tour_id")},
        inverseJoinColumns={@JoinColumn(name="hall_id")})
    Set<Hall> hall= new HashSet<>();

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tour_name) {
        this.tourName = tour_name;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date begin_Date) {
        this.beginDate = begin_Date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date end_Date) {
        this.endDate = end_Date;
    }

    public Set<Hall> getHall() {
        return hall;
    }

    public void setHall(Set<Hall> hall) {
        this.hall = hall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour)) return false;

        Tour tour = (Tour) o;

        if (beginDate != null ? !beginDate.equals(tour.beginDate) : tour.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(tour.endDate) : tour.endDate != null) return false;
        if (hall != null ? !hall.equals(tour.hall) : tour.hall != null) return false;
        if (!tourName.equals(tour.tourName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tourName.hashCode();
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (hall != null ? hall.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tourName).append(" ");
        if (beginDate != null) {
            stringBuilder.append("begin date: ").append(beginDate.toString()).append(" ");
        }
        if (endDate != null) {
            stringBuilder.append("end date: ").append(endDate.toString());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

}
