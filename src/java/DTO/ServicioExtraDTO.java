/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Service.ServiciosExtraService;
import WS.ServicioExtra;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oskardashh
 */
public class ServicioExtraDTO {
    
    private int ID;
    private String NOMBRE_SERVICIO;
    private int PRECIO_ACTUAL;
    private char ACTIVADO;

    public ServicioExtraDTO() {
    }

    public ServicioExtraDTO(int ID, String NOMBRE_SERVICIO, int PRECIO_ACTUAL, char ACTIVADO) {
        this.ID = ID;
        this.NOMBRE_SERVICIO = NOMBRE_SERVICIO;
        this.PRECIO_ACTUAL = PRECIO_ACTUAL;
        this.ACTIVADO = ACTIVADO;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNOMBRE_SERVICIO() {
        return NOMBRE_SERVICIO;
    }

    public void setNOMBRE_SERVICIO(String NOMBRE_SERVICIO) {
        this.NOMBRE_SERVICIO = NOMBRE_SERVICIO;
    }

    public int getPRECIO_ACTUAL() {
        return PRECIO_ACTUAL;
    }

    public void setPRECIO_ACTUAL(int PRECIO_ACTUAL) {
        this.PRECIO_ACTUAL = PRECIO_ACTUAL;
    }

    public char getACTIVADO() {
        return ACTIVADO;
    }

    public void setACTIVADO(char ACTIVADO) {
        this.ACTIVADO = ACTIVADO;
    }
    
    public ArrayList<ServicioExtraDTO> ListarServiciosReserva(int id_reserva) {
        List<ServicioExtra> bd_servicios = ServiciosExtraService.listarServicioExtraReserva(id_reserva).getServicioExtra();
        ArrayList<ServicioExtraDTO> servicios = new ArrayList<>();
        
        for (ServicioExtra servicio : bd_servicios) {
            ServicioExtraDTO serv = new ServicioExtraDTO();
            serv.setID(servicio.getID());
            serv.setNOMBRE_SERVICIO(servicio.getNOMBRESERVICIOEX().getValue());
            serv.setPRECIO_ACTUAL(servicio.getPRECIOACTUAL());
            
            servicios.add(serv);
        }
        
        
        return servicios;
    }
    
    public ArrayList<ServicioExtraDTO> ListarServicios() {
        List<ServicioExtra> bd_servicios = ServiciosExtraService.listarServiciosExtra().getServicioExtra();
        ArrayList<ServicioExtraDTO> servicios = new ArrayList<>();
        
        for (ServicioExtra servicio : bd_servicios) {
            ServicioExtraDTO serv = new ServicioExtraDTO();
            serv.setID(servicio.getID());
            serv.setNOMBRE_SERVICIO(servicio.getNOMBRESERVICIOEX().getValue());
            serv.setPRECIO_ACTUAL(servicio.getPRECIOACTUAL());
            
            servicios.add(serv);
        }
        
        
        return servicios;
    }
    
    public boolean contratarServicioExtra(int reserva_id, int id_servicio, boolean con_reserva) {
        return ServiciosExtraService.contratarServicioExtra((short)id_servicio, (short)reserva_id, con_reserva);
    }
    
    public boolean cancelarServicioExtra(int reserva_id, int id_servicio) {
        return ServiciosExtraService.cancelarServicio((short)id_servicio, (short)reserva_id);
    }
}
