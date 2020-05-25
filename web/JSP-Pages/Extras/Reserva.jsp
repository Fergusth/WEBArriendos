<%-- 
    Document   : Reserva
    Created on : 11/05/2020, 12:31:36 AM
    Author     : oskardashh
--%>


<%@page import="DTO.CiudadDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reserva</title>

    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <div class="container container-login col-lg-3">
            <form method="post" action="<c:url value="/ReservaController"/>">
                <div>
                    <p><strong>Reservar Departamento(s)</strong></p>
                </div>
                <div>
                    <label>Desde: </label>
                    <input value="${fechaDesde}" name="fechaDesde" type="date" pattern="dd/MM/yyyy" id="checkin_date" class="form-control">

                </div>
                <div>
                    <label>Hasta: </label>
                    <input value="${fechaHasta}" name="fechaHasta" pattern="dd/MM/yyyy" type="date" id="checkout_date" class="form-control">
                </div>
                <div>
                    <label for="ciudax">Ciudad: </label>
                    <select name="ciudax">
                        <c:forEach items="${ciudadex}" var="ciudax">
                            <option value="${ciudax.ID}"><c:out value="${ciudax.NOMBRE_CIUDAD}" /></option>
                            <!--ciudax.NOMBRE_CIUDAD-->
                        </c:forEach>
                    </select>

                </div>                
                <!-- <input class="btn btn-info btn-block" type="submit" name="btn_cancelar" value="Cancelar">-->
                <input class="btn btn-info btn-block" type="submit" name="btn_filtrar" value="Filtrar">

                <!-- Bloquear Lista, activar al momento de Filtrar -->
                <h1>Listado de Departamentos: </h1>
                <div class="row">
                    <div class="col-md-12">

                        <c:forEach var="depart" items="${departamentos}">
                            <div class="item">
                                <div class="text">
                                    <h2 class="Dirección">${depart.DIRECCION}</h2>
                                    <div class="Precio"><sup>$</sup><span class="number">${depart.PRECIO_DIARIO}</span><sub>Por día</sub></div>
                                    <ul class="specs">
                                        <li><strong>Cantidad de Baños: </strong>${depart.CANT_BANIOS}</li>
                                        <li><strong>Cantidad de Habitaciones: </strong>${depart.CANT_DORMITORIOS}</li>
                                        <li><strong>Descripción: </strong>${depart.DESCRIPCION}</li>
                                        <li><strong>Internet: </strong>${depart.INTERNET}</li>
                                        <li><strong>Calefacción: </strong>${depart.CALEFACCION}</li>

                                    </ul>
                                </div>
                            </div>
                            <form method="post" action="<c:url value="/ReservaController"/>">
                                <input type="hidden" name="idDepartamento" value="${depart.ID}"/>
                                <input type="hidden" name="precioDiario" value="${depart.PRECIO_DIARIO}"/>
                                <input type="hidden" name="cantBanios" value="${depart.CANT_BANIOS}"/>
                                <input type="hidden" name="cantHabit" value="${depart.CANT_DORMITORIOS}"/>
                                <input type="hidden" name="descripcion" value="${depart.DESCRIPCION}"/>
                                <input type="hidden" name="internet" value="${depart.INTERNET}"/>
                                <input type="hidden" name="calefaccion" value="${depart.CALEFACCION}"/>
                                <input value="${fechaDesde}" name="fechaDesde" type="hidden" pattern="dd/MM/yyyy" id="checkin_date" class="form-control">
                                <input value="${fechaHasta}" name="fechaHasta" type="hidden" pattern="dd/MM/yyyy" id="checkin_date" class="form-control">
                                <!--<input style="width: 160px;" type="submit" name="reservaDepa" value="Reservar" class="btn btn-primary btn-block"/>
                                -->
                            </form>

                        </c:forEach>
                                <input style="width: 160px;" type="submit" name="reservaDepa" value="Reservar" class="btn btn-primary btn-block"/>

                    </div>



                    </body>



                    </html>
