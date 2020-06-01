<%-- 
    Document   : ServiciosExtra
    Created on : may 27, 2020, 7:00:57 p.m.
    Author     : Gerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servicios extra</title>
    </head>
    <body>
        <jsp:include page="../Navbar1.jsp"/>
        <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/16.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="breadcrumb-content text-center">
                            <h2 class="page-title">Servicios extra</h2>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item"><a href="<c:url value="/UsuarioController"/>">Inicio</a></li>
                                    <li class="breadcrumb-item">Mis reservas</li>
                                    <li class="breadcrumb-item">Servicios extra</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br><br><br>
        <form method="POST" action="<c:url value="/ControlReservasController"/>">
            <input type="hidden" value="${reserva_id}" name="reserva_id"/>
            <label style="color: red; margin-left: 16px;"><c:out value="${error}"/></label>
            <div style="display: flex; padding: 0 16px; justify-content: space-between;">
                <div style="width: 40%">
                    <h2>Servicios extra para contratar</h2>
                    <br>

                    <select name="selectServ" id="Servicios" class="form-control">
                        <option value="NONE">Seleccione</option>
                        <c:forEach var="Servi" items="${servicios}">
                            <option value="${Servi.ID}">${Servi.NOMBRE_SERVICIO} - $${Servi.PRECIO_ACTUAL} /Día</option>
                        </c:forEach>
                    </select>
                    <br>
                    <center>
                        <button type="submit" name="btnContratarServicio" class="btn btn-info">Contratar servicio</button>
                    </center>
                </div>
                <div style="width: 40%">
                    <h2>Servicios extra contratados</h2>
                    <br>
                    <c:if test="${serviciosReserva.size() > 0}">
                        <table class="table table-bordered">
                            <thead>
                            <th>Servicio</th>
                            <th>Precio</th>
                            <th>Opciones</th>
                            </thead>
                            <tbody>
                                <c:forEach var="Servi" items="${serviciosReserva}">
                                    <tr>
                                        <td><c:out value="${Servi.NOMBRE_SERVICIO}"/></td>
                                        <td><c:out value="$${Servi.PRECIO_ACTUAL}"/></td>
                                        <form method="POST" action="<c:url value="/ControlReservasController"/>">
                                            <input type="hidden" value="${Servi.ID}" name="serv_id"/>
                                            <input type="hidden" value="${reserva_id}" name="reserva_id"/>
                                            <td><button style="width: 100%;" type="submit" name="btnQuitarServicio" class="btn btn-danger">Quitar</button></td>
                                        </form>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${serviciosReserva.size() ==  0}">
                        <h4>No tienes servicios contratados</h4>
                    </c:if>
                </div>
            </div>
        </form>

    </body>

</html>
