<%-- 
    Document   : ReservarDepartamento
    Created on : 24/05/2020, 09:30:10 PM
    Author     : maite
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>


        <!-- Favicon -->
        <link rel="icon" href="img/core-img/favicon-32x32.png">

        <!-- Stylesheet -->
        <link rel="stylesheet" href="style.css">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <!-- Stylesheet -->
        <link rel="stylesheet" href="style.css">



    </head>
    <body>

        <!-- Preloader -->
        <div id="preloader">
            <div class="loader"></div>
        </div>
        <!-- /Preloader -->

        <!-- Header Area Start -->
        <header class="header-area">
            <!-- Search Form -->
            <div class="search-form d-flex align-items-center">
                <div class="container">
                    <form action="" method="get">
                        <input type="search" name="search-form-input" id="searchFormInput" placeholder="cual es su pregunta? ...">
                        <button type="submit"><i class="icon_search"></i></button>
                    </form>
                </div>
            </div>

            <!-- Top Header Area Start -->
            <div class="top-header-area">
                <div class="container">
                    <div class="row">

                        <div class="col-6">
                            <div class="top-header-content">
                                <a href="#"><i class="icon_phone"></i> <span>(123) 456-789-1230</span></a>
                                <a href="#"><i class="icon_mail"></i> <span>turismoreal@gmail.com</span></a>
                            </div>
                        </div>

                        <div class="col-6">
                            <div class="top-header-content">
                                <!-- Top Social Area -->
                                <div class="top-social-area ml-auto">
                                    <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                                    <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                                    <a href="#"><i class="fa fa-tripadvisor" aria-hidden="true"></i></a>
                                    <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <!-- Top Header Area End -->

            <!-- Main Header Start -->
            <div class="main-header-area">
                <div class="classy-nav-container breakpoint-off">
                    <div class="container">
                        <!-- Classy Menu -->
                        <nav class="classy-navbar justify-content-between" id="robertoNav">

                            <!-- Logo -->
                            <a class="nav-brand" href="Inicio.jsp"><img src="img/core-img/turismo_logo.png" alt=""></a>
                            <!-- Navbar Toggler -->
                            <div class="classy-navbar-toggler">
                                <span class="navbarToggler"><span></span><span></span><span></span></span>
                            </div>

                            <!-- Menu -->
                            <div class="classy-menu">
                                <!-- Menu Close Button -->
                                <div class="classycloseIcon">
                                    <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                                </div>
                                <!-- Nav Start -->
                                <div class="classynav">
                                    <ul id="nav">
                                        <li class="active"><a href="Inicio.jsp">Inicio</a></li>
                                        <li><a href="<c:url value="ReservaController"/>">Departamentos</a></li>
                                        <li><a href="<c:url value="MultaController"/>">Multas</a></li>
                                        <li><a href="./contact.html">Contacto</a></li>
                                    </ul>

                                    <!-- Search -->
                                    <div class="search-btn ml-4">
                                        <i class="fa fa-search" aria-hidden="true"></i>
                                    </div>

                                    <!-- Book Now -->
                                    <div class="book-now-btn ml-3 ml-lg-5">
                                        <a href="<c:url value="UsuarioController"/>">Cerrar Sesión <i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
                                    </div>
                                </div>
                                <!-- Nav End -->
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </header>
        <!-- Header Area End -->


        <!-- Breadcrumb Area Start -->
        <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/16.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="breadcrumb-content text-center">
                            <h2 class="page-title">Reservar Departamento</h2>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item"><a href="index.html">Inicio</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Departamentos</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Breadcrumb Area End -->


        <div class="container">


            <div class="py-5 text-center">
                <hr>
            </div>

            <div class="row">
                <div class="col-md-4 order-md-2 mb-4">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-muted">Servicios Extra</span>
                    </h4>


                    <div class="panel panel-default">
                        <!--<div class="panel-heading">Servicios</div>-->
                        <div class="panel-body">


                            <form method="post" action="<c:url value="/ReservaController"/>">

                                <div class="input-group col-md-6">
                                    <select name="servicios" id="Servicios" class="form-control">
                                        <c:forEach var="Servi" items="${ServiceExt}">
                                            <option value="${Servi.ID}">${Servi.NOMBRE_SERVICIO}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-12">

                                </div>
                                <div class="input-group col-md-6">
                                    <input type="submit" name="agregarServicio" class="btn btn-danger btn-md" value="Añadir Servicio" id="btn-chat">
                                </div>
                        </div>
                    </div>





                </div>



                <!-- DATOS DE LA REVERSA: -->
                <div class="col-md-8 order-md-1">
                    <h4 class="mb-3">Datos Reserva: </h4>
                    <form class="needs-validation" novalidate>
                        <div class="row">

                            <div class="col-md-6 mb-3">
                                <label for="address">Dirección:</label>
                                <input disabled="true" value="${direccion}" type="text" class="form-control" id="address" required>
                            </div>

                            <li class="col-md-6 mb-3">
                                <label>Precio Diario: </label>
                                <input disabled="true" value="${precioDiario}" type="text" class="form-control" id="address" required>
                            </li>

                            <div class="col-md-6 mb-3">
                                <label for="firstName">Dormitorios:</label>
                                <input disabled="true" type="text" class="form-control" id="firstName" placeholder="" value="${cantHabit}" required>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label for="lastName">Baños:</label>
                                <input disabled="true" type="text" class="form-control" id="lastName" placeholder="" value="${cantBanios}" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="username">Descripción:</label>
                            <div class="input-group">

                                <input disabled="true" type="text" class="form-control" id="username" value="${descripcion}" placeholder="Username" required>
                                
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label for="checkin">Check-In:</label>
                                <div class="field-icon-wrap">
                                    <div class="icon"><span class="icon-calendar"></span></div>
                                    <input disabled="true" value="${fechaDesde}" name="fechaDesde" type="date" pattern="yyyy-MM-dd" id="checkin_date" class="form-control">
                                </div>

                            </div>
                            <div class="col-md-6">
                                <label for="checkin">Check-Out:</label>
                                <div class="field-icon-wrap">
                                    <div class="icon"><span class="icon-calendar"></span></div>
                                    <input disabled="true" value="${fechaHasta}" name="fechaHasta" pattern="yyyy-MM-dd" type="date" id="checkout_date" class="form-control">
                                </div>
                            </div>


                        </div>


                        <div class="row">
                            <div class="col-md-6">
                                <h1>Precio Total:</h1>
                                <h1>$ <c:out value="${total}"/></h1>  
                            </div>
                        </div>



                        <hr class="mb-4">
                        <!--<input type="submit" name="reservaPago" class="btn btn-danger btn-md" value="Pagar Reserva" id="btn-chat">-->

                        <button class="btn btn-primary btn-lg btn-block" type="submit" name="reservaPago">Pagar Reserva</button>
                        <hr class="mb-4">

                    </form>
                </div>
            </div>
        </div>


        <!--
                    <form method="post" action="<c:url value="/ReservaController"/>">
        
        
        <!--
        <div class="row">
            <div class="col-12 col-lg-8">

        -->
        <!-- <div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
             <div class="row">-->
        <!--<h2>Reservar el Departamento:</h2>-->
        <!--
        <div class="item">
            <div class="block-34">

                <div class="text">
                    <h2 class="Direccion">${direccion}</h2>
                    <div class="precio"><sup>$</sup><span class="number">${precioDiario}</span><sub>/por día</sub></div>
                    <ul class="specs">
                        <li><strong>Habitaciones:</strong>${cantHabit}</li>
                        <li><strong>Baños:</strong> ${cantBanios}</li>
                        <li><strong>Descripción:</strong>${descripcion}</li>
                        <li><strong>DIRECCION: </strong>${direccion}</li>

                    </ul>
                </div>
            </div>
        </div>
        -->
        <input type="hidden" name="iddepto" value="${ID}"/>
        <input type="hidden" name="direccion" value="${depart.Direccion}"/>
        <input type="hidden" name="precio" value="${PRECIO_DIARIO}"/>
        <input type="hidden" name="cantidaddormitorios" value="${CANT_DORMITORIOS}"/>
        <input type="hidden" name="cantidadbanios" value="${CANT_BANIOS}"/>
        <input type="hidden" name="descripcion" value="${DESCRIPCION}"/>
        <input type="hidden" name="totalPagado" value="${totalPagado}"/>

        <input value="${fechaDesde}" name="fechaDesde" type="hidden" pattern="yyyy-MM-dd"/>
        <input value="${fechaHasta}" name="fechaHasta" type="hidden" pattern="yyyy-MM-dd"/>
        <!--<div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
            <label for="checkin">Check-In</label>
            <div class="field-icon-wrap">
                <div class="icon"><span class="icon-calendar"></span></div>
                <input disabled="true" value="${fechaDesde}" name="fechaDesde" type="date" pattern="yyyy-MM-dd" id="checkin_date" class="form-control">
            </div>

        </div>
        <div class="col-md-6 mb-3 mb-lg-0 col-lg-3">
            <label for="checkin">Check-Out:</label>
            <div class="field-icon-wrap">
                <div class="icon"><span class="icon-calendar"></span></div>
                <input disabled="true" value="${fechaHasta}" name="fechaHasta" pattern="yyyy-MM-dd" type="date" id="checkout_date" class="form-control">
            </div>
        </div>
        <h1>Precio total:</h1>
        <h2>$ <c:out value="${total}"/></h2>
        
        -->
        <!--
        <div class="col-md-8">
            <div class="panel panel-default">
                <div class="panel-heading">Servicios</div>
                <div class="panel-body">
                    <form method="post" action="<c:url value="/ReservaController"/>">
                        <h4>Ingresar servicios</h4>
                        <div class="input-group col-md-2">
                            <select name="servicios" id="Servicios" class="form-control">
        <c:forEach var="Servi" items="${ServiceExt}">
            <option value="${Servi.ID}">${Servi.NOMBRE_SERVICIO}</option>
        </c:forEach>
    </select>
