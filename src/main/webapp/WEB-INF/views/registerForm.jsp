<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
</head>
<body>
<%@include file="navbar.jsp"%>

<div class="registration">
    <form:form modelAttribute="student" method="post">
        <%--    <form:label path="firstName">First name: </form:label>--%>
        <p>First name: <form:input title="First Name" path="firstName"/></p>
        <p>Last name: <form:input path="lastName"/></p>
        <p>Language: <form:radiobuttons path="lang"/></p>
        <p>Password: <form:password path="password"/></p>
        <p>About me: <br><form:textarea cols="20" rows="4" path="about"/></p>
        <p><form:checkbox path="terms" label="I accept terms & conditions"/> </p>
        <p><input type="submit" value="SAVE"></p>
    </form:form>
</div>
</body>
</html>
