/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import java.util.HashMap;
import java.util.Map;

/**
 * FinalStatesInTransitionsFileValidator
 * Clase que valida si los estados Finales (en el archivo de estados Finales), se encuentran en 
 * el Archivo de Transiciones.
 * @author Jorge Ignacio Rivera Cortes
 */
public class FinalStatesInTransitionsFileValidator {
    
    String[] gSCadenas;
    HashMap<Integer,String> objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales;
    String gSSimboloInicial;

    /**
     * FinalStatesInTransitionsFileValidator
     * CONSTRUCTOR que inicializa los parámetros que debe validar el automata
     * @param lSCadenas Corresponde a la funcion de transicion
     * @param objHashMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param gSSimboloInicial Corresponde al simbolo inicial
     */
    public FinalStatesInTransitionsFileValidator(String[] lSCadenas, HashMap<Integer, String> objHashMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, HashMap<Integer, String> objHashMapEstadosFinales, String gSSimboloInicial)
    {
        this.gSCadenas = lSCadenas;
        this.objHashMapAlfeboEstados = objHashMapAlfeboEstados;
        this.objHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.objHashMapEstadosFinales = objHashMapEstadosFinales;
        this.gSSimboloInicial = gSSimboloInicial;
    }
    
    /**
     * validaEstadosFinalesEnTransiciones
     * MÉTODO para verificar que todos los estados finales (del archivo de estados finales), se encuentren en 
     * los estados de la función de transición (archivo).
     * @return true si todos los edos finales se encuentran en la función de transición (archivo), false si no se encuetran todos o ninguno 
     */    
    public boolean validaEstadosFinalesEnTransiciones()
    {
        int lEIncrementos = 0;
        String lSCadenaCompleta = "";
        for (int lEi = 0; lEi < gSCadenas.length; lEi++)
        {
            lSCadenaCompleta += gSCadenas[lEi].replace("[", "").replace("]", "").split(",")[0] + gSCadenas[lEi].replace("[", "").replace("]", "").split(",")[2];
        }

        for (Map.Entry<Integer, String> objEntry : objHashMapEstadosFinales.entrySet())     // Por cada entrada del conjunto de entradas en el mapa de estados finales
        {
            if (lSCadenaCompleta.contains(objEntry.getValue()))
            {
                lEIncrementos++;
            }
        }
        
        return lEIncrementos == objHashMapEstadosFinales.size();
    }   // End of Method:  validaEstadosFinalesEnTransiciones()
    
}   // End of Class: FinalStatesInTransitionsFileValidator
