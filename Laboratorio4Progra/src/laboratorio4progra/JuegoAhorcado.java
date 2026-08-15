/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio4progra;

/**
 *
 * @author David Suazo Palao
 */
public interface JuegoAhorcado {
    void establecerPalabraSecreta(String palabra);
    void jugarPartida(char letra) throws CaracterInvalidoException, LetraDuplicadaException;
}
