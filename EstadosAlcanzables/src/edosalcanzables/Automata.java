package edosalcanzables;

import basics.ManejoArchivos;
import basics.MyListArgs;
import java.util.HashMap;
import java.util.HashSet;
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
    String gSPathTrabajo, gSAlfabetoEstados, gSAlfabetoTransiciones, gSEstadoInicial, gSEstadosFinales, gSTransiciones, gSPath_Salida;
    HashMap<Integer, String> objMapAlfabetoEstados, objMapAlfabetoTransiciones, objMapEstadosFinales;
    TreeMap<Integer, Set<Integer>> objTreeMapAutomata;
    Map<Integer, Set<Integer>> objMapEdosActualEdosSig;
    int gEEstadoInicial;
    String[] lSCadenas;    
    
    /**
     * Automata
     * Constructor que inicializa los parámetros y autributos del autómata
     * @param args Es la línea de comandos
     */
    public Automata(String[] args)
    {

        MyListArgs Param = new MyListArgs(args);
        String ConfigFile = Param.ValueArgsAsString("-CONFIG", "");//Archivo donde se especifican los parametros necesarios para este componente
        if (!ConfigFile.equals(""))
        {
            Param.AddArgsFromFile(ConfigFile);
        }

        gSPathTrabajo = Param.ValueArgsAsString("-FWORK", "");  //Ruta de trabajo
        gSAlfabetoEstados = Param.ValueArgsAsString("-Q", ""); // Q es igual al conjunto de estados del automata, los estados van separados por coma
        gSAlfabetoTransiciones = Param.ValueArgsAsString("-A", ""); // A es el alfabeto que corresponde al automata
        gSEstadoInicial = Param.ValueArgsAsString("-EI", ""); // EI es el estado incial que corresponde al automata, pertenece a Q
        gSEstadosFinales = Param.ValueArgsAsString("-F", ""); // F corresponde a los estados o estado final que corresponde al automata, pertenece a Q
        gSTransiciones = Param.ValueArgsAsString("-T", "");  // T corresponde a la funcion de transicion, el estado X con el elemento del alfabeto Y hacia que estado va
        gSPath_Salida = Param.ValueArgsAsString("-FSALIDA", "");  //Ruta donde se van a almacenar los resultados del automata
        
        objMapAlfabetoEstados = new HashMap<>();
        objMapAlfabetoTransiciones = new HashMap<>();
        objMapEstadosFinales = new HashMap<>();
        
        ManejoArchivos objArchivos = new ManejoArchivos();
        
        /* La siguiente linea guarda en un StringArray la primer linea (solo apunta a la primera linea del archivo [0]),
         * del archivo gSAlfabetoEstados. Esta linea está separada por comas; se divide y es devuelta como un 
         * StringArray por "split" */
        String[] lSContenidosAlfabetoEstados        = objArchivos.Read_Text_File_NoNull((gSPathTrabajo + "\\" + gSAlfabetoEstados).replace("\\", "/"))[0].split(",");
        String[] lSContenidoAlfabetoTransiciones    = objArchivos.Read_Text_File_NoNull((gSPathTrabajo + "\\" + gSAlfabetoTransiciones).replace("\\", "/"))[0].split(",");
        String[] lASEstadosAceptacion               = objArchivos.Read_Text_File_NoNull((gSPathTrabajo + "\\" + gSEstadosFinales).replace("\\", "/"))[0].split(",");
        lSCadenas                                   = objArchivos.Read_Text_File_NoNull((gSPathTrabajo + "\\" + gSTransiciones).replace("\\", "/"));
        
        int lEIterator = 0;        
        while (lEIterator < lSContenidosAlfabetoEstados.length)
        {
            /* Guarda en un HashMap la KEY "lEItarator" y el VALOR "lSContenidosAlfabetoEstados[lEIterator]" */
            objMapAlfabetoEstados.put(lEIterator, lSContenidosAlfabetoEstados[lEIterator].trim());
            lEIterator++;
        }

        lEIterator = 0;
        while (lEIterator < lSContenidoAlfabetoTransiciones.length)
        {
            objMapAlfabetoTransiciones.put(lEIterator, lSContenidoAlfabetoTransiciones[lEIterator].trim());
            lEIterator++;
        }
        
        lEIterator = 0;
        while (lEIterator < lASEstadosAceptacion.length)
        {
            objMapEstadosFinales.put(lEIterator, lASEstadosAceptacion[lEIterator].trim());
            lEIterator++;
        }
//        keyAux = this.obtenerKey(objMapAlfabetoEstados, lSElementos[0].trim());
        gEEstadoInicial = this.obtenerKey(objMapAlfabetoEstados, gSEstadoInicial);
//        gEEstadoInicial = Integer.parseInt(gSEstadoInicial);
    }

    /**
     * muestraAlfabetos
     * Metodo para imprimir en consola la conformacion del alfabeto
     */
    public void muestraAlfabetos()
    {
        System.out.println("Estados");
        objMapAlfabetoEstados.entrySet().forEach((entry) ->
        {
            System.out.println("Clave " + entry.getKey() + " con estado " + entry.getValue());
        });

        System.out.println("Transiciones");
        objMapAlfabetoTransiciones.entrySet().forEach((entry) ->
        {
            System.out.println("Clave " + entry.getKey() + " con transicion " + entry.getValue());
        });
        
        System.out.println("Estados aceptacion");
        objMapEstadosFinales.entrySet().forEach((entry) ->
        {
            System.out.println("Clave " + entry.getKey() + " con estado final " + entry.getValue());
        });
    }

    /**
     * contruirAutomata
     * Método para construir un autómata en un mapa ordenado con la clase TreeMap
     * @return Un mapa ordenado que contiene los datos relacionados a la función de transición
     */
