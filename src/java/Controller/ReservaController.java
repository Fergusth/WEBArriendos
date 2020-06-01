package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static Controller.AcompanianteController.validarDNI;
import DTO.AcompanianteDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import DTO.CiudadDTO;
import DTO.DepartamentoDTO;
import DTO.MultaDTO;
import DTO.ReservaDTO;
import DTO.ServicioExtraDTO;
import DTO.ServicioTourDTO;
import DTO.UsuarioDTO;
import Service.AcompanianteService;
import Service.DepartamentoService;
import Service.PagoService;
import Service.ReservaService;
import Service.ServiciosExtraService;
import Service.UsuarioService;
import WS.Acompaniante;
import WS.Ciudad;
import WS.Departamento;
import WS.FlowResponse;
import WS.ServicioExtra;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import java.util.Base64;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author oskardashh
 */
@WebServlet(name = "ReservaController", urlPatterns = {"/ReservaController"})
public class ReservaController extends HttpServlet {

    public ArrayList<ServicioExtraDTO> serviciosEx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*CiudadDTO ciud = new CiudadDTO();
        List<CiudadDTO> ciudades = ciud.ListarCiudades();
        request.setAttribute("ciudades", ciudades);

        request.getRequestDispatcher("JSP-Pages/Reserva.jsp").forward(request, response);
         */
        ArrayList<CiudadDTO> ciudadex = new ArrayList<>();
        List<Ciudad> listarCiudad = ReservaService.listaCiudades().getCiudad();
        for (Ciudad ciudax : listarCiudad) {
            CiudadDTO city = new CiudadDTO();
            city.setID(ciudax.getID());
            city.setNOMBRE_CIUDAD(ciudax.getNOMBRECIUDAD().getValue());
            city.setREGION(ciudax.getREGION().getValue());
            ciudadex.add(city);

        }
        request.setAttribute("ciudadex", ciudadex);
        HttpSession Session = request.getSession(true);
        if (Session.getAttribute("cliente") != null) {
            UsuarioDTO userDTO = (UsuarioDTO) Session.getAttribute("cliente");

            boolean existe = UsuarioService.existeUsuario(userDTO.getDNI());
            if (!existe) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                boolean tienemultas = new MultaDTO().tieneMultaImpagas(userDTO.getDNI());
                if (tienemultas) {
                    response.setContentType("text/html;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Tienes multas impagas, no puedes reservar hasta pagar tus multas');");
                        out.println("location='Inicio.jsp';");
                        out.println("</script>");
                    }
                } else {
                    request.getRequestDispatcher("JSP-Pages/Reserva2.jsp").forward(request, response);
                }
            }
            request.setAttribute("nombreUsuario", userDTO.getNOMBRE());
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession Session = request.getSession(true);
        UsuarioDTO USR = new UsuarioDTO();
        if (Session.getAttribute("cliente") != null) {
            USR = (UsuarioDTO) Session.getAttribute("cliente");
            request.setAttribute("nombreUsuario", USR.getNOMBRE());
            boolean existe = UsuarioService.existeUsuario(USR.getDNI());
            if (!existe) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
        if (request.getParameter("btn_filtrar") != null) {
            ArrayList<CiudadDTO> ciudadex = new ArrayList<>();
            List<Ciudad> listarCiudad = ReservaService.listaCiudades().getCiudad();
            for (Ciudad ciudax : listarCiudad) {
                CiudadDTO city = new CiudadDTO();
                city.setID(ciudax.getID());
                city.setNOMBRE_CIUDAD(ciudax.getNOMBRECIUDAD().getValue());
                city.setREGION(ciudax.getREGION().getValue());
                ciudadex.add(city);

            }
            request.setAttribute("ciudadex", ciudadex);

            try {

                String startDateStr = request.getParameter("fechaDesde");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = sdf1.parse(startDateStr);

                String endDateStr = request.getParameter("fechaHasta");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                Date endDate = sdf2.parse(endDateStr);
                Date today = new Date();

                int ciudad = Integer.parseInt(request.getParameter("ciudax"));
                XMLGregorianCalendar gregorianStart = getXmlGregorianCalendarFromDate(startDate);
                XMLGregorianCalendar gregorianEnd = getXmlGregorianCalendarFromDate(endDate);
                Session.setAttribute("dateStart", startDate);
                Session.setAttribute("dateEnd", endDate);
                Session.setAttribute("city", ciudad);
                // List<Departamento> departamentos;
                boolean correct = true;
                int dias = Days.daysBetween(new DateTime(today), new DateTime(startDate)).getDays();
                int dias2 = Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays();
                System.out.println(dias);
                if (dias < 0) {
                    request.setAttribute("error", "La fecha de inicio no puede ser menor a hoy");
                    correct = false;
                }
                if (dias2 < 0) {
                    request.setAttribute("error", "La fecha de inicio no puede ser menor a la de fin");
                    correct = false;
                }
                if (correct) {
                    List<Departamento> departamentos = DepartamentoService.departamentosDisponiblesPorFecha(gregorianStart, gregorianEnd, ciudad).getDepartamento();

                    ArrayList<DepartamentoDTO> depaDTO = new ArrayList<>();

                    for (Departamento depart : departamentos) {
                        DepartamentoDTO dpdto = new DepartamentoDTO();
                        dpdto.setID(depart.getID().intValue());
                        dpdto.setDIRECCION(depart.getDIRECCION().getValue());
                        dpdto.setPRECIO_DIARIO(depart.getPRECIODIARIO().intValue());
                        dpdto.setCANT_BANIOS(depart.getCANTBANIOS().intValue());
                        dpdto.setCANT_DORMITORIOS(depart.getCANTDORMITORIOS().shortValue());
                        dpdto.setDESCRIPCION(depart.getDESCRIPCION().getValue());
                        dpdto.setINTERNET(depart.getINTERNET().getValue() == "0" ? "No" : "Sí");
                        dpdto.setCALEFACCION(depart.getCALEFACCION().getValue() == "0" ? "No" : "Sí");

                        depaDTO.add(dpdto);

                    }
                    request.setAttribute("departamentos", depaDTO);
                }
                request.setAttribute("fechaDesde", request.getParameter("fechaDesde"));
                request.setAttribute("fechaHasta", request.getParameter("fechaHasta"));
                request.setAttribute("selectedCity", ciudad);
                request.getRequestDispatcher("JSP-Pages/Reserva2.jsp").forward(request, response);

            } catch (ParseException ex) {
                request.setAttribute("fechaDesde", "ERROR FECHA INICIO");
            } catch (DatatypeConfigurationException ex) {
                request.setAttribute("fechaHastaa", "ERROR FECHA TERMINO");
            }
            request.getRequestDispatcher("JSP-Pages/Reserva2.jsp").forward(request, response);
        }

        if (request.getParameter("btnPaso2") != null) {
            try {
                String startDateStr = request.getParameter("fechaDesde");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

                Date startDate = sdf1.parse(startDateStr);

                String endDateStr = request.getParameter("fechaHasta");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

                Date endDate = sdf2.parse(endDateStr);

                int ciudad = Integer.parseInt(request.getParameter("ciudax"));
                // List<Departamento> departamentos;
                List<Departamento> departamentos = DepartamentoService.departamentosDisponiblesPorFecha(getXmlGregorianCalendarFromDate(startDate), getXmlGregorianCalendarFromDate(endDate), ciudad).getDepartamento();

                ArrayList<DepartamentoDTO> depaDTO = new ArrayList<>();

                for (Departamento depart : departamentos) {
                    DepartamentoDTO dpdto = new DepartamentoDTO();
                    dpdto.setID(depart.getID().intValue());
                    dpdto.setDIRECCION(depart.getDIRECCION().getValue());
                    dpdto.setPRECIO_DIARIO(depart.getPRECIODIARIO().intValue());
                    dpdto.setCANT_BANIOS(depart.getCANTBANIOS().intValue());
                    dpdto.setCANT_DORMITORIOS(depart.getCANTDORMITORIOS().shortValue());
                    dpdto.setDESCRIPCION(depart.getDESCRIPCION().getValue());
                    dpdto.setINTERNET(depart.getINTERNET().getValue() == "0" ? "No" : "Sí");
                    dpdto.setCALEFACCION(depart.getCALEFACCION().getValue() == "0" ? "No" : "Sí");

                    depaDTO.add(dpdto);

                }
                boolean selecciono = false;
                ArrayList<DepartamentoDTO> seleccionados = new ArrayList<>();
                for (DepartamentoDTO departamen : depaDTO) {
                    if (request.getParameter("check-" + departamen.getID()) != null) {
                        selecciono = true;
                        seleccionados.add(departamen);
                    }
                }
                if (selecciono) {
                    Session.setAttribute("depasSelect", seleccionados);
                    Session.setAttribute("acompaniantes", new ArrayList<>());

                    ArrayList<DepartamentoDTO> sadads = (ArrayList<DepartamentoDTO>) Session.getAttribute("depasSelect");
                    request.getRequestDispatcher("JSP-Pages/Registro_acompa.jsp").forward(request, response);
                } else {
                    ArrayList<CiudadDTO> ciudadex = new ArrayList<>();
                    List<Ciudad> listarCiudad = ReservaService.listaCiudades().getCiudad();
                    for (Ciudad ciudax : listarCiudad) {
                        CiudadDTO city = new CiudadDTO();
                        city.setID(ciudax.getID());
                        city.setNOMBRE_CIUDAD(ciudax.getNOMBRECIUDAD().getValue());
                        city.setREGION(ciudax.getREGION().getValue());
                        ciudadex.add(city);

                    }
                    request.setAttribute("ciudadex", ciudadex);
                    request.setAttribute("error", "Debe seleccionar al menos un departamento");
                    request.setAttribute("fechaDesde", request.getParameter("fechaDesde"));
                    request.setAttribute("fechaHasta", request.getParameter("fechaHasta"));
                    request.setAttribute("selectedCity", ciudad);
                    request.setAttribute("departamentos", depaDTO);
                    request.getRequestDispatcher("JSP-Pages/Reserva2.jsp").forward(request, response);
                }
            } catch (ParseException ex) {
                request.setAttribute("fechaDesde", "ERROR FECHA INICIO");
            } catch (DatatypeConfigurationException ex) {
                request.setAttribute("fechaHastaa", "ERROR FECHA TERMINO");
            }
        }

        if (request.getParameter("btnBuscar") != null) {
            ArrayList<AcompanianteDTO> acompaniantes = (ArrayList<AcompanianteDTO>) Session.getAttribute("acompaniantes");
            String dni = request.getParameter("DNI");
            AcompanianteDTO acomp = new AcompanianteDTO().BuscarAcompaniante(dni);

            if (acomp.getDNI() != null) {
                boolean existe = false;
                if (acompaniantes != null) {
                    for (AcompanianteDTO acompaniante : acompaniantes) {
                        if (acomp.getDNI().equals(acompaniante.getDNI())) {
                            existe = true;
                        }
                    }
                } else {
                    existe = false;
                    acompaniantes = new ArrayList<>();
                    Session.setAttribute("acompaniantes", acompaniantes);
                }

                if (existe) {
                    request.setAttribute("error", "El acompañante ya está en la lista");
                } else {
                    acompaniantes.add(acomp);
                }
            } else {
                request.setAttribute("error", "El acompañante no existe, debes crearlo");
                request.setAttribute("DNI", request.getParameter("DNI"));
                request.setAttribute("NOMBREACOMP", request.getParameter("NOMBREACOMP"));
                request.setAttribute("CORREO", request.getParameter("CORREO"));
                request.setAttribute("TELEFONO", request.getParameter("TELEFONO"));
                request.setAttribute("EXTRANJERO", request.getParameter("EXTRANJERO"));
            }
            request.setAttribute("acompaniantes", acompaniantes);
            request.getRequestDispatcher("JSP-Pages/Registro_acompa.jsp").forward(request, response);
        }

        if (request.getParameter("QuitarAcom") != null) {
            ArrayList<AcompanianteDTO> acompaniantes = (ArrayList<AcompanianteDTO>) Session.getAttribute("acompaniantes");
            String dni = request.getParameter("dniQuitar");
            System.out.println(dni);
            ArrayList<AcompanianteDTO> acompaniantes_aux = new ArrayList<>();

            for (AcompanianteDTO acompaniante : acompaniantes) {
                if (!acompaniante.getDNI().equals(dni)) {
                    acompaniantes_aux.add(acompaniante);
                }
            }
            Session.setAttribute("acompaniantes", acompaniantes_aux);
            request.setAttribute("DNI", request.getParameter("DNI"));
            request.setAttribute("NOMBREACOMP", request.getParameter("NOMBREACOMP"));
            request.setAttribute("CORREO", request.getParameter("CORREO"));
            request.setAttribute("TELEFONO", request.getParameter("TELEFONO"));
            request.setAttribute("EXTRANJERO", request.getParameter("EXTRANJERO"));
            request.setAttribute("acompaniantes", acompaniantes_aux);
            request.getRequestDispatcher("JSP-Pages/Registro_acompa.jsp").forward(request, response);
        }

        if (request.getParameter("btnRegistroAcomp") != null) {
            ArrayList<String> Error = new ArrayList<String>();
            boolean isCorrect = true;
            String DNI = request.getParameter("DNI");
            AcompanianteDTO acomp = new AcompanianteDTO().BuscarAcompaniante(DNI);
            ArrayList<AcompanianteDTO> acompaniantes = (ArrayList<AcompanianteDTO>) Session.getAttribute("acompaniantes");

            if (acomp.getDNI() != null) {
                boolean existe = false;
                if (acompaniantes != null) {
                    for (AcompanianteDTO acompaniante : acompaniantes) {
                        if (acomp.getDNI().equals(acompaniante.getDNI())) {
                            existe = true;
                        }
                    }
                } else {
                    existe = false;
                    acompaniantes = new ArrayList<>();
                    Session.setAttribute("acompaniantes", acompaniantes);
                }

                if (existe) {
                    request.setAttribute("error", "El acompañante ya está en la lista");
                } else {
                    acompaniantes.add(acomp);
                }
                request.setAttribute("acompaniantes", acompaniantes);
                request.getRequestDispatcher("JSP-Pages/Registro_acompa.jsp").forward(request, response);
            } else {

                String NOMBRE_COMPLETO = request.getParameter("NOMBREACOMP");
                String CORREO = request.getParameter("CORREO");
                int TELEFONO = 0;
                String EXTRAJERO = request.getParameter("EXTRANJERO") != null ? "1" : "0";

                if (DNI.trim().equals("")) {
                    Error.add("Rut no puede estar vacío");
                    isCorrect = false;
                } else {
                    if (EXTRAJERO.equals("0")) {
                        if (!validarDNI(DNI)) {
                            Error.add("Rut no válido");
                            isCorrect = false;
                        }
                    }
                }
                if (NOMBRE_COMPLETO.trim().equals("")) {
                    Error.add("El Nombre no puede quedar Vacíó");
                    isCorrect = false;
                }
                if (CORREO.trim().equals("")) {
                    Error.add("El Correo no puede quedar Vacíó");
                    isCorrect = false;
                }
                if (request.getParameter("TELEFONO").trim().equals("")) {
                    Error.add("El Teléfono no puede quedar Vacíó");
                    isCorrect = false;
                } else {
                    TELEFONO = Integer.parseInt(request.getParameter("TELEFONO"));
                }
                if (isCorrect) {
                    AcompanianteService.crearAcompaniante(DNI, NOMBRE_COMPLETO, EXTRAJERO, CORREO, TELEFONO);
                    AcompanianteDTO acom = new AcompanianteDTO().BuscarAcompaniante(DNI);
                    if (acompaniantes != null) {
                        acompaniantes.add(acom);
                    } else {
                        acompaniantes = new ArrayList<>();
                        acompaniantes.add(acom);
                    }

                    Session.setAttribute("acompaniantes", acompaniantes);
                    request.setAttribute("DNI", request.getParameter("DNI"));
                    request.setAttribute("NOMBREACOMP", request.getParameter("NOMBREACOMP"));
                    request.setAttribute("CORREO", request.getParameter("CORREO"));
                    request.setAttribute("TELEFONO", request.getParameter("TELEFONO"));
                    request.setAttribute("EXTRANJERO", request.getParameter("EXTRANJERO"));
                    request.setAttribute("acompaniantes", acompaniantes);
                    request.getRequestDispatcher("JSP-Pages/Registro_acompa.jsp").forward(request, response);
                } else {
                    Session.setAttribute("acompaniantes", acompaniantes);
                    request.setAttribute("DNI", request.getParameter("DNI"));
                    request.setAttribute("Errores", Error);
                    request.setAttribute("NOMBREACOMP", request.getParameter("NOMBREACOMP"));
                    request.setAttribute("CORREO", request.getParameter("CORREO"));
                    request.setAttribute("TELEFONO", request.getParameter("TELEFONO"));
                    request.setAttribute("EXTRANJERO", request.getParameter("EXTRANJERO"));
                    request.setAttribute("acompaniantes", acompaniantes);
                    request.getRequestDispatcher("JSP-Pages/Registro_acompa.jsp").forward(request, response);
                }
            }
        }

        if (request.getParameter("btnPaso3") != null) {
            Date dateStart = (Date) Session.getAttribute("dateStart");
            Date dateEnd = (Date) Session.getAttribute("dateEnd");
            int ciudad = (int) Session.getAttribute("city");
            ArrayList<ServicioTourDTO> servicios_extra = new ArrayList<>();
            ArrayList<ServicioExtraDTO> servicios_tours = new ArrayList<>();

            Session.setAttribute("servicios_extra", servicios_extra);
            Session.setAttribute("servicios_tours", servicios_tours);

            try {
                ArrayList<ServicioTourDTO> servicios_disp = new ServicioTourDTO().buscarTourFecha(dateStart, dateEnd, ciudad);
                request.setAttribute("tours", servicios_disp);
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServicioExtraDTO servs = new ServicioExtraDTO();
            request.setAttribute("servicios", servs.ListarServicios());
            request.getRequestDispatcher("JSP-Pages/RegistroServicios.jsp").forward(request, response);
        }

        if (request.getParameter("btnContratarSE") != null) {
            ArrayList<ServicioTourDTO> servicios_tours = (ArrayList<ServicioTourDTO>) Session.getAttribute("servicios_tours");
            ArrayList<ServicioExtraDTO> servicios_extra = (ArrayList<ServicioExtraDTO>) Session.getAttribute("servicios_extra");
            Date dateStart = (Date) Session.getAttribute("dateStart");
            Date dateEnd = (Date) Session.getAttribute("dateEnd");
            int ciudad = (int) Session.getAttribute("city");
            int servicioid = 0;
            if (request.getParameter("selectServE").equals("NONE")) {
                request.setAttribute("error", "Debe seleccionar un servicio");
            } else {
                servicioid = Integer.parseInt(request.getParameter("selectServE"));
            }

            Session.setAttribute("servicios_extra", servicios_extra);
            Session.setAttribute("servicios_tours", servicios_tours);

            try {
                ArrayList<ServicioTourDTO> servicios_disp = new ServicioTourDTO().buscarTourFecha(dateStart, dateEnd, ciudad);
                request.setAttribute("tours", servicios_disp);
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServicioExtraDTO servs = new ServicioExtraDTO();
            for (ServicioExtraDTO servicio : servs.ListarServicios()) {
                if (servicio.getID() == servicioid) {
                    boolean existe = false;
                    for (ServicioExtraDTO servicioExtraDTO : servicios_extra) {
                        if (servicioExtraDTO.getID() == servicio.getID()) {
                            existe = true;
                        }
                    }
                    if (existe) {
                        request.setAttribute("error", "El servicio extra ya está agregado");
                    } else {
                        servicios_extra.add(servicio);
                    }
                }
            }
            request.setAttribute("EContratados", servicios_extra);
            request.setAttribute("TContratados", servicios_tours);
            request.setAttribute("servicios", servs.ListarServicios());
            request.getRequestDispatcher("JSP-Pages/RegistroServicios.jsp").forward(request, response);
        }

        if (request.getParameter("btnContratarST") != null) {
            ArrayList<ServicioTourDTO> servicios_tours = (ArrayList<ServicioTourDTO>) Session.getAttribute("servicios_tours");
            ArrayList<ServicioExtraDTO> servicios_extra = (ArrayList<ServicioExtraDTO>) Session.getAttribute("servicios_extra");
            Date dateStart = (Date) Session.getAttribute("dateStart");
            Date dateEnd = (Date) Session.getAttribute("dateEnd");
            int ciudad = (int) Session.getAttribute("city");
            int servicioid = 0;
            if (request.getParameter("selectServT") == "NONE") {
                request.setAttribute("error", "Debe seleccionar un servicio");
            } else {
                servicioid = Integer.parseInt(request.getParameter("selectServT"));
            }
            Session.setAttribute("servicios_extra", servicios_extra);
            Session.setAttribute("servicios_tours", servicios_tours);

            try {
                ArrayList<ServicioTourDTO> servicios_disp = new ServicioTourDTO().buscarTourFecha(dateStart, dateEnd, ciudad);
                request.setAttribute("tours", servicios_disp);
                for (ServicioTourDTO servicio : servicios_disp) {
                    if (servicio.getID_TOUR() == servicioid) {
                        boolean existe = false;
                        for (ServicioTourDTO ServicioTourDTO : servicios_tours) {
                            if (ServicioTourDTO.getID_TOUR() == servicio.getID_TOUR()) {
                                existe = true;
                            }
                        }
                        if (existe) {
                            request.setAttribute("error", "El servicio de tour ya está agregado");
                        } else {
                            servicios_tours.add(servicio);
                        }
                    }
                }
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServicioExtraDTO servs = new ServicioExtraDTO();
            request.setAttribute("EContratados", servicios_extra);
            request.setAttribute("TContratados", servicios_tours);
            request.setAttribute("servicios", servs.ListarServicios());
            request.getRequestDispatcher("JSP-Pages/RegistroServicios.jsp").forward(request, response);
        }

        if (request.getParameter("quitarSE") != null) {
            ArrayList<ServicioTourDTO> servicios_tours = (ArrayList<ServicioTourDTO>) Session.getAttribute("servicios_tours");
            ArrayList<ServicioExtraDTO> servicios_extra = (ArrayList<ServicioExtraDTO>) Session.getAttribute("servicios_extra");
            Date dateStart = (Date) Session.getAttribute("dateStart");
            Date dateEnd = (Date) Session.getAttribute("dateEnd");
            int ciudad = (int) Session.getAttribute("city");
            int servicioid = Integer.parseInt(request.getParameter("servId"));

            Session.setAttribute("servicios_extra", servicios_extra);
            Session.setAttribute("servicios_tours", servicios_tours);

            try {
                ArrayList<ServicioTourDTO> servicios_disp = new ServicioTourDTO().buscarTourFecha(dateStart, dateEnd, ciudad);
                request.setAttribute("tours", servicios_disp);
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ArrayList<ServicioExtraDTO> servicios_extra_n = new ArrayList<>();
            ServicioExtraDTO servs = new ServicioExtraDTO();
            for (ServicioExtraDTO servicio : servicios_extra) {
                if (servicio.getID() != servicioid) {
                    servicios_extra_n.add(servicio);
                }
            }
            Session.setAttribute("servicios_extra", servicios_extra_n);
            request.setAttribute("EContratados", servicios_extra_n);
            request.setAttribute("TContratados", servicios_tours);
            request.setAttribute("servicios", servs.ListarServicios());
            request.getRequestDispatcher("JSP-Pages/RegistroServicios.jsp").forward(request, response);
        }

        if (request.getParameter("quitarST") != null) {
            ArrayList<ServicioTourDTO> servicios_tours = (ArrayList<ServicioTourDTO>) Session.getAttribute("servicios_tours");
            ArrayList<ServicioExtraDTO> servicios_extra = (ArrayList<ServicioExtraDTO>) Session.getAttribute("servicios_extra");
            Date dateStart = (Date) Session.getAttribute("dateStart");
            Date dateEnd = (Date) Session.getAttribute("dateEnd");
            int ciudad = (int) Session.getAttribute("city");
            int servicioid = Integer.parseInt(request.getParameter("servId"));
            Session.setAttribute("servicios_extra", servicios_extra);
            Session.setAttribute("servicios_tours", servicios_tours);

            ArrayList<ServicioTourDTO> servicios_tour_ext = new ArrayList<>();
            try {
                ArrayList<ServicioTourDTO> servicios_disp = new ServicioTourDTO().buscarTourFecha(dateStart, dateEnd, ciudad);
                request.setAttribute("tours", servicios_disp);
                for (ServicioTourDTO servicio : servicios_tours) {
                    if (servicio.getID_TOUR() != servicioid) {
                        servicios_tour_ext.add(servicio);
                    }
                }
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServicioExtraDTO servs = new ServicioExtraDTO();
            Session.setAttribute("servicios_tours", servicios_tour_ext);
            request.setAttribute("EContratados", servicios_extra);
            request.setAttribute("TContratados", servicios_tour_ext);
            request.setAttribute("servicios", servs.ListarServicios());
            request.getRequestDispatcher("JSP-Pages/RegistroServicios.jsp").forward(request, response);
        }

        if (request.getParameter("btnPaso4") != null) {

            ArrayList<ServicioTourDTO> servicios_tours = (ArrayList<ServicioTourDTO>) Session.getAttribute("servicios_tours");
            ArrayList<ServicioExtraDTO> servicios_extra = (ArrayList<ServicioExtraDTO>) Session.getAttribute("servicios_extra");
            Date dateStart = (Date) Session.getAttribute("dateStart");
            Date dateEnd = (Date) Session.getAttribute("dateEnd");
            ArrayList<AcompanianteDTO> acompaniantes = (ArrayList<AcompanianteDTO>) Session.getAttribute("acompaniantes");
            ArrayList<DepartamentoDTO> departamentos = (ArrayList<DepartamentoDTO>) Session.getAttribute("depasSelect");
            int puerto = request.getLocalPort();
            int days = Days.daysBetween(new DateTime(dateStart), new DateTime(dateEnd)).getDays() + 1;
            int totalDepartamentos = 0;
            int totalServiciosExtra = 0;
            int totalServiciosTour = 0;
            for (DepartamentoDTO departamento : departamentos) {
                totalDepartamentos = totalDepartamentos + (departamento.getPRECIO_DIARIO() * days);
            }
            for (ServicioExtraDTO servicioExtraDTO : servicios_extra) {
                totalServiciosExtra = totalServiciosExtra + (servicioExtraDTO.getPRECIO_ACTUAL() * days);
            }
            for (ServicioTourDTO servicios_tour : servicios_tours) {
                totalServiciosTour = totalServiciosTour + servicios_tour.getPRECIO_ACTUAL();
            }
            int totalReserva = totalDepartamentos + totalServiciosExtra + totalServiciosTour;
            int pagoAnticipoPrecio = totalDepartamentos * PagoService.getPorcentajeAnticipo() / 100;
            ReservaDTO reserva = new ReservaDTO();
            try {
                reserva = new ReservaDTO().crearReserva(dateStart, dateEnd, USR.getDNI(), totalReserva, 0);
            } catch (DatatypeConfigurationException ex) {
                Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
            }

            String idReservaEncriptada = encriptar(String.valueOf(reserva.getID_RESERVA()));
            String precioAnticipoEncriptado = encriptar(String.valueOf(pagoAnticipoPrecio));

            FlowResponse flow_response = PagoService.generarLinkPago(pagoAnticipoPrecio, USR.getCORREO_ELECTRONICO(), "http://localhost:" + puerto + "/WEBArriendos/ConfirmacionPagoAnticipoController?token=" + idReservaEncriptada + ";" + precioAnticipoEncriptado);
            String linkPago = flow_response.getUrl().getValue() + "?token=" + flow_response.getToken().getValue();
            request.setAttribute("departamentos", departamentos);
            request.setAttribute("SExtras", servicios_extra);
            request.setAttribute("STours", servicios_tours);
            request.setAttribute("Checkin", dateStart);
            request.setAttribute("Checkout", dateEnd);
            request.setAttribute("Acompaniantes", acompaniantes);
            request.setAttribute("TotalDepartamentos", totalDepartamentos);
            request.setAttribute("TotalReserva", totalReserva);
            request.setAttribute("TotalSExtras", totalServiciosExtra);
            request.setAttribute("TotalSTour", totalServiciosTour);
            request.setAttribute("TotalAnticipo", pagoAnticipoPrecio);
            request.setAttribute("PorcentAnticipo", PagoService.getPorcentajeAnticipo());
            request.setAttribute("ReservaID", reserva.getID_RESERVA());
            request.setAttribute("LinkPago", linkPago);
            request.getRequestDispatcher("JSP-Pages/DetalleReserva.jsp").forward(request, response);
        }
        if (request.getParameter("CancelarRes") != null) {
            int reserva_id = Integer.parseInt(request.getParameter("ReservaID"));
            new ReservaDTO().cancelarReserva(reserva_id);

            response.sendRedirect("ReservaController");
        }

    }

    public static XMLGregorianCalendar getXmlGregorianCalendarFromDate(final Date date) throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

    }

    private static String encriptar(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
