<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Staff Portal</title>
  <%@ include file="../parts/staff_header_css.html" %>
</head>
<body>
<c:import url="message.jsp"/>
<div id="content">${staffProcessStatus}
  <h1>Credit Broker Portal</h1>
  <h2>${staffActiveAuthentication.firstName} ${staffActiveAuthentication.lastName} [${staffActiveAuthentication.id}]
    | <a href="/staff/logout">Logout</a>
    | <a onclick="doSubmit('reload');"><img id="refresh" alt="" src="/images/refresh.png">Reload</a>
  </h2>

  <form:form method="POST" modelAttribute="loanList" id="form">
    <form:hidden path="loanFileId" id="loanFileId"/>
    <form:hidden path="action" id="action"/>
    <c:choose>
      <c:when test="${staffManagedLoans != null && fn:length(staffManagedLoans) gt 0}">
        <div id="twotables">
          <table class="stripMe">
            <thead>
            <tr>
              <th>#</th>
              <th>Borrower(s)</th>
              <th>Residence</th>
              <th>Estate</th>
              <th>Price</th>
              <th>Personal contrib.</th>
              <th>Loan Amount</th>
              <th>Interest</th>
              <th>Loan Term</th>
              <th>Created Date</th>
              <th>Status</th>
              <th>Awaiting Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${staffManagedLoans}" var="loan" varStatus="loopVar">
              <tr>
                <td>${loopVar.index}</td>
                <td>
                  <a>${loan.borrower.firstName} ${loan.borrower.lastName}</a>
                  <c:if test="${loan.coBorrower != null}"> /
                    <a>${loan.coBorrower.firstName} ${loan.coBorrower.lastName}</a></c:if>
                </td>
                <td>${loan.residenceType}</td>
                <td title="${loan.estateLocation}">${loan.estateType}</td>
                <td>
                  <fmt:formatNumber value='${loan.totalPurchasePrice}' type="currency" groupingUsed="true"
                                    maxFractionDigits="0"/>
                </td>
                <td>
                  <fmt:formatNumber value='${loan.personalCapitalContribution}' type="currency" groupingUsed="true"
                                    maxFractionDigits="0"/>
                </td>
                <td>
                  <fmt:formatNumber value='${loan.loanAmount}' type="currency" groupingUsed="true"
                                    maxFractionDigits="0"/>
                </td>
                <td>${loan.interestRate}</td>
                <td>${loan.loanTerm}</td>
                <td nowrap="nowrap">
                  <tags:localDate date="${loan.createdDate}" pattern="dd-MM-yyyy"/>
                </td>
                <td width="160px" title="${loan.description}">
                  <a>${loan.status}</a>
                </td>
                <td width="105px">
                  <c:if test="${loan.status eq 'INITIALIZED' and loan.accessSensitiveData == true}">
                    <div class="buttons">
                      <a class="positive" onclick="doSubmit('accept','${loan.loanFileId}');">
                        <img src="/images/tick.png" alt=""/>Accept</a>
                    </div>
                  </c:if>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </c:when>
      <c:otherwise>
        <p>There are no available loan requests.</p>
      </c:otherwise>
    </c:choose>
  </form:form>
</div>
<%@ include file="../parts/footer_scripts.html" %>
<script type="text/javascript">
  $(document).ready(function () {
    $('table tr').mouseover(function () {
      $(this).addClass("over");
    });
    $('table tr').mouseout(function () {
      $(this).removeClass("over");
    });
    $('table tr:even').addClass('alt');
  });

  function doSubmit(action, loanFileId) {
    $("#action").val(action);
    $("#loanFileId").val(loanFileId);
    $("#form").submit();
  }
</script>
<c:import url="doc-ready.jsp"/>
</body>
</html>
