<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <br>
            <form:errors path="title" cssClass="error"/>
        </p>
        <p>Is it a book proposition?
            <c:set value="${['Yes', 'No']}" var="prop"/>
            <br>
            <form:radiobuttons path="proposition" items="${prop}"/>
            <c:set var="propValue" value="${book.proposition}"/>
        </p>
        <p>Rating:
            <form:input path="rating"/>
            <br>
            <c:if test="${propValue == false}">
                <form:errors path="rating" cssClass="error"/>
            </c:if>
        </p>
        <p>Book author (authors - separate by ',')
            <form:input path="authors"/>
            <br>
            <c:if test="${propValue == false}">
                <form:errors path="authors" cssClass="error"/>
            </c:if>
        </p>
        <p>Publisher: <br>
            <form:select path="publisher">
                <form:option label="select a publisher" value="0"/>
                <form:options items="${publishers}" itemValue="id" itemLabel="name"/>
            </form:select>
            <br>
            <c:if test="${propValue == false}">
                <form:errors path="publisher" cssClass="error"/>
            </c:if>
        </p>
        <input type="submit" value="SAVE">
    </form:form>

</div>

</body>
</html>
