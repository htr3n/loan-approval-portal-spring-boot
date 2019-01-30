package com.westbank.web.form;

import com.westbank.entity.EstateType;
import com.westbank.entity.MaritalStatus;
import com.westbank.entity.ResidenceType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoanForm {

    public LoanForm() {
    }

    private Double loanAmount;
    private String loanReason;
    private Integer loanTerm;
    private Double interestRate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    private Double totalPurchasePrice;
    private Double personalCapitalContribution;
    private ResidenceType residenceType;
    private EstateType estateType;
    private String estateAddress;
    private String borrowerTitle;
    private Long borrowerCustomerId;
    private String borrowerFirstName;
    private String borrowerLastName;
    private String borrowerPersonalId;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate borrowerDateOfBirth;
    private String borrowerStreet;
    private String borrowerCity;
    private String borrowerState;
    private String borrowerZipcode;
    private String borrowerPhone;
    private String borrowerMobilePhone;
    private String borrowerEmail;
    private MaritalStatus borrowerMaritalStatus;
    private Integer borrowerNumberOfChildren;
    private String borrowerOccupation;
    private Integer borrowerLengthOfService;
    private Double borrowerIncome;
    private String newPin;
    private String newPinAgain;
    private boolean hasCoborrower;
    private Long coborrowerCustomerId;
    private String coborrowerTitle;
    private String coborrowerFirstName;
    private String coborrowerLastName;
    private String coborrowerEmail;
    private String coborrowerPersonalId;
    @Nullable
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate coborrowerDateOfBirth;
    private String coborrowerOccupation;
    private Integer coborrowerLengthOfService;
    private Double coborrowerIncome;
    private boolean accessSensitiveData;
    private String clientTimezone;
    private String agency;

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

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public ResidenceType getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(ResidenceType residenceType) {
        this.residenceType = residenceType;
    }

    public EstateType getEstateType() {
        return estateType;
    }

    public void setEstateType(EstateType estateType) {
        this.estateType = estateType;
    }

    public String getEstateAddress() {
        return estateAddress;
    }

    public void setEstateAddress(String estateAddress) {
        this.estateAddress = estateAddress;
    }

    public String getBorrowerTitle() {
        return borrowerTitle;
    }

    public void setBorrowerTitle(String borrowerTitle) {
        this.borrowerTitle = borrowerTitle;
    }

    public Long getBorrowerCustomerId() {
        return borrowerCustomerId;
    }

    public void setBorrowerCustomerId(Long borrowerCustomerId) {
        this.borrowerCustomerId = borrowerCustomerId;
    }

    public String getBorrowerFirstName() {
        return borrowerFirstName;
    }

    public void setBorrowerFirstName(String borrowerFirstName) {
        this.borrowerFirstName = borrowerFirstName;
    }

    public String getBorrowerLastName() {
        return borrowerLastName;
    }

    public void setBorrowerLastName(String borrowerLastName) {
        this.borrowerLastName = borrowerLastName;
    }

    public String getBorrowerPersonalId() {
        return borrowerPersonalId;
    }

    public void setBorrowerPersonalId(String borrowerPersonalId) {
        this.borrowerPersonalId = borrowerPersonalId;
    }

    public LocalDate getBorrowerDateOfBirth() {
        return borrowerDateOfBirth;
    }

    public void setBorrowerDateOfBirth(LocalDate borrowerDateOfBirth) {
        this.borrowerDateOfBirth = borrowerDateOfBirth;
    }

    public String getBorrowerStreet() {
        return borrowerStreet;
    }

    public void setBorrowerStreet(String borrowerStreet) {
        this.borrowerStreet = borrowerStreet;
    }

    public String getBorrowerCity() {
        return borrowerCity;
    }

    public void setBorrowerCity(String borrowerCity) {
        this.borrowerCity = borrowerCity;
    }

    public String getBorrowerState() {
        return borrowerState;
    }

    public void setBorrowerState(String borrowerState) {
        this.borrowerState = borrowerState;
    }

    public String getBorrowerZipcode() {
        return borrowerZipcode;
    }

    public void setBorrowerZipcode(String borrowerZipcode) {
        this.borrowerZipcode = borrowerZipcode;
    }

    public String getBorrowerPhone() {
        return borrowerPhone;
    }

    public void setBorrowerPhone(String borrowerPhone) {
        this.borrowerPhone = borrowerPhone;
    }

    public String getBorrowerMobilePhone() {
        return borrowerMobilePhone;
    }

    public void setBorrowerMobilePhone(String borrowerMobilePhone) {
        this.borrowerMobilePhone = borrowerMobilePhone;
    }

    public String getBorrowerEmail() {
        return borrowerEmail;
    }

    public void setBorrowerEmail(String borrowerEmail) {
        this.borrowerEmail = borrowerEmail;
    }

    public MaritalStatus getBorrowerMaritalStatus() {
        return borrowerMaritalStatus;
    }

    public void setBorrowerMaritalStatus(MaritalStatus borrowerMaritalStatus) {
        this.borrowerMaritalStatus = borrowerMaritalStatus;
    }

    public Integer getBorrowerNumberOfChildren() {
        return borrowerNumberOfChildren;
    }

    public void setBorrowerNumberOfChildren(Integer borrowerNumberOfChildren) {
        this.borrowerNumberOfChildren = borrowerNumberOfChildren;
    }

    public String getBorrowerOccupation() {
        return borrowerOccupation;
    }

    public void setBorrowerOccupation(String borrowerOccupation) {
        this.borrowerOccupation = borrowerOccupation;
    }

    public Integer getBorrowerLengthOfService() {
        return borrowerLengthOfService;
    }

    public void setBorrowerLengthOfService(Integer borrowerLengthOfService) {
        this.borrowerLengthOfService = borrowerLengthOfService;
    }

    public Double getBorrowerIncome() {
        return borrowerIncome;
    }

    public void setBorrowerIncome(Double borrowerIncome) {
        this.borrowerIncome = borrowerIncome;
    }

    public String getNewPin() {
        return newPin;
    }

    public void setNewPin(String newPin) {
        this.newPin = newPin;
    }

    public String getNewPinAgain() {
        return newPinAgain;
    }

    public void setNewPinAgain(String newPinAgain) {
        this.newPinAgain = newPinAgain;
    }

    public boolean isHasCoborrower() {
        return hasCoborrower;
    }

    public void setHasCoborrower(boolean hasCoborrower) {
        this.hasCoborrower = hasCoborrower;
    }

    public Long getCoborrowerCustomerId() {
        return coborrowerCustomerId;
    }

    public void setCoborrowerCustomerId(Long coborrowerCustomerId) {
        this.coborrowerCustomerId = coborrowerCustomerId;
    }

    public String getCoborrowerTitle() {
        return coborrowerTitle;
    }

    public void setCoborrowerTitle(String coborrowerTitle) {
        this.coborrowerTitle = coborrowerTitle;
    }

    public String getCoborrowerFirstName() {
        return coborrowerFirstName;
    }

    public void setCoborrowerFirstName(String coborrowerFirstName) {
        this.coborrowerFirstName = coborrowerFirstName;
    }

    public String getCoborrowerLastName() {
        return coborrowerLastName;
    }

    public void setCoborrowerLastName(String coborrowerLastName) {
        this.coborrowerLastName = coborrowerLastName;
    }

    public String getCoborrowerEmail() {
        return coborrowerEmail;
    }

    public void setCoborrowerEmail(String coborrowerEmail) {
        this.coborrowerEmail = coborrowerEmail;
    }

    public String getCoborrowerPersonalId() {
        return coborrowerPersonalId;
    }

    public void setCoborrowerPersonalId(String coborrowerPersonalId) {
        this.coborrowerPersonalId = coborrowerPersonalId;
    }

    @Nullable
    public LocalDate getCoborrowerDateOfBirth() {
        return coborrowerDateOfBirth;
    }

    public void setCoborrowerDateOfBirth(@Nullable LocalDate coborrowerDateOfBirth) {
        this.coborrowerDateOfBirth = coborrowerDateOfBirth;
    }

    public String getCoborrowerOccupation() {
        return coborrowerOccupation;
    }

    public void setCoborrowerOccupation(String coborrowerOccupation) {
        this.coborrowerOccupation = coborrowerOccupation;
    }

    public Integer getCoborrowerLengthOfService() {
        return coborrowerLengthOfService;
    }

    public void setCoborrowerLengthOfService(Integer coborrowerLengthOfService) {
        this.coborrowerLengthOfService = coborrowerLengthOfService;
    }

    public Double getCoborrowerIncome() {
        return coborrowerIncome;
    }

    public void setCoborrowerIncome(Double coborrowerIncome) {
        this.coborrowerIncome = coborrowerIncome;
    }

    public boolean isAccessSensitiveData() {
        return accessSensitiveData;
    }

    public void setAccessSensitiveData(boolean accessSensitiveData) {
        this.accessSensitiveData = accessSensitiveData;
    }

    public String getClientTimezone() {
        return clientTimezone;
    }

    public void setClientTimezone(String clientTimezone) {
        this.clientTimezone = clientTimezone;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return "LoanForm{" +
                "loanAmount=" + loanAmount +
                ", loanReason='" + loanReason + '\'' +
                ", loanTerm=" + loanTerm +
                ", interestRate=" + interestRate +
                ", startDate=" + startDate +
                ", totalPurchasePrice=" + totalPurchasePrice +
                ", personalCapitalContribution=" + personalCapitalContribution +
                ", residenceType=" + residenceType +
                ", estateType=" + estateType +
                ", estateAddress='" + estateAddress + '\'' +
                ", borrowerTitle='" + borrowerTitle + '\'' +
                ", borrowerCustomerId=" + borrowerCustomerId +
                ", borrowerFirstName='" + borrowerFirstName + '\'' +
                ", borrowerLastName='" + borrowerLastName + '\'' +
                ", borrowerPersonalId='" + borrowerPersonalId + '\'' +
                ", borrowerDateOfBirth=" + borrowerDateOfBirth +
                ", borrowerStreet='" + borrowerStreet + '\'' +
                ", borrowerCity='" + borrowerCity + '\'' +
                ", borrowerState='" + borrowerState + '\'' +
                ", borrowerZipcode='" + borrowerZipcode + '\'' +
                ", borrowerPhone='" + borrowerPhone + '\'' +
                ", borrowerMobilePhone='" + borrowerMobilePhone + '\'' +
                ", borrowerEmail='" + borrowerEmail + '\'' +
                ", borrowerMaritalStatus=" + borrowerMaritalStatus +
                ", borrowerNumberOfChildren=" + borrowerNumberOfChildren +
                ", borrowerOccupation='" + borrowerOccupation + '\'' +
                ", borrowerLengthOfService=" + borrowerLengthOfService +
                ", borrowerIncome=" + borrowerIncome +
                ", newPin='" + newPin + '\'' +
                ", newPinAgain='" + newPinAgain + '\'' +
                ", hasCoborrower=" + hasCoborrower +
                ", coborrowerCustomerId=" + coborrowerCustomerId +
                ", coborrowerTitle='" + coborrowerTitle + '\'' +
                ", coborrowerFirstName='" + coborrowerFirstName + '\'' +
                ", coborrowerLastName='" + coborrowerLastName + '\'' +
                ", coborrowerEmail='" + coborrowerEmail + '\'' +
                ", coborrowerPersonalId='" + coborrowerPersonalId + '\'' +
                ", coborrowerDateOfBirth=" + coborrowerDateOfBirth +
                ", coborrowerOccupation='" + coborrowerOccupation + '\'' +
                ", coborrowerLengthOfService=" + coborrowerLengthOfService +
                ", coborrowerIncome=" + coborrowerIncome +
                ", accessSensitiveData=" + accessSensitiveData +
                ", clientTimezone='" + clientTimezone + '\'' +
                ", agency='" + agency + '\'' +
                '}';
    }
}
