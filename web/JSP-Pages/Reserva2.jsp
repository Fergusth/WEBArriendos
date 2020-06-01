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
        <style>
            .depa-box{
                width: 45%;
            }
        </style>
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
                                    <li class="breadcrumb-item"><a href="<c:url value="/UsuarioController"/>">Inicio</a></li>
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
        <form method="post" action="<c:url value="/ReservaController"/>">
            <section class="roberto-about-area section-padding-100-0">
                <!-- Hotel Search Form Area -->
                <div class="hotel-search-form-area">
                    <div class="container-fluid">
                        <div class="hotel-search-form">



                            <div class="row justify-content-between align-items-end">
                                <div class="col-6 col-md-2 col-lg-3">
                                    <label for="checkIn">Desde:</label>

                                    <input value="${fechaDesde}" placeholder="dd-mm-yyyy" type="date" class="form-control" id="checkin_date"
                                           name="fechaDesde">
                                </div>
                                <div class="col-6 col-md-2 col-lg-3">
                                    <label for="checkOut">Hasta:</label>

                                    <input value="${fechaHasta}" placeholder="dd-mm-yyyy" type="date" class="form-control" id="checkout_date"
                                           name="fechaHasta">
                                </div>

                                <div class="col-6 col-md-2 col-lg-3">
                                    <label for="ciudax">Ciudad:</label>
                                    <select name="ciudax" id="ciudax" class="form-control">

                                        <c:forEach items="${ciudadex}" var="ciudad">
                                            <option ${ciudad.ID == selectedCity ? 'selected="selected"' : ''} value="${ciudad.ID}">
                                                <c:out value="${ciudad.NOMBRE_CIUDAD}" />
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

                        </div>
                    </div>
                </div>
                <center>
                    <h5 style="color:red;">
                        <c:out value="${error}"/>
                    </h5>
                </center>
                <c:if test="${departamentos.size() == 0 && error == null}">
                    <center>
                        <h3>No hay departamentos disponibles</h3>
                    </center>
                </c:if>
                <c:if test="${departamentos.size() > 0 && error == null}">
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="paso1" role="tabpanel" aria-labelledby="paso1-tab">
                            <h5 style="color: red; margin-left: 16px;">${error}</h5>
                            <div style="display: flex; flex-wrap: wrap; padding: 0 16px;">
                                <c:forEach var="depart" items="${departamentos}">
                                    <div class="single-room-area d-flex align-items-center mb-50 wow fadeInUp depa-box"
                                         data-wow-delay="100ms">
                                        <!-- Room Thumbnail -->
                                        <div class="room-thumbnail">
                                            <img src="img/bg-img/43.jpg" alt="">
                                        </div>
                                        <!-- Room Content -->
                                        <div class="room-content">
                                            <h3 class="Direccion">${depart.DIRECCION}</h3>
                                            <h4 class="Precio">$${depart.PRECIO_DIARIO}<span>/ Día</span></h4>
                                            <div class="room-feature">
                                                <h6>Baños: <span>${depart.CANT_BANIOS}</span></h6>
                                                <h6>Dormitorios: <span>${depart.CANT_DORMITORIOS}</span></h6>
                                                <h6>Internet <span>${depart.INTERNET}</span></span></h6>
                                                <h6>Calefacción <span>${depart.CALEFACCION}</span></h6>
                                                <h6>Descripción: <span>${depart.DESCRIPCION}</span></h6>
                                            </div>
                                            <div style="display:flex;">
                                                <input type="checkbox" name="check-${depart.ID}"/>
                                                <h6 style="margin-top: -3px; margin-left: 8px;">Reservar </h6>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <br><br>
                        <div style="
                             position: fixed;
                             bottom: 0;
                             width: 100%; 
                             height: 60px; 
                             background: #f5f5f5; 
                             z-index: 9999;
                             display: flex;
                             justify-content: flex-end;
                             align-items: center;">
                            <button type="submit" name="btnPaso2" class="btn btn-warning" style="margin-right: 32px;">Siguiente paso</button>
                        </div>
                    </div>
                </c:if>
            </section>
        </form>
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
