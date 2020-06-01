/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.ArrayOfDepartamento;
import WS.Departamento;
import WS.Reserva;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Oskardashh
 */
public class DepartamentoService {

    public static ArrayOfDepartamento departamentosDisponiblesPorFecha(javax.xml.datatype.XMLGregorianCalendar inicio, javax.xml.datatype.XMLGregorianCalendar fin, java.lang.Integer idciudad) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.departamentosDisponiblesPorFecha(inicio, fin, idciudad);
    }

    public static Boolean contratoReservaDepartamento(java.lang.Integer idReserva, java.lang.Integer idDepartamento) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.contratoReservaDepartamento(idReserva, idDepartamento);
    }

    public static ArrayOfDepartamento departamentosReserva(java.lang.Integer idreserva) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.departamentosReserva(idreserva);
    }

}
