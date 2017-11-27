package afndtoafd;

import basics.ManejoArchivos;
import java.util.Map;
import java.util.Set;

/**
 * Salida
 * Clase que especifica las salidas en archivos de texto
 * @author Jonathan Rojas Simón <ids_jonathan_rojas@hotmail.com>
 */
public class Salida
{
    TransformAFNDtoAFD gobjTransformAFNDtoAFD;
    String gSPathSalida, gSPathTrabajo, gSFuncionTransicion, gSAlfeboEstados, gSAlfeboTransiciones, gSEdosFinales;
    Set<Integer> gobjTreeSetEstadoInicial;

    /**
     * Salida
     * Constructor que inicializa los parámetros del autómata de salida
     * @param gobjTransformAFNDtoAFD Es el objeto del AFD transformado
     * @param objAutomata Es el objeto del automat que contiene los datos de inicio
     */
    public Salida(TransformAFNDtoAFD gobjTransformAFNDtoAFD, Automata objAutomata)
    {
        this.gobjTransformAFNDtoAFD = gobjTransformAFNDtoAFD;
        this.gSPathSalida = objAutomata.gSPath_Salida;
        this.gobjTreeSetEstadoInicial = objAutomata.gobjSetEstadoInicial;
        this.gSAlfeboEstados = objAutomata.gSAlfabetoEstados;
        this.gSAlfeboTransiciones = objAutomata.gSAlfabetoTransiciones;
        this.gSFuncionTransicion = objAutomata.gSTransiciones;
        this.gSEdosFinales = objAutomata.gSEstadosFinales;
        this.gSPathSalida = this.gSPathSalida.replace("\\", "/");
        this.gSPathTrabajo = objAutomata.gSPath_Trabajo;
        this.gSPathTrabajo = this.gSPathTrabajo.replace("\\", "/");
    }
    
    /**
     * runSalidas
     * Método para generar las salidas del autómata en archivos de texto
     */
    public void runSalidas()
    {
        if (!new ManejoArchivos().ExisteCarpetaArchivo(gSPathSalida))
        {
            new ManejoArchivos().UntilCrearCarpetas(gSPathSalida);
        }
        
        System.out.println("Generando Archivos de salida del AFD");
        generaSimboloInicialArchivo();
        generaEstadosArchivoTexto();
        generaTransicionesArchivoTexto();
        generaEstadosFinales();
        generaArchivoFuncionTransicion();
        crearArchivoConfig();
    }
    
    
    /**
     * crearArchivoConfig
     * Método para crear en un archivo de texto los parámetros del archivo config
     */
    public void crearArchivoConfig()
    {
        String lSSalida = "#ELEMENTOS NECESARIOS PARA EL AUTOMATA";
        lSSalida += "\r\n \r\n";
        lSSalida += "# Q es igual al conjunto de estados del automata, los estados van separados por coma";
        lSSalida += "\r\n \r\n";
        lSSalida += "-Q \t \"" + gSAlfeboEstados + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "# A es el alfabeto que corresponde al automata";
        lSSalida += "\r\n \r\n";
        lSSalida += "-A \t \"" + gSAlfeboTransiciones + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "# EI es el estado incial que corresponde al automata, pertenece a Q";
        lSSalida += "\r\n \r\n";
        lSSalida += "-EI \t \"" + gobjTreeSetEstadoInicial.toString().replace("[", "").replace("]", "") + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "# F corresponde a los estados o estado final que corresponde al automata, pertenece a Q";
        lSSalida += "\r\n \r\n";
        lSSalida += "-F \t \"" + gSEdosFinales + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "# T corresponde a la funcion de transicion, el estado X con el elemento del alfabeto Y hacia que estado va";
        lSSalida += "\r\n \r\n";
        lSSalida += "-T \t \"" + gSFuncionTransicion + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "#Rutas de trabajo";
        lSSalida += "\r\n \r\n";
        lSSalida += "-FWORK \t \"" + gSPathSalida + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "-FSALIDA \t \"" + gSPathSalida + "\"";
        lSSalida += "\r\n \r\n";
        
        new ManejoArchivos().Write_String_File(gSPathSalida + "/CONFIG.txt", lSSalida);
    }
    
