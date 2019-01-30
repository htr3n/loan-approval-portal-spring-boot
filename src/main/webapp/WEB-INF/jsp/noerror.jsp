<%@ page pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
  <meta charset="UTF-8">
  <title><fmt:message key="ServerError"/></title>
</head>
<body>
<h1>
  <fmt:message key="ServerError"/>
</h1>

<br/>
Exception is: <%= exception %>

<br/>
<c:if test="${not empty exception}">
<pre style="color: red;">
Error: <b><c:out value="${exception.message}"/></b>
</pre>
</c:if>

</body>
</html>
