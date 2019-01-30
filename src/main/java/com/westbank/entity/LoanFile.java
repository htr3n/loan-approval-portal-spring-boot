package com.westbank.entity;

import com.westbank.adapter.jpa.EstateTypeConverter;
import com.westbank.adapter.jpa.LoanFileStatusConverter;
import com.westbank.adapter.jpa.LoanRiskConverter;
import com.westbank.adapter.jpa.ResidenceTypeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
public class LoanFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 6)
    private String loanFileId;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "borrower")
    private Customer borrower;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "coBorrower")
    private Customer coBorrower;

    @ManyToOne(targetEntity = Staff.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy")
    private Staff createdBy;

    @Column
    private LocalDate createdDate;

    @Column
    private String description;

    @Column
    private String estateLocation;

    @Column
    @Convert(converter = EstateTypeConverter.class)
    private EstateType estateType = EstateType.OTHER;

    @Column
    private boolean hasCoBorrower;

    @Column
    private Double interestRate;

    @Column
    private Double loanAmount;

    @Column
    private String loanReason;

    @Column
    private Integer loanTerm;

    @Column
    private Double monthlyPayment;

    @Column
    @Convert(converter = ResidenceTypeConverter.class)
    private ResidenceType residenceType = ResidenceType.OTHER;

    @Column
    @Convert(converter = LoanRiskConverter.class)
    private LoanRisk risk = LoanRisk.NOTASSESSED;

    @Column
    @Convert(converter = LoanFileStatusConverter.class)
    private LoanFileStatus status = LoanFileStatus.INITIALIZED;

    @Column
    private Double totalPurchasePrice;

    @Column
    private Double personalCapitalContribution;

    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "updatedBy")
    private Staff updatedBy;

    @Column
    private String updatedByRole;

    @Column
    private LocalDate updatedDate;

    @OneToOne(targetEntity = LoanContract.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "contract")
    private LoanContract contract;

    @Column
    private boolean accessSensitiveData;

    public LoanFile() {
    }

    public String getLoanFileId() {
        return loanFileId;
    }

    public void setLoanFileId(String loanFileId) {
        this.loanFileId = loanFileId;
    }

    public Customer getBorrower() {
        return borrower;
    }

    public void setBorrower(Customer borrower) {
        this.borrower = borrower;
    }

    public Customer getCoBorrower() {
        return coBorrower;
    }

    public void setCoBorrower(Customer coBorrower) {
        this.coBorrower = coBorrower;
    }

    public Staff getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Staff createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstateLocation() {
        return estateLocation;
    }

    public void setEstateLocation(String estateLocation) {
        this.estateLocation = estateLocation;
    }

    public EstateType getEstateType() {
        return estateType;
    }

    public void setEstateType(EstateType estateType) {
        this.estateType = estateType;
    }

    public boolean isHasCoBorrower() {
        return hasCoBorrower;
    }

    public void setHasCoBorrower(boolean hasCoBorrower) {
        this.hasCoBorrower = hasCoBorrower;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanReason() {
        return loanReason;
    }

    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public ResidenceType getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(ResidenceType residenceType) {
        this.residenceType = residenceType;
    }

    public LoanRisk getRisk() {
        return risk;
    }

    public void setRisk(LoanRisk risk) {
        this.risk = risk;
    }

    public LoanFileStatus getStatus() {
        return status;
    }

    public void setStatus(LoanFileStatus status) {
        this.status = status;
    }

    public Double getTotalPurchasePrice() {
        return totalPurchasePrice;
    }

    public void setTotalPurchasePrice(Double totalPurchasePrice) {
        this.totalPurchasePrice = totalPurchasePrice;
    }

    public Double getPersonalCapitalContribution() {
        return personalCapitalContribution;
    }

    public void setPersonalCapitalContribution(Double personalCapitalContribution) {
        this.personalCapitalContribution = personalCapitalContribution;
    }

    public Staff getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Staff updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedByRole() {
        return updatedByRole;
    }

    public void setUpdatedByRole(String updatedByRole) {
        this.updatedByRole = updatedByRole;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LoanContract getContract() {
        return contract;
    }

    public void setContract(LoanContract contract) {
        this.contract = contract;
    }

    public boolean isAccessSensitiveData() {
        return accessSensitiveData;
    }

    public void setAccessSensitiveData(boolean accessSensitiveData) {
        this.accessSensitiveData = accessSensitiveData;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String toString() {
        return "LoanFile{" +
                "loanFileId='" + loanFileId + '\'' +
                ", createdDate=" + createdDate +
                ", description='" + description + '\'' +
                ", estateLocation='" + estateLocation + '\'' +
                ", estateType=" + estateType +
                ", hasCoBorrower=" + hasCoBorrower +
                ", interestRate=" + interestRate +
                ", loanAmount=" + loanAmount +
                ", loanReason='" + loanReason + '\'' +
                ", loanTerm=" + loanTerm +
                ", monthlyPayment=" + monthlyPayment +
                ", residenceType=" + residenceType +
                ", risk=" + risk +
                ", status=" + status +
                ", totalPurchasePrice=" + totalPurchasePrice +
                ", personalCapitalContribution=" + personalCapitalContribution +
                ", updatedByRole='" + updatedByRole + '\'' +
                ", updatedDate=" + updatedDate +
                ", accessSensitiveData=" + accessSensitiveData +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanFile loanFile = (LoanFile) o;
        return loanFileId.equals(loanFile.loanFileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanFileId, borrower, createdDate);
    }
}
