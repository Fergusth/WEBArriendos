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
public class AcompanianteDTO {
    
    private String DNI;
    private String NOMBRE_COMPLETO;
    private char EXTRANJERO;
    private String CORREO;
    private int TELEFONO;

    public AcompanianteDTO() {
    }

    public AcompanianteDTO(String DNI, String NOMBRE_COMPLETO, char EXTRANJERO, String CORREO, int TELEFONO) {
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

    public char getEXTRANJERO() {
        return EXTRANJERO;
    }

    public void setEXTRANJERO(char EXTRANJERO) {
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
    
    
    
    
    
}
