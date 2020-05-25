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

    public static Reserva crearReserva(javax.xml.datatype.XMLGregorianCalendar fECHACHECKIN, javax.xml.datatype.XMLGregorianCalendar fECHACHECKOUT, java.lang.String hORACHECKIN, java.lang.String hORACHECKOUT, java.lang.String uSUARIODNI, java.lang.Integer pRECIOTOTAL, java.lang.Integer tOTALPAGADO, java.lang.String eSTADO, java.lang.String fORMAPAGO) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.crearReserva(fECHACHECKIN, fECHACHECKOUT, hORACHECKIN, hORACHECKOUT, uSUARIODNI, pRECIOTOTAL, tOTALPAGADO, eSTADO, fORMAPAGO);
    }

    
    
    
}
