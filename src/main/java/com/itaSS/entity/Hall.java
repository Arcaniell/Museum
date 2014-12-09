package com.itaSS.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Hall {
    @Id
    @Column
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToMany(mappedBy = "hall")
    private Set<Tour> tour = new HashSet<>();
    @OneToMany(mappedBy = "hall", cascade = CascadeType.PERSIST)
    private Set<Exhibit> exhibits;

    public Hall() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String toString() {
        StringBuilder sb =new StringBuilder();
        sb.append("Name: ").append(name).append(" \n")
            .append("Exhibits: \n");
        for (Exhibit exhibit : exhibits) {
            sb.append("\t").append(exhibit.getName()).append(" \n");
        }
        sb.append("Tours: \n");
        for (Tour tours : tour) {
            sb.append("\t").append(tours.getName()).append(" \n");
        }
        return sb.toString();
    }

}
