/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Service.ReservaService;
import WS.ArrayOfCiudad;
import WS.Ciudad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oskardashh
 */
public class CiudadDTO {
    
    public int ID;
    public String NOMBRE_CIUDAD;
    public String REGION;

    public CiudadDTO() {
    }

    public CiudadDTO(int ID, String NOMBRE_CIUDAD, String REGION) {
        this.ID = ID;
        this.NOMBRE_CIUDAD = NOMBRE_CIUDAD;
        this.REGION = REGION;
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNOMBRE_CIUDAD() {
        return NOMBRE_CIUDAD;
    }

    public void setNOMBRE_CIUDAD(String NOMBRE_CIUDAD) {
        this.NOMBRE_CIUDAD = NOMBRE_CIUDAD;
    }

    public String getREGION() {
        return REGION;
    }

    public void setREGION(String REGION) {
        this.REGION = REGION;
    }

    public List<CiudadDTO> ListarCiudades(){
        ReservaService rs = new ReservaService();
        List<Ciudad> ciudades = rs.listaCiudades().getCiudad();
        ArrayList<CiudadDTO> ciudadesReturn = new ArrayList<>();
        for (Ciudad ciudad : ciudades) {
            System.out.println(ciudad.getID().byteValue());
            CiudadDTO ciud = new CiudadDTO(ciudad.getID().byteValue(), ciudad.getNOMBRECIUDAD().getValue(), ciudad.getREGION().getValue());
            ciudadesReturn.add(ciud);
        }
        return ciudadesReturn;
    }
    
}
