/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Service.ServiciosTourService;
import WS.ServicioTour;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Gerald
 */
public class ServicioTourDTO {
    public short ID_TOUR;
    public String HORA_LLEGADA ;
    public String HORA_SALIDA;
    public int PRECIO_ACTUAL;
    public String DISPONIBLE ;
    public Date FECHA;
    public String ZONA_TOUR ;

    public ServicioTourDTO() {
    }

    public ServicioTourDTO(short ID_TOUR, String HORA_LLEGADA, String HORA_SALIDA, int PRECIO_ACTUAL, String DISPONIBLE, Date FECHA, String ZONA_TOUR) {
        this.ID_TOUR = ID_TOUR;
        this.HORA_LLEGADA = HORA_LLEGADA;
        this.HORA_SALIDA = HORA_SALIDA;
        this.PRECIO_ACTUAL = PRECIO_ACTUAL;
        this.DISPONIBLE = DISPONIBLE;
        this.FECHA = FECHA;
        this.ZONA_TOUR = ZONA_TOUR;
    }

    public short getID_TOUR() {
        return ID_TOUR;
    }

    public void setID_TOUR(short ID_TOUR) {
        this.ID_TOUR = ID_TOUR;
    }

    public String getHORA_LLEGADA() {
        return HORA_LLEGADA;
    }

    public void setHORA_LLEGADA(String HORA_LLEGADA) {
        this.HORA_LLEGADA = HORA_LLEGADA;
    }

    public String getHORA_SALIDA() {
        return HORA_SALIDA;
    }

    public void setHORA_SALIDA(String HORA_SALIDA) {
        this.HORA_SALIDA = HORA_SALIDA;
    }

    public int getPRECIO_ACTUAL() {
        return PRECIO_ACTUAL;
    }

    public void setPRECIO_ACTUAL(int PRECIO_ACTUAL) {
        this.PRECIO_ACTUAL = PRECIO_ACTUAL;
    }

    public String getDISPONIBLE() {
        return DISPONIBLE;
    }

    public void setDISPONIBLE(String DISPONIBLE) {
        this.DISPONIBLE = DISPONIBLE;
    }

    public Date getFECHA() {
        return FECHA;
    }

    public void setFECHA(Date FECHA) {
        this.FECHA = FECHA;
    }

    public String getZONA_TOUR() {
        return ZONA_TOUR;
    }

    public void setZONA_TOUR(String ZONA_TOUR) {
        this.ZONA_TOUR = ZONA_TOUR;
    }
    
    public ArrayList<ServicioTourDTO> buscarTourFecha(Date inicio, Date fin, int ciudad) throws DatatypeConfigurationException{
        XMLGregorianCalendar Start = getXmlGregorianCalendarFromDate(inicio);
        XMLGregorianCalendar End = getXmlGregorianCalendarFromDate(fin);
        ArrayList<ServicioTourDTO> servicios = new ArrayList<>(); 
        List<ServicioTour> bd_serv = ServiciosTourService.buscarToursPorFechaYCiudad(Start, End, ciudad).getServicioTour();
        for (ServicioTour serv : bd_serv) {
            ServicioTourDTO servi = new ServicioTourDTO();
            servi.setDISPONIBLE(serv.getDISPONIBLE().getValue());
            servi.setFECHA(serv.getFECHA().toGregorianCalendar().getTime());
            servi.setHORA_LLEGADA(serv.getHORALLEGADA().getValue());
            servi.setHORA_SALIDA(serv.getHORASALIDA().getValue());
            servi.setID_TOUR(serv.getIDTOUR());
            servi.setPRECIO_ACTUAL(serv.getPRECIOACTUAL());
            servi.setZONA_TOUR(serv.getZONATOUR().getValue());
            servicios.add(servi);
        }
        return servicios;
    }
    
    public ArrayList<ServicioTourDTO> ServiciosTourReserva(int idreserva){
        ArrayList<ServicioTourDTO> servicios = new ArrayList<>(); 
        List<ServicioTour> bd_serv = ServiciosTourService.listarServiciosTourReserva(idreserva).getServicioTour();
        
        for (ServicioTour serv : bd_serv) {
            ServicioTourDTO servi = new ServicioTourDTO();
            servi.setDISPONIBLE(serv.getDISPONIBLE().getValue());
            servi.setFECHA(serv.getFECHA().toGregorianCalendar().getTime());
            servi.setHORA_LLEGADA(serv.getHORALLEGADA().getValue());
            servi.setHORA_SALIDA(serv.getHORASALIDA().getValue());
            servi.setID_TOUR(serv.getIDTOUR());
            servi.setPRECIO_ACTUAL(serv.getPRECIOACTUAL());
            servi.setZONA_TOUR(serv.getZONATOUR().getValue());
            servicios.add(servi);
        }
        return servicios;
    }
    
    public boolean contratarServicioTour(int res, int tour) {
        return ServiciosTourService.contratarServicioTour((short)tour, (short)res);
    }
    
    private static XMLGregorianCalendar getXmlGregorianCalendarFromDate(final Date date) throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();

        calendar.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

    }
}
