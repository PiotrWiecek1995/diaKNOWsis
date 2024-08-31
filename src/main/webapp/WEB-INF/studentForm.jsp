<%--
  Created by IntelliJ IDEA.
  User: piotrwiecek
  Date: 14/07/2024
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Student Form</title>
</head>
<body>
<h2>Student Form</h2>
<%--@elvariable id="student" type=""--%>
<form:form method="post" modelAttribute="student">

    <label>
        First Name: <form:input path="firstName"/>
    </label><br><br>

    <label>
        Last Name: <form:input path="lastName"/>
    </label><br><br>

    <label>Gender:</label>
    <form:radiobutton path="gender" value="Male" label="Male" />
    <form:radiobutton path="gender" value="Female" label="Female" /><br><br>

    <label>
        Country: <form:select path="country">
        <form:options items="${countries}" />
    </form:select>
    </label><br><br>

    <label>
        Notes: <form:textarea path="notes"/>
    </label><br><br>

    <label>
        Join Mailing List: <form:checkbox path="mailingList"/>
    </label><br><br>

    <label>
        Programming Skills: <form:select path="programmingSkills" multiple="true">
        <form:options items="${programmingSkills}" />
    </form:select>
    </label><br><br>

    <label>Hobbies:</label><br>
    <c:forEach var="hobby" items="${hobbies}">
        <form:checkbox path="hobbies" value="${hobby}" label="${hobby}" /><br>
    </c:forEach><br>

    <input type="submit" value="Submit">
</form:form>
</body>
</html>
