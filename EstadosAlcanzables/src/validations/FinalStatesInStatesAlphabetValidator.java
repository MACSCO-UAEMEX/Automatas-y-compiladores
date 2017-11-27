/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import java.util.HashMap;
import java.util.Map;

/**
 * FinalStatesInStatesAlphabetValidator
 * CLASE que valida si los estados Finales (en el archivo de estados Finales), están en el Alfabeto de Estados(archivo).
 * @author Jorge Ignacio Rivera Cortes
 */
public class FinalStatesInStatesAlphabetValidator {
    
    String[] gSCadenas;
    HashMap<Integer,String> objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales;
    String gSSimboloInicial;

    /**
     * FinalStatesInStatesAlphabetValidator
     * CONSTRUCTOR que inicializa los parámetros que debe validar el automata
     * @param lSCadenas Corresponde a la funcion de transicion
     * @param objHashMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param gSSimboloInicial Corresponde al simbolo inicial
     */
    public FinalStatesInStatesAlphabetValidator(String[] lSCadenas, HashMap<Integer, String> objHashMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, HashMap<Integer, String> objHashMapEstadosFinales, String gSSimboloInicial)
    {
        this.gSCadenas = lSCadenas;
        this.objHashMapAlfeboEstados = objHashMapAlfeboEstados;
        this.objHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.objHashMapEstadosFinales = objHashMapEstadosFinales;
        this.gSSimboloInicial = gSSimboloInicial;
    }
    
    /**
     * validaEstadosFinales
     * Metodo para validar si el estado Final corresponde al alfabeto de estados
     * @return true si todos los estados estan dentro del alfabeto de estados, false su encuentra alguno que no esté
     */
    public boolean validaEstadosFinales()
    {
        for (Map.Entry<Integer, String> entry : objHashMapEstadosFinales.entrySet())
        {
            if (!objHashMapAlfeboEstados.containsValue(entry.getValue()))
            {
                return false;
            }
        }
        return true;
    }
    
}   // End of Class: FinalStatesInStatesAlphabetValidator
