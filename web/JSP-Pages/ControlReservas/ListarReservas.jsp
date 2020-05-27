<%-- 
    Document   : ListarReservas
    Created on : may 26, 2020, 5:41:07 p.m.
    Author     : Gerald
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis reservas</title>
    </head>
    <body>
        <jsp:include page="../Navbar1.jsp"/>
        <br><br><br>
        <div style="padding: 0 24px;">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Reservas a pagar</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Reservas futuras</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#activa" role="tab" aria-controls="activa" aria-selected="false">Reservas activas</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">Todas mis reservas</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <br><br>
                    <table class="table table-bordered" style="margin-bottom: 32px;">
                        <thead>
                            <th>ID</th>
                            <th>Check-in</th>
                            <th>Check-out</th>
                            <th>Precio total</th>
                            <th>Opciones</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${lstReservasAPagar}" var="reserva">
                                <tr>
                                    <td>${reserva.ID_RESERVA}</td>
                                    <td>
                                        <fmt:formatDate     
                                        value="${reserva.FECHA_CHECKIN}"
                                        pattern="dd/MM/yyy"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate     
                                        value="${reserva.FECHA_CHECKOUT}"
                                        pattern="dd/MM/yyy"/>
                                    </td>
                                    <td>${reserva.PRECIO_TOTAL}</td>
                                    <td>
                                        <button style="width: 100%;" type="button" class="btn btn-warning">Pagar</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <br><br>
                    <table class="table table-bordered" style="margin-bottom: 32px;">
                        <thead>
                            <th>ID</th>
                            <th>Check-in</th>
                            <th>Check-out</th>
                            <th>Precio total</th>
                            <th>Opciones</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${lstReservasFuturas}" var="reserva">
                                <tr>
                                    <td>${reserva.ID_RESERVA}</td>
                                    <td>
                                        <fmt:formatDate     
                                        value="${reserva.FECHA_CHECKIN}"
                                        pattern="dd/MM/yyy"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate     
                                        value="${reserva.FECHA_CHECKOUT}"
                                        pattern="dd/MM/yyy"/>
                                    </td>
                                    <td>${reserva.PRECIO_TOTAL}</td>
                                    <td style="width: 200px;">
                                        <div>
                                            <button style="width: 100%; margin-bottom: 8px;" type="button" class="btn btn-info">Acompañantes</button>
                                            <button style="width: 100%; margin-bottom: 8px;" type="button" class="btn btn-success">Servicios extra</button>
                                            <button style="width: 100%;" type="button" class="btn btn-danger">Cancelar reserva</button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="activa" role="tabpanel" aria-labelledby="activa-tab">
                    <br><br>
                    <table class="table table-bordered" style="margin-bottom: 32px;">
                        <thead>
                            <th>ID</th>
                            <th>Check-in</th>
                            <th>Check-out</th>
                            <th>Precio total</th>
                            <th>Opciones</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${lstReservasActivas}" var="reserva">
                                <tr>
                                    <td>${reserva.ID_RESERVA}</td>
                                    <td>
                                        <fmt:formatDate     
                                        value="${reserva.FECHA_CHECKIN}"
                                        pattern="dd/MM/yyy"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate     
                                        value="${reserva.FECHA_CHECKOUT}"
                                        pattern="dd/MM/yyy"/>
                                    </td>
                                    <td>${reserva.PRECIO_TOTAL}</td>
                                    <td>
                                        <button style="width: 100%; margin-bottom: 8px;" type="button" class="btn btn-success">Servicios extra</button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
                    <br><br>
                    <table class="table table-bordered" style="margin-bottom: 32px;">
                        <thead>
                            <th>ID</th>
                            <th>Check-in</th>
                            <th>Check-out</th>
                            <th>Precio total</th>
                            <th>Precio pagado</th>
                            <th>Estado</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${lstReservasTodas}" var="reserva">
                                <tr>
                                    <td>${reserva.ID_RESERVA}</td>
                                    <td>
                                        <fmt:formatDate     
                                        value="${reserva.FECHA_CHECKIN}"
                                        pattern="dd/MM/yyy"/>
                                    </td>
                                    <td>
                                        <fmt:formatDate     
                                        value="${reserva.FECHA_CHECKOUT}"
                                        pattern="dd/MM/yyy"/>
                                    </td>
                                    <td>${reserva.PRECIO_TOTAL}</td>
                                    <td>${reserva.TOTAL_PAGADO}</td>
                                    <td>${reserva.ESTADO}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
