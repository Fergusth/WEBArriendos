<%-- 
    Document   : DetalleReserva
    Created on : may 30, 2020, 12:19:16 p.m.
    Author     : Gerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle reserva</title>
    </head>
    <body>
        <jsp:include page="Navbar1.jsp"/>
        <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/16.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="breadcrumb-content text-center">
                            <h2 class="page-title">Detalle reserva</h2>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item"><a href="<c:url value="/UsuarioController"/>">Inicio</a></li>
                                    <li class="breadcrumb-item">Departamentos</li>
                                    <li class="breadcrumb-item">Registro de acompañantes</li>
                                    <li class="breadcrumb-item">Registro de servicios</li>
                                    <li class="breadcrumb-item">Detalle reserva</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br><br><br>
        <div style="display: flex; justify-content: space-around;">
            <div style="width: 45%; margin-left: 16px;">
                <h3>Detalle de la reserva</h3>
                <br>
                <table class="table">
                    <tbody>
                        <tr>
                            <td>Precio total departamentos:</td>
                            <td><strong>$${TotalDepartamentos}</strong></td>
                        </tr>
                        <c:if test="${SExtras.size() > 0}">
                            <tr>
                                <td>Precio total servicios extra:</td>
                                <td><strong>$${TotalSExtras}</strong></td>
                            </tr>
                        </c:if>
                        <c:if test="${STours.size() > 0}">
                            <tr>
                                <td>Precio total servicios de tour:</td>
                                <td><strong>$${TotalSTour}</strong></td>
                            </tr>
                        </c:if>
                        <tr>
                            <td>Precio total: </td>
                            <td><strong>$${TotalReserva}</strong></td>
                        </tr>
                        <tr>
                            <td>Porcentaje anticipo a pagar: </td>
                            <td><strong>${PorcentAnticipo}% Del total de los departamentos</strong></td>
                        </tr>
                        <tr>
                            <td>Anticipo a pagar: </td>
                            <td><strong>$${TotalAnticipo}</strong></td>
                        </tr>
                    </tbody>
                </table>
                <form method="POST" action="<c:url value="/ReservaController"/>">
                    <a class="btn btn-warning" href="${LinkPago}">Pagar</a>
                    <input type="hidden" name="ReservaID" value="${ReservaID}"/>
                    <button type="submit" name="CancelarRes" class="btn btn-danger" style="margin-right: 32px;">Cancelar</button>
                </form>
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
