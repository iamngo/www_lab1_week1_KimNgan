<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>SignIn</title>
    <link rel="stylesheet" href="admin.css">
</head>
<body>
<div class="container">
    <h1>Sign In</h1>
    <form action="login" method="post">
        <label for="email">Email:</label>
        <input type="email" required id="email" name="email" placeholder="Nhập email của bạn!!!" />
        <br/><br/>
        <label for="password">Password:</label>
        <input type="password" required id="password" name="password" placeholder="Nhập password!!!"/>
        <br/><br/>
        <input type="submit" value="Sign in" id="signin-btn">
    </form>
</div>
</body>
</html>