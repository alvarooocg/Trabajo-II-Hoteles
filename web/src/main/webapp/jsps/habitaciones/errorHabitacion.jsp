<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alumnoad
  Date: 22/5/25
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${mvc.locale}" />
<fmt:setBundle basename="messages" var="messages" />

<html>
<head>
  <title><fmt:message bundle="${messages}" key="title.habitacion.error" /></title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-8">
      <div class="card border-danger">
        <div class="card-body">
          <div class="text-center mb-4">
            <i class="bi bi-x-circle text-danger" style="font-size: 3rem;"></i>
            <h1 class="card-title mt-3 text-danger">
              <fmt:message bundle="${messages}" key="error.habitacion.title" />
            </h1>
          </div>

          <div class="alert alert-danger" role="alert">
            <h5 class="alert-heading">
              <fmt:message bundle="${messages}" key="error.details" />
            </h5>
            <p class="mb-0">${errorMessage}</p>
          </div>

          <div class="text-center">
            <a href="${mvc.uri('findAllHabitaciones', {'locale': mvc.locale})}" class="btn btn-primary mt-3">
              <fmt:message bundle="${messages}" key="action.back.habitaciones" />
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>