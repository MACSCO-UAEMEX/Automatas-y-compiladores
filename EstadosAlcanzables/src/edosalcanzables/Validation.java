package edosalcanzables;

import java.util.HashMap;
import java.util.Map;

/**
 * Validation
 Clase que realiza validaciones acerca del automata
 * @author Jonathan Rojas Simón <ids_jonathan_rojas@hotmail.com>
 */
public class Validation
{
    String[] gSCadenas;
    HashMap<Integer,String> objHashMapAlfeboEstados, objHashMapAlfeboTransiciones, objHashMapEstadosFinales;
    String gSSimboloInicial;

    /**
     * Validacion
     * Constructor que inicializa los parámetros que debe validar el automata
     * @param lSCadenas Corresponde a la funcion de transicion
     * @param objHashMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param gSSimboloInicial Corresponde al simbolo inicial
     */
    public Validation(String[] lSCadenas, HashMap<Integer, String> objHashMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, HashMap<Integer, String> objHashMapEstadosFinales, String gSSimboloInicial)
    {
        this.gSCadenas = lSCadenas;
        this.objHashMapAlfeboEstados = objHashMapAlfeboEstados;
        this.objHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.objHashMapEstadosFinales = objHashMapEstadosFinales;
        this.gSSimboloInicial = gSSimboloInicial;
    }
    
    /**
     * validacionIntegral
     * Método para validar todos los métodos de esta clase
     * @return true
     */
    public boolean validacionIntegral()
    {
        for (int lEi = 0; lEi < gSCadenas.length; lEi++)
        {
            switch (lEi)
            {
                case 0:
                case 2:
                    if (validaEstados(lEi))
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
                    if (validaTransiciones(lEi))
                    {
//                        System.out.println("El automata tiene los transiciones CORRECTAS");
                    } else
                    {
                        System.out.println("Transicion NO contemplado en el alfabeto de transiciones");
                        return false;
                    }
            }
        }
        
        if (validaEstadoInicial())
        {
//            System.out.println("Estado inicial correcto");
        }else
        {
            System.out.println("Error en la definicion del estado inicial");
            return false;
        }
        
        if (validaEstadosFinales())
        {
//            System.out.println("Estados finales correctos");
        }else
        {
            System.out.println("ERROR en los estados finales");
            return false;
        }
        
        if (validaEstadoInicialEnTransiciones())
        {
//            System.out.println("El estado inicial se encuentra en la función de transicion");
        }else
        {
            System.out.println("El estado inicial no se encuentra en la funcion de transicion");
            return false;
        }
        
        if (validaEstadosFinalesEnTransiciones())
        {
//            System.out.println("El conjunto de estados finales se encuentra en la función de transicion");
        }else
        {
            System.out.println("El conjunto de estados finales NO se encuentra en la función de transicion");
            return false;
        }
        
        return true;
    }
    
    public boolean validaEstadosFinalesEnTransiciones()
    {
        int lEIncrementos = 0;
        String lSCadenaCompleta = "";
        for (int lEi = 0; lEi < gSCadenas.length; lEi++)
        {
            lSCadenaCompleta += gSCadenas[lEi].replace("[", "").replace("]", "").split(",")[0] + gSCadenas[lEi].replace("[", "").replace("]", "").split(",")[2];
        }

        for (Map.Entry<Integer, String> objEntry : objHashMapEstadosFinales.entrySet())
        {
            if (lSCadenaCompleta.contains(objEntry.getValue()))
            {
                lEIncrementos++;
            }
        }
        
        return lEIncrementos == objHashMapEstadosFinales.size();
    }
    
    /**
     * validaEstadoInicialEnTransiciones
     * Método para verificar si el elemento del estado inicial se encuentra en la columna de estados actuales de la función de transición
     * @return true si el simbolo inicial se encuentra en la columna de estados actuales, false si no lo encuentra
     */
    public boolean validaEstadoInicialEnTransiciones()
    {
        for (int lEi = 0; lEi < gSCadenas.length; lEi++)
        {
            String[] lSElementos = gSCadenas[lEi].replace("[", "").replace("]", "").split(",");
            if (lSElementos[0].trim().equals(gSSimboloInicial.trim()))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * validaEstadoInicial
     * Método para verificar si el estado inicial pertenece al alfabeto de estados
     * @return true si el estado inicial pertenece al alfabeto de estados, false si no pertecece al alfabeto
     */
    public boolean validaEstadoInicial()
    {
        if (objHashMapAlfeboEstados.containsValue(gSSimboloInicial))
        {
            return true;
        }
        return false;
    }
    
    /**
     * validaEstadosFinales
     * Metodo para validar si el estado Final corresponde al alfabeto de estados
     * @return true si todos los estados estan dentro del alfabeto de estados, false su encuentra alguno que no esté
     */
    public boolean validaEstadosFinales()
    {
        for (Map.Entry<Integer, String> entry : objHashMapEstadosFinales.entrySet())
        {
            if (!objHashMapAlfeboEstados.containsValue(entry.getValue()))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * validaEstados
     * Método para validar si los estados de la funcion de transicion corresponden al alfabeto de estados
     * @param lEIndice Indica la columna que se desea analizar
     * @return true si todos los elementos corresponden al alfabeto de estados, false si encuentra algun estado que no se encuentre
     */
    public boolean validaEstados(int lEIndice)
    {
        for (int lEi = 0; lEi < gSCadenas.length; lEi++)
        {
            String lSElemento = gSCadenas[lEi].split(",")[lEIndice].replace("[", "").replace("]", "").trim();
            if (!objHashMapAlfeboEstados.containsValue(lSElemento.trim()))
            {
                System.out.println( "Metodo validaEstados FAIL    El elemento: " + lSElemento + " de la cadena: " + gSCadenas[lEi]);
                return false;
            }
        }
        return true;
    }

    /**
     * validaTransiciones
     * Método para validar si los transiciones de la funcion de transicion corresponden al alfabeto de transiciones
     * @param lEIndice Indica la columna que se desea analizar
     * @return true si todos los elementos corresponden al alfabeto de transiciones, false si encuentra alguna transicion que no se encuentre
     */
    public boolean validaTransiciones(int lEIndice)
    {
        for (int lEi = 0; lEi < gSCadenas.length; lEi++)
        {
//            String lSElemento = gSCadenas[lEi].split(",")[lEIndice].trim();
            String lSElemento = gSCadenas[lEi].split(",")[lEIndice].replace("[", "").replace("]", "").trim();  
            System.out.println("CadenaDeTransicion: " + lEi + "  ElementoString de la cadena: " + lEIndice);
            if (!objHashMapAlfeboTransiciones.containsValue(lSElemento.trim()))
            {
                return false;
            }
        }
        return true;
    }

}
