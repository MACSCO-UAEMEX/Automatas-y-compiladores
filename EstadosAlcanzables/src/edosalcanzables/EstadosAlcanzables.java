/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edosalcanzables;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * EstadosAlcanzables
 * CLASE que se encarga de Identificar los estados Alcanzables
 * @author Jorge Ignacio Rivera Cortes
 */
public class EstadosAlcanzables {
    Integer[] aAlcanzables;
    Integer gEEstadoInicial;
    Set<Integer> objSetEdosAlcanzables;
//    Set<Integer> objSetAlfabetoEstados, objSetAlfabetoTransiciones, objSetEstadosFinales, objSetEdosAlcanzables, objSetUltimosEdosAlcAgregados;
    Map<Integer, Set<Integer>> objMapEdosActualEdosSig;    

    
    /**
     * EstadosAlcanzables
     * CONSTRUCTOR que inicializa los parámetros y autributos a partir de un objeto Automata
     * @param objAutomata
     */    
    public EstadosAlcanzables(Automata objAutomata) {
        this.gEEstadoInicial = objAutomata.gEEstadoInicial;//gEEstadoInicial;
        this.objMapEdosActualEdosSig = objAutomata.objMapEdosActualEdosSig;//objMapEdosActualEdosSig;
        
//        System.out.println(this.objMapEdosActualEdosSig);
        
        this.aAlcanzables = new Integer[objAutomata.objMapAlfabetoEstados.size()];
        for (int i = 0; i< aAlcanzables.length; i++){
            aAlcanzables[i] = 0;
        }
        aAlcanzables[this.gEEstadoInicial] = 2;
        
        System.out.println(Arrays.toString(this.aAlcanzables));
    }

//    public boolean validaAutomata()
//    {
//        for (Map.Entry<Integer, Set<Integer>> objEntry: objMapEdosActualEdosSig.entrySet())
//        {
//            if (objEntry.getValue().size() > 1)
//            {
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * RegresaEstadosAlcanzables
     * METODO para obtener el conjunto de Estados Alcanzables 
     * @return objSetEdosAlcanzables que es el conjunto de Estados Alcanzables
     */    
    public Set<Integer> RegresaEstadosAlcanzables(){
        objSetEdosAlcanzables = new HashSet<>();                // Se crea el conjunto: "objSetEdosAlcanzables"
        Integer eEdoActual = gEEstadoInicial;      
        
        Boolean intFlagReinicio = true;
        while(intFlagReinicio){
            intFlagReinicio=false;      // Reinicio de la bandera
            for (int intEdoActual = 0; intEdoActual< aAlcanzables.length; intEdoActual++){
                
                System.out.println("\n" + this.objMapEdosActualEdosSig);
                System.out.println("Estado Actual: " + intEdoActual);
                System.out.println(Arrays.toString(this.aAlcanzables));
                
                if(aAlcanzables[intEdoActual]==2){
                    aAlcanzables[intEdoActual]=1;
                    
                    System.out.println(Arrays.toString(this.aAlcanzables));
                    
                    /* 
                     * Para cada "eEdoSiguiente" del conjunto referenciado por "intEdoActual" en el mapa de objMapEdosActualEdosSig, 
                     * evaluar si el array aAlcanzables en el indice eEdoSiguiente es igual a 0 
                     * Si es verdadero cambia el valor a 2 y reinicia
                     */
                    if(objMapEdosActualEdosSig.get(intEdoActual).isEmpty()){
                        intFlagReinicio=false;
                    }else{
                        
                        for(Integer eEdoSiguiente : objMapEdosActualEdosSig.get(intEdoActual)){       // "objMapEdosActualEdosSig.get(eEdoActual)" Devuelve el CONJUNTO de EstadosSiguientes, apuntado por eEdoActual
        //                    if(objSetEdosAlcanzables.add(eEdoSiguiente))    objSetUltimosEdosAlcAgregados.add(eEdoSiguiente);
                            if(aAlcanzables[eEdoSiguiente]==0){
                                aAlcanzables[eEdoSiguiente]=2;
                                intFlagReinicio=true;

                                System.out.println(Arrays.toString(this.aAlcanzables));
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i< aAlcanzables.length; i++){
            if(aAlcanzables[i]==1){
                objSetEdosAlcanzables.add(i);
            }
        }        
        return objSetEdosAlcanzables;
    }
}
