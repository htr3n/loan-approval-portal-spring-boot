package com.westbank.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "agency")
public class Agency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 6)
    private String code;

    @Basic
    private String city;

    @Basic
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country")
    private Country country;

    @Basic
    private String state;

    @Basic
    private String street;

    @Basic
    private String zipcode;

    public Agency() {
    }

    public Agency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
