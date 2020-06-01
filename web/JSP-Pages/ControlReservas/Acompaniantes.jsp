<%-- 
    Document   : Acompaniantes
    Created on : may 28, 2020, 5:36:39 p.m.
    Author     : Gerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acompañantes reserva</title>
    </head>
    <body>
        <jsp:include page="../Navbar1.jsp"/>
        <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/16.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="breadcrumb-content text-center">
                            <h2 class="page-title">Registro acompañantes</h2>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item"><a href="<c:url value="/UsuarioController"/>">Inicio</a></li>
                                    <li class="breadcrumb-item">Mis reservas</li>
                                    <li class="breadcrumb-item">Registro acompañantes</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br><br><br>
        <div class="container">           
            <h5 style="color: red; margin-left: 16px; margin-bottom: -20px;">${error}</h5>
            <div style="padding: 0 16px; display: flex; justify-content: space-between;">

                <div style="width: 45%;">
                    <br>
                    <br><br>
                    <h3>Registro Acompañante: </h3>
                    <form method="POST" action="<c:url value="/ControlReservasController"/>">
                        <input type="hidden" name="reserva_id" value="${reserva_id}"/>
                        <c:forEach items="${Errores}" var="error">
                            <div>
                                <label style="color: red;">- ${error}</label>
                            </div>
                        </c:forEach>
                        <div class="form-group">
                            <label>Dni: </label>
                            <input type="text" class="form-control" name="DNI" value="${DNI}" placeholder="Ingrese su DNI">
                            <br>
                            <button type="submit" name="btnBuscar" class="btn btn-warning">Buscar</button>
                        </div>

                        <div class="form-group">
                            <label>Nombre:</label>
                            <input type="text" class="form-control" name="NOMBREACOMP" value="${NOMBREACOMP}" placeholder="Ingrese nombre Completo">
                        </div>

                        <div class="form-group">
                            <label>Correo electrónico:</label>
                            <input type="email" class="form-control" name="CORREO" value="${CORREO}" placeholder="Ingrese correo electrónico">
                        </div>

                        <div class="form-group">
                            <label>Teléfono:</label>
                            <input type="number" class="form-control" name="TELEFONO" value="${TELEFONO}" placeholder="Ingrese correo teléfono">
                        </div>

                        <div class="form-group">
                            <input id="checkExt" ${EXTRANJERO == null ? '' : 'checked'} name="EXTRANJERO" type="checkbox"> 
                            <label for="checkExt">Extranjero</label>
                        </div>

                        <br>
                        <input style="width: 160px;" type="submit" name="btnRegistroAcomp" value="Registrar" class="btn btn-info"/>
                        <!--<button name="btnRegistroAcomp" class="btn btn-block">Guardar</button>-->
                        <br>
                        <br>
                        <br>
                    </form>
                </div>
                <div style="width: 45%;">
                    <br>
                    <br><br>
                    <h3>Acompañantes registrados</h3>
                    <table class="table table-bordered">
                        <thead>
                        <th>Dni</th>
                        <th>Nombre</th>
                        <th>Opciones</th>
                        </thead>
                        <tbody>
                            <c:forEach var="acompa" items="${acompaniantes}">
                                <tr>
                                    <td><c:out value="${acompa.DNI}"/></td>
                                    <td><c:out value="${acompa.NOMBRE_COMPLETO}"/></td>
                                    <td>
                                        <div>
                                            <form method="POST" action="<c:url value="/ControlReservasController"/>">                                                
                                                <button style="width: 100%;" type="button" data-toggle="modal" data-target="#modal-${acompa.DNI}" class="btn btn-danger">Quitar</button>
                                                
                                                <div class="modal fade" id="modal-${acompa.DNI}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Quitar acompañante</h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <center>
                                                                    <h3>¿Seguro que desea quitar a: <strong>${acompa.NOMBRE_COMPLETO}</strong>?</h3>
                                                                </center>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button style="width: 100px;" type="submit" name="QuitarAcom" class="btn btn-info">Sí</button>
                                                                <button style="width: 100px;" type="button" class="btn btn-danger" data-dismiss="modal">No</button>
                                                                <input type="hidden" value="${acompa.DNI}" name="dniQuitar"/>
                                                                <input type="hidden" name="reserva_id" value="${reserva_id}"/>
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
            </div>
        </div>
    </body>
</html>
