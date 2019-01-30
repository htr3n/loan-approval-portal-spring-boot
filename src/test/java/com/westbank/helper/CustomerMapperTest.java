package com.westbank.helper;

import com.westbank.CustomerTestConstants;
import com.westbank.entity.Customer;
import com.westbank.web.form.LoanForm;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMapperTest implements CustomerTestConstants {

    private LoanForm form;
    private LoanForm emptyForm;

    @Before
    public void setUp() {
        emptyForm = new LoanForm();

        form = new LoanForm();
        form.setBorrowerPersonalId(BORROWERPERSONALID);
        form.setBorrowerTitle(BORROWERTITLE);
        form.setNewPin(NEWPIN);
        form.setNewPinAgain(NEWPIN);
    }

    @Test
    public void map_shouldYieldNull_forNullInput() {
        assertThat(new CustomerMapper().from(null)).isNull();
    }

    @Test
    public void map_shouldYieldNonNull_forNonNullEmptyInput() {
        assertThat(new CustomerMapper().from(emptyForm)).isNotNull();
    }

    @Test
    public void map_shouldYieldMatchingResult_forNonNullNonEmptyInput() {

        Customer dest = new CustomerMapper().from(form);

        assertThat(dest).isNotNull();

        assertThat(dest.getPersonalId()).isEqualTo(form.getBorrowerPersonalId());
        assertThat(dest.getTitle()).isEqualTo(form.getBorrowerTitle());
        assertThat(dest.getPassword()).isEqualTo(form.getNewPin());
        assertThat(dest.getPassword()).isEqualTo(form.getNewPinAgain());
    }

}
