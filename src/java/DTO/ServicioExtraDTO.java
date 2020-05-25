/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

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
    
    
}
