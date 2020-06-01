/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.AcompanianteDTO;
import DTO.DepartamentoDTO;
import DTO.MultaDTO;
import DTO.ReservaDTO;
import DTO.ServicioExtraDTO;
import DTO.ServicioTourDTO;
import DTO.UsuarioDTO;
import Service.PagoService;
import Service.UsuarioService;
import Service.AcompanianteService;
import WS.FlowResponse;
import WS.ServicioTour;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gerald
 */
@WebServlet(name = "ControlReservasController", urlPatterns = {"/ControlReservasController"})
public class ControlReservasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession Session = request.getSession(true);
        if (Session.getAttribute("cliente") != null) {
            UsuarioDTO usudto = (UsuarioDTO) Session.getAttribute("cliente");
            boolean existe = UsuarioService.existeUsuario(usudto.getDNI());
            if (!existe) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            ReservaDTO reserva = new ReservaDTO();
            request.setAttribute("lstReservasTodas", reserva.listarTodasReservasUsuario(usudto.getDNI()));
            request.setAttribute("lstReservasFuturas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "RESERVADO"));
            request.setAttribute("lstReservasActivas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKIN-LISTO"));
            request.setAttribute("lstReservasAPagar", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKOUT-LISTO"));
            request.getRequestDispatcher("JSP-Pages/ControlReservas/ListarReservas.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession Session = request.getSession(true);
        UsuarioDTO usudto = new UsuarioDTO();
        if (Session.getAttribute("cliente") != null) {
            usudto = (UsuarioDTO) Session.getAttribute("cliente");
            boolean existe = UsuarioService.existeUsuario(usudto.getDNI());
            if (!existe) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        if (request.getParameter("btnPagar") != null) {
            int reserva_id = Integer.parseInt(request.getParameter("resApagarID"));
            ReservaDTO reserva = new ReservaDTO();
            for (ReservaDTO item : reserva.listarTodasReservasUsuario(usudto.getDNI())) {
                if (item.getID_RESERVA() == reserva_id) {
                    reserva = item;
                    break;
                }
            }
            int puerto = request.getLocalPort();
            int total_a_pagar = reserva.getPRECIO_TOTAL() - reserva.getTOTAL_PAGADO();
            FlowResponse flow_response = PagoService.generarLinkPago(total_a_pagar, usudto.getCORREO_ELECTRONICO(), "http://localhost:" + puerto + "/WEBArriendos/ConfirmacionPagoReservaController?token=" + encriptar(String.valueOf(reserva.getID_RESERVA())));
            String linkPago = flow_response.getUrl().getValue() + "?token=" + flow_response.getToken().getValue();
            
            ArrayList<DepartamentoDTO> departamentos = new DepartamentoDTO().buscarDepartamentosReserva(reserva_id);
            ArrayList<ServicioExtraDTO> servicios_extra = new ServicioExtraDTO().ListarServiciosReserva(reserva_id);
            ArrayList<ServicioTourDTO> servicios_tour = new ServicioTourDTO().ServiciosTourReserva(reserva_id);
            
            request.setAttribute("departamentos", departamentos);
            request.setAttribute("SExtras", servicios_extra);
            request.setAttribute("STours", servicios_tour);
            request.setAttribute("total_a_pagar", total_a_pagar);
            request.setAttribute("linkPago", linkPago);
            request.setAttribute("reserva", reserva);
            request.getRequestDispatcher("JSP-Pages/ControlReservas/PagoReserva.jsp").forward(request, response);
        }

        if (request.getParameter("btnServicios") != null) {
            int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
            request.setAttribute("reserva_id", reserva_id);
            ServicioExtraDTO servicioExtra = new ServicioExtraDTO();
            ArrayList<ServicioExtraDTO> serviciosReserva = servicioExtra.ListarServiciosReserva(reserva_id);
            ArrayList<ServicioExtraDTO> servicios = servicioExtra.ListarServicios();
            ArrayList<ServicioExtraDTO> servAux = new ArrayList<>();
            for (ServicioExtraDTO servicio : servicios) {
                boolean exist = false;
                for (ServicioExtraDTO serv : serviciosReserva) {
                    if (serv.getID() == servicio.getID()) {
                        exist = true;
                    }
                }
                if (!exist) {
                    servAux.add(servicio);
                }
            }
            request.setAttribute("serviciosReserva", serviciosReserva);
            request.setAttribute("servicios", servAux);
            request.getRequestDispatcher("JSP-Pages/ControlReservas/ServiciosExtra.jsp").forward(request, response);
        }

        if (request.getParameter("btnContratarServicio") != null) {
            String seleccion = request.getParameter("selectServ");
            int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
            request.setAttribute("reserva_id", reserva_id);
            ServicioExtraDTO servicioExtra = new ServicioExtraDTO();

            if (seleccion.equals("NONE")) {
                request.setAttribute("error", "Debe seleccionar un servicio extra");
            } else {
                int serv_id = Integer.parseInt(seleccion);
                servicioExtra.contratarServicioExtra(reserva_id, serv_id, true);
            }
            ArrayList<ServicioExtraDTO> serviciosReserva = servicioExtra.ListarServiciosReserva(reserva_id);
            ArrayList<ServicioExtraDTO> servicios = servicioExtra.ListarServicios();
            ArrayList<ServicioExtraDTO> servAux = new ArrayList<>();
            for (ServicioExtraDTO servicio : servicios) {
                boolean exist = false;
                for (ServicioExtraDTO serv : serviciosReserva) {
                    if (serv.getID() == servicio.getID()) {
                        exist = true;
                    }
                }
                if (!exist) {
                    servAux.add(servicio);
                }
            }
            request.setAttribute("serviciosReserva", serviciosReserva);
            request.setAttribute("servicios", servAux);
            request.getRequestDispatcher("JSP-Pages/ControlReservas/ServiciosExtra.jsp").forward(request, response);
        }

        if (request.getParameter("btnQuitarServicio") != null) {
            String seleccion = request.getParameter("selectServ");
            int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
            request.setAttribute("reserva_id", reserva_id);
            ServicioExtraDTO servicioExtra = new ServicioExtraDTO();

            int servicioId = Integer.parseInt(request.getParameter("serv_id"));
            servicioExtra.cancelarServicioExtra(reserva_id, servicioId);

            ArrayList<ServicioExtraDTO> serviciosReserva = servicioExtra.ListarServiciosReserva(reserva_id);
            ArrayList<ServicioExtraDTO> servicios = servicioExtra.ListarServicios();
            ArrayList<ServicioExtraDTO> servAux = new ArrayList<>();
            for (ServicioExtraDTO servicio : servicios) {
                boolean exist = false;
                for (ServicioExtraDTO serv : serviciosReserva) {
                    if (serv.getID() == servicio.getID()) {
                        exist = true;
                    }
                }
                if (!exist) {
                    servAux.add(servicio);
                }
            }
            request.setAttribute("serviciosReserva", serviciosReserva);
            request.setAttribute("servicios", servAux);
            request.getRequestDispatcher("JSP-Pages/ControlReservas/ServiciosExtra.jsp").forward(request, response);
        }

        if (request.getParameter("siCancelar") != null) {
            ReservaDTO reserva = new ReservaDTO();
            int reservaId = Integer.parseInt(request.getParameter("reserva_id"));
            reserva.cancelarReserva(reservaId);

            request.setAttribute("lstReservasTodas", reserva.listarTodasReservasUsuario(usudto.getDNI()));
            request.setAttribute("lstReservasFuturas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "RESERVADO"));
            request.setAttribute("lstReservasActivas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKIN-LISTO"));
            request.setAttribute("lstReservasAPagar", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKOUT-LISTO"));
            request.getRequestDispatcher("JSP-Pages/ControlReservas/ListarReservas.jsp").forward(request, response);
        }
        if (request.getParameter("noCancelar") != null) {
            ReservaDTO reserva = new ReservaDTO();
            request.setAttribute("lstReservasTodas", reserva.listarTodasReservasUsuario(usudto.getDNI()));
            request.setAttribute("lstReservasFuturas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "RESERVADO"));
            request.setAttribute("lstReservasActivas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKIN-LISTO"));
            request.setAttribute("lstReservasAPagar", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKOUT-LISTO"));
            request.getRequestDispatcher("JSP-Pages/ControlReservas/ListarReservas.jsp").forward(request, response);
        }
        if (request.getParameter("btnAcompanantes") != null) {
            int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
            request.setAttribute("reserva_id", reserva_id);

            ArrayList<AcompanianteDTO> acompaniantes = new AcompanianteDTO().BuscarAcompaniantesReserva(reserva_id);

            request.setAttribute("acompaniantes", acompaniantes);
            request.getRequestDispatcher("JSP-Pages/ControlReservas/Acompaniantes.jsp").forward(request, response);

        }

        if (request.getParameter("btnBuscar") != null) {
            String dni = request.getParameter("DNI");
            AcompanianteDTO acomp = new AcompanianteDTO().BuscarAcompaniante(dni);
            int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
            request.setAttribute("reserva_id", reserva_id);
            ArrayList<AcompanianteDTO> acompaniantes = new AcompanianteDTO().BuscarAcompaniantesReserva(reserva_id);

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
                }

                if (existe) {
                    request.setAttribute("error", "El acompañante ya está en la lista");
                } else {
                    new AcompanianteDTO().crearAcompanianteReserva(acomp.getDNI(), reserva_id);
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
            request.getRequestDispatcher("JSP-Pages/ControlReservas/Acompaniantes.jsp").forward(request, response);
        }
        if (request.getParameter("QuitarAcom") != null) {            
            String dni = request.getParameter("dniQuitar");
            System.out.println(dni);
            ArrayList<AcompanianteDTO> acompaniantes_aux = new ArrayList<>();
            int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
            request.setAttribute("reserva_id", reserva_id);
            ArrayList<AcompanianteDTO> acompaniantes = new AcompanianteDTO().BuscarAcompaniantesReserva(reserva_id);

            for (AcompanianteDTO acompaniante : acompaniantes) {
                if (!acompaniante.getDNI().equals(dni)) {
                } else {
                    new AcompanianteDTO().borrarAcompanianteReserva(reserva_id, dni);
                }
            }
            acompaniantes_aux = new AcompanianteDTO().BuscarAcompaniantesReserva(reserva_id);
            Session.setAttribute("acompaniantes", acompaniantes_aux);
            request.setAttribute("DNI", request.getParameter("DNI"));
            request.setAttribute("NOMBREACOMP", request.getParameter("NOMBREACOMP"));
            request.setAttribute("CORREO", request.getParameter("CORREO"));
            request.setAttribute("TELEFONO", request.getParameter("TELEFONO"));
            request.setAttribute("EXTRANJERO", request.getParameter("EXTRANJERO"));
            request.setAttribute("acompaniantes", acompaniantes_aux);
            request.getRequestDispatcher("JSP-Pages/ControlReservas/Acompaniantes.jsp").forward(request, response);
        }
        if (request.getParameter("btnRegistroAcomp") != null) {
            ArrayList<String> Error = new ArrayList<String>();
            boolean isCorrect = true;
            String DNI = request.getParameter("DNI");
            AcompanianteDTO acomp = new AcompanianteDTO().BuscarAcompaniante(DNI);
            int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
            request.setAttribute("reserva_id", reserva_id);

            ArrayList<AcompanianteDTO> acompaniantes = new AcompanianteDTO().BuscarAcompaniantesReserva(reserva_id);

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
                }

                if (existe) {
                    request.setAttribute("error", "El acompañante ya está en la lista");
                } else {
                    new AcompanianteDTO().crearAcompanianteReserva(acomp.getDNI(), reserva_id);
                    acompaniantes.add(acomp);
                }
                request.setAttribute("acompaniantes", acompaniantes);
                request.getRequestDispatcher("JSP-Pages/ControlReservas/Acompaniantes.jsp").forward(request, response);
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
                    new AcompanianteDTO().crearAcompanianteReserva(acom.getDNI(), reserva_id);
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
                    request.getRequestDispatcher("JSP-Pages/ControlReservas/Acompaniantes.jsp").forward(request, response);
                } else {
                    Session.setAttribute("acompaniantes", acompaniantes);
                    request.setAttribute("DNI", request.getParameter("DNI"));
                    request.setAttribute("Errores", Error);
                    request.setAttribute("NOMBREACOMP", request.getParameter("NOMBREACOMP"));
                    request.setAttribute("CORREO", request.getParameter("CORREO"));
                    request.setAttribute("TELEFONO", request.getParameter("TELEFONO"));
                    request.setAttribute("EXTRANJERO", request.getParameter("EXTRANJERO"));
                    request.setAttribute("acompaniantes", acompaniantes);
                    request.getRequestDispatcher("JSP-Pages/ControlReservas/Acompaniantes.jsp").forward(request, response);
                }
            }
        }
    }

    private static String encriptar(String s) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }

    public static boolean validarDNI(String dni) {

        boolean validacion = false;
        try {
            dni = dni.toUpperCase();
            dni = dni.replace(".", "");
            dni = dni.replace("-", "");
            int dniAux = Integer.parseInt(dni.substring(0, dni.length() - 1));

            char dv = dni.charAt(dni.length() - 1);

            int m = 0, s = 1;
            for (; dniAux != 0; dniAux /= 10) {
                s = (s + dniAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