</div>
<div class="col-md-12">

</div>
<input type="submit" name="reservaPago" class="btn btn-danger btn-md" value="Pagar Reserva" id="btn-chat">

</div>
</div>
</div>

</div>
</div>
</div>

</form>
        -->

        <!-- Footer Area Start -->
        <footer class="footer-area section-padding-80-0">
            <!-- Main Footer Area -->
            <div class="main-footer-area">
                <div class="container">
                    <div class="row align-items-baseline justify-content-between">
                        <!-- Single Footer Widget Area -->
                        <div class="col-12 col-sm-6 col-lg-3">
                            <div class="single-footer-widget mb-80">
                                <!-- Footer Logo -->
                                <a href="#" class="footer-logo"><img src="img/core-img/LogoTurismo1.png" alt=""></a>

                                <h4>+12 345-678-9999</h4>
                                <span>turismoreal@gmail.com</span>
                                <span>856 Paicaví, Concepción.</span>
                            </div>
                        </div>



                        <!-- Single Footer Widget Area -->
                        <div class="col-12 col-sm-4 col-lg-2">
                            <div class="single-footer-widget mb-80">
                                <!-- Widget Title -->
                                <h5 class="widget-title">Links</h5>

                                <!-- Footer Nav -->
                                <ul class="footer-nav">
                                    <li><a href="#"><i class="fa fa-caret-right" aria-hidden="true"></i>Departamentos</a></li>
                                    <li><a href="#"><i class="fa fa-caret-right" aria-hidden="true"></i>Nosotros</a></li>
                                    <li><a href="#"><i class="fa fa-caret-right" aria-hidden="true"></i>Multas</a></li>
                                    <li><a href="#"><i class="fa fa-caret-right" aria-hidden="true"></i>Contacto</a></li>
                                </ul>
                            </div>
                        </div>

                        <!-- Single Footer Widget Area -->
                        <div class="col-12 col-sm-8 col-lg-4">
                            <div class="single-footer-widget mb-80">
                                <!-- Widget Title -->
                                <h5 class="widget-title">Subscribete </h5>
                                <span>Subscribete para más información.</span>

                                <!-- Newsletter Form -->
                                <form action="index.html" class="nl-form">
                                    <input type="email" class="form-control" placeholder="Ingresa tu Email...">
                                    <button type="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Copywrite Area -->
            <div class="container">
                <div class="copywrite-content">
                    <div class="row align-items-center">
                        <div class="col-12 col-md-8">
                            <!-- Copywrite Text -->
                            <div class="copywrite-text">
                                <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank"></a>
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                            </div>
                        </div>
                        <div class="col-12 col-md-4">
                            <!-- Social Info -->
                            <div class="social-info">
                                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                                <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer Area End -->

        <!-- **** All JS Files ***** -->
        <!-- jQuery 2.2.4 -->
        <script src="js/jquery.min.js"></script>
        <!-- Popper -->
        <script src="js/popper.min.js"></script>
        <!-- Bootstrap -->
        <script src="js/bootstrap.min.js"></script>
        <!-- All Plugins -->
        <script src="js/roberto.bundle.js"></script>
        <!-- Active -->
        <script src="js/default-assets/active.js"></script>



    </body>
</html>
