/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import java.util.HashMap;

/**
 * InitialStateInTransitionsFileValidator
 * Clase que valida si el elemento del estado inicial se encuentra en la columna de 
 * estados actuales de la función de transición (archivo).
 * @author Jorge Ignacio Rivera Cortes
 */
public class InitialStateInTransitionsFileValidator {
    
    String[] gSCadenas;
    HashMap<Integer,String> objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales;
    String gSSimboloInicial;

    /**
     * InitialStateInTransitionsFileValidator
     * CONSTRUCTOR que inicializa los parámetros que debe validar el automata
     * @param lSCadenas Corresponde a la funcion de transicion
     * @param objHashMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param gSSimboloInicial Corresponde al simbolo inicial
     */
    public InitialStateInTransitionsFileValidator(String[] lSCadenas, HashMap<Integer, String> objHashMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, HashMap<Integer, String> objHashMapEstadosFinales, String gSSimboloInicial)
    {
        this.gSCadenas = lSCadenas;
        this.objHashMapAlfeboEstados = objHashMapAlfeboEstados;
        this.objHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.objHashMapEstadosFinales = objHashMapEstadosFinales;
        this.gSSimboloInicial = gSSimboloInicial;
    }
    
    /**
     * validaEstadoInicialEnTransiciones
     * MÉTODO para verificar si el elemento del estado inicial se encuentra en la columna de estados actuales de la función de transición
     * @return true si el simbolo inicial se encuentra en la columna de estados actuales, false si no lo encuentra
     */
    public boolean validaEstadoInicialEnTransiciones()
    {
        for (String gSCadena : gSCadenas) {
            String[] lSElementos = gSCadena.replace("[", "").replace("]", "").split(",");
            if (lSElementos[0].trim().equals(gSSimboloInicial.trim()))
            {
                return true;
            }
        }
        return false;
    }   // End of Method: validaEstadoInicialEnTransiciones
    
}   // End of Class: InitialStateInTransitionsFileValidator
