<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 9/17/2023
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>dashboard</title>
    <link rel="stylesheet" href="admin.css"/>
</head>
<body>
<h1>Admin</h1>

<%
//    String btnSubmit = request.getAttribute("btnSubmit").toString();
    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
    Account accountChosen = (Account) request.getAttribute("account");
%>
<div class="container">
    <form class="form-crud" action="accountmanage" method="post">
        <div class="form-info">
            <div class="form-content">
                <input type="hidden" name="accountID" value="<%= accountChosen.getId()%>" class="input"/>
                <label for="fullName" class="label">Full name</label>
                <input type="text" name="fullName" id="fullName" value="<%= accountChosen.getFullName()%>" class="input"/>
            </div><br/>
            <div class="form-content">
                <label for="email" class="label">Email</label>
                <input type="email" name="email" id="email" value="<%= accountChosen.getEmail()%>" class="input"/>
            </div><br/>
            <div class="form-content">
                <label for="password" class="label">Password</label>
                <input type="password" name="password" id="password" value="<%= accountChosen.getPassword()%>" class="input"/>
            </div><br/>
            <div class="form-content">
                <label for="phone" class="label">Phone</label>
                <input type="tel" name="phone" id="phone" value="<%= accountChosen.getPhone()%>" class="input"/>
            </div><br/>
            <div class="form-content">
                <label for="status" class="label">Status</label>
                <select name="status" id="status" value="<%= accountChosen.getStatus()%>" class="input">
                    <option value="1">ACTIVE</option>
                    <option value="0">DEACTIVE</option>
                    <option value="-1">DELETE</option>
                </select>
            </div><br/>
        </div>
        <input type="submit" name="btnSubmitFeature" value="Thêm"
               style="background-color: chartreuse"
        />
        <input type="submit" name="btnSubmitFeature" value="Cập Nhật"
               style="background-color: coral;"
        />
        <input type="hidden" name="accountID" value="<%= accountChosen.getId()%>" />
        <input type="submit" name="btnSubmitFeature" value="Xóa"
               style="background-color: red"/>

    </form>
    <hr/>
    <table class="table" >
        <thead class="thead">
        <tr class="tr" >
            <th class="id">ID</th>
            <th class="name">Full Name</th>
            <th class="email">Email</th>
            <th class="phone">Phone</th>
        </tr>
        </thead>
        <tbody class="tbody">
        <% for(Account account: accounts){ %>
        <tr class="tr" >
            <td class="id">
                <form action="accountmanage" method="post" >
                    <input type="submit" name="btnSubmitFeature" value="<%= account.getId()%>" class="btnAccountID"/>
                </form>
            </td>
            <td class="name"><%= account.getFullName()%></td>
            <td class="email"><%= account.getEmail()%></td>
            <td class="phone"><%= account.getPhone()%></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>