//    public TreeMap<Integer, Set<Integer>> contruirAutomata()
//    {
//        TreeMap<Integer,Set<Integer>> objHashMapAutomata = new TreeMap<>();
//
//        Validation objValidacion = new Validation(lSCadenas, objMapAlfabetoEstados, objMapAlfabetoTransiciones, objMapEstadosFinales, gSEstadoInicial);
//        if (!objValidacion.validacionIntegral())
//        {
//            System.out.println("La definicion del automata presenta errores");
//        } else
//        {
//            for (int i = 0; i < lSCadenas.length; i++)      // lSCadenas es un arreglo de cadenas, donde cada una de ellas una transicion
//            {
//                String[] lSElementos = lSCadenas[i].replace("[", "").replace("]", "").split(",");   // lSElementos es un Arreglo de Cadenas que  guarda los elementos de cada cadena del arreglo lSCadenas 
//
//                int lSIdentificador = obtenerIdentificador(obtenerKey(objMapAlfabetoEstados, lSElementos[0].trim()),
//                         objMapAlfabetoTransiciones.size(),
//                        obtenerKey(objMapAlfabetoTransiciones, lSElementos[1].trim()));
//                
////                System.out.println(lSIdentificador);
//                Set<Integer> objSetBandera = new TreeSet<>();
//
//                if (objHashMapAutomata.isEmpty())
//                {
//                    objSetBandera.add(Integer.parseInt(lSElementos[2].trim()));
//                    objHashMapAutomata.put(lSIdentificador, objSetBandera);
//                } else if (objHashMapAutomata.containsKey(lSIdentificador))
//                {
//                    objSetBandera = objHashMapAutomata.get(lSIdentificador);
//                    boolean lBEstadoRepetido = false;
//                    for (Iterator objIterator = objSetBandera.iterator(); objIterator.hasNext();)
//                    {
//                        if (Integer.parseInt(String.valueOf(objIterator.next())) == Integer.parseInt(lSElementos[2].trim()))
//                        {
//                            lBEstadoRepetido = true;
//                            break;
//                        }
//                    }
//
//                    if (!lBEstadoRepetido)
//                    {
//                        objSetBandera.add(Integer.parseInt(lSElementos[2].trim()));
//                        objHashMapAutomata.put(lSIdentificador, objSetBandera);
//                    }
//                } else
//                {
//                    objSetBandera = new TreeSet<>();
//                    objSetBandera.add(Integer.parseInt(lSElementos[2].trim()));
//                    objHashMapAutomata.put(lSIdentificador, objSetBandera);
//                }
//            }
//        }
//        return objHashMapAutomata;
//    }
    
    
    /**
     * contruirMapEdosActualEdosSig
     * Método para construir un Mapa de EstadoActual y sus EstadosSiguientes (conjunto), en un mapa ordenado con la clase TreeMap
     * @return Un mapa ordenado que contiene los datos relacionados a la función de transición
     */
    public Map<Integer, Set<Integer>> contruirMapEdosActualEdosSig(){
        objMapEdosActualEdosSig = new TreeMap<>();    

        Validation objValidacion = new Validation(lSCadenas, objMapAlfabetoEstados, objMapAlfabetoTransiciones, objMapEstadosFinales, gSEstadoInicial);
        if (!objValidacion.validacionIntegral())
//        if (1!=1)           
        {
            System.out.println("La definicion del automata presenta errores");
        } else
        {
            System.out.println(objMapAlfabetoEstados);
            
            for (int i = 0; i < lSCadenas.length; i++)      // lSCadenas es un arreglo de cadenas, donde cada una de ellas es una transicion [10,c,3,a,d]
            {
                Integer keyAux;
                Integer valueAux;
                Set objSetAux1 = new HashSet<>();
                Set objSetAux2 = new HashSet<>();
                String[] lSElementos = lSCadenas[i].replace("[", "").replace("]", "").split(",");   // lSElementos es un Arreglo de Cadenas que  guarda los elementosCadena de cada cadena del arreglo lSCadenas 

                System.out.println("Cadena:" + Integer.toString(i) + "    " + lSCadenas[i].replace("[", "").replace("]", ""));
//                objSetAux1.addAll(objMapEdosActualEdosSig.get(Integer.parseInt(lSElementos[0].trim())));
                
                keyAux = this.obtenerKey(objMapAlfabetoEstados, lSElementos[0].trim());
                valueAux = this.obtenerKey(objMapAlfabetoEstados, lSElementos[2].trim());
                if(!objMapEdosActualEdosSig.containsKey(keyAux)){
                    objSetAux1.clear();
                    /* Agrega un nuevo elemento al mapa de EstadosSiguientes */ 
                    objMapEdosActualEdosSig.put(keyAux,objSetAux1);
                    objMapEdosActualEdosSig.get(keyAux).add(valueAux);
                    
                    System.out.println(objMapEdosActualEdosSig);
                    
                    if(!objMapEdosActualEdosSig.containsKey(valueAux)){
                        objSetAux2.clear();
                        /* Agrega un nuevo elemento al mapa de EstadosSiguientes */ 
                        objMapEdosActualEdosSig.put(valueAux,objSetAux2 );
                        
                        System.out.println(objMapEdosActualEdosSig);
                    }                    
                }else{
                    /* Agrega un edoSiguiente al conjunto de EstadosSiguientes del EstadoActual: "Integer.parseInt(lSElementos[0].trim())" */
//                    objMapEdosActualEdosSig.get(Integer.parseInt(lSElementos[0].trim())).add(Integer.parseInt(lSElementos[2].trim()));
                    objMapEdosActualEdosSig.get(keyAux).add(valueAux);
                    
                    System.out.println(objMapEdosActualEdosSig);
                    
                    if(!objMapEdosActualEdosSig.containsKey(valueAux)){
                        objSetAux1.clear();
                        /* Agrega un nuevo elemento al mapa de EstadosSiguientes */ 
                        objMapEdosActualEdosSig.put(valueAux,objSetAux1 );
                        
                        System.out.println(objMapEdosActualEdosSig);
                    }                     
                }
            }
        }        
        return objMapEdosActualEdosSig;        
    }
    
    /**
     * obtenerKey
     * Metodo para obtener la clave del alfabeto a partir de una cadena
     * @param objHashMapCadenas Es el mapa que contiene los elementos de un alfabeto
     * @param lSValue Es la cadena asociada para ontener la clave
     * @return la clave a partir de una coincidencia de lSValue, si no se encuentra entonces no está dentro del automata
     */
    public int obtenerKey(HashMap<Integer, String> objHashMapCadenas, String lSValue)
    {
        for (Map.Entry<Integer, String> entry : objHashMapCadenas.entrySet())
        {
            if (entry.getValue().trim().equals(lSValue))
            {
                return entry.getKey();
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
        return ((lEIndiceEstados * lETamanioAlfeboTransiciones) + lEIndiceTransicion +1);
    }
    
    
    /**
     * run
     * Método para mostrar el Autómata
     */
//    public void run()
//    {
//        this.muestraAlfabetos();
//        objTreeMapAutomata = this.contruirAutomata();
//        
//        for (Map.Entry<Integer, Set<Integer>> entry : objTreeMapAutomata.entrySet())
//        {
//            System.out.println("Clave " + entry.getKey());
//            System.out.println("Con estados " + entry.getValue().toString());
//        }
//
//        System.out.println("Tamaño del mapa " + objTreeMapAutomata.size());
//
//    }
}
