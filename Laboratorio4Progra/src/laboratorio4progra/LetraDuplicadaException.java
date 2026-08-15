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
public class LetraDuplicadaException extends Exception{
    private final char letraDuplicada;

    public LetraDuplicadaException(char letraDuplicada) {
        super("La letra: "+Character.toUpperCase(letraDuplicada)+" ha sido ingresada previamente.");
        this.letraDuplicada = letraDuplicada;
    }

    public char getLetraDuplicada() {
        return letraDuplicada;
    }

    
    
}
