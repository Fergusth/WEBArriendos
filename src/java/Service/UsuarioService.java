/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import WS.Usuario;

/**
 *
 * @author Gerald
 */
public class UsuarioService {

    public static Usuario login(java.lang.String dni, java.lang.String pass) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.login(dni, pass);
    }

    public static Boolean crearUsuario(java.lang.String dni, java.lang.String nombre, java.lang.String apellidos, java.lang.String correoELECTRONICO, java.lang.String direccion, java.lang.Integer telefono, java.lang.Short rolIDROL, java.lang.String extranjero, java.lang.String frecuente) {
        WS.ServicioTR service = new WS.ServicioTR();
        WS.IServicioTR port = service.getBasicHttpBindingIServicioTR();
        return port.crearUsuario(dni, nombre, apellidos, correoELECTRONICO, direccion, telefono, rolIDROL, extranjero, frecuente);
    }
    
}
