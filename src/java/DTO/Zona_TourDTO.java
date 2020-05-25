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
public class Zona_TourDTO {
    
    private int ID_ZONA;
    private String NOMBRE_ZONA;
    private int CIUDAD_ID;

    public Zona_TourDTO() {
    }

    public Zona_TourDTO(int ID_ZONA, String NOMBRE_ZONA, int CIUDAD_ID) {
        this.ID_ZONA = ID_ZONA;
        this.NOMBRE_ZONA = NOMBRE_ZONA;
        this.CIUDAD_ID = CIUDAD_ID;
    }

    public int getID_ZONA() {
        return ID_ZONA;
    }

    public void setID_ZONA(int ID_ZONA) {
        this.ID_ZONA = ID_ZONA;
    }

    public String getNOMBRE_ZONA() {
        return NOMBRE_ZONA;
    }

    public void setNOMBRE_ZONA(String NOMBRE_ZONA) {
        this.NOMBRE_ZONA = NOMBRE_ZONA;
    }

    public int getCIUDAD_ID() {
        return CIUDAD_ID;
    }

    public void setCIUDAD_ID(int CIUDAD_ID) {
        this.CIUDAD_ID = CIUDAD_ID;
    }
    
    
}
