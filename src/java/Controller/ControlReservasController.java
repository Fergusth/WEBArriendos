/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.MultaDTO;
import DTO.ReservaDTO;
import DTO.ServicioExtraDTO;
import DTO.UsuarioDTO;
import Service.PagoService;
import WS.FlowResponse;
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
        if (Session.getAttribute("cliente") != null){
            UsuarioDTO usudto = (UsuarioDTO) Session.getAttribute("cliente");
            ReservaDTO reserva = new ReservaDTO();
            request.setAttribute("lstReservasTodas", reserva.listarTodasReservasUsuario(usudto.getDNI()));
            request.setAttribute("lstReservasFuturas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "RESERVADO"));
            request.setAttribute("lstReservasActivas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKIN-LISTO"));
            request.setAttribute("lstReservasAPagar", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKOUT-LISTO"));
            request.getRequestDispatcher("JSP-Pages/ControlReservas/ListarReservas.jsp").forward(request, response);
        } else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession Session = request.getSession(true);
        UsuarioDTO usudto = new UsuarioDTO();
        if (Session.getAttribute("cliente") != null){
            usudto = (UsuarioDTO) Session.getAttribute("cliente");
        } else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        if (request.getParameter("btnPagar") != null){
            int reserva_id = Integer.parseInt(request.getParameter("resApagarID"));
            ReservaDTO reserva = new ReservaDTO();
            for (ReservaDTO item : reserva.listarTodasReservasUsuario(usudto.getDNI())) {
                if (item.getID_RESERVA() == reserva_id){
                    reserva = item;
                    break;
                }
            }
            int puerto = request.getLocalPort();
            FlowResponse flow_response = PagoService.generarLinkPago(reserva.getPRECIO_TOTAL(), usudto.getCORREO_ELECTRONICO(), "http://localhost:" + puerto + "/WEBArriendos/ConfirmacionPagoReservaController?token=" + encriptar(String.valueOf(reserva.getID_RESERVA())));
            String linkPago = flow_response.getUrl().getValue() + "?token=" + flow_response.getToken().getValue();
            
            request.setAttribute("linkPago", linkPago);
            request.setAttribute("reserva", reserva);
            request.getRequestDispatcher("JSP-Pages/ControlReservas/PagoReserva.jsp").forward(request, response);
        }
      
        if (request.getParameter("btnServicios") != null){
            int reserva_id = Integer.parseInt(request.getParameter("reserva_id"));
            request.setAttribute("reserva_id", reserva_id);
            ServicioExtraDTO servicioExtra = new ServicioExtraDTO();
            ArrayList<ServicioExtraDTO> serviciosReserva = servicioExtra.ListarServiciosReserva(reserva_id);
            ArrayList<ServicioExtraDTO> servicios = servicioExtra.ListarServicios();
            ArrayList<ServicioExtraDTO> servAux = new ArrayList<>();
            for (ServicioExtraDTO servicio : servicios) {
                boolean exist = false;
                for (ServicioExtraDTO serv : serviciosReserva) {
                    if (serv.getID() == servicio.getID()){
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
        
        if (request.getParameter("btnContratarServicio") != null){
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
                    if (serv.getID() == servicio.getID()){
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
        
        if (request.getParameter("btnQuitarServicio") != null){
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
                    if (serv.getID() == servicio.getID()){
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
        
        if (request.getParameter("siCancelar") != null){
            ReservaDTO reserva = new ReservaDTO();
            int reservaId = Integer.parseInt(request.getParameter("reserva_id"));
            reserva.cancelarReserva(reservaId);
            
            request.setAttribute("lstReservasTodas", reserva.listarTodasReservasUsuario(usudto.getDNI()));
            request.setAttribute("lstReservasFuturas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "RESERVADO"));
            request.setAttribute("lstReservasActivas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKIN-LISTO"));
            request.setAttribute("lstReservasAPagar", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKOUT-LISTO"));
            request.getRequestDispatcher("JSP-Pages/ControlReservas/ListarReservas.jsp").forward(request, response);
        }
        if (request.getParameter("noCancelar") != null){
            ReservaDTO reserva = new ReservaDTO();
            request.setAttribute("lstReservasTodas", reserva.listarTodasReservasUsuario(usudto.getDNI()));
            request.setAttribute("lstReservasFuturas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "RESERVADO"));
            request.setAttribute("lstReservasActivas", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKIN-LISTO"));
            request.setAttribute("lstReservasAPagar", reserva.listarReservasUsuarioEstado(usudto.getDNI(), "CHECKOUT-LISTO"));
            request.getRequestDispatcher("JSP-Pages/ControlReservas/ListarReservas.jsp").forward(request, response);
        }
    }
    private static String encriptar(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
    

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
