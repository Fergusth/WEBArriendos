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
        <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/16.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="breadcrumb-content text-center">
                            <h2 class="page-title">Pago reserva</h2>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item"><a href="<c:url value="/UsuarioController"/>">Inicio</a></li>
                                    <li class="breadcrumb-item">Mis reservas</li>
                                    <li class="breadcrumb-item">Pago reserva</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br><br><br>
        <div style="display: flex; justify-content: space-around;">
            <div style="padding: 0 24px; margin-bottom: 120px;">
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
                <h5>Total reserva: <c:out value="$${reserva.PRECIO_TOTAL}"/></h5>
                <h5>Anticipo pagado: <c:out value="$${reserva.TOTAL_PAGADO}"/></h5>
                <h5>Total a pagar: <c:out value="$${total_a_pagar}"/></h5>
                <br><br>
                <a class="btn btn-warning" href="${linkPago}">Pagar</a>
            </div>
            <div style="width: 45%; margin-right: 16px;">
                <h3>Departamento/s</h3>
                <br>
                <table class="table table-bordered">
                    <thead>
                    <th>Dirección</th>
                    <th>Precio diario</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${departamentos}" var="depa">
                            <tr>
                                <td>${depa.DIRECCION}</td>
                                <td>$${depa.PRECIO_DIARIO}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${Acompaniantes.size() > 0}">
                    <h3>Acompañantes</h3>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                        <th>Dni</th>
                        <th>Nombre</th>
                        </thead>
                        <tbody>
                            <c:forEach var="acompa" items="${Acompaniantes}">
                                <tr>
                                    <td><c:out value="${acompa.DNI}"/></td>
                                    <td><c:out value="${acompa.NOMBRE_COMPLETO}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br><br>
                </c:if>
                <c:if test="${SExtras.size() > 0}">
                    <h3>Servicios extra</h3>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                        <th>Servicio</th>
                        <th>Precio diario</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${SExtras}" var="serv">
                                <tr>
                                    <td>${serv.NOMBRE_SERVICIO}</td>
                                    <td>$${serv.PRECIO_ACTUAL}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br><br>
                </c:if>
                <c:if test="${STours.size() > 0}">
                    <h3>Servicios tour</h3>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                        <th>Zona</th>
                        <th>Fecha</th>
                        <th>Hora ida</th>
                        <th>Hora regreso</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${STours}" var="serv">    
                                <tr>
                                    <td><c:out value="${serv.ZONA_TOUR}"/></td>
                                    <td><fmt:formatDate     
                                            value="${serv.FECHA}"
                                            pattern="dd/MM/yyy"/></td>
                                    <td><c:out value="${serv.HORA_SALIDA}"/></td>
                                    <td><c:out value="${serv.HORA_LLEGADA}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br><br>
                </c:if>                
            </div>      
        </div>        
    </body>
</html>
