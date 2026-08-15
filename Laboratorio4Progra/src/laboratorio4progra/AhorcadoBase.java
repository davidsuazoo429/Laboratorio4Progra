/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio4progra;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AhorcadoBase implements JuegoAhorcado {
    protected String palabraSecreta;
    protected String palabraSecretaSinTildes;
    protected char[] palabraMostrada;
    protected int intentosRestantes;
    protected final int intentosmax = 6;
    protected List<Character> letrasIngresadas;
    
    public AhorcadoBase(){
        this.intentosRestantes = intentosmax;
        this.letrasIngresadas = new ArrayList<>();
    }
    
    private String quitarTildes(String texto) {
        if (texto == null) return "";
        String normalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return normalizado.replaceAll("\\p{M}", "");
    }
    
    @Override
    public void establecerPalabraSecreta(String palabra){
        if (palabra == null || palabra.trim().isEmpty()) {
            palabra = "JAVA";
        }
        this.palabraSecreta = palabra.toUpperCase().trim();
        this.palabraSecretaSinTildes = quitarTildes(this.palabraSecreta);
        this.palabraMostrada = new char[palabraSecreta.length()];
        Arrays.fill(this.palabraMostrada, '_');
        
        for(int i = 0; i < palabraSecreta.length(); i++){
            if(palabraSecreta.charAt(i) == ' '){
                palabraMostrada[i] = ' ';
            }
        }
    }
    
    @Override
    public void jugarPartida(char letra) throws CaracterInvalidoException, LetraDuplicadaException {
        char letraMayus = Character.toUpperCase(letra);

        if (!Character.isLetter(letraMayus)) {
            throw new CaracterInvalidoException(String.valueOf(letraMayus));
        }

        if (letrasIngresadas.contains(letraMayus)) {
            throw new LetraDuplicadaException(letraMayus);      
        }
        letrasIngresadas.add(letraMayus);

        if (verificarLetra(letraMayus)) {
            actualizarPalabraMostrada(letraMayus);
        } else {
            intentosRestantes--;
        }
    }
    
    protected abstract void actualizarPalabraMostrada(char letra);
    protected abstract boolean verificarLetra(char letra);

    public boolean determinarGanador() {
        if (palabraMostrada == null || palabraSecreta == null) return false;
        return new String(palabraMostrada).equals(palabraSecreta);
    }

    public boolean determinarPerdedor() {
        return intentosRestantes <= 0;
    }

    public String getPalabraMostrada() {
        if (palabraMostrada == null) return "";
        return new String(palabraMostrada);
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public List<Character> getLetrasIngresadas() {
        return letrasIngresadas;
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }
}