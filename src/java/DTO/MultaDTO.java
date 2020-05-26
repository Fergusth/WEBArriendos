/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Service.MultasService;
import WS.Multa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gerald
 */
public class MultaDTO {
    private int ID_MULTA;
    private int TOTAL_MULTA;
    private int TOTAL_PAGADO;
    private String PAGADO;
    private String DESCRIPCION;
    private short RESERVA_ID;

    public MultaDTO() {
    }

    public MultaDTO(int ID_MULTA, int TOTAL_MULTA, int TOTAL_PAGADO, String PAGADO, String DESCRIPCION, short RESERVA_ID) {
        this.ID_MULTA = ID_MULTA;
        this.TOTAL_MULTA = TOTAL_MULTA;
        this.TOTAL_PAGADO = TOTAL_PAGADO;
        this.PAGADO = PAGADO;
        this.DESCRIPCION = DESCRIPCION;
        this.RESERVA_ID = RESERVA_ID;
    }

    public int getID_MULTA() {
        return ID_MULTA;
    }

    public void setID_MULTA(int ID_MULTA) {
        this.ID_MULTA = ID_MULTA;
    }

    public int getTOTAL_MULTA() {
        return TOTAL_MULTA;
    }

    public void setTOTAL_MULTA(int TOTAL_MULTA) {
        this.TOTAL_MULTA = TOTAL_MULTA;
    }

    public int getTOTAL_PAGADO() {
        return TOTAL_PAGADO;
    }

    public void setTOTAL_PAGADO(int TOTAL_PAGADO) {
        this.TOTAL_PAGADO = TOTAL_PAGADO;
    }

    public String getPAGADO() {
        return PAGADO;
    }

    public void setPAGADO(String PAGADO) {
        this.PAGADO = PAGADO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public short getRESERVA_ID() {
        return RESERVA_ID;
    }

    public void setRESERVA_ID(short RESERVA_ID) {
        this.RESERVA_ID = RESERVA_ID;
    }
    
    public ArrayList<MultaDTO> ListarMultasUsuario(String dni){
        List<Multa> srv_multas = MultasService.listaMultasUsuario(dni).getMulta();
        ArrayList<MultaDTO> multas = new ArrayList<>();
        for (Multa srv_multa : srv_multas) {
            MultaDTO multa = new MultaDTO();
            multa.setDESCRIPCION(srv_multa.getDESCRIPCION().getValue() + " referente al estado que se entregó");
            multa.setID_MULTA(srv_multa.getIDMULTA().intValue());
            multa.setPAGADO(srv_multa.getPAGADO().getValue().equals("0") ? "No" : "Sí");
            multa.setTOTAL_MULTA(srv_multa.getTOTALMULTA().intValue());
            multa.setTOTAL_PAGADO(srv_multa.getTOTALPAGADO().intValue());
            multas.add(multa);
        }
        
        return multas;
    }
    
    public ArrayList<MultaDTO> ListarMultasImpagasUsuario(String dni){
        List<Multa> srv_multas = MultasService.listaMultasImpagasUsuario(dni).getMulta();
        ArrayList<MultaDTO> multas = new ArrayList<>();
        for (Multa srv_multa : srv_multas) {
            MultaDTO multa = new MultaDTO();
            multa.setDESCRIPCION(srv_multa.getDESCRIPCION().getValue() + " referente al estado que se entregó");
            multa.setID_MULTA(srv_multa.getIDMULTA().intValue());
            multa.setPAGADO(srv_multa.getPAGADO().getValue().equals("0") ? "No" : "Sí");
            multa.setTOTAL_MULTA(srv_multa.getTOTALMULTA().intValue());
            multa.setTOTAL_PAGADO(srv_multa.getTOTALPAGADO().intValue());
            multas.add(multa);
        }
        
        return multas;
    }
    
    public boolean tieneMulta(String dni){
        return ListarMultasImpagasUsuario(dni).size() > 0;
    }
    
    public boolean pagarMulta(int multa_id, int pago) {
        return MultasService.pagarMulta(multa_id, pago);
    }
}
