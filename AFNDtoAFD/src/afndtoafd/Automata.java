package afndtoafd;

import basics.ManejoArchivos;
import basics.MyListArgs;
import basics.MySintaxis;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Automata
 * Clase que se encarga de crear un automata
 * @author Jonathan Rojas Simón <ids_jonathan_rojas@hotmail.com>
 */
public class Automata
{
    String gSAlfabetoEstados, gSAlfabetoTransiciones, gSEstadosFinales, gSTransiciones, gSPath_Salida, gSPath_Trabajo;
    HashMap<Integer, String> gobjHashMapAlfabetoTransiciones;
    TreeMap<Integer, Set<String>> gobjTreeMapAutomata;
    TreeMap<Integer, Set<String>> gobjTreeMapAlfabetoEstados;
    Set<String> gobjSetEstadosFinales, gobjSetEstadoInicial;

    /**
     * Automata
     * Constructor que inicializa los parámetros y autributos del autómata
     * @param args Es la línea de comandos
     */
    public Automata(String[] args)
    {
        MyListArgs objMyListArgsParam = new MyListArgs(args);
        String lSConfigFile = objMyListArgsParam.ValueArgsAsString("-CONFIG", "");//Archivo donde se especifican los parametros necesarios para este componente
        if (!lSConfigFile.equals(""))
        {
            objMyListArgsParam.AddArgsFromFile(lSConfigFile);
        }
        
        String lSSintaxis = "-FWORK:str -FSALIDA:str -Q:str -A:str  -EI:str -F:str -T:str";
        MySintaxis objMySintaxis = new MySintaxis(lSSintaxis, objMyListArgsParam);

        gSAlfabetoEstados = objMyListArgsParam.ValueArgsAsString("-Q", ""); // Q es igual al conjunto de estados del automata, los estados van separados por coma
        gSAlfabetoTransiciones = objMyListArgsParam.ValueArgsAsString("-A", ""); // A es el alfabeto que corresponde al automata
        gobjSetEstadoInicial = new TreeSet<>();
        try
        {
            gobjSetEstadoInicial.add(objMyListArgsParam.ValueArgsAsString("-EI", "")); // EI es el estado incial que corresponde al automata, pertenece a Q
        } catch (Exception objException)
        {
            System.out.println("Error en la definicion del estado inicial " + objException.getMessage());
            System.exit(0);
        }
        
        gSEstadosFinales = objMyListArgsParam.ValueArgsAsString("-F", ""); // F corresponde a los estados o estado final que corresponde al automata, pertenece a Q
        gSTransiciones = objMyListArgsParam.ValueArgsAsString("-T", "");  // T corresponde a la funcion de transicion, el estado X con el elemento del alfabeto Y hacia que estado va
        gSPath_Salida = objMyListArgsParam.ValueArgsAsString("-FSALIDA", "");  //Ruta donde se van a almacenar los resultados del automata
        gSPath_Trabajo = objMyListArgsParam.ValueArgsAsString("-FWORK", "");
        gSPath_Trabajo = gSPath_Trabajo.replace("\\", "/");
        
        gobjTreeMapAlfabetoEstados = new TreeMap<>();
        gobjHashMapAlfabetoTransiciones = new HashMap<>();

        try
        {
            String[] lASContenidosAlfabetoEstados = new ManejoArchivos().Read_Text_File_NoNull(gSPath_Trabajo + "/" + gSAlfabetoEstados)[0].split(",");
            String[] lASContenidoAlfabetoTransiciones = new ManejoArchivos().Read_Text_File_NoNull(gSPath_Trabajo + "/" + gSAlfabetoTransiciones)[0].split(",");
            String[] lASEstadosAceptacion = new ManejoArchivos().Read_Text_File_NoNull(gSPath_Trabajo + "/" +  gSEstadosFinales.replace("\\", "/"))[0].split(",");

            for (int lEIterator = 0; lEIterator < lASContenidosAlfabetoEstados.length; lEIterator++)
            {
                Set<String> objSetEstado = new TreeSet<>();
                objSetEstado.add(lASContenidosAlfabetoEstados[lEIterator].trim());
                gobjTreeMapAlfabetoEstados.put(lEIterator, objSetEstado);
            }

            for (int lEIterator = 0; lEIterator < lASContenidoAlfabetoTransiciones.length; lEIterator++)
            {
                gobjHashMapAlfabetoTransiciones.put(lEIterator, lASContenidoAlfabetoTransiciones[lEIterator].trim());
            }

            gobjSetEstadosFinales = new TreeSet<>();
            for (int lEIterator = 0; lEIterator < lASEstadosAceptacion.length; lEIterator++)
            {
                gobjSetEstadosFinales.add(lASEstadosAceptacion[lEIterator].trim());
            }

        } catch (Exception objException)
        {
            System.out.println("Error en la definicion de alfabetos del automata " + new Throwable(objException.getCause()).getLocalizedMessage());
            System.exit(0);
        }
    }

