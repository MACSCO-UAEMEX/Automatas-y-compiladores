package estados_na;

import java.util.HashMap;
import java.util.Map;

public class Valida_EI {
	
	Map<Integer, String> EstadosI_HM = new HashMap<Integer, String>();
	Map<Integer, HashMap<Integer, String>> Transiciones_HM = new HashMap<Integer, HashMap<Integer, String>>();
	
	public Valida_EI(Map<Integer, String> EstadosI_HM, Map<Integer, HashMap<Integer, String>> Transiciones_HM) {
		this.EstadosI_HM = EstadosI_HM;
		this.Transiciones_HM = Transiciones_HM;
	}
	
	
public boolean validaEstadoInicial() {
		
		boolean valida_ei = false;
		String estadoi= "";
		for (int i = 0; i < EstadosI_HM.size(); i++) {
			estadoi = EstadosI_HM.get(i).toString();		
			valida_ei = false;
			for (int j = 0; j < Transiciones_HM.size(); j++) {
			    for ( int k = 0; k < Transiciones_HM.get(j).size(); k++) {

			    	if (Transiciones_HM.get(j).containsValue(estadoi)) {
			    		valida_ei = true;
			    	}
				}
			}
			if(valida_ei == false) {
				System.out.println("La validacion del estado Iniicla presenta errores");
				System.out.println("Por favor revisa que tu estado inicial este dentro de los estados del autómata");
				break;
			}
		}
		
	  return valida_ei;
	}

}
