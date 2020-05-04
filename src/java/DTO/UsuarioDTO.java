/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Gerald
 */
public class UsuarioDTO {
    
    public String DNI;
    public String NOMBRE;
    public String APELLIDOS;
    public String CORREO_ELECTRONICO;
    public String DIRECCION;
    public int TELEFONO;
    public String ROL;
    public String EXTRANJERO;
    public String FRECUENTE;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String DNI, String NOMBRE, String APELLIDOS, String CORREO_ELECTRONICO, String DIRECCION, int TELEFONO, String ROL, String EXTRANJERO, String FRECUENTE) {
        this.DNI = DNI;
        this.NOMBRE = NOMBRE;
        this.APELLIDOS = APELLIDOS;
        this.CORREO_ELECTRONICO = CORREO_ELECTRONICO;
        this.DIRECCION = DIRECCION;
        this.TELEFONO = TELEFONO;
        this.ROL = ROL;
        this.EXTRANJERO = EXTRANJERO;
        this.FRECUENTE = FRECUENTE;
    }
    
    
    
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public String getCORREO_ELECTRONICO() {
        return CORREO_ELECTRONICO;
    }

    public void setCORREO_ELECTRONICO(String CORREO_ELECTRONICO) {
        this.CORREO_ELECTRONICO = CORREO_ELECTRONICO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public int getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(int TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getROL() {
        return ROL;
    }

    public void setROL(String ROL) {
        this.ROL = ROL;
    }

    public String getEXTRANJERO() {
        return EXTRANJERO;
    }

    public void setEXTRANJERO(String EXTRANJERO) {
        this.EXTRANJERO = EXTRANJERO;
    }

    public String getFRECUENTE() {
        return FRECUENTE;
    }

    public void setFRECUENTE(String FRECUENTE) {
        this.FRECUENTE = FRECUENTE;
    }
    
}
