/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author oskardashh
 */
public class ReservaDTO {
    
    public int ID_RESERVA;
    public Date FECHA_CHECKIN;
    public Date FECHA_CHECKOUT;
    public String HORA_CHECKIN;
    public String USUARIO_DNI;
    public int PRECIO_TOTAL;
    public int TOTAL_PAGADO;
    public String FORMA_PAGO;

    public ReservaDTO() {
    }

    public int getID_RESERVA() {
        return ID_RESERVA;
    }

    public void setID_RESERVA(int ID_RESERVA) {
        this.ID_RESERVA = ID_RESERVA;
    }

    public Date getFECHA_CHECKIN() {
        return FECHA_CHECKIN;
    }

    public void setFECHA_CHECKIN(Date FECHA_CHECKIN) {
        this.FECHA_CHECKIN = FECHA_CHECKIN;
    }

    public Date getFECHA_CHECKOUT() {
        return FECHA_CHECKOUT;
    }

    public void setFECHA_CHECKOUT(Date FECHA_CHECKOUT) {
        this.FECHA_CHECKOUT = FECHA_CHECKOUT;
    }

    public String getHORA_CHECKIN() {
        return HORA_CHECKIN;
    }

    public void setHORA_CHECKIN(String HORA_CHECKIN) {
        this.HORA_CHECKIN = HORA_CHECKIN;
    }

    public String getUSUARIO_DNI() {
        return USUARIO_DNI;
    }

    public void setUSUARIO_DNI(String USUARIO_DNI) {
        this.USUARIO_DNI = USUARIO_DNI;
    }

    public int getPRECIO_TOTAL() {
        return PRECIO_TOTAL;
    }

    public void setPRECIO_TOTAL(int PRECIO_TOTAL) {
        this.PRECIO_TOTAL = PRECIO_TOTAL;
    }

    public int getTOTAL_PAGADO() {
        return TOTAL_PAGADO;
    }

    public void setTOTAL_PAGADO(int TOTAL_PAGADO) {
        this.TOTAL_PAGADO = TOTAL_PAGADO;
    }

    public String getFORMA_PAGO() {
        return FORMA_PAGO;
    }

    public void setFORMA_PAGO(String FORMA_PAGO) {
        this.FORMA_PAGO = FORMA_PAGO;
    }
    
    
    
    
}
