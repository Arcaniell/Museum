package com.itaSS.entity;

import com.itaSS.entity.enumInfo.Materials;
import com.itaSS.entity.enumInfo.Technics;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
public class Exhibit {
    @Id
    @Column
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column
    private Date arrive_date;
    @Column(nullable = false)
    private String author_name;
    @Column
    private Materials material;
    @Column
    private Technics technic;
    @ManyToOne
    private Hall hall;

//    You have to override the equals() and hashCode() methods if you:
//    intend to put instances of persistent classes in a Set
//    (the recommended way to represent many-valued associations);

    public boolean equals(Exhibit exhibit) {
        return this.name.equals(exhibit.getName())
                && this.arrive_date.equals(exhibit.getArrive_date())
                && this.author_name.equals(exhibit.getAuthor_name())
                && this.material.equals(exhibit.getMaterial())
                && this.technic.equals(exhibit.getTechnic());
    }

    public Exhibit() {}

    public Exhibit(Hall hall) {
        this.hall = hall;
    }

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

    public Date getArrive_date() {
        return arrive_date;
    }

    public void setArrive_date(Date arrive_date) {
        this.arrive_date = arrive_date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public Materials getMaterial() {
        return material;
    }

    public void setMaterial(Materials material) {
        this.material = material;
    }

    public Technics getTechnic() {
        return technic;
    }

    public void setTechnic(Technics technic) {
        this.technic = technic;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public String toString() {
        return "Exhibit name: " + name + "\n";
    }

}
