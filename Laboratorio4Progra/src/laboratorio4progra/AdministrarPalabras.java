/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio4progra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author David Suazo Palao
 */
public class AdministrarPalabras {
    private List<String> palabrasDisponibles;
    private Random random;

    public AdministrarPalabras() {
        this.palabrasDisponibles = new ArrayList<>();
        this.random = new Random();
        
        
        palabrasDisponibles.add("manzana");
        palabrasDisponibles.add("pera");
        palabrasDisponibles.add("fruta");
        palabrasDisponibles.add("ingeniero");
    }

    public void agregarPalabra(String palabra) throws PalabraExistenteException {
        if (palabra == null || palabra.trim().isEmpty()) {
            throw new PalabraExistenteException("palabra vacia, debe de ingresar una palabra");
        }
        
        String palabraMayus = palabra.toUpperCase().trim();
        
        if (palabrasDisponibles.contains(palabraMayus)) {
            throw new PalabraExistenteException("La palabra: " + palabraMayus + " ya existe en el diccionario de palabras.");
        }
        
        palabrasDisponibles.add(palabraMayus);
    }

    public String obtenerPalabraAlAzar() {
        if (palabrasDisponibles.isEmpty()) {
            return "pasar";
        }
        return palabrasDisponibles.get(random.nextInt(palabrasDisponibles.size()));
    }
}
