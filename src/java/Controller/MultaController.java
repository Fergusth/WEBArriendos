/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.MultaDTO;
import DTO.UsuarioDTO;
import Service.PagoService;
import Service.UsuarioService;
import WS.FlowResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
 * @author oskardashh
 */
@WebServlet(name = "MultaController", urlPatterns = {"/MultaController"})
public class MultaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession Session = request.getSession(true);
        if (Session.getAttribute("cliente") != null){
            UsuarioDTO usudto = (UsuarioDTO) Session.getAttribute("cliente");
            boolean existe = UsuarioService.existeUsuario(usudto.getDNI());
            if (!existe) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            MultaDTO multa = new MultaDTO();
            request.setAttribute("tieneMultasImpagas", multa.tieneMultaImpagas(usudto.getDNI()));
            request.setAttribute("tieneMultas", multa.tieneMulta(usudto.getDNI()));
            request.setAttribute("lstMultasImpagas", multa.ListarMultasImpagasUsuario(usudto.getDNI()));
            request.setAttribute("lstMultas", multa.ListarMultasUsuario(usudto.getDNI()));
            request.getRequestDispatcher("JSP-Pages/Multas/ListarMultas.jsp").forward(request, response);
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
    private static String encriptar(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btnPago") != null) {
            HttpSession Session = request.getSession(true);
            if (Session.getAttribute("cliente") != null){
                UsuarioDTO usudto = (UsuarioDTO) Session.getAttribute("cliente");
                boolean existe = UsuarioService.existeUsuario(usudto.getDNI());
                if (!existe) {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                MultaDTO multa = new MultaDTO();
                request.setAttribute("tieneMultasImpagas", multa.tieneMulta(usudto.getDNI()));
                request.setAttribute("lstMultasImpagas", multa.ListarMultasImpagasUsuario(usudto.getDNI()));
                request.setAttribute("lstMultas", multa.ListarMultasUsuario(usudto.getDNI()));

                List<MultaDTO> bd_multas = multa.ListarMultasImpagasUsuario(usudto.getDNI());
                ArrayList<MultaDTO> multas = new ArrayList<>();
                int total_multa = 0;
                String multasId = "";
                boolean haMarcado = false;
                for (MultaDTO mul : bd_multas) {
                    if (request.getParameter("check-" + mul.getID_MULTA()) != null) {
                        multas.add(mul);
                        total_multa = total_multa + mul.getTOTAL_MULTA();
                        multasId = multasId + encriptar(String.valueOf(mul.getID_MULTA())) + ";";
                        haMarcado = true;
                    }
                }
                if (haMarcado) {
                    int puerto = request.getLocalPort();
                    FlowResponse flow_response = PagoService.generarLinkPago(total_multa, usudto.getCORREO_ELECTRONICO(), "http://localhost:" + puerto + "/WEBArriendos/ConfirmacionMultaController?token=" + multasId);
                    String linkPago = flow_response.getUrl().getValue() + "?token=" + flow_response.getToken().getValue();
  
                    request.setAttribute("linkPago", linkPago);
                    request.setAttribute("totalMulta", total_multa);
                    request.setAttribute("lstMultasPorPagar", multas);
                    request.getRequestDispatcher("JSP-Pages/Multas/PagoMultas.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Debe marcar al menos una multa");
                    request.getRequestDispatcher("JSP-Pages/Multas/ListarMultas.jsp").forward(request, response);
                }
            } else{
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
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
