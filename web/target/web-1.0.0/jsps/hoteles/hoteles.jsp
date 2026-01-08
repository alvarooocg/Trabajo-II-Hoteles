<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: administrador
  Date: 17/5/25
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="messages" var="messages"/>

<html>
<head>
    <title> <fmt:message bundle="${messages}" key="title.hoteles"/> </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
</head>
<body>
<div class="container mt-4">
    <c:set var="uriInsertHotel" value="${mvc.uri('formInsertHotel', {'locale': mvc.locale})}" />
    <a href="${uriInsertHotel}" class="btn btn-primary mb-3">
        <fmt:message bundle="${messages}" key="label.action.insert.hotel" />
    </a>

    <table class="table table-striped table-hover caption-top">
        <caption> <fmt:message bundle="${messages}" key="title.hoteles" /> </caption>
        <thead class="table-dark">
            <tr>
                <th scope="col"> <fmt:message bundle="${messages}" key="label.hotel.nombre"/> </th>
                <th scope="col"> <fmt:message bundle="${messages}" key="label.hotel.ciudad"/> </th>
                <th scope="col"> <fmt:message bundle="${messages}" key="label.hotel.estrellas"/> </th>
                <th scope="col"> <fmt:message bundle="${messages}" key="label.hotel.descripcion"/> </th>
                <th scope="col"> <fmt:message bundle="${messages}" key="label.acciones"/> </th>
            </tr>
        </thead>
        <tbody class="table-group-divider">
            <c:forEach var="hotel" items="${hoteles}">
                <c:set var="uriHotel"           value="${mvc.uri('findHotelById',       {'id': hotel.id, 'locale': mvc.locale})}"/>
                <c:set var="uriUpdateHotelById" value="${mvc.uri('formUpdateHotelById', {'id': hotel.id, 'locale': mvc.locale})}"/>
                <c:set var="uriDeleteHotelById" value="${mvc.uri('formDeleteHotelById', {'id': hotel.id, 'locale': mvc.locale})}"/>
                <tr>
                    <td><a href="${uriHotel}">${hotel.nombre}</a></td>
                    <td>${hotel.ciudad}</td>
                    <td>${hotel.estrellas}</td>
                    <td>${hotel.descripcion}</td>
                    <td>
                        <a href="${uriUpdateHotelById}" class="link-primary"> <fmt:message bundle="${messages}" key="action.update" /> </a>
                        <a href="${uriDeleteHotelById}" class="link-danger"> <fmt:message bundle="${messages}" key="action.delete" /> </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
</body>
</html>
