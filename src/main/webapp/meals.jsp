<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table style="border: 1px solid black; border-collapse: collapse;">
    <tr>
        <td style="border: 1px solid black;">Дата/Время</td>
        <td style="border: 1px solid black;">Описание</td>
        <td style="border: 1px solid black;">Калории</td>
    </tr>
    <c:forEach var="mealTo" items="${mealTo}">
        <tr style="color:${mealTo.excess ? 'red' : 'green'}">
            <td style="border: 1px solid black;">${mealTo.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
            <td style="border: 1px solid black;">${mealTo.description}</td>
            <td style="border: 1px solid black;">${mealTo.calories}</td>
        </tr>
    </c:forEach>
</table>
<hr>
</body>
</html>
