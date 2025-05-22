<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: administrador
  Date: 17/5/25
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${mvc.locale}" />
<fmt:setBundle basename="messages" var="messages" />


<html>
<head>
    <title><fmt:message bundle="${messages}" key="title.clientes" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">

    <c:set var="uriInsertCliente" value="${mvc.uri('formInsertCliente', {'locale': mvc.locale})}" />
    <a href="${uriInsertCliente}" class="btn btn-primary mb-3">
        <fmt:message bundle="${messages}" key="label.action.insert.cliente" />
    </a>

    <table class="table table-striped table-hover caption-top">
        <caption><fmt:message bundle="${messages}" key="title.clientes" /></caption>
        <thead class="table-dark">
        <tr>
            <th scope="col"><fmt:message bundle="${messages}" key="label.cliente.nombre" /></th>
            <th scope="col"><fmt:message bundle="${messages}" key="label.cliente.email" /></th>
            <th scope="col"><fmt:message bundle="${messages}" key="label.cliente.telefono" /></th>
            <th scope="col"><fmt:message bundle="${messages}" key="label.cliente.acciones" /></th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach var="cliente" items="${clientes}">
            <c:set var="uriCliente" value="${mvc.uri('findClienteById', {'id': cliente.id, 'locale': mvc.locale})}" />
            <c:set var="uriUpdateClienteById" value="${mvc.uri('formUpdateClienteById', {'id': cliente.id, 'locale': mvc.locale})}" />
            <c:set var="uriDeleteClienteById" value="${mvc.uri('formDeleteClienteById', {'id': cliente.id, 'locale': mvc.locale})}" />

            <tr>
                <td><a href="${uriCliente}">${cliente.nombre}</a></td>
                <td>${cliente.email}</td>
                <td>${cliente.telefono}</td>
                <td>
                    <a href="${uriUpdateClienteById}" class="link-primary"><fmt:message bundle="${messages}" key="action.update" /></a>
                    <a href="${uriDeleteClienteById}" class="link-danger"><fmt:message bundle="${messages}" key="action.delete" /></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="mt-4">
        <a href="/web-1.0.0" class="btn btn-secondary">
            ← Volver al inicio
        </a>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
