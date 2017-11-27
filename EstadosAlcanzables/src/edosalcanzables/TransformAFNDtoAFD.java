package edosalcanzables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * TransformAFNDtoAFD
 * Clase que transforma el AFND a un AFD
 * @author Jonathan Rojas Simón <ids_jonathan_rojas@hotmail.com>
 */
public class TransformAFNDtoAFD
{
    String gSEstadoInicial;
    HashMap<Integer, String> objMapAlfabetoEstados, objMapAlfabetoTransiciones, objMapEstadosFinales;
    TreeMap<Integer, Set<Integer>> objTreeMapMapaAutomata;

    public TransformAFNDtoAFD(String gSEstadoInicial, HashMap<Integer, String> objMapAlfabetoEstados, HashMap<Integer, String> objMapAlfabetoTransiciones
            , HashMap<Integer, String> objMapEstadosFinales, TreeMap<Integer, Set<Integer>> objTreeMapMapaAutomata)
    {
        this.gSEstadoInicial = gSEstadoInicial;
        this.objMapAlfabetoEstados = objMapAlfabetoEstados;
        this.objMapAlfabetoTransiciones = objMapAlfabetoTransiciones;
        this.objMapEstadosFinales = objMapEstadosFinales;
        this.objTreeMapMapaAutomata = objTreeMapMapaAutomata;
    }
    
