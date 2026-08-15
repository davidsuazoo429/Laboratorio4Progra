/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio4progra;

public class PalabraAlAzar extends AhorcadoBase {
    public PalabraAlAzar(AdministrarPalabras administrador) {
        super();
        String palabraAleatoria = "JAVA";
        if (administrador != null) {
            try {
                palabraAleatoria = administrador.obtenerPalabraAlAzar();
            } catch (Exception e) {
                palabraAleatoria = "JAVA";
            }
        }
        establecerPalabraSecreta(palabraAleatoria);
    }

    @Override
    protected boolean verificarLetra(char letra) {
        if (palabraSecretaSinTildes == null) return false;
        return palabraSecretaSinTildes.indexOf(letra) >= 0;
    }

    @Override
    protected void actualizarPalabraMostrada(char letra) {
        if (palabraSecreta == null || palabraSecretaSinTildes == null) return;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecretaSinTildes.charAt(i) == letra) {
                palabraMostrada[i] = palabraSecreta.charAt(i);
            }
        }
    }
}