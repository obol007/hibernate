<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Publisher</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<div class="registration">
    <h5>Add a publisher</h5>
    <form:form modelAttribute="publisher">
        <p>Publisher's name: <form:input path="name"/>
            <form:errors path="name"/></p>
        <p>City: <form:input path="city"/></p>
        <input class="submit" type="submit" value="SAVE">
    </form:form>
</div>

</body>
</html>
