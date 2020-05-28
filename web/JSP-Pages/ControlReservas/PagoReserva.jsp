<%-- 
    Document   : PagoReserva
    Created on : may 27, 2020, 3:47:07 p.m.
    Author     : Gerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagar reserva</title>
    </head>
    <body style="text-align: center;">
        <jsp:include page="../Navbar1.jsp"/>
        <br><br><br>
        <div style="padding: 0 24px;">
            <h3>Pago de reserva</h3>
            <div>
                <strong><label>ID de la reserva: </label></strong>
                <label><c:out value="${reserva.ID_RESERVA}"/></label>
            </div>
            <div>
                <strong><label>Check-in: </label></strong>
                <label><fmt:formatDate value="${reserva.FECHA_CHECKIN}" pattern="dd/MM/yyy"/></label>
            </div>
            <div>
                <strong><label>Check-out: </label></strong>
                <label><fmt:formatDate value="${reserva.FECHA_CHECKOUT}" pattern="dd/MM/yyy"/></label>
            </div>
            <br><br>
            <h5>Total: <c:out value="$${reserva.PRECIO_TOTAL}"/></h5>
            <br><br>
            <a class="btn btn-warning" href="${linkPago}">Pagar</a>
        </div>
    </body>
</html>
