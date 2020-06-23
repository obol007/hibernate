<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form</title>
</head>
<body>
<%--Gdy brak atrybutu action formularz wraca pod ten sam adres metodą POST--%>
<%--<form action="/form/result">--%>
<form>
    <label for="product">Wpisz nazwę produktu</label>
    <input id="product" type="text" name="product">
    <input type="submit">
</form>

</body>
</html>
