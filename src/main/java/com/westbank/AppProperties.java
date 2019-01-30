package com.westbank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "com.westbank")
@Validated
public class AppProperties {

    @Valid
    private Length length = new Length();
    @Valid
    private Endpoint endpoint = new Endpoint();
    @Valid
    private Loan loan = new Loan();

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public Length getLength() {
        return length;
    }

    public Loan getLoan() {
        return loan;
    }

    public class Length {
        private int customeId = 10;
        private int customerPin = 6;
        private int customerUsername = 10;
        private int staffId = 4;
        private int loanfileId = 6;

        public int getCustomeId() {
            return customeId;
        }

        public void setCustomeId(int customeId) {
            this.customeId = customeId;
        }

        public int getCustomerPin() {
            return customerPin;
        }

        public void setCustomerPin(int customerPin) {
            this.customerPin = customerPin;
        }

        public int getCustomerUsername() {
            return customerUsername;
        }

        public void setCustomerUsername(int customerUsername) {
            this.customerUsername = customerUsername;
        }

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public int getLoanfileId() {
            return loanfileId;
        }

        public void setLoanfileId(int loanfileId) {
            this.loanfileId = loanfileId;
        }
    }

    public class Endpoint {
        private String base;
        private String process;

        public String getProcess() {
            return process;
        }

        public void setProcess(String process) {
            this.process = process;
        }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }
    }

    public class Loan {
        private double threshold = 1000000.0;

        public double getThreshold() {
            return threshold;
        }

        public void setThreshold(double threshold) {
            this.threshold = threshold;
        }
    }
}
