/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Service.AcompanianteService;
import WS.Acompaniante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oskardashh
 */
public class AcompanianteDTO {
    
    private String DNI;
    private String NOMBRE_COMPLETO;
    private String EXTRANJERO;
    private String CORREO;
    private int TELEFONO;

    public AcompanianteDTO() {
    }

    public AcompanianteDTO(String DNI, String NOMBRE_COMPLETO, String EXTRANJERO, String CORREO, int TELEFONO) {
        this.DNI = DNI;
        this.NOMBRE_COMPLETO = NOMBRE_COMPLETO;
        this.EXTRANJERO = EXTRANJERO;
        this.CORREO = CORREO;
        this.TELEFONO = TELEFONO;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNOMBRE_COMPLETO() {
        return NOMBRE_COMPLETO;
    }

    public void setNOMBRE_COMPLETO(String NOMBRE_COMPLETO) {
        this.NOMBRE_COMPLETO = NOMBRE_COMPLETO;
    }

    public String getEXTRANJERO() {
        return EXTRANJERO;
    }

    public void setEXTRANJERO(String EXTRANJERO) {
        this.EXTRANJERO = EXTRANJERO;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public int getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(int TELEFONO) {
        this.TELEFONO = TELEFONO;
    }
    
    public AcompanianteDTO BuscarAcompaniante(String dni) {
        Acompaniante bd_acomp = AcompanianteService.buscarAcompaniante(dni);
        return new AcompanianteDTO(bd_acomp.getDNI().getValue(), bd_acomp.getNOMBRECOMPLETO().getValue(), bd_acomp.getEXTRANJERO().getValue(), bd_acomp.getCORREO().getValue(), bd_acomp.getTELEFONO().intValue());
    }
    
    public boolean crearAcompanianteReserva(String dni, int res) {
        return AcompanianteService.crearAcompanianteReserva(dni, res);
    }
    
    public ArrayList<AcompanianteDTO> BuscarAcompaniantesReserva(int id_reserva) {
        List<Acompaniante> acompaniantes = AcompanianteService.listarAcompaniatesReserva(id_reserva).getAcompaniante();
        ArrayList<AcompanianteDTO> acomps = new ArrayList<>();
        for (Acompaniante acompaniante : acompaniantes) {
            AcompanianteDTO aco = new AcompanianteDTO();
            aco.setCORREO(acompaniante.getCORREO().getValue());
            aco.setDNI(acompaniante.getDNI().getValue());
            aco.setEXTRANJERO(acompaniante.getEXTRANJERO().getValue());
            aco.setNOMBRE_COMPLETO(acompaniante.getNOMBRECOMPLETO().getValue());
            aco.setTELEFONO(acompaniante.getTELEFONO());
            acomps.add(aco);
        }
        
        return acomps;
    }
    
    public boolean borrarAcompanianteReserva(int reserva, String dni) {
        return AcompanianteService.borrarAcompanianteReserva(reserva, dni);
    }
    
}