    /**
     * muestraAlfabetos
     * Metodo para imprimir en consola la conformacion del alfabeto
     */
    public void muestraAlfabetos()
    {
        System.out.println("Estados");
        gobjTreeMapAlfabetoEstados.entrySet().forEach((objEntry) ->
        {
            System.out.println("Clave " + objEntry.getKey() + " con estado " + objEntry.getValue());
        });

        System.out.println("Transiciones");
        gobjHashMapAlfabetoTransiciones.entrySet().forEach((objEntry) ->
        {
            System.out.println("Clave " + objEntry.getKey() + " con transicion " + objEntry.getValue());
        });
        
        System.out.println("Estados aceptacion " + gobjSetEstadosFinales.toString());
    }

    /**
     * contruirAutomata
     * Método para construir un autómata en un mapa ordenado con la clase TreeMap
     * @return Un mapa ordenado que contiene los datos relacionados a la función de transición
     */
    public TreeMap<Integer, Set<String>> contruirAutomata()
    {
        TreeMap<Integer,Set<String>> objTreeMapAutomata = new TreeMap<>();
        String[] lASCadenas = new ManejoArchivos().Read_Text_File_NoNull(gSPath_Trabajo + "/" + gSTransiciones.replace("\\", "/"));
        
        if (lASCadenas.length < 1)
        {
            System.out.println("No hay producciones en la funcion de transicion");
            System.exit(0);
        }

        Validation objValidacion = new Validation(lASCadenas, gobjTreeMapAlfabetoEstados, gobjHashMapAlfabetoTransiciones, gobjSetEstadosFinales, gobjSetEstadoInicial);
        if (!objValidacion.validacionIntegral())
        {
            System.out.println("La definicion del automata presenta errores");
        } else
        {
            for (int lEitera = 0; lEitera < lASCadenas.length; lEitera++)
            {
                String[] lASElementos = lASCadenas[lEitera].replace("[", "").replace("]", "").split(",");
                Set<String> objTreeSetEstado = new TreeSet<>();
                objTreeSetEstado.add(lASElementos[0].trim());

                int lEIdentificador = obtenerIdentificador(obtenerKeyEstado(gobjTreeMapAlfabetoEstados, objTreeSetEstado),
                         gobjHashMapAlfabetoTransiciones.size(),
                        obtenerKeyTransicion(gobjHashMapAlfabetoTransiciones, lASElementos[1].trim()));
               
                Set<String> objTreeSetBandera = new TreeSet<>();
                if (objTreeMapAutomata.isEmpty())
                {
                    objTreeSetBandera.add(lASElementos[2].trim());
                    objTreeMapAutomata.put(lEIdentificador, objTreeSetBandera);
                } else if (objTreeMapAutomata.containsKey(lEIdentificador))
                {
                    objTreeSetBandera = objTreeMapAutomata.get(lEIdentificador);
                    boolean lBEstadoRepetido = false;
                    for (Iterator objIterator = objTreeSetBandera.iterator(); objIterator.hasNext();)
                    {
                        if (String.valueOf(objIterator.next()).equals(lASElementos[2].trim()))
                        {
                            System.out.println("Hay producciones en la Función de transición repetidos, por lo tanto se van a unificar");
                            lBEstadoRepetido = true;
                            break;
                        }
                    }

                    if (!lBEstadoRepetido)
                    {
                        objTreeSetBandera.add(lASElementos[2].trim());
                        objTreeMapAutomata.put(lEIdentificador, objTreeSetBandera);
                    }
                } else
                {
                    objTreeSetBandera = new TreeSet<>();
                    objTreeSetBandera.add(lASElementos[2].trim());
                    objTreeMapAutomata.put(lEIdentificador, objTreeSetBandera);
                }
            }
        }
        return objTreeMapAutomata;
    }
    
    /**
     * obtenerKey
     * Metodo para obtener la clave del alfabeto a partir de una cadena
     * @param objTreeMapEstados Es el mapa que contiene los elementos de un alfabeto
     * @param objSetValue Es la cadena asociada para ontener la clave
     * @return la clave a partir de una coincidencia de lSValue, si no se encuentra entonces no está dentro del automata
     */
    public int obtenerKeyEstado(TreeMap<Integer, Set<String>> objTreeMapEstados, Set<String> objSetValue)
    {
        for (Map.Entry<Integer, Set<String>> objEntry : objTreeMapEstados.entrySet())
        {
            if (objEntry.getValue().equals(objSetValue))
            {
                return objEntry.getKey();
            }
        }
        return -1;
    }
    
    /**
     * obtenerKey
     * Metodo para obtener la clave del alfabeto a partir de una cadena
     * @param objHashMapCadenas Es el mapa que contiene los elementos de un alfabeto
     * @param lSValue Es la cadena asociada para ontener la clave
     * @return la clave a partir de una coincidencia de lSValue, si no se encuentra entonces no está dentro del automata
     */
    public int obtenerKeyTransicion(HashMap<Integer, String> objHashMapCadenas, String lSValue)
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

    public void run()
    {
//        this.muestraAlfabetos();
        gobjTreeMapAutomata = this.contruirAutomata();
        
        for (Map.Entry<Integer, Set<String>> objEntry : gobjTreeMapAutomata.entrySet())
        {
            System.out.println("Clave " + objEntry.getKey());
            System.out.println("Con estados " + objEntry.getValue().toString());
        }
    }
}
