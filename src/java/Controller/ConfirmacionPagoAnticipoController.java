/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.AcompanianteDTO;
import DTO.DepartamentoDTO;
import DTO.ReservaDTO;
import DTO.ServicioExtraDTO;
import DTO.ServicioTourDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
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
@WebServlet(name = "ConfirmacionPagoAnticipoController", urlPatterns = {"/ConfirmacionPagoAnticipoController"})
public class ConfirmacionPagoAnticipoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parametro = request.getParameter("token");
        int id_reserva = Integer.parseInt(desencriptar(parametro.split(";")[0]));
        int pago = Integer.parseInt(desencriptar(parametro.split(";")[1]));
        ReservaDTO res = new ReservaDTO();
        HttpSession Session = request.getSession(true);
        
        ArrayList<ServicioTourDTO> servicios_tours = (ArrayList<ServicioTourDTO>) Session.getAttribute("servicios_tours");
        ArrayList<ServicioExtraDTO> servicios_extra = (ArrayList<ServicioExtraDTO>) Session.getAttribute("servicios_extra");
        ArrayList<AcompanianteDTO> acompaniantes = (ArrayList<AcompanianteDTO>) Session.getAttribute("acompaniantes");
        ArrayList<DepartamentoDTO> departamentos = (ArrayList<DepartamentoDTO>) Session.getAttribute("depasSelect");

        for (DepartamentoDTO departamento : departamentos) {
            new DepartamentoDTO().contratoDepartamentoReserva(departamento.getID(), id_reserva);
        }
        for (ServicioExtraDTO servicioExtraDTO : servicios_extra) {
            new ServicioExtraDTO().contratarServicioExtra(id_reserva, servicioExtraDTO.getID(), false);
        }
        for (ServicioTourDTO servicios_tour : servicios_tours) {
            new ServicioTourDTO().contratarServicioTour(id_reserva, servicios_tour.getID_TOUR());
        }
        for (AcompanianteDTO acompaniante : acompaniantes) {
            new AcompanianteDTO().crearAcompanianteReserva(acompaniante.getDNI(), id_reserva);
        }

        res.pagarReservaAnticipo(id_reserva, pago);
        request.getRequestDispatcher("JSP-Pages/PagoConfirmadoAnticipo.jsp").forward(request, response);
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
