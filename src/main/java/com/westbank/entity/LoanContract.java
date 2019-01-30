package com.westbank.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class LoanContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(targetEntity = Agency.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "agency")
    private Agency agency;

    @Column
    private LocalDate settlementDate;

    @OneToOne(targetEntity = LoanFile.class, mappedBy = "contract", fetch = FetchType.LAZY)
    private LoanFile loanFile;

    @Column
    private LocalDate signedByCustomer;

    @Column
    private LocalDate signedByManager;

    public LoanContract() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public LoanFile getLoanFile() {
        return loanFile;
    }

    public void setLoanFile(LoanFile loanFile) {
        this.loanFile = loanFile;
    }

    public LocalDate getSignedByCustomer() {
        return signedByCustomer;
    }

    public void setSignedByCustomer(LocalDate signedByCustomer) {
        this.signedByCustomer = signedByCustomer;
    }

    public LocalDate getSignedByManager() {
        return signedByManager;
    }

    public void setSignedByManager(LocalDate signedByManager) {
        this.signedByManager = signedByManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanContract that = (LoanContract) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, settlementDate);
    }

    @Override
    public String toString() {
        return "LoanContract{" +
                "id=" + id +
                ", settlementDate=" + settlementDate +
                ", signedByCustomer=" + signedByCustomer +
                ", signedByManager=" + signedByManager +
                '}';
    }
}
