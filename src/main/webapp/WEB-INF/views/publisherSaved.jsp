<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<div class="registration">
    <p>Publisher ${publisher} has been added!</p>
    <form:form action="/publisher/add" method="get">
        <input class="submit" type="submit" value="Add a new publisher">
    </form:form>
</div>

</body>
</html>
