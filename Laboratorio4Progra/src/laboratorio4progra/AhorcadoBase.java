/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio4progra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author David Suazo Palao
 */
public abstract class AhorcadoBase implements JuegoAhorcado {
    protected String palabraSecreta;
    protected char[] palabraMostrada;
    protected int intentosRestantes;
    protected final int intentosmax=6;
    protected List<Character> letrasIngresadas;
    
    public AhorcadoBase(){
        this.intentosRestantes=intentosmax;
        this.letrasIngresadas=new ArrayList<>();
    }
    
    @Override
    public void establecerPalabraSecreta(String palabra){
        this.palabraSecreta=palabra.toUpperCase().trim();
        this.palabraMostrada=new char [palabraSecreta.length()];
        Arrays.fill(this.palabraMostrada, '_');
        
        for(int i=0; i <palabraSecreta.length();i++){
            if(palabraSecreta.charAt(i)== ' '){
                palabraMostrada[i]= ' ';
            }
        }
    }
    
    @Override
    public void jugarPartida(char letra) throws FormatoCaracterInvalidoException, IntentoLetraDuplicadoException {
        char letraMayus = Character.toUpperCase(letra);

        if (!Character.isLetter(letraMayus)) {
            throw new FormatoCaracterInvalidoException("Debe ingresar un carácter alfabético válido.");
        }

        if (letrasIngresadas.contains(letraMayus)) {
            throw new IntentoLetraDuplicadoException("Ya intentaste con la letra '" + letraMayus + "'.");
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
        return new String(palabraMostrada).equals(palabraSecreta);
    }

    public boolean determinarPerdedor() {
        return intentosRestantes <= 0;
    }

    public String getPalabraMostrada() {
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
