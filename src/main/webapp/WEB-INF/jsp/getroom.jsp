<%--
  Created by IntelliJ IDEA.
  User: Dimas
  Date: 07.07.2021
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <title>$Title$</title>
    <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Room_id</th>
        <th>isFreeRoom</th>
        <th>numberOfBeds</th>
        <th>costPerHour</th>
        <th>statusRoom</th>
        <th>equipments</th>
        <th>guests</th>
    </tr>
    </thead>
    <tbody>
    <tr>${room.room_id}</tr>
    <tr>${room.isFreeRoom}</tr>
    <tr>${room.numberOfBeds}</tr>
    <tr>${room.costPerHour}</tr>
    <tr>${room.statusRoom}</tr>
    <tr>
        <c:forEach items="${room.equipments}" var="equipment">
            <td>${equipment.toString()}</td>
        </c:forEach>
    </tr>
    <tr>
        <c:forEach items="${room.equipments}" var="equipment">
            <td>${equipment.toString()}</td>
        </c:forEach>
    </tr>
    </tbody>
</table>
$END$
</body>
</html>
