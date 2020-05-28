<%-- 
    Document   : PagoMultas
    Created on : may 25, 2020, 8:20:14 p.m.
    Author     : Gerald
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagar multas</title>
    </head>
    <body>
        <jsp:include page="../Navbar1.jsp"/>
        <div style="padding: 0 24px;">
            <h3>Multas a pagar</h3>
            <table class="table table-bordered" style="margin-bottom: 32px;">
                <thead>
                    <th>ID Multa</th>
                    <th>Descripción</th>
                    <th>Precio (Pesos)</th>
                </thead>
                <tbody>
                    <c:forEach items="${lstMultasPorPagar}" var="multa">
                        <tr>
                            <td>${multa.ID_MULTA}</td>
                            <td>${multa.DESCRIPCION}</td>
                            <td>$${multa.TOTAL_MULTA}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <div style="display: flex; justify-content: space-between">
                <h4>Total a pagar: $<c:out value="${totalMulta}"/></h4>
                <a class="btn btn-warning" href="${linkPago}">Pagar</a>
            </div>
        </div>
    </body>
</html>
