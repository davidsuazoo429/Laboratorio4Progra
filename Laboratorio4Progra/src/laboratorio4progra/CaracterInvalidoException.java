/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio4progra;

/**
 *
 * @author Ian Suazo Palao
 */
public class CaracterInvalidoException extends Exception{
    private final String respRecibida;
    
    public CaracterInvalidoException(String respRecibida){
        super("El caracter ingresado: "+respRecibida+" no es valida. Debe ingresar solamente un caracter, asegurandose que esté dentro del abecedario.");
        this.respRecibida=respRecibida;
    }

    public String getRespRecibida() {
        return respRecibida;
    }
    
    
}
