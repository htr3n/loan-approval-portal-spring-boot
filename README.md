[![Build Status](https://img.shields.io/travis/htr3n/loan-approval-portal-spring-boot/master.svg)](https://travis-ci.org/htr3n/loan-approval-portal-spring-boot)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

# Loan Approval Portal & Services

This project constitutes two parts of a fictitious WestBank system:

* Web service-based back-end functionality for the loan approval process
* Front-end for customers and staffs

## Technologies

* [Spring Boot 2](https://spring.io/projects/spring-boot)
* [Spring 5 Web MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html) for front-end and controllers
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa) for data access layer
* [Embedded H2 Database](https://www.h2database.com/)
* [Apache CXF 3.2](http://cxf.apache.org) for JAX-WS Web services
* [Apache Maven](https://maven.apache.org) for dependency management, building, packaging, and deployment
* [JUnit 4](https://junit.org/junit4/) for unit testing
* [Mockito](https://site.mockito.org/) for mocking the service layer
* [MockMvc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html) for Spring MVC controllers testing

## Quick Start

```sh
mvn clean spring-boot:run
```

The portal should be up and running at <http://localhost:9999>.

* Customer Login: <http://localhost:9999/login>
* Customer Portal: <http://localhost:9999/portal>
* Staff Login: <http://localhost:9999/staff/login>

## Debugging

* Apache CXF services: <http://localhost:9999/services>
* Dev: <http://localhost:9999/dev>
* H2: <http://localhost:9999/h2-console>
