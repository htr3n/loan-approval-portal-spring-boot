<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Staff Login</title>
  <%@ include file="../parts/header_css.html" %>
  <link rel="stylesheet" type="text/css" href="/css/login.css"/>
</head>
<body>
<c:import url="message.jsp"/>
<div id="content">
  <form:form id="loginForm" method="POST" modelAttribute="loginForm">
    <div id="heading"></div>
    <div id="title"><img id="imgLock" src="/images/lock.png" alt=""/>
      Log in @ Staff Portal
    </div>
    <div id="formBody">
      <label for="loginId">Staff ID/E-Mail: </label>
      <form:input path="loginId"/>&nbsp;&nbsp;
      <form:errors cssClass="loginError" path="loginId"/>

      <label for="loginPassword">Password:</label>
      <form:password path="loginPassword"/>&nbsp;&nbsp;
      <form:errors cssClass="loginError" path="loginPassword"/>

      <div id="divSubmit">
        <div class="buttons">
          <a onclick="submitForm('');"><img src="/images/login.png" alt=""/>Log In</a>
        </div>
      </div>
    </div>
  </form:form>
</div>
<%@ include file="../parts/footer_scripts.html" %>
<script type="text/javascript">
  <!--
  function submitForm(action) {
    $("#action").val(action);
    $('#loginForm').submit();
  }

  //-->
</script>
<c:import url="doc-ready.jsp"/>
</body>
</html>
