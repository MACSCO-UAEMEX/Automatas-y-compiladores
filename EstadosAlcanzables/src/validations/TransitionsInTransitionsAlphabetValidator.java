/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import java.util.HashMap;

/**
 * TransitionsInTransitionsAlphabetValidator
 * CLASE que valida si las transiciones (en el archivo de transiciones), están en el alfabeto de transiciones
 * @author Jorge Ignacio Rivera Cortes
 */
public class TransitionsInTransitionsAlphabetValidator {
    
    String[] gSCadenas;
    HashMap<Integer,String> objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales;
    String gSSimboloInicial;

    /**
     * TransitionsInTransitionsAlphabetValidator
     * CONSTRUCTOR que inicializa los parámetros que debe validar el automata
     * @param lSCadenas Corresponde a la funcion de transicion
     * @param objHashMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param gSSimboloInicial Corresponde al simbolo inicial
     */
    public TransitionsInTransitionsAlphabetValidator(String[] lSCadenas, HashMap<Integer, String> objHashMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, HashMap<Integer, String> objHashMapEstadosFinales, String gSSimboloInicial)
    {
        this.gSCadenas = lSCadenas;
        this.objHashMapAlfeboEstados = objHashMapAlfeboEstados;
        this.objHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.objHashMapEstadosFinales = objHashMapEstadosFinales;
        this.gSSimboloInicial = gSSimboloInicial;
    }
    
    /**
     * validaTransiciones
     * Método para validar si los transiciones de la funcion de transicion corresponden al alfabeto de transiciones
     * @param lEIndice Indica la columna que se desea analizar
     * @return true si todos los elementos corresponden al alfabeto de transiciones, false si encuentra alguna transicion que no se encuentre
     */
    public boolean validaTransiciones(int lEIndice)
    {
        for (String gSCadena : gSCadenas) {
            String lSElemento = gSCadena.split(",")[lEIndice].trim();
            if (!objHashMapAlfeboTransiciones.containsValue(lSElemento.trim()))
            {
                return false;
            }
        }
        return true;
    }
    
}   // End of Class: TransitionsInTransitionsAlphabetValidator
