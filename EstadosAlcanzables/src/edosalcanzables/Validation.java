package edosalcanzables;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Validation
 * Clase que realiza validaciones acerca del automata
 * @author Jonathan Rojas Simón <ids_jonathan_rojas@hotmail.com>
 */
public class Validation
{
    String[] gASCadenas;
    HashMap<Integer,String> gobjHashMapAlfeboTransiciones;
    HashMap<Integer, String> gobjHashMapAlfeboEstados;
    String  gstrSimboloInicial;
    HashMap<Integer, String> gobjHashMapEstadosFinales;

    /**
     * Validacion
     * Constructor que inicializa los parámetros que debe validar el automata
     * @param lASCadenas Corresponde a la funcion de transicion
     * @param objHashMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param strSimboloInicial Corresponde al simbolo inicial
     */
    public Validation(String[] lASCadenas, HashMap<Integer, String> objHashMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, HashMap<Integer, String> objHashMapEstadosFinales, String strSimboloInicial)
    {
        this.gASCadenas = lASCadenas;
        this.gobjHashMapAlfeboEstados = objHashMapAlfeboEstados;
        this.gobjHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.gobjHashMapEstadosFinales = objHashMapEstadosFinales;
        this.gstrSimboloInicial = strSimboloInicial;
    }
    
    /**
     * validacionIntegral
     * Método para validar todos los métodos de esta clase
     * @return true
     */
    public boolean validacionIntegral()
    {
        for (int lEi = 0; lEi < gASCadenas.length; lEi++)
        {
            for (int lEIteraInterno = 0; lEIteraInterno < gASCadenas[lEi].split(",").length; lEIteraInterno++)
            {
                switch (lEIteraInterno)
                {
                    case 0:
                    case 2:
                        if (validaEstados(lEIteraInterno))
                        {
//                        System.out.println("El automata tiene los estados CORRECTOS");
                        } else
                        {
                            System.out.println("Estado NO contemplado en el alfabeto de estados");
                            return false;
                        }
                        break;
                    case 1:
//                    case 3:
                        if (validaTransiciones(lEIteraInterno))
                        {
//                        System.out.println("El automata tiene los transiciones CORRECTAS");
                        } else
                        {
                            System.out.println("Transicion NO contemplado en el alfabeto de transiciones");
                            return false;
                        }
                        break;
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
        
//        if (validaEstadosFinalesEnTransiciones())
        if (1==1)            
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
        for (int lEi = 0; lEi < gASCadenas.length; lEi++)
        {
            lSCadenaCompleta += gASCadenas[lEi].replace("[", "").replace("]", "").split(",")[0] + gASCadenas[lEi].replace("[", "").replace("]", "").split(",")[2];
        }

        Object[] lAOConjunto = gobjHashMapEstadosFinales.values().toArray();
        for (int i = 0; i < lAOConjunto.length; i++)
        {
            if (lSCadenaCompleta.contains(String.valueOf(lAOConjunto[i]).trim()))
            {
                lEIncrementos++;
            }
        }
                
        return lEIncrementos == gobjHashMapEstadosFinales.size();
    }
    
    /**
     * validaEstadoInicialEnTransiciones
     * Método para verificar si el elemento del estado inicial se encuentra en la columna de estados actuales de la función de transición
     * @return true si el simbolo inicial se encuentra en la columna de estados actuales, false si no lo encuentra
     */
    public boolean validaEstadoInicialEnTransiciones()
    {
        for (int lEi = 0; lEi < gASCadenas.length; lEi++)
        {
            String[] lASElementos = gASCadenas[lEi].replace("[", "").replace("]", "").split(",");
            Set<Integer> objTreeSetInicial = new TreeSet<>();
            objTreeSetInicial.add(Integer.parseInt(lASElementos[0].trim()));
            if (objTreeSetInicial.contains(Integer.parseInt(gstrSimboloInicial)))
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
        if (gobjHashMapAlfeboEstados.containsValue(gstrSimboloInicial))
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
        Object[] lAOFinales = gobjHashMapEstadosFinales.values().toArray();
        for (int lEItera = 0; lEItera < lAOFinales.length; lEItera++)
        {
//            Set<Integer> objTreeSetEstadoFinal = new TreeSet<>();
//            objTreeSetEstadoFinal.add(Integer.parseInt(String.valueOf(lAOFinales[lEItera]).trim()));
            if (!gobjHashMapAlfeboEstados.containsValue(String.valueOf(lAOFinales[lEItera]).trim()))
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
        for (int lEi = 0; lEi < gASCadenas.length; lEi++)
        {
            try
            {
//                Set<Integer> objTreeSetAux = new TreeSet<>();
//                objTreeSetAux.add(Integer.parseInt(gASCadenas[lEi].split(",")[lEIndice].replace("[", "").replace("]", "").trim()));
                if (!gobjHashMapAlfeboEstados.containsValue(gASCadenas[lEi].split(",")[lEIndice].replace("[", "").replace("]", "").trim()))
                {
                    return false;
                }
            } catch (Exception objException)
            {
                System.out.println("Los estados de la función de transición no corresponden al alfabeto de estados");
                System.exit(0);
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
        for (int lEi = 0; lEi < gASCadenas.length; lEi++)
        {
            String lSElemento = gASCadenas[lEi].split(",")[lEIndice].trim();
            if (!gobjHashMapAlfeboTransiciones.containsValue(lSElemento.trim()))
            {
                return false;
            }
        }
        return true;
    }

}
