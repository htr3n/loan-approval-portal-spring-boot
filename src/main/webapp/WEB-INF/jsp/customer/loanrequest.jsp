<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Loan Request</title>
  <%@ include file="../parts/header_css.html" %>
</head>
<body>
<c:import url="tab.jsp"/>
<c:import url="message.jsp"/>

<form:form modelAttribute="form" method="POST" onsubmit="updateBeforeSubmitting(this);">
  <form:hidden path="clientTimezone" id="clientTimezone"/>
  <div id="content">
    <c:set var="thisYear" value="<%=java.time.Year.now().getValue()%>"/>
    <fieldset id="pt1">
      <legend>1.</legend>
      <h3>Loan Request</h3>

      <label for="loanReason">Purpose of Loan <span class="required">*</span></label>
      <form:select id="loanReason" path="loanReason" tabindex="11">
        <form:option value="" label=""/>
        <form:option value="Home Mortgage" label="Home Mortgage"/>
        <form:option value="Refinance" label="Refinance"/>
        <form:option value="Debt Consolidation" label="Debt Consolidation"/>
        <form:option value="Second Mortgage" label="Second Mortgage"/>
        <form:option value="Home Equity" label="Home Equity"/>
        <form:option value="Home Improvement" label="Home Improvement"/>
        <form:option value="Bad Credit Mortgage" label="Bad Credit Mortgage"/>
      </form:select>
      <form:errors path="loanReason" cssClass="fieldError"/>

      <label for="residenceType">Residence Type<span class="required">*</span></label>
      <form:select path="residenceType" id="residenceType" tabindex="12">
        <form:option value="" label=""/>
        <form:option value="MAIN_HOUSE" label="Main House"/>
        <form:option value="SECOND_HOUSE" label="Second House"/>
        <form:option value="OTHER" label="Other"/>
      </form:select>
      <form:errors path="residenceType" cssClass="fieldError"/>

      <label for="estateType">Estate Type<span class="required">*</span></label>
      <form:select path="estateType" id="estateType" tabindex="13">
        <form:option value="" label=""/>
        <form:option value="FLAT" label="Flat"/>
        <form:option value="HOUSE" label="House"/>
        <form:option value="PARKING" label="Parking"/>
        <form:option value="OTHER" label="Other"/>
      </form:select>
      <form:errors path="estateType" cssClass="fieldError"/>

      <label for="estateAddress">Estate address<span class="required">*</span></label>
      <form:input id="estateAddress" path="estateAddress" tabindex="14"/>
      <form:errors path="estateAddress" cssClass="fieldError"/>

      <label for="totalPurchasePrice">Total Purchase Price ($)<span class="required">*</span></label>
      <form:input path="totalPurchasePrice" id="totalPurchasePrice" tabindex="15"/>
      <form:errors path="totalPurchasePrice" cssClass="fieldError"/>

      <label for="personalCapitalContribution">Personal Contribution ($)<span class="required">*</span></label>
      <form:input path="personalCapitalContribution" id="personalCapitalContribution" tabindex="16"/>
      <form:errors path="personalCapitalContribution" cssClass="fieldError"/>

      <label for="loanAmount">Loan Amount ($)<span class="required">*</span></label>
      <form:input path="loanAmount" id="loanAmount" tabindex="17"/>
      <form:errors path="loanAmount" cssClass="fieldError"/>

      <label for="loanTerm">Loan Term (years)<span class="required">*</span></label>
      <form:select path="loanTerm" id="loanTerm" tabindex="18">
        <form:option value="0" label=""/>
        <c:forEach begin="1" end="50" var="term">
          <form:option value="${term}" label="${term}"/>
        </c:forEach>
      </form:select>
      <form:errors path="loanTerm" cssClass="fieldError"/>

      <label for="interestRate">Interest Rate (annual)</label>
      <form:input path="interestRate" id="interestRate" tabindex="19" readonly="true"/>
      <form:errors path="interestRate" cssClass="fieldError"/>

      <label for="startDate">Expected Start Date (dd/MM/yyyy)</label>
      <form:input path="startDate" id="startDate" tabindex="20"/>
      <form:errors path="startDate" cssClass="fieldError"/>
    </fieldset>

    <fieldset id="pt2">
      <legend>2.</legend>
      <h3>Access Sensitive Data</h3>

      <label for="accessSensitiveData">Consent access to sensitive data?
        <form:checkbox id="accessSensitiveData" path="accessSensitiveData" tabindex="21"/>
      </label>
    </fieldset>

    <fieldset id="pt3">
      <legend>3.</legend>
      <h3>Co-Borrower</h3>
      <label for="hasCoborrower">Has a co-borrower?
        <form:checkbox id="hasCoborrower" path="hasCoborrower" onclick="toggleCoBorrower();" tabindex="31"/>
      </label>
      <div id="divCoBorrower" style="display: none;">

        <label for="coborrowerCustomerId">Co-borrower ID:<br/>(for existing customer)</label>
        <form:input id="coborrowerCustomerId" path="coborrowerCustomerId" tabindex="31"/>

        <label for="coborrowerTitle">Title</label>
        <form:select path="coborrowerTitle" id="coborrowerTitle" tabindex="32">
          <form:option value=""/>
          <c:forTokens items="Dr,Miss,Mr,Mrs,Prof" delims="," var="title">
            <form:option value="${title}"/> label="${title}" />
          </c:forTokens>
        </form:select>
        <form:errors path="coborrowerTitle" cssClass="fieldError"/>

        <label for="coborrowerFirstName">First Name <span class="required">*</span></label>
        <form:input id="coborrowerFirstName" path="coborrowerFirstName" tabindex="33"/>
        <form:errors path="coborrowerFirstName" cssClass="fieldError"/>

        <label for="coborrowerLastName">Last Name <span class="required">*</span></label>
        <form:input id="coborrowerLastName" path="coborrowerLastName" tabindex="34"/>
        <form:errors path="coborrowerLastName" cssClass="fieldError"/>

        <label for="coborrowerDateOfBirth">Date of Birth (dd/mm/yyyy)<span class="required">*</span></label>
        <form:input path="coborrowerDateOfBirth" id="coborrowerDateOfBirth" tabindex="35"/>
        <form:errors path="coborrowerDateOfBirth" cssClass="fieldError"/>

        <label for="coborrowerEmail">Email <span class="required">*</span></label>
        <form:input path="coborrowerEmail" id="coborrowerEmail" tabindex="36"/>
        <form:errors path="coborrowerEmail" cssClass="fieldError"/>

        <label for="coborrowerOccupation">Occupation <span class="required">*</span></label>
        <form:input id="coborrowerOccupation" path="coborrowerOccupation" tabindex="37"/>
        <form:errors path="coborrowerOccupation" cssClass="fieldError"/>

        <label for="coborrowerIncome">Annual Income ($)<span class="required">*</span></label>
        <form:input id="coborrowerIncome" path="coborrowerIncome" tabindex="38"/>
        <form:errors path="coborrowerIncome" cssClass="fieldError"/>

        <label for="coborrowerLengthOfService">Length of Service (years)<span class="required">*</span></label>
        <form:select id="coborrowerLengthOfService" path="coborrowerLengthOfService" tabindex="39">
          <form:option value="-1" label="Please select"/>
          <form:option value="0" label="Less than 1 year"/>
          <c:forEach begin="1" end="15" var="term">
            <form:option value="${term}" label="${term}"/>
          </c:forEach>
          <form:option value="16" label="More than 15 years"/>
        </form:select>
        <form:errors path="coborrowerLengthOfService" cssClass="fieldError"/>
      </div>
    </fieldset>

    <fieldset id="pt4">
      <legend>4.</legend>
      <h3>Loan Agency</h3>
      <label for="agency">Bank</label>
      <form:select id="agency" path="agency" tabindex="41">
        <form:option value="-1" label="Please select"/>
        <c:forEach items="${customerFormAgencies}" var="agency">
          <form:option value="${agency.code}" label="${agency.name}"/>
        </c:forEach>
      </form:select>
    </fieldset>

    <fieldset id="last">
      <div id=divsubmit>
        <div class="buttons">
          <a onclick="$('#form').submit();"><img src="/images/tick.png" alt=""/>Submit</a>
          <a class="negative" onclick="$('#form')[0].reset();"><img src="/images/reset.png" alt=""/>Reset</a>
        </div>
      </div>
      <div id="disclaimer">By clicking the &#8220;Submit&#8221; button,
        I agree to WestBank's Terms of Services Agreement; and consent that my data will be handed over to and
        handled via WestBank loan approval process.

        I understand that if I do not agree to these terms of use and privacy statements, I should refrain from
        using services provided by WestBank.
      </div>
    </fieldset>
  </div>
