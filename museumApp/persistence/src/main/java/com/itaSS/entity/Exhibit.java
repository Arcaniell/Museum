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
    @Column(name = "author_name", nullable = false)
    private String authorName;
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;
    @Column(name = "arrive_date")
    private Date arriveDate;
    @Column
    private Materials material;
    @Column
    private Technics technic;
    @ManyToOne(fetch = FetchType.LAZY)
    private Hall hall;

//    You have to override the equals() and hashCode() methods if you:
//    intend to put instances of persistent classes in a Set
//    (the recommended way to represent many-valued associations);

    public Exhibit() {}

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(Date arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exhibit)) return false;

        Exhibit exhibit = (Exhibit) o;

        if (arriveDate != null ? !arriveDate.equals(exhibit.arriveDate) : exhibit.arriveDate != null) return false;
        if (!authorName.equals(exhibit.authorName)) return false;
        if (!creationDate.equals(exhibit.creationDate)) return false;
        if (hall != null ? !hall.equals(exhibit.hall) : exhibit.hall != null) return false;
        if (material != exhibit.material) return false;
        if (!name.equals(exhibit.name)) return false;
        if (technic != exhibit.technic) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + authorName.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + (arriveDate != null ? arriveDate.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (technic != null ? technic.hashCode() : 0);
//        result = 31 * result + (hall != null ? hall.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Exhibit name: ").append(name).append(" Author name: ").append(authorName);
        sb.append(" Creation date: ").append(creationDate);
        if (arriveDate != null) {
            sb.append(" Arrival date: ").append(arriveDate);
        }
        if (material != null) {
            sb.append(" Material: ").append(material);
        }
        if (technic != null) {
            sb.append(" Technic: ").append(technic);
        }
        sb.append("\n");
        return sb.toString();
    }

}
