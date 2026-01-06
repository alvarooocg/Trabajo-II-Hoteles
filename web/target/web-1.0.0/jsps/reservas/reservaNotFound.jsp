<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alumnoad
  Date: 17/5/25
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${mvc.locale}" />
<fmt:setBundle basename="messages" var="messages" />

<html>
<head>
  <title><fmt:message bundle="${messages}" key="title.reserva.notfound" /></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6 text-center">
      <div class="card border-warning">
        <div class="card-body">
          <i class="bi bi-exclamation-triangle text-warning" style="font-size: 3rem;"></i>
          <h1 class="card-title mt-3"><fmt:message bundle="${messages}" key="error.reserva.notfound.title" /></h1>
          <p class="card-text text-muted">
            <fmt:message bundle="${messages}" key="error.reserva.notfound.message" />
          </p>
          <a href="${mvc.uri('findAllReservas', {'locale': mvc.locale})}" class="btn btn-primary mt-3">
            <fmt:message bundle="${messages}" key="action.back.reservas" />
          </a>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>