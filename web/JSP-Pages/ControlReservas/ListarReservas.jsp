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
        <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/16.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="breadcrumb-content text-center">
                            <h2 class="page-title">Mis reservas</h2>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item"><a href="<c:url value="/UsuarioController"/>">Inicio</a></li>
                                    <li class="breadcrumb-item">Mis reservas</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br><br><br>
        <div style="padding: 0 24px;">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Reservas a pagar</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Reservas posteriores</a>
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
                            <th>Precio pagado</th>
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
                                    <td>$${reserva.PRECIO_TOTAL}</td>
                                    <td>$${reserva.TOTAL_PAGADO}</td>
                                    <td>
                                        <form method="POST" action="<c:url value="/ControlReservasController"/>">
                                            <input type="hidden" value="${reserva.ID_RESERVA}" name="resApagarID"/>
                                            <button style="width: 100%;" type="submit" name="btnPagar" class="btn btn-warning">Pagar</button>
                                        </form>
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
                            <th>Precio pagado</th>
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
                                    <td>$${reserva.PRECIO_TOTAL}</td>
                                    <td>$${reserva.TOTAL_PAGADO}</td>
                                    <td style="width: 200px;">
                                        <div>
                                            <form method="POST" action="<c:url value="/ControlReservasController"/>">
                                                <input type="hidden" value="${reserva.ID_RESERVA}" name="reserva_id"/>
                                                <button style="width: 100%; margin-bottom: 8px;" type="submit" name="btnAcompanantes" class="btn btn-info">Acompañantes</button>
                                                <button style="width: 100%; margin-bottom: 8px;" type="submit" name="btnServicios" class="btn btn-success">Servicios extra</button>
                                                
                                                <!-- Button trigger modal -->
                                                <button style="width: 100%;" type="button" data-toggle="modal" data-target="#modal-${reserva.ID_RESERVA}" class="btn btn-danger">Cancelar reserva</button>

                                                <!-- Modal -->
                                                <div class="modal fade" id="modal-${reserva.ID_RESERVA}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                  <div class="modal-dialog">
                                                    <div class="modal-content">
                                                      <div class="modal-header">
                                                        <h5 class="modal-title" id="modal-${reserva.ID_RESERVA}">Cancelar reserva</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                          <span aria-hidden="true">&times;</span>
                                                        </button>
                                                      </div>
                                                      <div class="modal-body">
                                                        <center>
                                                            <h3>¿Seguro que desea cancelar la reserva ID: <strong>${reserva.ID_RESERVA}</strong>?</h3>
                                                        </center>
                                                      </div>
                                                      <div class="modal-footer">
                                                        <button style="width: 100px;" type="submit" name="siCancelar" class="btn btn-info">Sí</button>
                                                        <button style="width: 100px;" type="button" name="noCancelar" class="btn btn-danger" data-dismiss="modal">No</button>
                                                       
                                                      </div>
                                                    </div>
                                                  </div>
                                                </div>
                                            </form>
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
                            <th>Precio pagado</th>
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
                                    <td>$${reserva.PRECIO_TOTAL}</td>
                                    <td>$${reserva.TOTAL_PAGADO}</td>
                                    <td style="width: 200px;">
                                        <form method="POST" action="<c:url value="/ControlReservasController"/>">
                                                <input type="hidden" value="${reserva.ID_RESERVA}" name="reserva_id"/>
                                                <button style="width: 100%; margin-bottom: 8px;" type="submit" name="btnServicios" class="btn btn-success">Servicios extra</button>
                                        </form>                                        
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
                                    <td>$${reserva.PRECIO_TOTAL}</td>
                                    <td>$${reserva.TOTAL_PAGADO}</td>
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
