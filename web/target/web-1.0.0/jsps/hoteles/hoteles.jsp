<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <table class="table table-striped table-hover caption-top">
        <caption> <fmt:message bundle="${messages}" key="title.hoteles" /> </caption>
        <thead class="table-dark">
            <tr>
                <th scope="col"> <fmt:message bundle="${messages}" key </th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
        </thead>
    </table>

</body>
</html>
