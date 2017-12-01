package estados_na;

import java.util.HashMap;
import java.util.Map;

import AFD_to_AFDM.Actualiza_Transiciones;
import AFD_to_AFDM.ArrList_Estados;
import AFD_to_AFDM.Minimiza_Automata;
import AFD_to_AFDM.Separa_Estados;
import AFD_to_AFDM.Verifica_Transiciones;
import AFD_to_AFDM.verifica_ultimos_nodos;
import librerias_ena.MyListArgs;

public class Parametros {
	private String ConfigFile;
	private MyListArgs Param;
	private String Path_Trabajo;
	public static String Path_Salida;
	
	
	// Q es igual al conjunto de estados del automata, los estados van separados por coma

	private String Estados;

	// A es el alfabeto que corresponde al automata

	private String Alfabeto;
	
	// EI es el estado incial que corresponde al automata, pertenece a Q

	private String Estado_inicial;

	// F corresponde a los estados o estado final que corresponde al automata, pertenece a Q

	private String Estados_Finales;

	// T corresponde a la funcion de transicion, el estado X con el elemento del alfabeto Y hacia que estado va
	// se define por [estado, alfabeto, estado] (Tabulador) [estado, alfabeto, estado]

	private String Transciciones;
	
	private Map<Integer, ArrList_Estados> estados_jerarquia_all;
	private Map<Integer, HashMap<Integer, String>> map_transiciones = new HashMap<Integer, HashMap<Integer, String>>();
	private Map<Integer, String> Estados_HM = new HashMap<Integer, String>();
	private Map<Integer, String> EstadosF_HM = new HashMap<Integer, String>();
	private Map<Integer, String> Alfabeto_HM = new HashMap<Integer, String>();
	private Map<Integer, String> EstadoI_HM = new HashMap<Integer, String>();
	
	
	public Parametros(String[] Args) {
		Param = new MyListArgs(Args);

	    ConfigFile = Param.ValueArgsAsString("-CONFIG", "");//Archivo donde se especifican los parametros necesarios para este componente
	    if (!ConfigFile.equals("")) {
	        Param.AddArgsFromFile(ConfigFile);
	        
	    }
	    
		Path_Trabajo      = Param.ValueArgsAsString   ("-FWORK"   , ""    );  //Ruta de trabajo
		Estados= Param.ValueArgsAsString("-Q", ""); // Q es igual al conjunto de estados del automata, los estados van separados por coma
		Alfabeto= Param.ValueArgsAsString("-A", ""); // A es el alfabeto que corresponde al automata
		Estado_inicial = Param.ValueArgsAsString("-EI", ""); // EI es el estado incial que corresponde al automata, pertenece a Q
		Estados_Finales = Param.ValueArgsAsString("-F", ""); // F corresponde a los estados o estado final que corresponde al automata, pertenece a Q
		Transciciones = Param.ValueArgsAsString("-T", "");  // T corresponde a la funcion de transicion, el estado X con el elemento del alfabeto Y hacia que estado va
		Path_Salida       = Param.ValueArgsAsString   ("-FSALIDA"   , ""    );  //Ruta donde se van a almacenar los resultados del automata
		
	}
	
	
	public void Run() {
		
		
		Get_Datos_Automata gda= new Get_Datos_Automata(Path_Trabajo, Estados, Alfabeto, Estado_inicial, Estados_Finales, Transciciones);
		map_transiciones = gda.get_Transiciones();
		Estados_HM = gda.getEstados();
		EstadosF_HM = gda.get_estadosFinales();
		Alfabeto_HM= gda.get_Alfabeto();
		EstadoI_HM= gda.get_estadoInicial();
		Valida_EstadosAt vlea= new Valida_EstadosAt(Estados_HM, map_transiciones);
		Valida_EI vli= new Valida_EI(gda.get_estadoInicial(), map_transiciones);
		Valida_Alfabeto vla = new Valida_Alfabeto(Alfabeto_HM, map_transiciones);
		Valida_EstadosF vlf = new Valida_EstadosF(EstadosF_HM, map_transiciones);
		

		if(vlea.validaEstadosAt() == true) {
			System.out.println("Los estados del  automata son correctos");
		}
		
		if(vli.validaEstadoInicial() == true) {
			System.out.println("Estado inicial Correcto");
		}
		
		if(vla.validaAlfabeto() == true) {
			System.out.println("El alfabeto es Correcto");
		}
		
		if(vlf.validaEstadosF() == true) {
			System.out.println("Los estados finales son correctos");
		}
		Separa_Estados mina = new Separa_Estados(Estados_HM, map_transiciones, EstadoI_HM, EstadosF_HM);
		mina.separa_estados_aceptacion();
		mina.printMap();
		Verifica_Transiciones vt= new Verifica_Transiciones(mina.separa_estados_aceptacion(), map_transiciones);
		vt.participantes();
		

	    estados_jerarquia_all= vt.printMap();


	    Actualiza_Transiciones act= new Actualiza_Transiciones(estados_jerarquia_all, map_transiciones, EstadoI_HM);
		act.actualizaTransicioines();
		estados_jerarquia_all= act.printMap();
		Minimiza_Automata minimiza = new Minimiza_Automata(estados_jerarquia_all, act.actualizaTransicioines(), EstadoI_HM, EstadosF_HM);	
	 	minimiza.minimiza();
	 	minimiza.eliminaRepetidos();
	 	minimiza.printEstadosAutomata();
	 	minimiza.printEstadosAutomataFinales();

	}



	public String getPath_trabajo() {
		return Path_Trabajo;
	}



	public void setPath_trabajo(String path_trabajo) {
		Path_Trabajo = path_trabajo;
	}



	public String getPath_salida() {
		return Path_Salida;
	}



	
	
	
}
