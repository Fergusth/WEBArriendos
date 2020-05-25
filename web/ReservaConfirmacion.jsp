<%-- 
    Document   : ReservaConfirmacion
    Created on : 24/05/2020, 05:59:25 PM
    Author     : Oskardashh
--%>

<%-- 
    Document   : ReservaInfo
    Created on : 24/05/2020, 05:58:27 PM
    Author     : Oskardashh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">


        <title>Paso 3</title>

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
        <script type="text/javascript">
            function printpage()
            {
                window.print()
            }
        </script>

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
                            <h2 class="page-title">Reserva Generada</h2>
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb justify-content-center">
                                    <li class="breadcrumb-item"><a href="index.html">Info</a></li>
                                    <li class="breadcrumb-item active" aria-current="page">Reserva</li>
                                </ol>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Breadcrumb Area End -->


        <!-- Contact Form Area Start -->
        <div class="roberto-contact-form-area section-padding-100">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <!-- Section Heading -->
                        <div class="section-heading text-center wow fadeInUp" data-wow-delay="100ms">
                            <h6>Información</h6>
                            <h2>Reserva</h2>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <!-- Form -->
                        <div class="roberto-rooms-area section-padding-100-0">
                            <form action="#" method="post">
                                <div class="row">

                                    <div class="col-12 col-lg-6 wow fadeInUp" data-wow-delay="100ms">
                                        <h2> Confirmación </h2>
                                    </div>

                                                                <div class="col-12 col-md-3">
                                        <a href="#" class="form-control btn roberto-btn w-100" title="Print booking" onclick="printpage()">Imprimir</a>
                                        <p>Gracias por confirmar su Reserva.</p>
                                    </div>

                                    
                                    <div class="col-12 col-lg-6 wow fadeInUp" data-wow-delay="100ms">
                                        <h2> Datos Reserva: </h2>
                                    </div>
                                    
                                    <div class="col-12 col-lg-6 wow fadeInUp" data-wow-delay="100ms">
                                        <div class="output">
                                            <p>Número de Reserva:</p>
                                            <p>Fecha Check-In:</p>
                                            <p>Fecha Check-Out:</p>
                                            <p>Nombre:</p>
                                            <p>Apellidos: </p>
                                            <p>Precio Total: </p>
                                            <p>Total Pagado: </p>
                                            <p>Forma de Pago: </p>
                                        </div>
                                    </div>

                                    <div class="col-12 col-lg-6 wow fadeInUp" data-wow-delay="100ms">
                                    <h3>Pago</h3>
                                    </div>
                                    <div class="text-wrap">
                                        <p>Su Pago será confirmado.</p>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
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