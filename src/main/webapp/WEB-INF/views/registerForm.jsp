<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
</head>
<body>

<form:form modelAttribute="student" method="post">
    <form:label path="firstName">First name: </form:label>
    <form:input title="First Name" path="firstName"/>
    <form:label path="lastName">Last name: </form:label>
    <form:input path="lastName"/>
    <input type="submit" value="SAVE">
</form:form>

</body>
</html>
