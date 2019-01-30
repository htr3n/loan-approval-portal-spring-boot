package com.westbank.entity;

import com.westbank.adapter.jpa.MaritalStatusConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String username;

    @Column
    private String email;

    @Basic
    private String firstName;

    @Basic
    private Double income;

    @Basic
    private String lastName;

    @Basic
    private Integer lengthOfService;

    @OneToMany(targetEntity = LoanFile.class, mappedBy = "borrower", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<LoanFile> loans;

    @OneToMany(targetEntity = LoanFile.class, mappedBy = "coBorrower", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<LoanFile> coborrowedloans;

    @Column
    @Convert(converter = MaritalStatusConverter.class)
    private MaritalStatus maritalStatus;

    @Basic
    private String mobilePhone;

    @Basic
    private Integer numberOfChildren;

    @Basic
    private String occupation;

    @Basic
    private String personalId;

    @Basic
    private String phone;

    @Basic
    private String password;

    @Basic
    private String title;

    @Embedded
    private Address address;

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getLengthOfService() {
        return lengthOfService;
    }

    public void setLengthOfService(Integer lengthOfService) {
        this.lengthOfService = lengthOfService;
    }

    public List<LoanFile> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanFile> loans) {
        this.loans = loans;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<LoanFile> getCoborrowedloans() {
        return coborrowedloans;
    }

    public void setCoborrowedloans(List<LoanFile> coborrowedloans) {
        this.coborrowedloans = coborrowedloans;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", personalId='" + personalId + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", occupation='" + occupation + '\'' +
                ", income=" + income +
                ", lengthOfService=" + lengthOfService +
                ", maritalStatus=" + maritalStatus +
                ", numberOfChildren=" + numberOfChildren +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", phone='" + phone + '\'' +
                ", title='" + title + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) &&
                Objects.equals(dateOfBirth, customer.dateOfBirth) &&
                Objects.equals(username, customer.username) &&
                Objects.equals(email, customer.email) &&
                Objects.equals(personalId, customer.personalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfBirth, username, email, personalId);
    }
}
