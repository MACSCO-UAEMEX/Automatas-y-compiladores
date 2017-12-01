package estados_na;

import java.util.HashMap;
import java.util.Map;

public class Valida_EstadosAt {
	
	Map<Integer, String> Estados_HM = new HashMap<Integer, String>();
	Map<Integer, HashMap<Integer, String>> Transiciones_HM = new HashMap<Integer, HashMap<Integer, String>>();
	
	public Valida_EstadosAt(Map<Integer, String> Estados_HM, Map<Integer, HashMap<Integer, String>> Transiciones_HM) {
		this.Estados_HM = Estados_HM;
		this.Transiciones_HM = Transiciones_HM;
	}
	
	public boolean validaEstadosAt() {

		boolean valida_ei = false;
		String estadoi = "";
		for (int i = 0; i < Estados_HM.size(); i++) {
			estadoi = Estados_HM.get(i).toString();
			valida_ei = false;
			for (int j = 0; j < Transiciones_HM.size(); j++) {
				for (int k = 0; k < Transiciones_HM.get(j).size(); k++) {
					//System.out.println(Transiciones_HM.get(j)+"  ==  "+ estadoi);
					if (Transiciones_HM.get(j).containsValue(estadoi)) {
						valida_ei = true;
					}
				}
			}
			if (valida_ei == false) {
				System.out.println("La validacion de los estados del automata presenta errores");
				System.out.println("Por favor revisa que los estados de la transicion T correspondan a los estados del autómata");
				break;
			}
		}

		return valida_ei;
	}

	

}
