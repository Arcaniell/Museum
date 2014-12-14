package com.itaSS.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "id")
public class Hall extends Job{

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "hall", cascade = CascadeType.ALL)
    private Set<Tour> tour = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hall", cascade = CascadeType.ALL)
    private Set<Exhibit> exhibits;

    public Hall() { }

    public Set<Exhibit> getExhibits() {
        return exhibits;
    }

    public void setExhibits(Set<Exhibit> exhibits) {
        this.exhibits = exhibits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Tour> getTour() {
        return tour;
    }

    public void setTour(Set<Tour> tour) {
        this.tour = tour;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hall)) return false;

        Hall hall = (Hall) o;

        if (exhibits != null ? !exhibits.equals(hall.exhibits) : hall.exhibits != null) return false;
        if (!name.equals(hall.name)) return false;
        if (tour != null ? !tour.equals(hall.tour) : hall.tour != null) return false;

        return true;
    }

    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (tour != null ? tour.hashCode() : 0);
//        result = 31 * result + (exhibits != null ? exhibits.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder sb =new StringBuilder();
        sb.append("Name: ").append(name).append(" \n");
        if (exhibits.size() != 0) {
            sb.append("Exhibits: \n");
            for (Exhibit exhibit : exhibits) {
                sb.append("\t").append(exhibit.getName()).append(" \n");
            }
        }
        if (tour.size() != 0) {
            sb.append("Tours: \n");
            for (Tour tours : tour) {
                sb.append("\t").append(tours.getTour_name()).append(" \n");
            }
        }
        return sb.toString();
    }

}
