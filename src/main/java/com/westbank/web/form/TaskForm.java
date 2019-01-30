package com.westbank.web.form;

import org.springframework.stereotype.Component;

@Component
public class TaskForm {

    public static final String ACTION_ACCEPT = "accept";
    public static final String ACTION_GRANT = "grant";
    public static final String ACTION_REJECT = "reject";
    public static final String ACTION_SIGN = "sign";
    public static final String ACTION_LOGOUT = "logout";
    public static final String ACTION_RELOAD = "reload";

    protected String loanFileId;
    protected Long contractId;
    protected String action;

    public String getLoanFileId() {
        return loanFileId;
    }

    public void setLoanFileId(String loanFileId) {
        this.loanFileId = loanFileId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "TaskForm{" +
                "loanFileId=" + loanFileId +
                ", contractId=" + contractId +
                ", action='" + action + '\'' +
                '}';
    }
}
