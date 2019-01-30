package com.westbank.web.controller;

import com.westbank.helper.Debugger;
import com.westbank.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DevController {

    private static final String DEV_INDEX = "dev";

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected LoanContractRepository loanContractRepository;

    @Autowired
    protected LoanFileRepository loanFileRepository;

    @Autowired
    protected StaffRepository staffRepository;

    @Autowired
    protected RoleRepository roleRepository;

    protected static Logger log = LoggerFactory.getLogger(DevController.class);

    @RequestMapping("/dev")
    public String dev(HttpSession session) {
        session.setAttribute("dev_customers", customerRepository.findAllWithFetch());
        session.setAttribute("dev_loanfiles", loanFileRepository.findAllWithFetch());
        session.setAttribute("dev_contracts", loanContractRepository.findAllWithFetch());
        session.setAttribute("dev__staff", staffRepository.findAllWithFetch());
        session.setAttribute("dev_roles", roleRepository.findAll());
        return DEV_INDEX;
    }
}
