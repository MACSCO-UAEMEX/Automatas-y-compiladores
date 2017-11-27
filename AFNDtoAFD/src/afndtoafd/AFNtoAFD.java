package afndtoafd;

/**
 * AFNDtoAFD
 * Clase principal
 * @author Jonathan Rojas Simón <ids_jonathan_rojas@hotmail.com>
 */
public class AFNtoAFD
{
    /**
     * main
     * Método principal
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Automata objAutomata = new Automata(args);
        objAutomata.run();
        TransformAFNDtoAFD objAFNDtoAFD = new TransformAFNDtoAFD(objAutomata);
        objAFNDtoAFD.run();
        Salida objSalida = new Salida(objAFNDtoAFD, objAutomata);
        objSalida.runSalidas();
        
        System.out.println(objAFNDtoAFD.gobjTreeMapAlfabetoEstados);
        
    }
    
}
