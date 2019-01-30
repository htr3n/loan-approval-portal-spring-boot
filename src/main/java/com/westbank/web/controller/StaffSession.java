package com.westbank.web.controller;

public interface StaffSession {

    String ACTIVE_AUTHENTICATION = "staffActiveAuthentication";
    String ACTIVE_ROLE = "staffActiveRole";

    String MANAGED_LOAN_FILES = "staffManagedLoans";
    String MANAGED_LOAN_CONTRACTS = "staffManagedContracts";

    /* Process status */
    String PROCESS_STATUS = "staffProcessStatus";
    String PROCESS_STATUS_OK = "staffProcessStatusOK";
    String PROCESS_STATUS_ERROR = "staffProcessStatusError";
    String PROCESS_STATUS_KEY = "staffProcessStatusKey";

    String MSG_LOGIN_FAILED = "staff.login.authentication.failed";
    String MSG_DAO_ERR = "staff.dao.error";
    String MSG_INVOCATION_ERR = "staff.invocation.error";

}
