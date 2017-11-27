package afndtoafd;

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
    HashMap<Integer, String> gobjHashMapAlfabetoTransiciones;
    TreeMap<Integer, Set<Integer>> gobjTreeMapMapaAutomata, gobjTreeMapAlfabetoEstados;
    Set<Integer> objTreeSetNuevosFinales, gobjTreeSetEstadoInicial;

    /**
     * TransformAFNDtoAFD 
     * Constructor que inicializa los parámetros del automata finito determinista
     * @param objAutomataAnterior Es un objeto de tipo automata
     */
    public TransformAFNDtoAFD(Automata objAutomataAnterior)
    {
        this.gobjTreeSetEstadoInicial = objAutomataAnterior.gobjSetEstadoInicial;
        this.gobjTreeMapAlfabetoEstados = objAutomataAnterior.gobjTreeMapAlfabetoEstados;
        this.gobjHashMapAlfabetoTransiciones = objAutomataAnterior.gobjHashMapAlfabetoTransiciones;
        this.objTreeSetNuevosFinales = objAutomataAnterior.gobjSetEstadosFinales;
        this.gobjTreeMapMapaAutomata = objAutomataAnterior.gobjTreeMapAutomata;
    }

    /**
     * validaAutomata 
     * Método para validar si el automata es NO determinista
     * @return true si el autómata es NO determinista, false en caso contrario
     */
    public boolean validaAutomata()
    {
        for (Map.Entry<Integer, Set<Integer>> objEntry : gobjTreeMapMapaAutomata.entrySet())
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
     */
    public void transformarAutomata()
    {
        int lEValores;
        do
        {
            lEValores = 0;
            TreeMap<Integer, Set<Integer>> objTreeMapConjuntosCombinados = new TreeMap<>();

            for (Map.Entry<Integer, Set<Integer>> objEntry : gobjTreeMapMapaAutomata.entrySet())
            {
                if (!gobjTreeMapAlfabetoEstados.containsValue(objEntry.getValue()))
                {
                    gobjTreeMapAlfabetoEstados.put((gobjTreeMapAlfabetoEstados.lastKey() + 1), objEntry.getValue());
                    objTreeMapConjuntosCombinados.put(objTreeMapConjuntosCombinados.size(), objEntry.getValue());

                }
            }

            //Para recorrer los identificadores con estados repetidos
            for (Map.Entry<Integer, Set<Integer>> objEntryConjuntos : objTreeMapConjuntosCombinados.entrySet())
            {
                TreeMap<Integer, ArrayList<Integer>> objTreeMapEstadosMapeados = new TreeMap<>();
                //Para recorrer los estados dentro de cada identificador
                for (Iterator objIterator = objEntryConjuntos.getValue().iterator(); objIterator.hasNext();)
                {
                    int lEEstado = Integer.parseInt(String.valueOf(objIterator.next()).trim());
                    int lEIndiceInicio = lEEstado * gobjHashMapAlfabetoTransiciones.size() + 1;
                    int lEIndiceFinal = lEEstado * gobjHashMapAlfabetoTransiciones.size() + gobjHashMapAlfabetoTransiciones.size();
//                System.out.println("Estado "+ lEEstado + " con indice inicial " + lEIndiceInicio + " hasta " + lEIndiceFinal);
                    ArrayList<Integer> objArrayListLista = new ArrayList<>();
                    //Para conocer si hay un elemento dentro de cada rango
                    for (Map.Entry<Integer, Set<Integer>> objEntry : gobjTreeMapMapaAutomata.entrySet())
                    {
                        if (objEntry.getKey() >= lEIndiceInicio && objEntry.getKey() <= lEIndiceFinal)
                        {
//                        System.out.println("Clave " + objEntry.getKey()+ " con estados " + gobjTreeMapMapaAutomata.get(objEntry.getKey()).toString());
                            objArrayListLista.add(objEntry.getKey());
                        }
                    }
                    objTreeMapEstadosMapeados.put(lEEstado, objArrayListLista);
                }

                for (Map.Entry<Integer, ArrayList<Integer>> objEntryLocalidador : objTreeMapEstadosMapeados.entrySet())
                {
                    if (!objEntryLocalidador.getValue().isEmpty())
                    {
                        for (int lEIteraInterno = 0; lEIteraInterno < objEntryLocalidador.getValue().size(); lEIteraInterno++)
                        {
                            for (Map.Entry<Integer, ArrayList<Integer>> objEntryLocalidador1 : objTreeMapEstadosMapeados.entrySet())
                            {
                                if (objEntryLocalidador.getKey() != objEntryLocalidador1.getKey())
                                {
                                    int lEIdentificadorLocalizado = obtenerIdentificador(objEntryLocalidador1.getKey(), gobjHashMapAlfabetoTransiciones.size(), obtenerCoordenadaTransicion(objEntryLocalidador.getValue().get(lEIteraInterno), gobjHashMapAlfabetoTransiciones.size()) - 1);
                                    int lEIdentificadorNuevo = obtenerIdentificador(obtenerKeyEstado(gobjTreeMapAlfabetoEstados, objEntryConjuntos.getValue()), gobjHashMapAlfabetoTransiciones.size(), obtenerCoordenadaTransicion(objEntryLocalidador.getValue().get(lEIteraInterno), gobjHashMapAlfabetoTransiciones.size()) - 1);

                                    Set<Integer> objTreeSetAuxiliar = new TreeSet<>();
                                    if (gobjTreeMapMapaAutomata.containsKey(lEIdentificadorNuevo)) //Si el mapa contiene el identificador nuevo
                                    {
                                        objTreeSetAuxiliar.addAll(gobjTreeMapMapaAutomata.get(lEIdentificadorNuevo));
                                    }
                                    if (gobjTreeMapMapaAutomata.containsKey(lEIdentificadorLocalizado)) //Si el mapa de automata contiene el identificador comparable
                                    {
                                        objTreeSetAuxiliar.addAll(gobjTreeMapMapaAutomata.get(lEIdentificadorLocalizado));
                                    }
                                    
                                    objTreeSetAuxiliar.addAll(gobjTreeMapMapaAutomata.get(objEntryLocalidador.getValue().get(lEIteraInterno)));
                                    gobjTreeMapMapaAutomata.put(lEIdentificadorNuevo, objTreeSetAuxiliar);
                                }
                            }
                        }
                    }
                }
            }

           System.out.println("*************************************");

            for (Map.Entry<Integer, Set<Integer>> objEntry : gobjTreeMapMapaAutomata.entrySet())
            {
                System.out.println("Clave " + objEntry.getKey());
                System.out.println("Con estados " + objEntry.getValue().toString());
            }
            
            
            for (Map.Entry<Integer, Set<Integer>> objEntry : gobjTreeMapMapaAutomata.entrySet())
            {
                if (gobjTreeMapAlfabetoEstados.containsValue(objEntry.getValue()))
                {
                     lEValores++;
                }
            }
        } while (lEValores != gobjTreeMapMapaAutomata.size());

        for (Map.Entry<Integer, Set<Integer>> objEntryUno : gobjTreeMapMapaAutomata.entrySet())
        {
            for (Map.Entry<Integer, Set<Integer>> objEntryDos : gobjTreeMapAlfabetoEstados.entrySet())
            {
                if (objEntryUno.getValue().equals(objEntryDos.getValue()))
                {
                    Set<Integer> objTreeSetAux = new TreeSet<>();
                    objTreeSetAux.add(objEntryDos.getKey());
                    gobjTreeMapMapaAutomata.put(objEntryUno.getKey(), objTreeSetAux);
                }
            }
        }

        System.out.println("*************************************");

        for (Map.Entry<Integer, Set<Integer>> objEntry : gobjTreeMapMapaAutomata.entrySet())
        {
            System.out.println("Clave " + objEntry.getKey());
            System.out.println("Con estados " + objEntry.getValue().toString());
        }

    }
    
     /**
     * obtenerKey
     * Metodo para obtener la clave del alfabeto a partir de una cadena
     * @param objTreeMapEstados Es el mapa que contiene los elementos de un alfabeto
     * @param objTreeSetValue Es el conjunto asociado para ontener la clave
     * @return la clave a partir de una coincidencia de lSValue, si no se encuentra entonces no está dentro del automata
     */
    public int obtenerKeyEstado(TreeMap<Integer, Set<Integer>> objTreeMapEstados, Set<Integer> objTreeSetValue)
    {
        for (Map.Entry<Integer, Set<Integer>> entry : objTreeMapEstados.entrySet())
        {
            if (entry.getValue().equals(objTreeSetValue))
            {
                return entry.getKey();
            }
        }
        return -1;
    }

    /**
     * obtenerKey 
     * Metodo para obtener la clave del alfabeto a partir de una cadena
     * @param objHashMapCadenas Es el mapa que contiene los elementos de un alfabeto
     * @param lSValue Es el conjunto asociado para obtener la clave
     * @return la clave a partir de una coincidencia de lSValue, si no se encuentra entonces no está dentro del automata
     */
    public int obtenerKey(HashMap<Integer, String> objHashMapCadenas, String lSValue)
    {
        
        for (Map.Entry<Integer, String> objEntry : objHashMapCadenas.entrySet())
        {
            if (objEntry.getValue().trim().equals(lSValue))
            {
                return objEntry.getKey();
            }
        }
        return -1;
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
        double lDResiduo = ((lEIdentificador - 1) / lETamanoTransiciones);
        return (((int) lDResiduo) + 1);
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
        return ((lEIndiceEstados * lETamanioAlfeboTransiciones) + lEIndiceTransicion + 1);
    }

    /**
     * run 
     * Método principal de ejecución
     */
    public void run()
    {
        if (validaAutomata())
        {
            System.out.println("El automata es no determinista (AFND), por lo tanto es necesario proceder");
            transformarAutomata();
//            System.out.println(objTreeSetNuevosFinales);
            for (Map.Entry<Integer, Set<Integer>> objEntryEstados : gobjTreeMapAlfabetoEstados.entrySet())
            {
                String[] lASCadena = objTreeSetNuevosFinales.toString().replace("[", "").replace("]", "").split(",");
                for (int lEKesimo = 0; lEKesimo < lASCadena.length; lEKesimo++)
                {
                    String[] lASCadenasSeparadas = objEntryEstados.getValue().toString().replace("[", "").replace("]", "").split(",");
                    for (int lEIesimo = 0; lEIesimo < lASCadenasSeparadas.length; lEIesimo++)
                    {
                        if (Integer.parseInt(lASCadena[lEKesimo].trim()) == Integer.parseInt(lASCadenasSeparadas[lEIesimo].trim()))
                        {
                            objTreeSetNuevosFinales.add(objEntryEstados.getKey());
                        }
                    }
                }
            }
//            System.out.println("Nuevos estados finales " + objTreeSetNuevosFinales.toString());
        }else
        {
            System.out.println("El automata es determinista (AFD), por lo tanto NO es necesario proceder");
        }
    }

}
