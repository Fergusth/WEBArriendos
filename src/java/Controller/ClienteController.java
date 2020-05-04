/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioDTO us = (UsuarioDTO) session.getAttribute("cliente");
        
        if (us != null) {
            request.setAttribute("nombreUsuario", us.getNOMBRE());
            request.getRequestDispatcher("JSP-Pages/welcome.jsp").forward(request, response);
        } else {
            // Este usuario se crea porque al guardar cambios, se borra la sesión, y para no
            // tener que iniciar sesión de nuevo, creo el usuario, en producción esto se borra :).
            UsuarioDTO us2 = new UsuarioDTO(
                "16599405-1",
                "Daniel Off",
                "Espinoza",
                "dani@gmail.com",
                "Las Manzanas #2, Los Ángeles",
                55864458,
                "3",
                "0",
                "1"
            );
            session.setAttribute("cliente", us2);
            
            request.setAttribute("nombreUsuario", us2.getNOMBRE());
            request.getRequestDispatcher("JSP-Pages/welcome.jsp").forward(request, response);
            // request.getRequestDispatcher("index.jsp").forward(request, response);
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
