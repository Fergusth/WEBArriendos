<%-- 
    Document   : ListarMultas
    Created on : may 25, 2020, 6:16:12 p.m.
    Author     : Gerald
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Multas</title>
        <style>
            .error{
                color: red;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../Navbar1.jsp"/>
        <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/16.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="breadcrumb-content text-center">
                            <h2 class="page-title">Multas</h2>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item"><a href="<c:url value="/UsuarioController"/>">Inicio</a></li>
                                    <li class="breadcrumb-item">Multas</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br><br><br>
        <div style="padding: 0 24px;">
            <h5 class="error"><c:out value="${error}"/></h5>
            <c:if test="${tieneMultasImpagas == true}">
                <form method="POST" action="<c:url value="/MultaController"/>">
                    <h3>Multas impagas</h3>
                    <table class="table table-bordered" style="margin-bottom: 32px;">
                        <thead>
                        <th>ID Multa</th>
                        <th>Descripción</th>
                        <th>Precio (Pesos)</th>
                        <th>Pagar</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${lstMultasImpagas}" var="multa">
                                <tr>
                                    <td>${multa.ID_MULTA}</td>
                                    <td>${multa.DESCRIPCION}</td>
                                    <td>$${multa.TOTAL_MULTA}</td>
                                    <td>
                                        <input type="checkbox" name="check-${multa.ID_MULTA}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button type="submit" class="btn btn-info" name="btnPago" style="margin-bottom: 32px;">Pagar seleccionadas</button>
                </form>
            </c:if>
            <c:if test="${tieneMultas == true}">
                <h3>Todas mis multas</h3>
                <table class="table table-bordered">
                    <thead>
                    <th>ID Multa</th>
                    <th>Descripción</th>
                    <th>Precio (Pesos)</th>
                    <th>¿Pagado?</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstMultas}" var="multa">
                            <tr>
                                <td>${multa.ID_MULTA}</td>
                                <td>${multa.DESCRIPCION}</td>
                                <td>$${multa.TOTAL_MULTA}</td>
                                <td>${multa.PAGADO}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${tieneMultas == false}">
                <center><h3>No tienes multas en tu historial</h3></center>
                </c:if>
        </div>
    </body>
</html>
