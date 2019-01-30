package com.westbank.web.controller;

import java.util.List;

public interface CustomerSession {

    String ACTIVE_AUTHENTICATION = "customerActiveAuthentication";
    String LOAN_FILES = "customerLoanFiles";
    String LOAN_CONTRACTS = "customerLoanContracts";
    String TITLE = "customerTitle";
    String FIRSTNAME = "customerFirstname";
    String LASTNAME = "customerLastname";
    String EMAIL = "customerEmail";

    /* Form values */
    String FORM_AGENCIES = "customerFormAgencies";

    /* Process status */
    String PROCESS_STATUS = "customerProcessStatus";
    String PROCESS_STATUS_OK = "customerProcessStatusOK";
    String PROCESS_STATUS_ERROR = "customerProcessStatusError";
    String PROCESS_STATUS_KEY = "customerProcessStatusKey";

    /* Message keys */
    String MSG_SIGNUP_IDENTITY_EXIST = "customer.signup.identity.already.used";
    String MSG_REQUEST_IDENTITY_EXIST = "customer.request.identity.already.used";
    String MSG_REQUEST_OK = "customer.request.ok";
    String MSG_LOGIN_FAILED = "customer.login.authentication.failed";
    String MSG_SIGNUP_OK = "customer.signup.ok";

    String MSG_PROFILE_UPDATE_OK = "customer.profile.update.ok";
    String MSG_CUSTOMER_DAO_ERR = "customer.dao.error";
    String MSG_INVOCATION_ERR = "customer.invocation.error";

    /* Portal Navigation */
    String NAV_INDEX = "customerNavigationIndex";
    String NAV_HOME = "0";
    String NAV_LOGIN = "1";
    String NAV_PORTAL = "2";
    String NAV_PROFILE = "3";
    String NAV_REQUEST = "4";

}
