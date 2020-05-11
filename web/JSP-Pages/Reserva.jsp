<%-- 
    Document   : Reserva
    Created on : 11/05/2020, 12:31:36 AM
    Author     : oskardashh
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reserva</title>
    </head>
    <body>
        <div class="container container-login col-lg-3">
            <form action="<c:url value="/ReservaController"/>" method="POST">
                <div>
                    <p><strong>Reservar Departamento(s)</strong></p>
                </div>
                <div>
                    <label>Desde: </label>
                    
                    <label>Hasta: </label>
                    
                </div>
                <div>
                    <label>Ciudad: </label>
                    <select id="CiudadesSeleccionadas" name="ciudadesSelect">
                    </select>
                    
                </div>                
                <input class="btn btn-info btn-block" type="submit" name="btn_cancelar" value="Cancelar">
                <input class="btn btn-info btn-block" type="submit" name="btn_filtrar" value="Filtrar">
                
                   
              
            </form>
            
        </div>
    </body>
    
</html>
