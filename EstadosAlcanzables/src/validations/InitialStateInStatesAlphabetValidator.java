/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import java.util.HashMap;

/**
 * InitialStateInStatesAlphabetValidator
 * CLASE que valida si el simbolo de estado Inicial (en el archivo CONFIG), está en el Alfabeto de Estados(archivo).
 * @author Jorge Ignacio Rivera Cortes
 */
public class InitialStateInStatesAlphabetValidator {
    
    String[] gSCadenas;
    HashMap<Integer,String> objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales;
    String gSSimboloInicial;

    /**
     * InitialStateInStatesAlphabetValidator
     * CONSTRUCTOR que inicializa los parámetros que debe validar el automata
     * @param lSCadenas Corresponde a la funcion de transicion
     * @param objHashMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param gSSimboloInicial Corresponde al simbolo inicial
     */
    public InitialStateInStatesAlphabetValidator(String[] lSCadenas, HashMap<Integer, String> objHashMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, HashMap<Integer, String> objHashMapEstadosFinales, String gSSimboloInicial)
    {
        this.gSCadenas = lSCadenas;
        this.objHashMapAlfeboEstados = objHashMapAlfeboEstados;
        this.objHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.objHashMapEstadosFinales = objHashMapEstadosFinales;
        this.gSSimboloInicial = gSSimboloInicial;
    }
    
    /**
     * validaEstadoInicial
     * Método para verificar si el estado inicial pertenece al alfabeto de estados
     * @return true si el estado inicial pertenece al alfabeto de estados, false si no pertecece al alfabeto
     */
    public boolean validaEstadoInicial()
    {
        return objHashMapAlfeboEstados.containsValue(gSSimboloInicial);
    }
    
}   // End of Class: InitialStateInStatesAlphabetValidator