    public boolean validaAutomata()
    {
        for (Map.Entry<Integer, Set<Integer>> objEntry: objTreeMapMapaAutomata.entrySet())
        {
            if (objEntry.getValue().size() > 1)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * transformarAutomata
     * Clase que realiza la transformacion AFND a AFD
     * @return El AFD en un mapa
     */
    public TreeMap<Integer, TreeMap<Integer, Integer>> transformarAutomata()
    {
        TreeMap<Integer, TreeMap<Integer, Integer>> objTreeMapTransformado = new TreeMap<>();
        TreeMap<Integer, Set<Integer>> objTreeMapConjuntosCombinados = new TreeMap<>();
        
        int lEIdEstados = objMapAlfabetoEstados.size()-1;
        for (Map.Entry<Integer, Set<Integer>> objEntry : objTreeMapMapaAutomata.entrySet())
        {
            if (objEntry.getValue().size() > 1)
            {
//                objMapAlfabetoEstados.put(objMapAlfabetoEstados.size(), String.valueOf(objMapAlfabetoEstados.size()));
                objMapAlfabetoEstados.put(objMapAlfabetoEstados.size(), objEntry.getValue().toString());
                Set<Integer> objIntegers = new TreeSet<>();
                for (Iterator objIterator = objEntry.getValue().iterator(); objIterator.hasNext();)
                {
                    objIntegers.add(Integer.parseInt(String.valueOf(objIterator.next())));
                }
                objTreeMapConjuntosCombinados.put(objTreeMapConjuntosCombinados.size(), objIntegers);
            }
        }
        
        for (Map.Entry<Integer, String> objEntry : objMapAlfabetoEstados.entrySet())
        {
            System.out.println("Clave " + objEntry.getKey() + " con estados " + objEntry.getValue());
        }
        
        //Para recorrer los identificadores con estados repetidos
        for (Map.Entry<Integer, Set<Integer>> objEntryConjuntos : objTreeMapConjuntosCombinados.entrySet())
        {
            System.out.println("\nConjunto recuperado " + objEntryConjuntos.getKey() + " con conjuntos " + objEntryConjuntos.getValue().toString());
            
            TreeMap<Integer, ArrayList<Integer>> objTreeMapEstadosMapeados = new TreeMap<>();
            
            //Para recorrer los estados dentro de cada identificador
            for (Iterator objIterator = objEntryConjuntos.getValue().iterator(); objIterator.hasNext();)
            {
                int lEEstado = Integer.parseInt(String.valueOf(objIterator.next()).trim());
                int lEIndiceInicio = lEEstado * objMapAlfabetoTransiciones.size() + 1;
                int lEIndiceFinal = lEEstado * objMapAlfabetoTransiciones.size() + objMapAlfabetoTransiciones.size();
                System.out.println("Estado "+ lEEstado + " con indice inicial " + lEIndiceInicio + " hasta " + lEIndiceFinal);
                ArrayList<Integer> objArrayListLista = new ArrayList<>();
                //Para conocer si hay un elemento dentro de cada rango
                for (Map.Entry<Integer, Set<Integer>> objEntry: objTreeMapMapaAutomata.entrySet())
                {
                    if (objEntry.getKey() >= lEIndiceInicio && objEntry.getKey() <= lEIndiceFinal)
                    {
                        System.out.println("Clave " + objEntry.getKey()+ " con estados " + objTreeMapMapaAutomata.get(objEntry.getKey()).toString());
                        objArrayListLista.add(objEntry.getKey());
                    }
                }
                objTreeMapEstadosMapeados.put(lEEstado, objArrayListLista);
            }
            
            for (Map.Entry<Integer, ArrayList<Integer>> objEntryLocalidador : objTreeMapEstadosMapeados.entrySet())
            {
                System.out.print("El estado " + objEntryLocalidador.getKey());
                if (!objEntryLocalidador.getValue().isEmpty())
                {
                    for (int i = 0; i < objEntryLocalidador.getValue().size(); i++)
                    {
                        for (Map.Entry<Integer, ArrayList<Integer>> objEntryLocalidador1 : objTreeMapEstadosMapeados.entrySet())
                        {
                            if (objEntryLocalidador.getKey() != objEntryLocalidador1.getKey())
                            {
                                System.out.println(" con su identificador " + objEntryLocalidador.getValue().get(i) + " tiene su comparacion con el identificador "
                                        + obtenerIdentificador(objEntryLocalidador1.getKey(),
                                                objMapAlfabetoTransiciones.size(),
                                                obtenerCoordenadaTransicion(objEntryLocalidador.getValue().get(i),
                                                        objMapAlfabetoTransiciones.size()) - 1)
                                        + " del estado " + objEntryLocalidador1.getKey());
                            }
                        }
                    }
                }else
                {
                    System.out.println(" esta vacio");
                }
                
            }

        }

        return objTreeMapTransformado;
    }
    
    /**
     * obtenerCoordenadaTransicion
     * Método para obtener el indice de la transición asociado de acuerdo a su clave de matriz
     * @param lEIdentificador Almacena el valor de la matriz
     * @param lETamanoTransiciones Almacena el tamaño del alfabeto de transiciones
     * @return un numero entero que indica el indice del esyado
     */
    public int obtenerCoordenadaTransicion(int lEIdentificador, int lETamanoTransiciones)
    {
        return (((lEIdentificador-1)%lETamanoTransiciones) +1);
    }
    
    /**
     * obtenerCoordenadaEstado
     * Método para obtener el indice del estado asociado de acuerdo a su clave de matriz
     * @param lEIdentificador Almacena el valor de la matriz
     * @param lETamanoTransiciones Almacena el tamaño del alfabeto de transiciones
     * @return un numero entero que indica el indice del estado
     */
    public int obtenerCoordenadaEstado(int lEIdentificador, int lETamanoTransiciones)
    {
        double lDResiduo = ((lEIdentificador -1)/lETamanoTransiciones);
        return (((int) lDResiduo)+1);
    }
    
    /**
     * obtenerIdentificador
     * Método para obtener la coordenada para identificar el mapa
     * @param lEIndiceEstados Es el indice en donde se encuentra el elemento del alfabeto de estados que lo vincula
     * @param lETamanioAlfeboTransiciones Es el tamaño del alfabeto de transiciones
     * @param lEIndiceTransicion Es el indice de la transición
     * @return  un valor que especifica el identificador asociado
     */
    public int obtenerIdentificador(int lEIndiceEstados, int lETamanioAlfeboTransiciones, int lEIndiceTransicion)
    {
        return ((lEIndiceEstados * lETamanioAlfeboTransiciones) + lEIndiceTransicion +1);
    }

    public void run()
    {
        TreeMap<Integer, TreeMap<Integer, Integer>> objTreeMapTransformado;
        if (validaAutomata())
        {
            System.out.println("El automata es no determinista (AFND), por lo tanto es necesario proceder");
            objTreeMapTransformado = transformarAutomata();
        }else
        {
            System.out.println("El automata es determinista (AFD), por lo tanto NO es necesario proceder");
        }
        
    }

}
