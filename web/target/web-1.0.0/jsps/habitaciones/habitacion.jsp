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
    <c:set var="urlForm" value="${mvc.uri('updateHabitacionById', {'id': habitacion.id, 'locale': mvc.locale})}" />
    <c:set var="method" value="PUT" />
  </c:when>
  <c:when test="${action == 'INSERT'}">
    <c:set var="readonly" value=""/>
    <fmt:message bundle="${messages}" key="label.submit.insert" var="labelSubmit"/>
    <c:set var="urlForm" value="${mvc.uri('insertHabitacion', {'locale': mvc.locale}) }" />
    <c:set var="method" value="POST" />
  </c:when>
  <c:otherwise>
    <c:set var="readonly" value="readonly"/>
    <fmt:message bundle="${messages}" key="label.submit.delete" var="labelSubmit"/>
    <c:set var="urlForm" value="${mvc.uri('deleteHabitacionById', {'id': habitacion.id, 'locale': mvc.locale})}" />
    <c:set var="method" value="DELETE" />
  </c:otherwise>
</c:choose>

<html>
<head>
  <title><fmt:message bundle="${messages}" key="title.habitacion"/></title>
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
          <c:if test="${action != 'INSERT'}">
            <div class="mb-3">
              <label for="id" class="form-label"><fmt:message bundle="${messages}" key="label.habitacion.id"/></label>
              <input type="text" class="form-control" id="id" name="id" value="${habitacion.id}" readonly>
            </div>
          </c:if>

          <div class="mb-3">
            <label for="id_hotel" class="form-label"><fmt:message bundle="${messages}" key="label.habitacion.hotel"/></label>
            <input type="text" class="form-control" id="id_hotel" name="id_hotel" value="${habitacion.id_hotel}" ${readonly}>
            <c:if test="${not empty errores['id_hotel']}">
              <div class="form-text text-danger">
                <ul>
                  <c:forEach var="msg" items="${errores['id_hotel']}">
                    <li>${msg}</li>
                  </c:forEach>
                </ul>
              </div>
            </c:if>
          </div>

          <div class="mb-3">
            <label for="numero" class="form-label"><fmt:message bundle="${messages}" key="label.habitacion.numero"/></label>
            <input type="text" class="form-control" id="numero" name="numero" value="${habitacion.numero}" ${readonly}>
            <c:if test="${not empty errores['numero']}">
              <div class="form-text text-danger">
                <ul>
                  <c:forEach var="msg" items="${errores['numero']}">
                    <li>${msg}</li>
                  </c:forEach>
                </ul>
              </div>
            </c:if>
          </div>

          <div class="mb-3">
            <label for="tipo" class="form-label"><fmt:message bundle="${messages}" key="label.habitacion.tipo"/></label>
            <c:choose>
              <c:when test="${readonly == 'readonly'}">
                <input type="text" class="form-control" id="tipo" name="tipo" value="${habitacion.tipo}" readonly>
              </c:when>
              <c:otherwise>
                <select class="form-select" id="tipo" name="tipo">
                  <option value="">Seleccione un tipo</option>
                  <option value="INDIVIDUAL" ${habitacion.tipo == 'INDIVIDUAL' ? 'selected' : ''}>Individual</option>
                  <option value="DOBLE" ${habitacion.tipo == 'DOBLE' ? 'selected' : ''}>Doble</option>
                  <option value="SUITE" ${habitacion.tipo == 'SUITE' ? 'selected' : ''}>Suite</option>
                  <option value="FAMILIAR" ${habitacion.tipo == 'FAMILIAR' ? 'selected' : ''}>Familiar</option>
                </select>
              </c:otherwise>
            </c:choose>
            <c:if test="${not empty errores['tipo']}">
              <div class="form-text text-danger">
                <ul>
                  <c:forEach var="msg" items="${errores['tipo']}">
                    <li>${msg}</li>
                  </c:forEach>
                </ul>
              </div>
            </c:if>
          </div>

          <div class="mb-3">
            <label for="precio" class="form-label"><fmt:message bundle="${messages}" key="label.habitacion.precio"/></label>
            <div class="input-group">
              <input type="number" step="0.01" min="0" class="form-control" id="precio" name="precio" value="${habitacion.precio}" ${readonly}>
              <span class="input-group-text">€</span>
            </div>
            <c:if test="${not empty errores['precio']}">
              <div class="form-text text-danger">
                <ul>
                  <c:forEach var="msg" items="${errores['precio']}">
                    <li>${msg}</li>
                  </c:forEach>
                </ul>
              </div>
            </c:if>
          </div>

          <div class="mb-3">
            <label class="form-label"><fmt:message bundle="${messages}" key="label.habitacion.disponible"/></label>
            <c:choose>
              <c:when test="${readonly == 'readonly'}">
                <div class="form-control-plaintext">
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
                </div>
              </c:when>
              <c:otherwise>
                <div class="form-check form-switch">
                  <input class="form-check-input" type="checkbox" id="disponible" name="disponible" value="true" ${habitacion.disponible ? 'checked' : ''}>
                  <label class="form-check-label" for="disponible">
                    <fmt:message bundle="${messages}" key="label.habitacion.disponible.check" />
                  </label>
                </div>
              </c:otherwise>
            </c:choose>
            <c:if test="${not empty errores['disponible']}">
              <div class="form-text text-danger">
                <ul>
                  <c:forEach var="msg" items="${errores['disponible']}">
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
            <a href="${mvc.uri('findAllHabitaciones', {'locale': mvc.locale})}" class="btn btn-secondary">
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