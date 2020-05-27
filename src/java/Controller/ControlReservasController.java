/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.MultaDTO;
import DTO.ReservaDTO;
import DTO.UsuarioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
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
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
