<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Dev List</title>
  <link rel="stylesheet" type="text/css" href="/css/portal.css"/>
  <style type="text/css">
    body {
      font-family: sans-serif;
      font-size: 10px;
    }

    th {
      background: #898B5E;
      color: #fff;
      padding: 4px 4px !important;
      text-align: center !important;
    }

    td {
      height: 24px !important;
      white-space: nowrap;
    }

    hr {
      margin-top: 20px;
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<h2>Table: Customer</h2>
<c:if test="${fn:length(dev_customers) gt 0}">
  <table class="stripeMe task">
    <thead>
    <tr>
      <th>ID</th>
      <th>Title</th>
      <th>Full Name</th>
      <th>Email</th>
      <th>Personal ID</th>
      <th>Date Of Birth</th>
      <th>Street</th>
      <th>City</th>
      <th>State</th>
      <th>Zip</th>
      <th>Country</th>
      <th>Phone</th>
      <th>Mobile-phone</th>
      <th>Marital</th>
      <th>#Children</th>
      <th>PIN</th>
      <th>Occupation</th>
      <th>LoS</th>
      <th>Income</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dev_customers}" var="customer">
      <tr>
        <td>${customer.id}</td>
        <td>${customer.title}</td>
        <td>${customer.firstName} &nbsp; ${customer.lastName}</td>
        <td>${customer.email}</td>
        <td>${customer.personalId}</td>
        <td>
          <tags:localDate date="${customer.dateOfBirth}" pattern="dd-MM-yyyy"/>
        </td>
        <td>${customer.address.street}</td>
        <td>${customer.address.city}</td>
        <td>${customer.address.state}</td>
        <td>${customer.address.zipcode}</td>
        <td>${customer.address.country.name}</td>
        <td>${customer.phone}</td>
        <td>${customer.mobilePhone}</td>
        <td>${customer.maritalStatus}</td>
        <td>${customer.numberOfChildren}</td>
        <td>${customer.password}</td>
        <td>${customer.occupation}</td>
        <td>${customer.lengthOfService}</td>
        <td>
          <fmt:formatNumber value='${customer.income}' type="currency" groupingUsed="true" maxFractionDigits="0"/>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>
<hr/>
<!-- Test to show loans or not -->
<h2>Table: LoanFile</h2>
<c:if test="${fn:length(dev_loanfiles) gt 0}">
  <table class="stripeMe task">
    <thead>
    <tr>
      <th>#</th>
      <th>Borrower(s)</th>
      <th>Loan Amount</th>
      <th>Interest Rate</th>
      <th>Loan Term</th>
      <th>Per. Contrib.</th>
      <th>Residence</th>
      <th>Estate</th>
      <th>Created</th>
      <th>Created By</th>
      <th>Updated</th>
      <th>Updated By</th>
      <th>Risk</th>
      <th>Status</th>
      <th>Description</th>
      <th>Contract</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dev_loanfiles}" var="loanfile" varStatus="loopVar">
      <tr>
        <td title="${loanfile.contract}">${loopVar.index}</td>
        <td>${loanfile.borrower.firstName} ${loanfile.borrower.lastName}
          <c:if test="${loanfile.coBorrower != null}">
            /${loanfile.coBorrower.firstName} ${loanfile.coBorrower.lastName}
          </c:if>
        </td>
        <td><fmt:formatNumber value='${loanfile.loanAmount}' type="currency" groupingUsed="true" maxFractionDigits="0"/>
        </td>
        <td>${loanfile.interestRate}</td>
        <td>${loanfile.loanTerm}</td>
        <td>
          <fmt:formatNumber value='${loanfile.personalCapitalContribution}' type="currency" groupingUsed="true"
                            maxFractionDigits="0"/>
        </td>
        <td>${loanfile.residenceType}</td>
        <td>${loanfile.estateType}</td>
        <td title="${loanfile.createdBy.id}">
          <tags:localDate date="${loanfile.createdDate}" pattern="dd-MM-yyyy"/>
        </td>
        <td>${loanfile.createdBy}</td>
        <td>
          <tags:localDate date="${loanfile.updatedDate}" pattern="dd-MM-yyyy"/>
        </td>
        <td>${loanfile.updatedBy.id}/${loanfile.updatedByRole}</td>
        <td>${loanfile.risk}</td>
        <td>${loanfile.status}</td>
        <td>${loanfile.description}</td>
        <td>${loanfile.contract}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>
<hr/>
<h2>Loan Contract</h2>
<c:choose>
  <c:when test="${dev_contracts != null && fn:length(dev_contracts) gt 0}">
    <table class="stripMe task">
      <thead>
      <tr>
        <th>#</th>
        <th>Borrower(s)</th>
        <th>Monthly Payment</th>
        <th>Bank</th>
        <th>Customer signed</th>
        <th>Manager signed</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${dev_contracts}" var="contract" varStatus="loopVar">
        <tr>
          <td>${loopVar.index}</td>
          <td><a> ${contract.borrower.firstName}
              ${contract.borrower.lastName} </a> <c:if
            test="${contract.coBorrower != null}">
            /<a> ${contract.coBorrower.firstName}
            ${contract.coBorrower.lastName} </a>
          </c:if></td>
          <td>
            <fmt:formatNumber value='${loan.contract.monthlyPayment}' type="currency" groupingUsed="true"
                              maxFractionDigits="0"/>
          </td>
          <td title="${contract.agency.agencyCode}">${contract.agency.bankName}</td>
          <td><fmt:formatDate value='${contract.signedByCustomer}' type="date" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value='${contract.signedByManager}' type="date" pattern="yyyy-MM-dd"/></td>
          <td></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:when>
</c:choose>
<hr/>
<h2>Table: Staff</h2>
<c:if test="${fn:length(dev_staff) gt 0}">
  <table class="stripeMe task">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Email</th>
      <th>Password</th>
      <th>Role(s)</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dev_staff}" var="s">
      <tr>
        <td>${s.id}</td>
        <td>${s.firstName} &nbsp; ${s.lastName}</td>
        <td>${s.email}</td>
        <td>${s.password}</td>
        <td>${s.role}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>

<h2>Table: Role</h2>
<c:if test="${fn:length(dev_roles) gt 0}">
  <table class="stripeMe task">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dev_roles}" var="role">
      <tr>
        <td>${role.id}</td>
        <td>${role.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</c:if>
<%@ include file="parts/footer_scripts.html" %>
</body>
</html>
