package AFD_to_AFDM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Mapas_Estados {
	private Map<Integer, HashMap<Integer, String>> Transiciones_HM = new HashMap<Integer, HashMap<Integer, String>>(); 
	
	public Mapas_Estados( Map<Integer, HashMap<Integer, String>> Transiciones_HM) {
		this.Transiciones_HM = Transiciones_HM;
	}
	
	public Mapas_Estados() {
		//this.Transiciones_HM = Transiciones_HM;
	}


	public Map<Integer, HashMap<Integer, String>> getTransiciones_HM() {
		return Transiciones_HM;
	}

	public void setTransiciones_HM(Map<Integer, HashMap<Integer, String>> transiciones_HM) {
		Transiciones_HM = transiciones_HM;
	}
	
	/*@Override
	public String toString() {
		String item = "";
		for (Iterator<String> it = Transiciones_HM.iterator(); it.hasNext();) {
		    item = item + it.next() + ",";
		}
		return item;
	}*/



	public int size() {
		// TODO Auto-generated method stub
		int size;
		size= Transiciones_HM.size();
		return size;
	}
	
	
	
	


}
