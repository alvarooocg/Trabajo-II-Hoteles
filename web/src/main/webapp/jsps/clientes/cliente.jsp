<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alumnoad
  Date: 17/5/25
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <form method="POST" action="${urlForm}" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="_method" value="${method}" />

        <fieldset>
            <div class="row">
                <div class="col-12 col-md-8 offset-md-2">

                    <div class="mb-3">
                        <label for="nombre" class="form-label"><fmt:message bundle="${messages}" key="label.cliente.nombre"/></label>
                        <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente.nombre}" ${readonly}>
                        <c:if test="${not empty errores['nombre']}">
                            <div class="form-text text-danger">
                                <ul>
                                    <c:forEach var="msg" items="${errores['nombre']}">
                                        <li>${msg}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label"><fmt:message bundle="${messages}" key="label.cliente.email"/></label>
                        <input type="email" class="form-control" id="email" name="email" value="${cliente.email}" ${readonly}>
                        <c:if test="${not empty errores['email']}">
                            <div class="form-text text-danger">
                                <ul>
                                    <c:forEach var="msg" items="${errores['email']}">
                                        <li>${msg}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>

                    <div class="mb-3">
                        <label for="telefono" class="form-label"><fmt:message bundle="${messages}" key="label.cliente.telefono"/></label>
                        <input type="text" class="form-control" id="telefono" name="telefono" value="${cliente.telefono}" ${readonly}>
                        <c:if test="${not empty errores['telefono']}">
                            <div class="form-text text-danger">
                                <ul>
                                    <c:forEach var="msg" items="${errores['telefono']}">
                                        <li>${msg}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                    </div>

                    <div class="d-flex justify-content-start gap-3 mt-4">
                        <c:if test="${action != 'VIEW'}">
                            <input type="submit" class="btn btn-primary" value="${labelSubmit}" />
                        </c:if>
                        <a href="${mvc.uri('findAllClientes', {'locale': mvc.locale})}" class="btn btn-secondary">
                            <fmt:message bundle="${messages}" key="action.back"/>
                        </a>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>