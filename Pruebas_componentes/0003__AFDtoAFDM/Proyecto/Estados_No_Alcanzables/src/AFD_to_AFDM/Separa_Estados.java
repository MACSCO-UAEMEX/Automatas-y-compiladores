package AFD_to_AFDM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Separa_Estados {
	
	private Map<Integer, String> Nodo_Estados_HM = new HashMap<Integer, String>();
	private Map<Integer, HashMap<Integer, String>> Transiciones_HM = new HashMap<Integer, HashMap<Integer, String>>();
	private Map<Integer, ArrList_Estados> estados_todos = new HashMap<Integer, ArrList_Estados>();
	private Map<Integer, ArrList_Estados> copia_estados_todos;
	
	private Map<Integer, String> Nodo_Copia = new HashMap<Integer, String>();
	private Map<Integer, String> EstadosI_HM = new HashMap<Integer, String>();
	private Map<Integer, String> EstadosEF_HM = new HashMap<Integer, String>();
	
	public Separa_Estados(Map<Integer, String> Estados_HM, Map<Integer, HashMap<Integer, String>> Transiciones_HM, Map<Integer, String> EstadosI_HM, Map<Integer, String> EstadosEF_HM ) {
		this.Nodo_Estados_HM = Estados_HM;
		this.Transiciones_HM = Transiciones_HM;
		this.EstadosEF_HM = EstadosEF_HM;
		this.EstadosI_HM = EstadosI_HM;
	}
	
	public Map<Integer, ArrList_Estados> separa_estados_aceptacion() {
		ArrayList<String> estados_sep= new ArrayList<String>();
		int bin = 0;
		
		
		Nodo_Copia = new HashMap<Integer, String>(Nodo_Estados_HM);
		ArrayList<String> copia_estados= new ArrayList<>();
		Iterator itr = Nodo_Estados_HM.keySet().iterator();
    	while(itr.hasNext()){
    	  Integer key = (Integer) itr.next();
    	  if (!EstadosEF_HM.containsValue(Nodo_Estados_HM.get(key)) ) {
    		  bin=0;
    		  //System.out.println("Soy nodo Copia"+ Nodo_Copia.get(key)); 
	     		 //estados_na = estados_na + Nodo_Copia.get(key) + ",";
	     		 estados_sep.add(Nodo_Copia.get(key));
	     		 //System.out.println("Estados "+ estados_na);
	     		 estados_todos.put(bin, new ArrList_Estados(copia_estados= new ArrayList<>(estados_sep)));

	     	} 
    	}
    	/*for (Entry<Integer, ArrList_Estados> arrlist : estados_todos.entrySet()){
    		Integer clave = arrlist.getKey();
    		ArrList_Estados valor = arrlist.getValue();
    		System.out.println(clave+"  ->  "+valor.toString()); 
    		
    	}*/
    	
    	//limpia_estado();
    	estados_sep.clear();
    	//copia_estados= new ArrayList<>();

    	Iterator it = Nodo_Estados_HM.keySet().iterator();
    	while(it.hasNext()){
    	  Integer key = (Integer) it.next();
    	  if (EstadosEF_HM.containsValue(Nodo_Estados_HM.get(key))) {
    		  bin=1;
    		 // System.out.println("Soy nodo Copia"+ Nodo_Copia.get(key)); 

	     		 estados_sep.add(Nodo_Copia.get(key));

	     		 estados_todos.put(bin, new ArrList_Estados(copia_estados= new ArrayList<>(estados_sep)));

	     	}
    	  
    	}  	
    	
    	return estados_todos;
	}
	
	
	public void sigue_separando(int clave, String Estado_aseparar) {
	//	int clave=0;
		//clave = estados_todos.size() + 1;
		ArrayList<String> estados_sep= new ArrayList<String>();
		estados_sep.add(Estado_aseparar);
		estados_todos.put(clave, new ArrList_Estados(estados_sep));
		
	}
	
	
	public HashMap<Integer, ArrList_Estados> printMap() {
		copia_estados_todos = new HashMap<Integer, ArrList_Estados>(estados_todos);
		System.out.println("Entre A Imprime Mapa Copia" + copia_estados_todos);
	    Iterator it = estados_todos.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " ---> " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    return (HashMap<Integer, ArrList_Estados>) copia_estados_todos;
	}
	
	public Map<Integer, ArrList_Estados>  getMap() {
	    return copia_estados_todos;
	}
}
