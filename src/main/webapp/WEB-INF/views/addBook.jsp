<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<div class="registration">
    <h4>Add a book</h4>
    <form:form modelAttribute="book">
        <p>Title:
            <form:input path="title"/>
        </p>
       <p>Publisher:
        <form:select path="publisher">
            <form:option label="select a publisher" value="0"/>
            <form:options items="${publishers}" itemValue="id" itemLabel="name"/>
        </form:select>
       </p>
        <input type="submit" value="SAVE">
    </form:form>

</div>

</body>
</html>
