package com.itaSS.entity;

import com.itaSS.entity.enumInfo.Positions;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Worker {

    @Id
    @Column
    @GeneratedValue
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column
    private BigDecimal salary;

    @Column
    private Positions position;;

    @OneToOne(cascade = CascadeType.ALL)
    private Job job;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;

        Worker worker = (Worker) o;

        if (!firstName.equals(worker.firstName)) return false;
        if (id != null ? !id.equals(worker.id) : worker.id != null) return false;
        if (job != null ? !job.equals(worker.job) : worker.job != null) return false;
        if (!lastName.equals(worker.lastName)) return false;
        if (position != worker.position) return false;
        if (salary != null ? !salary.equals(worker.salary) : worker.salary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (salary != null ? salary.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First name: ").append(firstName).append(" ");
        sb.append("Last name: ").append(lastName).append(" ");
        if (salary != null) {
            sb.append("Salary:").append(salary).append(" ");
        }
        if (position != null) {
            sb.append("Position: ").append(position).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
