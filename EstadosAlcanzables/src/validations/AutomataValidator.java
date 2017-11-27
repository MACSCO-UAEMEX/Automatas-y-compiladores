/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validations;

import java.util.HashMap;

/**
 * AutomataValidator
 * CLASE que de evalua si el autómata es Válido, realiza varias validaciones
 * @author Jorge Ignacio Rivera Cortes
 */
public class AutomataValidator {

    String[] gSCadenas;
    HashMap<Integer,String> objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales;
    String gSSimboloInicial;    
    
    /**
     * ReachableFinalStatesValidator
     * CONSTRUCTOR que inicializa los parámetros que debe validar el automata
     * @param lSCadenas Corresponde a la funcion de transicion
     * @param objHashMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param gSSimboloInicial Corresponde al simbolo inicial
     */    
    public AutomataValidator(String[] lSCadenas, HashMap<Integer, String> objHashMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, HashMap<Integer, String> objHashMapEstadosFinales, String gSSimboloInicial) {
        this.gSCadenas = lSCadenas;
        this.objHashMapAlfeboEstados = objHashMapAlfeboEstados;
        this.objHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.objHashMapEstadosFinales = objHashMapEstadosFinales;
        this.gSSimboloInicial = gSSimboloInicial;        
    }
    
    /**
     * validarAutomata
     * Método para validar si los estados finales (del Alfabeto de Estados Finales), son alcanzables
     * @return true si todas la validaciones fueron correctas
     */
    public boolean validarAutomata(){
        FinalStatesInStatesAlphabetValidator fsiav = new FinalStatesInStatesAlphabetValidator(gSCadenas, objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales, gSSimboloInicial);
        FinalStatesInTransitionsFileValidator fsitfv = new FinalStatesInTransitionsFileValidator(gSCadenas, objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales, gSSimboloInicial);
        InitialStateInStatesAlphabetValidator isisav = new InitialStateInStatesAlphabetValidator(gSCadenas, objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales, gSSimboloInicial);
        InitialStateInTransitionsFileValidator isitfv = new InitialStateInTransitionsFileValidator(gSCadenas, objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales, gSSimboloInicial);
        ReachableFinalStatesValidator rfsv = new ReachableFinalStatesValidator(gSCadenas, objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales, gSSimboloInicial);
        StatesInStatesAlphabetValidator sisav = new StatesInStatesAlphabetValidator(gSCadenas, objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales, gSSimboloInicial);
        TransitionsInTransitionsAlphabetValidator titav = new TransitionsInTransitionsAlphabetValidator(gSCadenas, objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales, gSSimboloInicial);
        
    
        for (int lEi = 0; lEi < gSCadenas.length; lEi++)
        {
            switch (lEi)
            {
                case 0:
                case 2:
                    if (sisav.validaEstados(lEi))
                    {
//                        System.out.println("El automata tiene los estados CORRECTOS");
                    } else
                    {
                        System.out.println("Estado NO contemplado en el alfabeto de estados");
                        return false;
                    }
                    break;
                case 1:
                case 3:
                    if (titav.validaTransiciones(lEi))
                    {
//                        System.out.println("El automata tiene los transiciones CORRECTAS");
                    } else
                    {
                        System.out.println("Transicion NO contemplado en el alfabeto de transiciones");
                        return false;
                    }
            }
        }
        
        if (isisav.validaEstadoInicial())
        {
//            System.out.println("Estado inicial correcto");
        }else
        {
            System.out.println("Error en la definicion del estado inicial");
            return false;
        }
        
        if (fsiav.validaEstadosFinales())
        {
//            System.out.println("Estados finales correctos");
        }else
        {
            System.out.println("ERROR en los estados finales");
            return false;
        }
        
        if (isitfv.validaEstadoInicialEnTransiciones())
        {
//            System.out.println("El estado inicial se encuentra en la función de transicion");
        }else
        {
            System.out.println("El estado inicial no se encuentra en la funcion de transicion");
            return false;
        }
        
        if (fsitfv.validaEstadosFinalesEnTransiciones())
        {
//            System.out.println("El conjunto de estados finales se encuentra en la función de transicion");
        }else
        {
            System.out.println("El conjunto de estados finales NO se encuentra en la función de transicion");
            return false;
        }
        
        return true;
            
        
    }
}
