/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Service.AcompanianteService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oskardashh
 */
@WebServlet(name = "AcompanianteController", urlPatterns = {"/AcompanianteController"})
public class AcompanianteController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AcompanianteController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AcompanianteController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        // processRequest(request, response);
        if (request.getParameter("btnRegistroAcomp") != null) {

            ArrayList<String> Error = new ArrayList<String>();
            boolean isCorrect = true;
           /* String DNI = request.getParameter("Dni");
            String NOMBRE_COMPLETO = request.getParameter("NombreAcomp");
            char EXTRANJERO = request.getParameter("Extranjero");
        */
            String DNI = request.getParameter("Dni");
            String NOMBRE_COMPLETO = request.getParameter("NombreAcomp");
            
           // String EXTRANJERO = request.getParameterValues("Extranjero");
           //OPERADOR TERNARIO
            //String EXTRANJERO = request.getParameterValues("Extranjero") == true ? "1" : "0";

            String CORREO = request.getParameter("Correo");
            String TELEFONO = request.getParameter("Telefono");
            
            if (DNI.trim().equals("")){
                Error.add("Rut no puede estar vacío");
                isCorrect = false;
            } else {
                if(!validarDNI(DNI)){
                    Error.add("Rut no válido");
                    isCorrect = false;
                }
            }
            if(NOMBRE_COMPLETO.trim().equals("")){
                Error.add("El Nombre no puede quedar Vacíó");
                isCorrect = false;
            }
            if(CORREO.trim().equals("")){
                Error.add("El Correo no puede quedar Vacíó");
                isCorrect = false;
            }
            if(request.getParameter("TELEFONO").trim().equals("")){
                Error.add("El Teléfono no puede quedar Vacíó");
                isCorrect = false;
            }
            if(isCorrect) {
                request.setAttribute("Error", "Registro de Acompañante Exitoso!");
                request.getRequestDispatcher("Reserva.jsp").forward(request, response);
                AcompanianteService.crearAcompaniante(DNI, NOMBRE_COMPLETO, CORREO, CORREO, Integer.SIZE);
            } else {
                request.setAttribute("Error", Error);
                request.setAttribute("DNI", DNI);
                request.setAttribute("NOMBRE_COMPLETO", NOMBRE_COMPLETO);
                request.setAttribute("CORREO", CORREO);
                request.setAttribute("TELEFONO", TELEFONO);
                request.getRequestDispatcher("/Registro_acompa.jsp").forward(request, response);
                
            }
          
         }

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
