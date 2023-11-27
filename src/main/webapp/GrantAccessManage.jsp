<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.models.GrantAccess" %>
<%@ page import="vn.edu.iuh.fit.models.Role" %>
<%@ page import="vn.edu.iuh.fit.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 11/7/2023
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Grant Access Manage</title>
    <link rel="stylesheet" href="admin.css">
</head>
<body>
    <%
        List<GrantAccess> grantAccesses = (List<GrantAccess>) request.getAttribute("grantAccesses");
        List<Role> roles = (List<Role>) request.getAttribute("roles");
        List<Account> accounts = (List<Account>) request.getAttribute("accounts");
        GrantAccess grantAccessChosen = (GrantAccess) request.getAttribute("grantAccess");
    %>
    <div class="container">
        <form method="post" action="grantaccess">
            <div class="form-info">
                <div class="form-content">
                    <label for="account" class="label">Account</label>
                    <select name="selectAccount" id="account" >
                        <% for(Account a : accounts) {
                            if(grantAccessChosen.getAccount().getId() == a.getId()) { %>
                        <option selected value="<%= a.getId()%>"><%= a.getEmail()%></option>
                        <% } else { %>
                        <option value="<%= a.getId()%>"><%= a.getEmail()%></option>
                        <% } %>
                        <% } %>
                    </select>
                </div><br/>
                <div class="form-content">
                    <label for="role" class="label">Role</label>
                    <select name="selectRole" id="role">
                        <% for(Role r : roles) {
                            if(grantAccessChosen.getRole().getId() == r.getId()) { %>
                        <option selected value="<%= r.getId()%>"><%= r.getName()%></option>
                        <% } else { %>
                        <option value="<%= r.getId()%>"><%= r.getName()%></option>
                        <% } %>
                        <% } %>
                    </select>
                </div><br/>
                <div class="form-content">
                    <label for="isGrant" class="label">IsGrant</label>
                    <select name="selectIsGrant" id="isGrant">
                        <option value="1">ENABLE</option>
                        <option value="0">DISABLE</option>
                    </select>
                </div><br/>
                <div class="form-content">
                    <label for="note" class="label">Note</label>
                    <input type="text" name="note" id="note"  value="<%= grantAccessChosen.getNote()%>"/>
                </div><br/>
            </div>
            <input type="submit" name="btnSubmitFeature" value="Thêm"
                   style="background-color: chartreuse"
            />
            <input type="submit" name="btnSubmitFeature" value="Cập Nhật"
                   style="background-color: coral;"
            />
            <input type="hidden" name="accountID" value="<%= grantAccessChosen.getAccount().getId()%>" class="input"/>
            <input type="hidden" name="roleID" value="<%= grantAccessChosen.getRole().getId()%>" class="input"/>
            <input type="submit" name="btnSubmitFeature" value="Xóa"
                   style="background-color: red"/>
        </form>
        <hr/>
        <table class="table" >
            <thead class="thead">
            <tr class="tr" >
                <th class="email">Account</th>
                <th class="name">Role</th>
                <th class="desc">Note</th>
                <th class="status">Is Grant</th>
            </tr>
            </thead>
            <tbody class="tbody">
            <% for(GrantAccess grantAccess : grantAccesses){ %>
            <form action="grantaccess" method="post">
                <input type="hidden" name="accountID" value="<%= grantAccess.getAccount().getId()%>"/>
                <input type="hidden" name="roleID" value="<%= grantAccess.getRole().getId()%>"/>
                <input type="hidden" name="isGrant" value="<%= grantAccess.getIsGrant()%>"/>
                <input type="hidden" name="note" value="<%= grantAccess.getNote()%>"/>
                <tr class="tr">
                    <td class="email"><%= grantAccess.getAccount().getEmail()%>
                        <input type="submit" name="btnSubmitFeature" value="selectRow"/>
                    </td>
                    <td class="name"><%= grantAccess.getRole().getName()%></td>
                    <td class="desc"><%= grantAccess.getNote()%></td>
                    <td class="status"><%= grantAccess.getIsGrant()==1?"ENABLE":"DISABLE"%></td>
                </tr>
            </form>
            <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
