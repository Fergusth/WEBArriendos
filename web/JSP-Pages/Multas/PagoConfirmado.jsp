<%-- 
    Document   : PagoConfirmado
    Created on : may 25, 2020, 9:52:42 p.m.
    Author     : Gerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pago Exitoso</title>
    </head>
    <body style="text-align: center">
        <jsp:include page="../Navbar1.jsp"/>
        <br><br><br>
        <h3>¡El pago fue exitoso!</h3>
        <img style="height: 100px; width: 100px" src="https://i.ya-webdesign.com/images/check-green-png-4.png"/>
        <br><br>
        <a href="<c:url value="/MultaController"/>">Volver</a>
    </body>
</html>
