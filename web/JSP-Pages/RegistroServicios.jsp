<%-- 
    Document   : RegistroServicios
    Created on : may 29, 2020, 9:00:21 p.m.
    Author     : Gerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro servicios</title>
    </head>
    <body>
        <jsp:include page="Navbar1.jsp"/>
        <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/16.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="breadcrumb-content text-center">
                            <h2 class="page-title">Registro de servicios</h2>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item"><a href="<c:url value="/UsuarioController"/>">Inicio</a></li>
                                    <li class="breadcrumb-item">Departamentos</li>
                                    <li class="breadcrumb-item">Registro de acompañantes</li>
                                    <li class="breadcrumb-item">Registro de servicios</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br><br><br>
        <h6 style="color: red; margin-left: 16px;"><c:out value="${error}"/></h6>
        <div style="display: flex; justify-content: space-between;">
            <div style="width: 45%">
                <h3 style="margin-left: 16px;">Servicios extra</h3>
                <form method="POST" action="<c:url value="/ReservaController"/>">
                    <div class="input-group col-md-6">                
                        <select name="selectServE" id="Servicios" class="form-control" style="width: 80%">
                            <option value="NONE">Seleccione</option>
                            <c:forEach var="Servi" items="${servicios}">
                                <option value="${Servi.ID}">${Servi.NOMBRE_SERVICIO} - $${Servi.PRECIO_ACTUAL}/día</option>
                            </c:forEach>
                        </select>                    
                    </div>
                    <br>
                    <div class="input-group col-md-6">
                        <button type="submit" name="btnContratarSE" class="btn btn-info" style="margin-right: 32px;">Contratar</button>
                    </div>
                </form>
                <br>
                <table class="table" style="margin-left: 16px;">
                    <thead>
                    <th>Servicio</th>
                    <th>Precio</th>
                    <th>Opciones</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${EContratados}" var="serv">    
                            <tr>
                                <td><c:out value="${serv.NOMBRE_SERVICIO}"/></td>
                                <td><c:out value="$${serv.PRECIO_ACTUAL}/día"/></td>
                                <td>
                                    <form method="POST" action="<c:url value="/ReservaController"/>">
                                        <input type="hidden" value="${serv.ID}" name="servId"/>
                                        <button type="submit" name="quitarSE" class="btn btn-danger" style="margin-right: 32px;">Quitar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div style="width: 45%; margin-bottom: 120px">
                <h3 style="margin-left: 16px;">Servicios de tour</h3>
                <form method="POST" action="<c:url value="/ReservaController"/>">
                    <div class="input-group col-md-6">                
                        <select name="selectServT" id="tours" class="form-control" style="width: 80%">
                            <option value="NONE">Seleccione</option>
                            <c:forEach var="Servi" items="${tours}">
                                <option value="${Servi.ID_TOUR}">${Servi.ZONA_TOUR} - <fmt:formatDate     
                                        value="${Servi.FECHA}"
                                        pattern="dd/MM/yyy"/> - $${Servi.PRECIO_ACTUAL}</option>
                                </c:forEach>
                        </select>
                    </div>
                    <br>
                    <div class="input-group col-md-6">
                        <button type="submit" name="btnContratarST" class="btn btn-info" style="margin-right: 32px;">Contratar</button>
                    </div>
                </form>
                <br>
                <table class="table" style="margin-right: 16px;">
                    <thead>
                    <th>Zona</th>
                    <th>Fecha</th>
                    <th>Hora ida</th>
                    <th>Hora regreso</th>
                    <th>Opciones</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${TContratados}" var="serv">    
                            <tr>
                                <td><c:out value="${serv.ZONA_TOUR}"/></td>
                                <td><fmt:formatDate     
                                        value="${serv.FECHA}"
                                        pattern="dd/MM/yyy"/></td>
                                <td><c:out value="${serv.HORA_SALIDA}"/></td>
                                <td><c:out value="${serv.HORA_LLEGADA}"/></td>
                                <td>
                                    <form method="POST" action="<c:url value="/ReservaController"/>">
                                        <input type="hidden" name="servId" value="${serv.ID_TOUR}"/>
                                        <button type="submit" name="quitarST" class="btn btn-danger" style="margin-right: 32px;">Quitar</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
            </div>
                    
            <form method="POST" action="<c:url value="/ReservaController"/>">
                <input style="width: 160px; position: fixed; bottom: 32px; right: 32px;" type="submit" name="btnPaso4" value="Paso final" class="btn btn-warning"/>
            </form>
        </div>
    </body>
</html>
