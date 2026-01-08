<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${mvc.locale}" />
<fmt:setBundle basename="messages" var="messages" />

<html>
<head>
    <title><fmt:message bundle="${messages}" key="title.reservas" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-4">

    <c:set var="uriInsertReserva" value="${mvc.uri('formInsertReserva', {'locale': mvc.locale})}" />
    <a href="${uriInsertReserva}" class="btn btn-primary mb-3">
        <fmt:message bundle="${messages}" key="label.action.insert.reserva" />
    </a>

    <table class="table table-striped table-hover caption-top">
        <caption><fmt:message bundle="${messages}" key="title.reservas" /></caption>
        <thead class="table-dark">
        <tr>
            <th><fmt:message bundle="${messages}" key="label.reserva.cliente" /></th>
            <th><fmt:message bundle="${messages}" key="label.reserva.habitacion" /></th>
            <th><fmt:message bundle="${messages}" key="label.reserva.fechaEntrada" /></th>
            <th><fmt:message bundle="${messages}" key="label.reserva.fechaSalida" /></th>
            <th><fmt:message bundle="${messages}" key="label.reserva.acciones" /></th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach var="reserva" items="${reservas}">

            <c:set var="uriReserva" value="${mvc.uri('findReservaById', {'id': reserva.id, 'locale': mvc.locale})}" />
            <c:set var="uriUpdateReserva" value="${mvc.uri('formUpdateReservaById', {'id': reserva.id, 'locale': mvc.locale})}" />
            <c:set var="uriDeleteReserva" value="${mvc.uri('formDeleteReservaById', {'id': reserva.id, 'locale': mvc.locale})}" />

            <tr>

                <td><a href="${uriReserva}">${reserva.id_cliente}</a></td>
                <td>${reserva.id_habitacion}</td>
                <td>${reserva.fecha_entrada}</td>
                <td>${reserva.fecha_salida}</td>
                <td>
                    <a href="${uriUpdateReserva}" class="link-primary"><fmt:message bundle="${messages}" key="action.update" /></a>
                    <a href="${uriDeleteReserva}" class="link-danger"><fmt:message bundle="${messages}" key="action.delete" /></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>