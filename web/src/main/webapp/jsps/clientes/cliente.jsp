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

<fmt:setLocale value="${mvc.locale}"/>
<fmt:setBundle basename="messages" var="messages"/>

<c:choose>
    <c:when test="${action == 'VIEW'}">
        <c:set var="readonly" value="readonly"/>
        <c:set var="labelSubmit" value=""/>
        <c:set var="urlForm" value="" />
        <c:set var="method" value="" />
    </c:when>
    <c:when test="${action == 'UPDATE'}">
        <c:set var="readonly" value=""/>
        <fmt:message bundle="${messages}" key="label.submit.update" var="labelSubmit"/>
        <c:set var="urlForm" value="${mvc.uri('updateClienteById', {'id': cliente.id, 'locale': mvc.locale})}" />
        <c:set var="method" value="PUT" />
    </c:when>
    <c:when test="${action == 'INSERT'}">
        <c:set var="readonly" value=""/>
        <fmt:message bundle="${messages}" key="label.submit.insert" var="labelSubmit"/>
        <c:set var="urlForm" value="${mvc.uri('insertCliente', {'locale': mvc.locale})}" />
        <c:set var="method" value="POST" />
    </c:when>
    <c:otherwise>
        <c:set var="readonly" value="readonly"/>
        <fmt:message bundle="${messages}" key="label.submit.delete" var="labelSubmit"/>
        <c:set var="urlForm" value="${mvc.uri('deleteClienteById', {'id': cliente.id, 'locale': mvc.locale})}" />
        <c:set var="method" value="DELETE" />
    </c:otherwise>
</c:choose>

<html>
<head>
    <title><fmt:message bundle="${messages}" key="title.cliente"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<c:if test="${not empty errores}">
    <ul class="alert alert-danger">
        <c:forEach var="entry" items="${errores}">
            <li><strong>${entry.key}:</strong> <c:forEach var="msg" items="${entry.value}">${msg}<br/></c:forEach></li>
        </c:forEach>
    </ul>
</c:if>

<div class="container mt-3">
    <form method="POST" action="${urlForm}" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="_method" value="${method}" />
        <fieldset>
            <div class="mb-3">
                <label for="id" class="form-label"><fmt:message bundle="${messages}" key="label.id"/></label>
                <input type="text" class="form-control" id="id" name="id" value="${cliente.id}" ${action == 'INSERT' ? '' : 'readonly'} />
            </div>
            <div class="mb-3">
                <label for="nombre" class="form-label"><fmt:message bundle="${messages}" key="label.nombre"/></label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}" ${readonly} />
            </div>
            <div class="mb-3">
                <label for="email" class="form-label"><fmt:message bundle="${messages}" key="label.email"/></label>
                <input type="email" class="form-control" id="email" name="email" value="${cliente.email}" ${readonly} />
            </div>
            <div class="mb-3">
                <label for="telefono" class="form-label"><fmt:message bundle="${messages}" key="label.telefono"/></label>
                <input type="text" class="form-control" id="telefono" name="telefono" value="${cliente.telefono}" ${readonly} />
            </div>

            <c:if test="${action != 'VIEW'}">
                <button type="submit" class="btn btn-primary">${labelSubmit}</button>
            </c:if>
            <a href="${mvc.uri('findAllClientes', {'locale': mvc.locale})}" class="btn btn-secondary ms-2"><fmt:message bundle="${messages}" key="action.back"/></a>
        </fieldset>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

