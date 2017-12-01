package estados_na;

import java.util.HashMap;
import java.util.Map;

public class Valida_Alfabeto {
	Map<Integer, String> Alfabeto_HM = new HashMap<Integer, String>();
	Map<Integer, HashMap<Integer, String>> Transiciones_HM = new HashMap<Integer, HashMap<Integer, String>>();
	
	public Valida_Alfabeto(Map<Integer, String> Alfabeto_HM, Map<Integer, HashMap<Integer, String>> Transiciones_HM) {
		this.Alfabeto_HM = Alfabeto_HM;
		this.Transiciones_HM = Transiciones_HM;
	}
	
		
	public boolean validaAlfabeto() {
		
		boolean validacion_alfabeto = false;
		String alfabeto= "";
		for (int i = 0; i < Alfabeto_HM.size(); i++) {
			alfabeto = Alfabeto_HM.get(i).toString();		
			validacion_alfabeto = false;
			for (int j = 0; j < Transiciones_HM.size(); j++) {
			   // for ( int k = 0; k < Transiciones_HM.get(j).size(); k++) {

			    	if (Transiciones_HM.get(j).containsValue(alfabeto)) {
			    		validacion_alfabeto = true;
			    	}
				//}
			}
			if(validacion_alfabeto == false) {
				System.out.println("La validacion del alfabeto presenta errores");
				System.out.println("Por favor revisa tu sintaxis");
				break;
			}
		}
		
	  return validacion_alfabeto;
	}
	

}
