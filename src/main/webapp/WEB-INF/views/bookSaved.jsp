<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<div class="registration">
    Book ${book} has been saved!
</div>
<div class="registration">
    <p><a href="${pageContext.request.contextPath}/book/add">Add a new book!</a></p>
</div>

</body>
</html>
