/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Service.DepartamentoService;
import WS.Departamento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oskardashh
 */
public class DepartamentoDTO {

    private int ID;
    private String DIRECCION;
    private int CANT_BANIOS;
    private int CANT_DORMITORIOS;
    private int PRECIO_DIARIO;
    private String DESCRIPCION;
    private String CONDICIONES_USO;
    private int CIUDAD_ID;
    private String INTERNET;
    private String CALEFACCION;
    private int PROM_MES_DIVIDENDO;
    private int PROM_MES_CONTRIBUCIONES;

    public DepartamentoDTO() {
    }

    public DepartamentoDTO(int ID, String DIRECCION, int CANT_BANIOS, int CANT_DORMITORIOS, int PRECIO_DIARIO,
            String DESCRIPCION, String CONDICIONES_USO, int CIUDAD_ID, String INTERNET, String CALEFACCION, int PROM_MES_DIVIDENDO,
            int PROM_MES_CONTRIBUCIONES) {
        this.ID = ID;
        this.DIRECCION = DIRECCION;
        this.CANT_BANIOS = CANT_BANIOS;
        this.CANT_DORMITORIOS = CANT_DORMITORIOS;
        this.PRECIO_DIARIO = PRECIO_DIARIO;
        this.DESCRIPCION = DESCRIPCION;
        this.CONDICIONES_USO = CONDICIONES_USO;
        this.CIUDAD_ID = CIUDAD_ID;
        this.INTERNET = INTERNET;
        this.CALEFACCION = CALEFACCION;
        this.PROM_MES_DIVIDENDO = PROM_MES_DIVIDENDO;
        this.PROM_MES_CONTRIBUCIONES = PROM_MES_CONTRIBUCIONES;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public int getCANT_BANIOS() {
        return CANT_BANIOS;
    }

    public void setCANT_BANIOS(int CANT_BANIOS) {
        this.CANT_BANIOS = CANT_BANIOS;
    }

    public int getCANT_DORMITORIOS() {
        return CANT_DORMITORIOS;
    }

    public void setCANT_DORMITORIOS(int CANT_DORMITORIOS) {
        this.CANT_DORMITORIOS = CANT_DORMITORIOS;
    }

    public int getPRECIO_DIARIO() {
        return PRECIO_DIARIO;
    }

    public void setPRECIO_DIARIO(int PRECIO_DIARIO) {
        this.PRECIO_DIARIO = PRECIO_DIARIO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getCONDICIONES_USO() {
        return CONDICIONES_USO;
    }

    public void setCONDICIONES_USO(String CONDICIONES_USO) {
        this.CONDICIONES_USO = CONDICIONES_USO;
    }

    public int getCIUDAD_ID() {
        return CIUDAD_ID;
    }

    public void setCIUDAD_ID(int CIUDAD_ID) {
        this.CIUDAD_ID = CIUDAD_ID;
    }

    public String getINTERNET() {
        return INTERNET;
    }

    public void setINTERNET(String INTERNET) {
        this.INTERNET = INTERNET;
    }

    public String getCALEFACCION() {
        return CALEFACCION;
    }

    public void setCALEFACCION(String CALEFACCION) {
        this.CALEFACCION = CALEFACCION;
    }

    public int getPROM_MES_DIVIDENDO() {
        return PROM_MES_DIVIDENDO;
    }

    public void setPROM_MES_DIVIDENDO(int PROM_MES_DIVIDENDO) {
        this.PROM_MES_DIVIDENDO = PROM_MES_DIVIDENDO;
    }

    public int getPROM_MES_CONTRIBUCIONES() {
        return PROM_MES_CONTRIBUCIONES;
    }

    public void setPROM_MES_CONTRIBUCIONES(int PROM_MES_CONTRIBUCIONES) {
        this.PROM_MES_CONTRIBUCIONES = PROM_MES_CONTRIBUCIONES;
    }

    public boolean contratoDepartamentoReserva(int id_depa, int id_reserva) {
        return DepartamentoService.contratoReservaDepartamento(id_reserva, id_depa);
    }

    public ArrayList<DepartamentoDTO> buscarDepartamentosReserva(int idreserva) {
        List<Departamento> bd_depas = DepartamentoService.departamentosReserva(idreserva).getDepartamento();
        ArrayList<DepartamentoDTO> depas = new ArrayList<>();
        for (Departamento bd_depa : bd_depas) {
            DepartamentoDTO depa = new DepartamentoDTO();
            depa.setDIRECCION(bd_depa.getDIRECCION().getValue());
            depa.setPRECIO_DIARIO(bd_depa.getPRECIODIARIO());
            depas.add(depa);
        }
        
        return depas;
    }
}