    /**
     * generaTransicionesArchivoTexto
     * Método que transforma el mapa de las transiciones en un formato legible de texto
     */
    public void generaTransicionesArchivoTexto()
    {
        String lSSalida = "";
        for (Map.Entry<Integer, String> objEntry : gobjTransformAFNDtoAFD.gobjHashMapAlfabetoTransiciones.entrySet())
        {
            lSSalida += objEntry.getValue() + ",";
        }
        if (lSSalida.trim().lastIndexOf(",") == lSSalida.length() - 1)
        {
            lSSalida = lSSalida.trim().substring(0, lSSalida.length() - 1);
        }
        System.out.println("Transiciones " + lSSalida);
        new ManejoArchivos().Write_String_File(gSPathSalida + "/" + gSAlfeboTransiciones, lSSalida);
    }
    
    /**
     * generaEstadosArchivoTexto
     * Método que genera un archivo de texto que muestra el alfabeto de estados
     */
    public void generaEstadosArchivoTexto()
    {
        String lSSalida = "";
        for (Map.Entry<Integer, Set<Integer>> objEntry: gobjTransformAFNDtoAFD.gobjTreeMapAlfabetoEstados.entrySet())
        {
            lSSalida += objEntry.getKey() + ",";
        }
        if (lSSalida.trim().lastIndexOf(",") == lSSalida.length() -1)
        {
            lSSalida = lSSalida.trim().substring(0, lSSalida.length() -1);
        }
        System.out.println("Estados salida: " + lSSalida);
        new ManejoArchivos().Write_String_File(gSPathSalida + "/" + gSAlfeboEstados, lSSalida);
    }
    
    /**
     * generaSimboloInicialArchivo
     * Método para generar el símbolo inicial en un archivo de texto
     */
    public void generaSimboloInicialArchivo()
    {
        System.out.println("Simbolo inicial " + gobjTreeSetEstadoInicial);
        new ManejoArchivos().Write_String_File(gSPathSalida + "/Inicial.txt", gobjTreeSetEstadoInicial.toString().replace("[", "").replace("]", ""));
    }
    
    /**
     * generaEstadosFinales
     * Método para generar el conjunto de estados finales en un archivo de texto
     */
    public void generaEstadosFinales()
    {
        String lSSalida= gobjTransformAFNDtoAFD.objTreeSetNuevosFinales.toString().replace("[", "").replace("]", "").trim();
        System.out.println("Estados finales " + lSSalida);
        new ManejoArchivos().Write_String_File(gSPathSalida + "/" + gSEdosFinales, lSSalida);
    }
    
    /**
     * generaArchivoFuncionTransicion
     * Método que genera lafunción de transición en un archivo de texto
     */
    public void generaArchivoFuncionTransicion()
    {
        String lSSalida = "";
        for (Map.Entry<Integer, Set<Integer>> objEntryFT: gobjTransformAFNDtoAFD.gobjTreeMapMapaAutomata.entrySet())
        {
            String lSEstadoActual = String.valueOf(gobjTransformAFNDtoAFD.obtenerKeyEstado(gobjTransformAFNDtoAFD.gobjTreeMapAlfabetoEstados, gobjTransformAFNDtoAFD.gobjTreeMapAlfabetoEstados.get(gobjTransformAFNDtoAFD.obtenerCoordenadaEstado(objEntryFT.getKey(), gobjTransformAFNDtoAFD.gobjHashMapAlfabetoTransiciones.size()) -1)));
            String lSTransicion = gobjTransformAFNDtoAFD.gobjHashMapAlfabetoTransiciones.get(gobjTransformAFNDtoAFD.obtenerCoordenadaTransicion(objEntryFT.getKey(), gobjTransformAFNDtoAFD.gobjHashMapAlfabetoTransiciones.size()) -1);
            String lSEstadoSiguiente = gobjTransformAFNDtoAFD.gobjTreeMapMapaAutomata.get(objEntryFT.getKey()).toString().replace("[", "").replace("]", "");
            System.out.println("Estado actual " + lSEstadoActual + ", trasicion " + lSTransicion + " estado siguiente " + lSEstadoSiguiente);
            lSSalida += "[" + lSEstadoActual + "," + lSTransicion + "," + lSEstadoSiguiente + "]\r\n";
        }
        new ManejoArchivos().Write_String_File(gSPathSalida + "/" + gSFuncionTransicion, lSSalida);
    }
    
}
