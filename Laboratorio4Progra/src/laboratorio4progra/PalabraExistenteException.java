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
public class PalabraExistenteException extends Exception{
    private final String palabraext;

    public PalabraExistenteException(String palabraext) {
        super("La palabra: "+palabraext+" ya ha sido ingresada al registro, por lo cual se encuentra duplicada. ");
        this.palabraext = palabraext;
    }

    public String getPalabraext() {
        return palabraext;
    }
    
}
