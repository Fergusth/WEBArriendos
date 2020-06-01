/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.UsuarioDTO;
import Service.UsuarioService;
import WS.Usuario;
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
@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {
    UsuarioService service = new UsuarioService();
   


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
        //request.getRequestDispatcher("index.jsp").forward(request, response);
        HttpSession Session = request.getSession(true);
        if (Session.getAttribute("cliente") != null){
            UsuarioDTO usudto = (UsuarioDTO) Session.getAttribute("cliente");
            request.getRequestDispatcher("Inicio.jsp").forward(request, response);
        } else{
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
        if(request.getParameter("btnIniciar") != null){
            String dni = request.getParameter("txtDNI");
            String pass = request.getParameter("txtPassword");
            
            Usuario us = service.login(dni, pass);
            if (us.getROLIDROL().byteValue() == 0) {
                request.setAttribute("error", "Usuario y contraseña no coinciden");
            } else {
                if (us.getROLIDROL().byteValue() == 1) {
                    request.setAttribute("error", "No se permite este tipo de usuario");
                }
                if (us.getROLIDROL().byteValue() == 2) {
                    request.setAttribute("error", "No se permite este tipo de usuario");
                }
                if (us.getROLIDROL().byteValue() == 3) {
                    request.setAttribute("nombreUsuario", us.getNOMBRE().getValue());
                    HttpSession session = request.getSession();
                    UsuarioDTO usuario = new UsuarioDTO(
                            us.getDNI().getValue(),
                            us.getNOMBRE().getValue(),
                            us.getAPELLIDOS().getValue(),
                            us.getCORREOELECTRONICO().getValue(),
                            us.getDIRECCION().getValue(),
                            us.getTELEFONO(),
                            us.getROLIDROL().toString(),
                            us.getEXTRANJERO().getValue(),
                            us.getFRECUENTE().getValue()
                    );
                    session.setAttribute("cliente", usuario);
                    
                    request.getRequestDispatcher("Inicio.jsp").forward(request, response);
                }
            }
            
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
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
