package com.westbank.demo;

import com.westbank.entity.Customer;
import com.westbank.entity.MaritalStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.*;

public class DataInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;

    @Value("classpath:init-data.sql")
    private Resource dataScript;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (dataScript != null) {
            // https://github.com/spring-projects/spring-boot/issues/8899
            if (event.getApplicationContext().getParent() == null) {
                LOG.debug("Initialising some application data.");
                ScriptUtils.executeSqlScript(DataSourceUtils.getConnection(dataSource), dataScript);
            }
        }
    }

    private Collection<Customer> initCustomers() {
        final Set<Customer> customers = new HashSet<>();
        final Customer alice = new Customer();
        alice.setFirstName("Alice");
        alice.setLastName("Power");
        alice.setPassword("abcd1234");
        alice.setEmail("alice@test.com");
        alice.setPersonalId("205026069");
        alice.setIncome(80000.0);
        alice.setLengthOfService(10);
        alice.setMobilePhone("1234567890");
        alice.setMaritalStatus(MaritalStatus.MARRIED);
        alice.setDateOfBirth(LocalDate.of(1980, 01, 01));
        customers.add(alice);

        final Customer bob = new Customer();
        customers.add(bob);
        return customers;
    }
}
