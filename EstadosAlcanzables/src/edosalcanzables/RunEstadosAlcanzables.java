/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edosalcanzables;

import basics.ManejoArchivos;
import java.util.Map;
import java.util.Set;

/**
 * RunEstadosAlcanzables
 * CLASE que entrega un autómata solo con los estados Alcanzables
 * @author Jorge Ignacio Rivera Cortes
 */
public class RunEstadosAlcanzables {

    public static void main(String[] args) {
        String strArfFTOutput;
        Automata objAutomata = new Automata(args);
        Set<Integer> objSetEdosAlc;
        objAutomata.contruirMapEdosActualEdosSig();
        System.out.println(objAutomata.objMapEdosActualEdosSig+"\n");

        EstadosAlcanzables objSetEstadosAlcanzables = new EstadosAlcanzables(objAutomata);
        objSetEdosAlc = objSetEstadosAlcanzables.RegresaEstadosAlcanzables();
        strArfFTOutput = objSetEdosAlc.toString().replace("[", "").replace("]", "");

        System.out.println("\n Los estados Alcanzables son: " + strArfFTOutput);

        
        
        
        /*#####################      Creación de Archivos de Salida    ###############3*/
        

        
        
        /* Valida si existe la carpeta de salida y si no la crea */
        ManejoArchivos objNewFile = new ManejoArchivos();
        if (!objNewFile.ExisteCarpetaArchivo(objAutomata.gSPath_Salida)) {      // Validando si existe Carpeta
            objNewFile.CrearCarpetas(objAutomata.gSPath_Salida);
        }
        
        
        /* Crea el archivo de SALIDA "Estados.txt"  */
        objNewFile.Write_String_File(objAutomata.gSPath_Salida + "/Estados.txt", strArfFTOutput);
        
        
        /* Por cada cadena en el archivo de ENTRADA "FT.txt", valida si el estadoActual es un Estado Alcanzable, 
         * si es así entonces lo escribe en el archivo de SALIDA "FT.txt"
        */
        String lSSalidaFT = "";
        Integer intAuxEdoActual;
        for (int i = 0; i < objAutomata.lSCadenas.length; i++) // lSCadenas es un arreglo de cadenas, donde cada una de ellas es una transicion [10,c,3,a,d]
        {
            String[] lSElementos = objAutomata.lSCadenas[i].replace("[", "").replace("]", "").split(",");   // lSElementos es un Arreglo de Cadenas que  guarda los elementosCadena de cada cadena del arreglo lSCadenas 
            
            /* Traduce el elemento "lSElementos[0].trim()" (en este caso el EstadoSigiente) en su entero 
            correspondiente en el mapa "objAutomata.objMapAlfabetoEstados" y lo guarda en: "intAuxEdoActual"
            */
            intAuxEdoActual = objAutomata.obtenerKey(objAutomata.objMapAlfabetoEstados, lSElementos[0].trim());     
            if (objSetEdosAlc.contains(intAuxEdoActual)) {
                /* Crea el archivo de SALIDA "FT.txt" y escribe el contenido de arrLstAuxEdo */
                lSSalidaFT += objAutomata.lSCadenas[i] + "\r\n";
            }
        }
        
        objNewFile.Write_String_File(objAutomata.gSPath_Salida + "/FT.txt", lSSalidaFT.trim() + "\n");
        
        /* Crea el archivo de SALIDA "Finales.txt"  */ 
        String lSFinal = "";
        for (String strAuxEdoFinal : objAutomata.objMapEstadosFinales.values()){  // Para cada EstadoFinal (string), del mapaEstadosFinales
//            intAuxEdoActual = objAutomata.obtenerKey(objAutomata.objMapAlfabetoEstados, strAuxEdoFinal.trim());     // Buscalo en el MapaAlfabetoEstados y obten su traducción a numeroEntero
            if (objSetEdosAlc.contains(Integer.parseInt(strAuxEdoFinal.trim()))) {  // Si se encuentra el estado Final dentro de los EstadosAlanzables  
                /* Crea el archivo de SALIDA "FT.txt" y escribe el contenido de arrLstAuxEdo */
                lSFinal += strAuxEdoFinal + ",";
            }        
        }
        
        if (lSFinal.trim().endsWith(","))
        {
            lSFinal = lSFinal.trim().substring(0, lSFinal.trim().lastIndexOf(","));
        }
        objNewFile.Write_String_File(objAutomata.gSPath_Salida + "/Finales.txt", lSFinal + "\n");

        /* Crea el archivo de SALIDA del alfabeto de transiciones  */
        String lSSalidaTransiciones = "";
        for (Map.Entry<Integer, String> objEntryRecorrido: objAutomata.objMapAlfabetoTransiciones.entrySet())
        {
            lSSalidaTransiciones += objEntryRecorrido.getValue() + ",";
        }
        
        if (lSSalidaTransiciones.trim().endsWith(","))
        {
            lSSalidaTransiciones = lSSalidaTransiciones.trim().substring(0, lSSalidaTransiciones.trim().lastIndexOf(","));
        }
        objNewFile.Write_String_File(objAutomata.gSPath_Salida + "/" + objAutomata.gSAlfabetoTransiciones, lSSalidaTransiciones);
        
        /* Crea el archivo de SALIDA "CONFIG.txt"  */
        String lSSalida = "#ELEMENTOS NECESARIOS PARA EL AUTOMATA";
        lSSalida += "\r\n \r\n";
        lSSalida += "# Q es igual al conjunto de estados del automata, los estados van separados por coma";
        lSSalida += "\r\n \r\n";
        lSSalida += "-Q \t \"" + objAutomata.gSAlfabetoEstados + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "# A es el alfabeto que corresponde al automata";
        lSSalida += "\r\n \r\n";
        lSSalida += "-A \t \"" + objAutomata.gSAlfabetoTransiciones + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "# EI es el estado incial que corresponde al automata, pertenece a Q";
        lSSalida += "\r\n \r\n";
        lSSalida += "-EI \t" + objAutomata.gSEstadoInicial;
        lSSalida += "\r\n \r\n";
        lSSalida += "# F corresponde a los estados o estado final que corresponde al automata, pertenece a Q";
        lSSalida += "\r\n \r\n";
        lSSalida += "-F \t \"" + objAutomata.gSEstadosFinales + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "# T corresponde a la funcion de transicion, el estado X con el elemento del alfabeto Y hacia que estado va";
        lSSalida += "\r\n \r\n";
        lSSalida += "-T \t \"" + objAutomata.gSTransiciones + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "#Rutas de trabajo";
        lSSalida += "\r\n \r\n";
        lSSalida += "-FWORK \t \"" + objAutomata.gSPath_Salida + "\"";
        lSSalida += "\r\n \r\n";
        lSSalida += "-FSALIDA \t \"" + objAutomata.gSPath_Salida + "\"";
        lSSalida += "\r\n \r\n";
        
        new ManejoArchivos().Write_String_File(objAutomata.gSPath_Salida + "/CONFIG.txt", lSSalida);
    }
}
