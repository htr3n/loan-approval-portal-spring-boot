<%@ page language="java" pageEncoding="UTF-8" session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <%@ include file="../parts/header_css.html" %>
  <link rel="stylesheet" type="text/css" href="/css/login.css"/>
</head>
<body>
<c:import url="tab.jsp"/>
<c:import url="message.jsp"/>
<form:form id="loginForm" method="post" modelAttribute="loginForm">
  <div id="content">
    <div id="formBody">
      <label for="loginId">Email</label>
      <form:input path="loginId"/>&nbsp;&nbsp;<form:errors cssClass="loginError" path="loginId"/>
      <label for="loginPassword">PIN</label>
      <form:password path="loginPassword"/>&nbsp;&nbsp;<form:errors cssClass="loginError" path="loginPassword"/>
      <div id="divSubmit">
        <div class="buttons">
          <a onclick="$('#loginForm').submit();"><img src="/images/login.png" alt=""/>Log In</a>
        </div>
      </div>
    </div>
  </div>
</form:form>
<%@ include file="../parts/footer_scripts.html" %>
<c:import url="doc-ready.jsp"/>
</body>
</html>