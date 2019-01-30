<% boolean isLogged = session.getAttribute("customerActiveAuthentication") != null; %>
<div id="banner">
  <h1>WestBank Loan Portal</h1>
</div>
<ul id="tabmenu">
  <li>
    <% if (!isLogged) { %>
    <a href="/index">&nbsp;Home&nbsp;</a>
    <% } %>
  </li>
  <!--
  <li>
    <% if (!isLogged) { %>
    <a href="/request">&nbsp;Loan Request&nbsp;</a>
    <% } %>
  </li>
  -->
  <li>
    <% if (!isLogged) { %>
    <a href="/login">&nbsp;Log in&nbsp;</a>
    <% } %>
  </li>
  <li>
    <% if (isLogged) { %>
    <a href="/portal">&nbsp;Loan Portal&nbsp;</a>
    <% } %>
  </li>
  <li>
    <% if (isLogged) { %>
    <a href="/profile">&nbsp;Profile&nbsp;</a>
    <% } %>
  </li>
  <li>
    <% if (isLogged) { %>
    <a href="/request">&nbsp;New Loan Request&nbsp;</a>
    <% } %>
  </li>
  <li>
    <% if (isLogged) { %>
    <a href="/logout">&nbsp;Log out&nbsp;</a>
    <% } %>
  </li>
</ul> 
