package afndtoafd;

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
    TreeMap<Integer, Set<String>> gobjTreeMapAlfeboEstados;
    Set<String> gobjTreeSetEstadosFinales, gobjTreeSetSimboloInicial;

    /**
     * Validacion
     * Constructor que inicializa los parámetros que debe validar el automata
     * @param lASCadenas Corresponde a la funcion de transicion
     * @param objTreeMapAlfeboEstados Corresponde al alfabeto de estados
     * @param objHashMapAlfeboTransiciones Corresponde al alfabeto de transiciones
     * @param objHashMapEstadosFinales Corresponde al alfabeto de estados finales
     * @param objTreeSetSimboloInicial Corresponde al simbolo inicial
     */
    public Validation(String[] lASCadenas, TreeMap<Integer, Set<String>> objTreeMapAlfeboEstados, HashMap<Integer, String> objHashMapAlfeboTransiciones, Set<String> objHashMapEstadosFinales, Set<String> objTreeSetSimboloInicial)
    {
        this.gASCadenas = lASCadenas;
        this.gobjTreeMapAlfeboEstados = objTreeMapAlfeboEstados;
        this.gobjHashMapAlfeboTransiciones = objHashMapAlfeboTransiciones;
        this.gobjTreeSetEstadosFinales = objHashMapEstadosFinales;
        this.gobjTreeSetSimboloInicial = objTreeSetSimboloInicial;
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
        for (int lEi = 0; lEi < gASCadenas.length; lEi++)
        {
            lSCadenaCompleta += gASCadenas[lEi].replace("[", "").replace("]", "").split(",")[0] + gASCadenas[lEi].replace("[", "").replace("]", "").split(",")[2];
        }

        Object[] lAOConjunto = gobjTreeSetEstadosFinales.toArray();
        for (int i = 0; i < lAOConjunto.length; i++)
        {
            if (lSCadenaCompleta.contains(String.valueOf(lAOConjunto[i]).trim()))
            {
                lEIncrementos++;
            }
        }
                
        return lEIncrementos == gobjTreeSetEstadosFinales.size();
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
            Set<String> objTreeSetInicial = new TreeSet<>();
            objTreeSetInicial.add(lASElementos[0].trim());
            if (objTreeSetInicial.equals(gobjTreeSetSimboloInicial))
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
        if (gobjTreeMapAlfeboEstados.containsValue(gobjTreeSetSimboloInicial))
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
        Object[] lAOFinales = gobjTreeSetEstadosFinales.toArray();
        for (int lEItera = 0; lEItera < lAOFinales.length; lEItera++)
        {
            Set<String> objTreeSetEstadoFinal = new TreeSet<>();
            objTreeSetEstadoFinal.add(String.valueOf(lAOFinales[lEItera]).trim());
            if (!gobjTreeMapAlfeboEstados.containsValue(objTreeSetEstadoFinal))
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
                Set<String> objTreeSetAux = new TreeSet<>();
                objTreeSetAux.add(gASCadenas[lEi].split(",")[lEIndice].replace("[", "").replace("]", "").trim());
                if (!gobjTreeMapAlfeboEstados.containsValue(objTreeSetAux))
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
