package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import DTO.ServicioExtraDTO;
import DTO.UsuarioDTO;
import Service.DepartamentoService;
import Service.ReservaService;
import Service.ServiciosExtraService;
import WS.Ciudad;
import WS.Departamento;
import WS.ServicioExtra;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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
        Inicializar(request, response);
        HttpSession Session = request.getSession(true);
        if (Session.getAttribute("cliente") != null) {
            UsuarioDTO userDTO = (UsuarioDTO) Session.getAttribute("cliente");
            request.setAttribute("nombreUsuario", userDTO.getNOMBRE());
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        request.getRequestDispatcher("JSP-Pages/Reserva2.jsp").forward(request, response);
    }

    public void Inicializar(HttpServletRequest request, HttpServletResponse response) {
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
        //processRequest(request, response);
        /*
        CiudadDTO ciud = new CiudadDTO();
        List<CiudadDTO> ciudades = ciud.ListarCiudades();
        request.setAttribute("ciudades", ciudades);
         */

        //Filtrar Departamentos disponibles:
        if (request.getParameter("btn_filtrar") != null) {
            Inicializar(request, response);
            HttpSession Session = request.getSession(true);
            if (Session.getAttribute("cliente") != null) {
                UsuarioDTO USR = (UsuarioDTO) Session.getAttribute("cliente");
                request.setAttribute("nombreUsuario", USR.getNOMBRE());
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);

            }
            try {

                String startDateStr = request.getParameter("fechaDesde");
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

                Date startDate = sdf1.parse(startDateStr);

                String endDateStr = request.getParameter("fechaHasta");
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

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
                    dpdto.setINTERNET(depart.getINTERNET().getValue() == "0" ? "No" : "Si");
                    dpdto.setCALEFACCION(depart.getCALEFACCION().getValue() == "0" ? "No" : "Si");

                    depaDTO.add(dpdto);

                }
                request.setAttribute("fechaDesde", request.getParameter("fechaDesde"));
                request.setAttribute("fechaHasta", request.getParameter("fechaHasta"));
                request.setAttribute("departamentos", depaDTO);
                request.getRequestDispatcher("JSP-Pages/Reserva2.jsp").forward(request, response);

            } catch (ParseException ex) {
                request.setAttribute("fechaDesde", "ERROR FECHA INICIO");
            } catch (DatatypeConfigurationException ex) {
                request.setAttribute("fechaHastaa", "ERROR FECHA TERMINO");
            }
            request.getRequestDispatcher("JSP-Pages/Reserva2.jsp").forward(request, response);
        }

        if (request.getParameter("BTN_RESERVAR") != null) {
            HttpSession Session = request.getSession(true);
            if (Session.getAttribute("cliente") != null) {
                UsuarioDTO us = (UsuarioDTO) Session.getAttribute("cliente");
                request.setAttribute("nombreUsuario", us.getNOMBRE());
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            DatosReserva(request, response);

        }
        if (request.getParameter("reservaPago") != null) {
          /*  try {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

               // int departId = Integer.parseInt(request.getParameter("idDepartamento"));
                Date fechaDesde = dateFormat.parse(request.getParameter("fechaDesde"));
                Date fechaHasta = dateFormat.parse(request.getParameter("fechaHasta"));
                String horaIn = "09:00AM";
                String horaSa = "09:00AM";
                String dniUsuario = "";
                int precio = Integer.parseInt(request.getParameter("precioDiario"));
                int totalPagado = (precio - Integer.parseInt(request.getParameter("totalPagado")));
                String estado = "FaltaPago";
                String formaPago = "MasterCard";

                HttpSession Session = request.getSession(true);
                if (Session.getAttribute("cliente") != null) {
                    UsuarioDTO udto = (UsuarioDTO) Session.getAttribute("cliente");
                    dniUsuario = udto.getDNI();
                }
                //ARREGLAR HORAS!!!!
                
                //if (DepartamentoService.crearReserva(getXmlGregorianCalendarFromDate(fechaDesde), getXmlGregorianCalendarFromDate(fechaHasta), horaIn, horaSa, dniUsuario, precio, totalPagado, estado, formaPago)) {

               // } else {
                    request.getRequestDispatcher("ReservarDepartamento.jsp").forward(request, response);

                }

           // } catch (ParseException ex) {
          //      Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);

          //  } catch (DatatypeConfigurationException ex) {
           //     Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
          //  }*/
        }
    }

    public void DatosReserva(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<ServicioExtraDTO> ServiceExt = new ArrayList<>();
        List<ServicioExtra> ServiciosEx = ServiciosExtraService.listarServiciosExtra().getServicioExtra();
        for (ServicioExtra Servi : ServiciosEx) {
            ServicioExtraDTO svdto = new ServicioExtraDTO();
            svdto.setID(Servi.getID());
            svdto.setNOMBRE_SERVICIO(Servi.getNOMBRESERVICIOEX().getValue());
            svdto.setPRECIO_ACTUAL(Servi.getPRECIOACTUAL().intValue());
            ServiceExt.add(svdto);

        }

        request.setAttribute("ServiceExt", ServiceExt);
        request.setAttribute("fechaDesde", request.getParameter("fechaDesde"));
        request.setAttribute("fechaHasta", request.getParameter("fechaHasta"));
        request.setAttribute("idDepartamento", request.getParameter("idDepartamento"));
        request.setAttribute("direccion", request.getParameter("direccion"));
        request.setAttribute("precioDiario", request.getParameter("precioDiario"));
        request.setAttribute("cantBanios", request.getParameter("cantBanios"));
        request.setAttribute("cantHabit", request.getParameter("cantHabit"));
        request.setAttribute("descripcion", request.getParameter("descripcion"));
        request.setAttribute("internet", request.getParameter("internet"));
        request.setAttribute("calefaccion", request.getParameter("calefaccion"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date checkIn = dateFormat.parse(request.getParameter("fechaDesde"));
            Date checkOut = dateFormat.parse(request.getParameter("fechaHasta"));
            int precio = Integer.parseInt(request.getParameter("precioDiario"));
            int dias = (int) ((checkOut.getTime() - checkIn.getTime()) / 86400000);
            request.setAttribute("total", dias * precio);
            request.getRequestDispatcher("ReservarDepartamento.jsp").forward(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static XMLGregorianCalendar getXmlGregorianCalendarFromDate(final Date date) throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

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
