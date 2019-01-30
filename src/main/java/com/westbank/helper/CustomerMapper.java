package com.westbank.helper;

import com.westbank.entity.Address;
import com.westbank.entity.Customer;
import com.westbank.web.form.LoanForm;

public class CustomerMapper {

    public Customer from(LoanForm form) {
        if (form != null) {
            final Customer customer = new Customer();

            customer.setPersonalId(form.getBorrowerPersonalId());
            customer.setTitle(form.getBorrowerTitle());
            customer.setFirstName(form.getBorrowerFirstName());
            customer.setLastName(form.getBorrowerLastName());
            customer.setDateOfBirth(form.getBorrowerDateOfBirth());
            customer.setMaritalStatus(form.getBorrowerMaritalStatus());
            customer.setNumberOfChildren(form.getBorrowerNumberOfChildren());
            customer.setPhone(form.getBorrowerPhone());
            customer.setMobilePhone(form.getBorrowerMobilePhone());
            customer.setEmail(form.getBorrowerEmail());
            customer.setIncome(form.getBorrowerIncome());
            customer.setOccupation(form.getBorrowerOccupation());
            customer.setLengthOfService(form.getBorrowerLengthOfService());

            final Address a = new Address();
            a.setStreet(form.getBorrowerStreet());
            a.setCity(form.getBorrowerCity());
            a.setState(form.getBorrowerState());
            a.setZipcode(form.getBorrowerZipcode());
            customer.setAddress(a);

            String newPin = form.getNewPin();
            String newPinAgain = form.getNewPinAgain();

            if (newPin != null && !newPin.isEmpty()
                    && newPin.equals(newPinAgain)) {
                customer.setPassword(newPin);
            }
            return customer;
        }
        return null;
    }

}