</form:form>

<%@ include file="../parts/footer_scripts.html" %>
<script type="text/javascript">
  function toggleCoBorrower() {
    var hasCoBorrower = $('#hasCoborrower:checked').val();
    if (hasCoBorrower == 'true')
      $('#divCoBorrower').show();
    else
      $('#divCoBorrower').hide();
  }

  function updateBeforeSubmitting(form) {
    var tzOffset = (-1 * new Date().getTimezoneOffset() / 60);
    var tzStr = (tzOffset > 0) ? "UTC+" + tzOffset : "UTC" + tzOffset;
    form.clientTimezone.value = tzStr;
    console.log(tzStr);
    return true;
  }

  $(function () {
    $.datepicker.setDefaults(
      {
        showMonthAfterYear: false,
        changeMonth: true,
        changeYear: true,
        dateFormat: 'dd/mm/yyyy',
        buttonImage: '/images/calendar.gif',
        buttonImageOnly: true,
        showOn: 'both'
      }
    );
    $("#borrowerDateOfBirth").datepicker(
      {
        yearRange: "-100:-18"
      }
    );
    $("#coborrowerDateOfBirth").datepicker(
      {
        yearRange: "-100:-18"
      }
    );
    $("#startDate").datepicker({minDate: 0, maxDate: '+1Y'});
  });
</script>
<c:import url="doc-ready.jsp"/>
</body>
</html>
