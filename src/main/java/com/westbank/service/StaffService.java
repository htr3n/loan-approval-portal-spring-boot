package com.westbank.service;

import com.westbank.entity.Staff;
import com.westbank.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    private final StaffRepository repository;

    @Autowired
    public StaffService(StaffRepository repository) {
        this.repository = repository;
    }

    public Staff authenticate(String loginId, String password) {
        final Staff searched = repository.findByIdOrEmail(loginId, loginId).orElse(null);
        if (searched != null) {
            if (password.equals(searched.getPassword()))
                return searched;
        }
        return null;
    }

}
