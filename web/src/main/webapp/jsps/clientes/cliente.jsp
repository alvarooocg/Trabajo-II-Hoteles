<%--
  Created by IntelliJ IDEA.
  User: alumnoad
  Date: 17/5/25
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${mvc.locale}" />
<fmt:setBundle basename="messages" var="messages" />

<c:choose>
    <c:when test="${action == 'VIEW'}">
        <c:set var="readonly" value="readonly"/>
        <c:set var="labelSubmit" value="" />
        <c:set var="urlForm" value="" />
        <c:set var="method" value="" />
    </c:when>
    <c:otherwise>
        <c:set var="readonly" value="readonly"/>
        <c:set var="labelSubmit" value="" />
        <c:set var="urlForm" value="" />
        <c:set var="method" value="" />
    </c:otherwise>
</c:choose>

<html>
<head>
    <title><fmt:message key="title.cliente" bundle="${messages}" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2><fmt:message key="label.detalles.cliente" bundle="${messages}" /></h2>

    <form method="POST" action="${urlForm}">
        <input type="hidden" name="_method" value="${method}" />

        <div class="mb-3">
            <label class="form-label" for="id"><fmt:message key="label.id" bundle="${messages}" /></label>
            <input type="text" class="form-control" id="id" name="id" value="${cliente.id}" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label" for="nombre"><fmt:message key="label.nombre" bundle="${messages}" /></label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}" ${readonly}>
        </div>

        <div class="mb-3">
            <label class="form-label" for="email"><fmt:message key="label.email" bundle="${messages}" /></label>
            <input type="email" class="form-control" id="email" name="email" value="${cliente.email}" ${readonly}>
        </div>

        <div class="mb-3">
            <label class="form-label" for="telefono"><fmt:message key="label.telefono" bundle="${messages}" /></label>
            <input type="text" class="form-control" id="telefono" name="telefono" value="${cliente.telefono}" ${readonly}>
        </div>

        <c:if test="${action != 'VIEW'}">
            <input type="submit" class="btn btn-primary" value="${labelSubmit}" />
        </c:if>

        <div class="mt-4">
            <a class="btn btn-secondary" href="${mvc.uri('findAllClientes', {'locale': mvc.locale})}">
                <fmt:message bundle="${messages}" key="action.backToClientes" />
            </a>
        </div>

    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

