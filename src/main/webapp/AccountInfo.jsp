<%@ page import="vn.edu.iuh.fit.models.Account" %>
<%@ page import="vn.edu.iuh.fit.models.Role" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11/6/2023
  Time: 10:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Information</title>
    <link rel="stylesheet" href="admin.css">
</head>
<body>
<%
    Account account = (Account) request.getAttribute("account");
    List<Role> roles = (List<Role>) request.getAttribute("roles");
%>
<form action="logout" method="post">
    <input type="hidden" name="accountID" value="<%= account.getId()%>"/>
    <input type="submit" name="btnSubmitFeature" value="Log out" class="btnLogout"/>
</form>
<div class="container">

    <h1>Account Information</h1>
    <table class="table">
        <thead class="thead">
        <tr class="tr">
            <th class="id">ID</th>
            <th class="name">FULL NAME</th>
            <th class="phone">EMAIL</th>
            <th class="email">PHONE</th>
            <th class="pass">PASSWORD</th>
            <th class="status">STATUS</th>
        </tr>
        </thead>
        <tbody class="tbody">
        <tr class="tr">
            <td class="id"><%= account.getId()%></td>
            <td class="name"><%= account.getFullName()%></td>
            <td class="phone"><%= account.getEmail()%></td>
            <td class="email"><%= account.getPhone()%></td>
            <td class="pass"><%= account.getPassword()%></td>
            <td class="status"><%= account.getStatus()%></td>
        </tr>
        </tbody>
    </table>
    <h1>Roles</h1>
    <table class="table-role">
        <thead class="thead-role">
        <tr class="tr">
            <th class="id">ID</th>
            <th class="name">NAME</th>
            <th class="desc">DESCRIPTION</th>
            <th class="status">STATUS</th>
        </tr>
        </thead>
        <tbody class="tbody-role">
        <% for(Role r : roles){ %>
        <tr class="tr">
            <td class="id"><%= r.getId()%></td>
            <td class="name"><%= r.getName()%></td>
            <td class="desc"><%= r.getDescription()%></td>
            <td class="status"><%= r.getStatus()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
