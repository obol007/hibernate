<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 23.06.2020
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email</title>
</head>
<body>
<form action="/form/reset" method="post">
    <label for="email">Wpisz email, dla którego chcesz zresetować hasło: </label>
    <input id="email" type="email" name="email">
    <input type="submit">
</form>

</body>
</html>
