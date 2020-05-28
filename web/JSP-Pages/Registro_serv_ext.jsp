<%-- 
    Document   : Registro_serv_ext
    Created on : 12/05/2020, 02:32:24 AM
    Author     : oskardashh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        
                <jsp:include page="../Navbar1.jsp"/>

                
                    <form method="POST" action="<c:url value="/ReservaController"/>">
                        <h3>Añadir Servicios Extra:</h3>
                        <table class="table table-bordered" style="margin-bottom: 32px;">
                            <thead>
                            <th>ID Servicio</th>
                            <th>Nombre</th>
                            <th>Precio (Pesos)</th>
                            <th>Añadir</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${ServiceExt}" var="Servi">
                                    <tr>
                                        <td>${Servi.ID}</td>
                                        <td>${Servi.NOMBRE_SERVICIO}</td>
                                        <td>$${Servi.PRECI_ACTUAL}</td>
                                        <td>
                                            <input type="checkbox" name="check-${Servi.ID}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <button type="submit" class="btn btn-info" name="btnServi" style="margin-bottom: 32px;">AÑADIR</button>
                    </form>





        
        
    </body>
</html>
