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
public class PalabraAlAzar extends AhorcadoBase {
    public PalabraAlAzar(AdministrarPalabras administrador) {
        super();
        String palabraAleatoria = administrador.obtenerPalabraAlAzar();
        establecerPalabraSecreta(palabraAleatoria);
    }

    @Override
    protected boolean verificarLetra(char letra) {
        return palabraSecreta.indexOf(letra) >= 0;
    }

    @Override
    protected void actualizarPalabraMostrada(char letra) {
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraMostrada[i] = letra;
            }
        }
    }
}
