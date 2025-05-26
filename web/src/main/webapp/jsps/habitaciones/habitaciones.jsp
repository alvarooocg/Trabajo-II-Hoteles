<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${mvc.locale}" />
<fmt:setBundle basename="messages" var="messages" />

<html>
<head>
  <title><fmt:message bundle="${messages}" key="title.habitaciones" /></title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-4">

  <c:set var="uriInsertHabitacion" value="${mvc.uri('formInsertHabitacion', {'locale': mvc.locale})}" />
  <a href="${uriInsertHabitacion}" class="btn btn-primary mb-3">
    <fmt:message bundle="${messages}" key="label.action.insert.habitacion" />
  </a>

  <table class="table table-striped table-hover caption-top">
    <caption><fmt:message bundle="${messages}" key="title.habitaciones" /></caption>
    <thead class="table-dark">
    <tr>
      <th><fmt:message bundle="${messages}" key="label.habitacion.id" /></th>
      <th><fmt:message bundle="${messages}" key="label.habitacion.hotel" /></th>
      <th><fmt:message bundle="${messages}" key="label.habitacion.numero" /></th>
      <th><fmt:message bundle="${messages}" key="label.habitacion.tipo" /></th>
      <th><fmt:message bundle="${messages}" key="label.habitacion.precio" /></th>
      <th><fmt:message bundle="${messages}" key="label.habitacion.disponible" /></th>
      <th><fmt:message bundle="${messages}" key="label.habitacion.acciones" /></th>
    </tr>
    </thead>
    <tbody class="table-group-divider">
    <c:forEach var="habitacion" items="${habitaciones}">
      <c:set var="uriHabitacion" value="${mvc.uri('findHabitacionById', {'id': habitacion.id, 'locale': mvc.locale})}" />
      <c:set var="uriUpdateHabitacion" value="${mvc.uri('formUpdateHabitacionById', {'id': habitacion.id, 'locale': mvc.locale})}" />
      <c:set var="uriDeleteHabitacion" value="${mvc.uri('formDeleteHabitacionById', {'id': habitacion.id, 'locale': mvc.locale})}" />

      <tr>
        <td><a href="${uriHabitacion}">${habitacion.id}</a></td>
        <td>${habitacion.id_hotel}</td>
        <td>${habitacion.numero}</td>
        <td>
          <span class="badge bg-secondary">${habitacion.tipo}</span>
        </td>
        <td>
          <fmt:formatNumber value="${habitacion.precio}" type="currency" currencySymbol="€" />
        </td>
        <td>
          <c:choose>
            <c:when test="${habitacion.disponible}">
                            <span class="badge bg-success">
                                <fmt:message bundle="${messages}" key="label.disponible" />
                            </span>
            </c:when>
            <c:otherwise>
                            <span class="badge bg-danger">
                                <fmt:message bundle="${messages}" key="label.no.disponible" />
                            </span>
            </c:otherwise>
          </c:choose>
        </td>
        <td>
          <div class="btn-group btn-group-sm" role="group">
            <a href="${uriUpdateHabitacion}" class="btn btn-outline-primary btn-sm">
              <fmt:message bundle="${messages}" key="action.update" />
            </a>
            <a href="${uriDeleteHabitacion}" class="btn btn-outline-danger btn-sm">
              <fmt:message bundle="${messages}" key="action.delete" />
            </a>
          </div>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <!-- Información de debug -->
  <div class="mt-4">
    <div class="card">
      <div class="card-header">
        <h6 class="mb-0">Información de Debug</h6>
      </div>
      <div class="card-body">
        <p class="mb-1">Lista habitaciones es null? <span class="badge bg-info">${habitaciones == null}</span></p>
        <p class="mb-0">Tamaño lista: <span class="badge bg-info">${fn:length(habitaciones)}</span></p>
      </div>
    </div>
  </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>