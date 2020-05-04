<%-- 
    Document   : Login
    Created on : 07-04-2020, 22:29:22
    Author     : oskardashh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                background-image: url("Imagenes/body.jpeg");
                background-repeat: no-repeat;
                background-size: cover;
            }
            .error {
                color: firebrick;
            }
        </style>
        <title>Inicio de Sesión</title>
    </head>
    <body>
        <div class="container container-login col-lg-3">
            <form action="<c:url value="/UsuarioController"/>" method="POST">
                <div class="form-group text-center">
                    <img src="<c:url value="Imagenes/logo.png"/>" height="80" width="80"/>
                    <p><strong>¡Bienvenido a Turismo Real!</strong></p>
                    
                </div>
                
                <div class="form-group">
                    <label>DNI: </label>
                    <input class="form-control" type="text" name="txtDNI" placeholder="1.111.111-1">
                </div>
                <div class="form-group">
                    <label>Contraseña: </label>
                    <input type="password" name="txtPassword" placeholder="*************" class="form-control">
                </div>
                 <label class="error"><c:out value="${error}"/></label>
                <input class="btn btn-info btn-block" type="submit" name="btn" value="Ingresar">
            </form>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
   
        
        
    </body>
</html>
