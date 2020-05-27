/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Service.ReservaService;
import WS.Reserva;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author oskardashh
 */
public class ReservaDTO {
    
    public int ID_RESERVA;
    public Date FECHA_CHECKIN;
    public Date FECHA_CHECKOUT;
    public String HORA_CHECKIN;
    public String HORA_CHECKOUT;
    public String USUARIO_DNI;
    public String ESTADO;

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
    public int PRECIO_TOTAL;
    public int TOTAL_PAGADO;
    public String FORMA_PAGO;

    public ReservaDTO() {
    }

    public String getHORA_CHECKOUT() {
        return HORA_CHECKOUT;
    }

    public void setHORA_CHECKOUT(String HORA_CHECKOUT) {
        this.HORA_CHECKOUT = HORA_CHECKOUT;
    }

    public ReservaDTO(int ID_RESERVA, Date FECHA_CHECKIN, Date FECHA_CHECKOUT, String HORA_CHECKIN, String HORA_CHECKOUT, String USUARIO_DNI, int PRECIO_TOTAL, int TOTAL_PAGADO, String FORMA_PAGO) {
        this.ID_RESERVA = ID_RESERVA;
        this.FECHA_CHECKIN = FECHA_CHECKIN;
        this.FECHA_CHECKOUT = FECHA_CHECKOUT;
        this.HORA_CHECKIN = HORA_CHECKIN;
        this.HORA_CHECKOUT = HORA_CHECKOUT;
        this.USUARIO_DNI = USUARIO_DNI;
        this.PRECIO_TOTAL = PRECIO_TOTAL;
        this.TOTAL_PAGADO = TOTAL_PAGADO;
        this.FORMA_PAGO = FORMA_PAGO;
    }
    

    public int getID_RESERVA() {
        return ID_RESERVA;
    }

    public void setID_RESERVA(int ID_RESERVA) {
        this.ID_RESERVA = ID_RESERVA;
    }

    public Date getFECHA_CHECKIN() {
        return FECHA_CHECKIN;
    }

    public void setFECHA_CHECKIN(Date FECHA_CHECKIN) {
        this.FECHA_CHECKIN = FECHA_CHECKIN;
    }

    public Date getFECHA_CHECKOUT() {
        return FECHA_CHECKOUT;
    }

    public void setFECHA_CHECKOUT(Date FECHA_CHECKOUT) {
        this.FECHA_CHECKOUT = FECHA_CHECKOUT;
    }

    public String getHORA_CHECKIN() {
        return HORA_CHECKIN;
    }

    public void setHORA_CHECKIN(String HORA_CHECKIN) {
        this.HORA_CHECKIN = HORA_CHECKIN;
    }

    public String getUSUARIO_DNI() {
        return USUARIO_DNI;
    }

    public void setUSUARIO_DNI(String USUARIO_DNI) {
        this.USUARIO_DNI = USUARIO_DNI;
    }

    public int getPRECIO_TOTAL() {
        return PRECIO_TOTAL;
    }

    public void setPRECIO_TOTAL(int PRECIO_TOTAL) {
        this.PRECIO_TOTAL = PRECIO_TOTAL;
    }

    public int getTOTAL_PAGADO() {
        return TOTAL_PAGADO;
    }

    public void setTOTAL_PAGADO(int TOTAL_PAGADO) {
        this.TOTAL_PAGADO = TOTAL_PAGADO;
    }

    public String getFORMA_PAGO() {
        return FORMA_PAGO;
    }

    public void setFORMA_PAGO(String FORMA_PAGO) {
        this.FORMA_PAGO = FORMA_PAGO;
    }
    
    public ArrayList<ReservaDTO> listarTodasReservasUsuario(String dni) {
        List<Reserva> bdreservas = ReservaService.listarReservasUsuario(dni).getReserva();
        ArrayList<ReservaDTO> reservas = new ArrayList<>();
        for (Reserva reserva : bdreservas) {
            ReservaDTO r = new ReservaDTO();
            r.setFECHA_CHECKIN(reserva.getFECHACHECKIN().toGregorianCalendar().getTime());
            r.setFECHA_CHECKOUT(reserva.getFECHACHECKOUT().toGregorianCalendar().getTime());
            r.setFORMA_PAGO(reserva.getFORMAPAGO().getValue());
            r.setHORA_CHECKIN(reserva.getHORACHECKIN().getValue());
            r.setID_RESERVA(reserva.getIDRESERVA());
            r.setPRECIO_TOTAL(reserva.getPRECIOTOTAL());
            r.setTOTAL_PAGADO(reserva.getTOTALPAGADO());
            r.setUSUARIO_DNI(reserva.getUSUARIODNI().getValue());
            r.setHORA_CHECKOUT(reserva.getHORACHECKOUT().getValue());
            r.setESTADO(reserva.getESTADO().getValue());
            reservas.add(r);
        }
        return reservas;
    }
    
    public ArrayList<ReservaDTO> listarReservasUsuarioEstado(String dni, String estado) {
        List<Reserva> bdreservas = ReservaService.listarReservasUsuarioEstado(dni, estado).getReserva();
        ArrayList<ReservaDTO> reservas = new ArrayList<>();
        for (Reserva reserva : bdreservas) {
            ReservaDTO r = new ReservaDTO();
            r.setFECHA_CHECKIN(reserva.getFECHACHECKIN().toGregorianCalendar().getTime());
            r.setFECHA_CHECKOUT(reserva.getFECHACHECKOUT().toGregorianCalendar().getTime());
            r.setFORMA_PAGO(reserva.getFORMAPAGO().getValue());
            r.setHORA_CHECKIN(reserva.getHORACHECKIN().getValue());
            r.setID_RESERVA(reserva.getIDRESERVA());
            r.setPRECIO_TOTAL(reserva.getPRECIOTOTAL());
            r.setTOTAL_PAGADO(reserva.getTOTALPAGADO());
            r.setUSUARIO_DNI(reserva.getUSUARIODNI().getValue());
            r.setHORA_CHECKOUT(reserva.getHORACHECKOUT().getValue());
            r.setESTADO(reserva.getESTADO().getValue());
            reservas.add(r);
        }
        return reservas;
    }
    
    private static XMLGregorianCalendar getXmlGregorianCalendarFromDate(final Date date) throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

    }
    
}
