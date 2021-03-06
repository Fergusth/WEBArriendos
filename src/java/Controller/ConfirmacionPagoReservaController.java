/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.MultaDTO;
import DTO.ReservaDTO;
import DTO.UsuarioDTO;
import Service.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
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
@WebServlet(name = "ConfirmacionPagoReservaController", urlPatterns = {"/ConfirmacionPagoReservaController"})
public class ConfirmacionPagoReservaController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String parametro = request.getParameter("token");
        HttpSession Session = request.getSession(true);
        UsuarioDTO usudto = (UsuarioDTO) Session.getAttribute("cliente");
        boolean existe = UsuarioService.existeUsuario(usudto.getDNI());
        if (!existe) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        int idReserva = Integer.parseInt(desencriptar(parametro));
        ReservaDTO reserva = new ReservaDTO();
        reserva.pagarReservaTotal(idReserva);

        request.getRequestDispatcher("JSP-Pages/ControlReservas/PagoConfirmado.jsp").forward(request, response);
    }

    private static String desencriptar(String s) throws UnsupportedEncodingException {
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
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
