<%-- 
    Document   : Registro_acompa
    Created on : 12/05/2020, 02:31:56 AM
    Author     : oskardashh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acompaniante</title>
    </head>
    <body>
    <jsp:include page="navbar.jsp" />
        <div class="container container-login col-lg-3">

        <h1>Registro Acompañante: </h1>
        
        <form method="POST" action="<c:out value="/AcompanianteController"/>">
        
            <div class="login100-form">
                <div class="form-group">
                <label>DNI: </label>
                <input type="text" class="form-control" name="DNI" placeholder="Ingrese su DNI">
                </div>
                
                <div class="form-group">
                <label>NOMBRE:</label>
                <input type="text" class="form-control" name="NOMBREACOMP" placeholder="Ingrese su Nombre Completo">
                </div>
                
                <div class="form-group">
                <label>EXTRANJERO:</label>
                <input type="checkbox" class="form-control" name="EXTRANJERO">

                </div>
                
                <div class="form-group">
                <label>CORREO:</label>
                <input type="email" class="form-control" name="CORREO">
                </div>
                
                <div class="form-group">
                <label>TELEFONO:</label>
                <input type="text" class="form-control" name="TELEFONO">
                </div>
                
                <br>
                <input style="width: 160px;" type="submit" name="btnRegistroAcomp" value="Registrar" class="btn btn-primary btn-block"/>
                <!--<button name="btnRegistroAcomp" class="btn btn-block">Guardar</button>-->
                <br>
                
                
            </div>
                
        </form>
        </div>
            
    </body>
</html>
