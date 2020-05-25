/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Oskardashh
 */
public class MultasDTO {
    
    private int ID_MULTA;
    private int TOTAL_MULTA;
    private int TOTAL_PAGADO;
    private char PAGADO;
    private String DESCRIPCION;
    private int RESERVA_ID;

    public MultasDTO() {
    }

    public MultasDTO(int ID_MULTA, int TOTAL_MULTA, int TOTAL_PAGADO, char PAGADO, String DESCRIPCION, int RESERVA_ID) {
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

    public char getPAGADO() {
        return PAGADO;
    }

    public void setPAGADO(char PAGADO) {
        this.PAGADO = PAGADO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getRESERVA_ID() {
        return RESERVA_ID;
    }

    public void setRESERVA_ID(int RESERVA_ID) {
        this.RESERVA_ID = RESERVA_ID;
    }
    
    
    //SERVICIO PARA MULTAS
    
    
    
}
