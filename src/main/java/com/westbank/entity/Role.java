package com.westbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    public static final String ID_MANAGER = "MGR";
    @Transient
    public static final String ID_SUPERVISOR = "SPR";
    @Transient
    public static final String ID_POST_PROCESSING_CLERK = "PPC";
    @Transient
    public static final String ID_CREDIT_BROKER = "CBR";
    @Transient
    public static final Role MANAGER = new Role(ID_MANAGER);
    @Transient
    public static final Role SUPERVISOR = new Role(ID_SUPERVISOR);
    @Transient
    public static final Role POST_PROCESSING_CLERK = new Role(ID_POST_PROCESSING_CLERK);
    @Transient
    public static final Role CREDIT_BROKER = new Role(ID_CREDIT_BROKER);

    @Id
    @Column(length = 3)
    protected String id;

    @Basic
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Staff> staff = new ArrayList<>();

    public Role() {
    }

    public Role(String id) {
        this.id = id;
    }

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public void addStaff(Staff newStaff) {
        if (staff == null) {
            staff = new ArrayList<Staff>();
        }
        staff.add(newStaff);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
