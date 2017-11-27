/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import java.util.HashMap;

/**
 * StatesInStatesAlphabetValidator
 * CLASE que valida si los estados del archivo transiciones, están en el Alfabeto de estados
 * @author Jorge Ignacio Rivera Cortes
 */
public class StatesInStatesAlphabetValidator {
    
    String[] gSCadenas;
    HashMap<Integer,String> objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales;
    String gSSimboloInicial;

    /**
     * StatesInStatesAlphabetValidator
     * CONSTRUCTOR que inicializa los parámetros que debe validar el automata
     * @param lSCadenas Corresponde a la funcion de transicion
     * @param objHashMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param gSSimboloInicial Corresponde al simbolo inicial
     */
    public StatesInStatesAlphabetValidator(String[] lSCadenas, HashMap<Integer, String> objHashMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, HashMap<Integer, String> objHashMapEstadosFinales, String gSSimboloInicial)
    {
        this.gSCadenas = lSCadenas;
        this.objHashMapAlfeboEstados = objHashMapAlfeboEstados;
        this.objHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.objHashMapEstadosFinales = objHashMapEstadosFinales;
        this.gSSimboloInicial = gSSimboloInicial;
    }
    
    /**
     * validaEstados
     * Método para validar si los estados de la funcion de transicion corresponden al alfabeto de estados
     * @param lEIndice Indica la columna que se desea analizar
     * @return true si todos los elementos corresponden al alfabeto de estados, false si encuentra algun estado que no se encuentre
     */
    public boolean validaEstados(int lEIndice)
    {
        for (String gSCadena : gSCadenas) {
            String lSElemento = gSCadena.split(",")[lEIndice].replace("[", "").trim();
            if (!objHashMapAlfeboEstados.containsValue(lSElemento.trim()))
            {
                return false;
            }
        }
        return true;
    }
    
}   // End of Class: StatesInStatesAlphabetValidator
