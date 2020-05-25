<%-- 
    Document   : Reserva2
    Created on : 24/05/2020, 12:45:01 AM
    Author     : Oskardashh
--%>

<%@page import="DTO.CiudadDTO"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="icon" href="img/core-img/favicon-32x32.png">

        <!-- Titulo -->
        <title>Lista de Departamentos</title>



    </head>

    <body>
        <jsp:include page="Navbar1.jsp"/>

        <!-- Breadcrumb Area Start -->
        <div class="breadcrumb-area bg-img bg-overlay jarallax" style="background-image: url(img/bg-img/16.jpg);">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="breadcrumb-content text-center">
                            <h2 class="page-title">Nuestros Departamentos</h2>
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

        <!-- About Us Area Start -->
        <section class="roberto-about-area section-padding-100-0">
            <!-- Hotel Search Form Area -->
            <div class="hotel-search-form-area">
                <div class="container-fluid">
                    <div class="hotel-search-form">

                        <form method="post" action="<c:url value="/ReservaController"/>">

                            <div class="row justify-content-between align-items-end">
                                <div class="col-6 col-md-2 col-lg-3">
                                    <label for="checkIn">Desde:</label>

                                    <input value="${fechaDesde}" type="date" class="form-control" id="checkin_date"
                                           name="fechaDesde">
                                </div>
                                <div class="col-6 col-md-2 col-lg-3">
                                    <label for="checkOut">Hasta:</label>

                                    <input value="${fechaHasta}" type="date" class="form-control" id="checkout_date"
                                           name="fechaHasta">
                                </div>

                                <div class="col-6 col-md-2 col-lg-3">
                                    <label for="ciudax">Ciudad:</label>
                                    <select name="ciudax" id="ciudax" class="form-control">

                                        <c:forEach items="${ciudadex}" var="ciudax">
                                            <option value="${ciudax.ID}">
                                                <c:out value="${ciudax.NOMBRE_CIUDAD}" />
                                            </option>
                                            <!--ciudax.NOMBRE_CIUDAD-->
                                        </c:forEach>

                                    </select>
                                </div>

                                <div class="col-12 col-md-3">
                                    <button type="submit" class="form-control btn roberto-btn w-100" name="btn_filtrar">
                                        FILTRAR
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>


            <!-- Rooms Area Start -->
            <div class="roberto-rooms-area section-padding-100-0">
                <div class="container">
                    <div class="row">
                        <div class="col-12 col-lg-8">
                            <!-- Single Room Area -->

                            <c:forEach var="depart" items="${departamentos}">

                                <div class="single-room-area d-flex align-items-center mb-50 wow fadeInUp"
                                     data-wow-delay="100ms">


                                    <!-- Room Thumbnail -->
                                    <div class="room-thumbnail">
                                        <img src="img/bg-img/43.jpg" alt="">
                                    </div>
                                    <!-- Room Content -->
                                    <div class="room-content">
                                        <h2 class="Direccion">${depart.DIRECCION}</h2>
                                        <h4 class="Precio">$${depart.PRECIO_DIARIO}<span>/ Día</span></h4>

                                        <div class="room-feature">
                                            <h6>Baños: <span>${depart.CANT_BANIOS}</span></h6>
                                            <h6>Dormitorios: <span>${depart.CANT_DORMITORIOS}</span></h6>
                                            <h6>Descripción: <span>${depart.DESCRIPCION}</span></h6>
                                            <h6>Internet <span>${depart.INTERNET}</span></span></h6>
                                            <h6>Calefacción <span>${depart.CALEFACCION}</span></h6>
                                        </div>






                                        <!--  <a href="ReservaInfo.jsp" class="btn view-detail-btn">Reservar <i
                                                  class="fa fa-long-arrow-right" aria-hidden="true"></i></a>-->
                                    </div>
                                </div>

                                <form method="POST" action="<c:url value="/ReservaController"/>">
                                    <input type="hidden" name="idDepartamento" value="${depart.ID}" />
                                    <input type="hidden" name="precioDiario" value="${depart.PRECIO_DIARIO}" />
                                    <input type="hidden" name="cantBanios" value="${depart.CANT_BANIOS}" />
                                    <input type="hidden" name="cantHabit" value="${depart.CANT_DORMITORIOS}" />
                                    <input type="hidden" name="descripcion" value="${depart.DESCRIPCION}" />
                                    <input type="hidden" name="internet" value="${depart.INTERNET}" />
                                    <input type="hidden" name="calefaccion" value="${depart.CALEFACCION}" />
                                    <input type="hidden" name="direccion" value="${depart.DIRECCION}"/>
                                    
                                    <input value="${fechaDesde}" name="fechaDesde" type="hidden" pattern="dd/MM/yyyy" id="checkin_date"
                                           class="form-control">
                                    <input value="${fechaHasta}" name="fechaHasta" type="hidden" pattern="dd/MM/yyyy" id="checkin_date"
                                           class="form-control">

                                  <!--  <div class="col-12 col-md-3">-->
                                        <input type="submit" name="BTN_RESERVAR" class="form-control btn roberto-btn w-100" value="Reservar">
                                   <!-- </div>-->

                                </form>

                            </c:forEach>



                            <!-- Pagination -->
                            <!-- <nav class="roberto-pagination wow fadeInUp mb-100" data-wow-delay="1000ms">
                                 <ul class="pagination">
                                     <li class="page-item"><a class="page-link" href="#">1</a></li>
                                     <li class="page-item"><a class="page-link" href="#">2</a></li>
                                     <li class="page-item"><a class="page-link" href="#">3</a></li>
                                     <li class="page-item"><a class="page-link" href="#">Next <i
                                                 class="fa fa-angle-right"></i></a></li>
                                 </ul>
                             </nav>
                            -->
                        </div>

                        <div class="col-12 col-lg-4">
                            <!-- Hotel Reservation Area -->
                            <div class="hotel-reservation--area mb-100">
                                <form action="#" method="post">
                                    <div class="form-group mb-30">
                                        <label for="checkInDate">Fecha</label>
                                        <div class="input-daterange" id="datepicker">
                                            <div class="row no-gutters">
                                                <div class="col-6">
                                                    <input type="text" class="input-small form-control" id="checkInDate"
                                                           name="checkInDate" placeholder="Check In">
                                                </div>
                                                <div class="col-6">
                                                    <input type="text" class="input-small form-control" name="checkOutDate"
                                                           placeholder="Check Out">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group mb-30">
                                        <label for="guests">Acompañante</label>
                                        <div class="row">
                                            <div class="col-6">
                                                <select name="adults" id="guests" class="form-control">
                                                    <option value="adults">Adults</option>
                                                    <option value="01">01</option>
                                                    <option value="02">02</option>
                                                    <option value="03">03</option>
                                                    <option value="04">04</option>
                                                    <option value="05">05</option>
                                                    <option value="06">06</option>
                                                </select>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="form-group mb-50">
                                        <div class="slider-range">
                                            <div class="range-price">Precio Máximo: $0 - $100.000</div>
                                            <div data-min="0" data-max="3000" data-unit="$"
                                                 class="slider-range-price ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"
                                                 data-value-min="0" data-value-max="3000" data-label-result="Max Price: ">
                                                <div class="ui-slider-range ui-widget-header ui-corner-all"></div>
                                                <span class="ui-slider-handle ui-state-default ui-corner-all"
                                                      tabindex="0"></span>
                                                <span class="ui-slider-handle ui-state-default ui-corner-all"
                                                      tabindex="0"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" name="BTN_RESERVAR" class="btn roberto-btn w-100">Revisa Disponibilidad</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Rooms Area End -->



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
