package AFD_to_AFDM;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import librerias_ena.ManejoArchivos;

public class Actualiza_Transiciones {
	
	private Map<Integer, HashMap<Integer, String>> map_transiciones;
	private Map<Integer, ArrList_Estados> copia_jerarquia ;
	private Map<Integer, ArrList_Estados> estados_jerarquia;
	private Map<Integer, HashMap<Integer, String>> copia_transiciones;
	private Map<Integer, String> EstadosI_HM;
	
	public Actualiza_Transiciones(Map<Integer, ArrList_Estados> estados_jerarquia, Map<Integer, HashMap<Integer, String>> map_transiciones, Map<Integer, String> EstadosI_HM) {
		this.estados_jerarquia = estados_jerarquia;
		this.map_transiciones = map_transiciones;
		this.EstadosI_HM = EstadosI_HM;
	}
	
	
	public Map<Integer, HashMap<Integer, String>> actualizaTransicioines() {
	
		int clave_sutituye= 0;

		copia_transiciones = new HashMap<Integer, HashMap<Integer, String>>(map_transiciones);
		for (Entry<Integer, HashMap<Integer, String>> entry : map_transiciones.entrySet()) {
			int key = entry.getKey();
			HashMap<Integer, String> mapa = entry.getValue();
			//System.out.println("Mapa: " + key + "   Valor---> " + mapa);
			
			for (Entry<Integer, ArrList_Estados> nodos_arraylist : estados_jerarquia.entrySet()) {
				int nodo = nodos_arraylist.getKey();
				//int key1 = (int) pair.getKey();
				//System.out.println("Valor de key Mapa:  "+key1);
				//for( int i=0; i < estados_jerarquia.get(key1).getN_estado().size(); i++) {
				//System.out.println("Voy a imprimir valor:  " + estados_jerarquia.get(key1).getN_estado().get(i));
				
				//if(estados_jerarquia.get(key1).getN_estado().size() > 1) {
				
					for(int x= 1; x < estados_jerarquia.get(nodo).getN_estado().size(); x++) {
						String elemento_arl = estados_jerarquia.get(nodo).getN_estado().get(x);
						String elemento_sustituir = estados_jerarquia.get(nodo).getN_estado().get(0);
						
						for( int y =0; y < entry.getValue().size(); y++) {
							clave_sutituye = clave_sutituye + 1;
							if (mapa.get(y).contains(elemento_arl)) {
								//if (! EstadosI_HM.containsValue(elemento_arl)) {
								    
									mapa.put(y, elemento_sustituir);
									//map_transiciones.remove(key);
								//	System.out.println("Entre:  "+mapa+"  Elemento mapa --->"+ mapa.get(y) +" Elemento comparar---->"+ elemento_arl +"   LLave:  "+y);
								 
								//}		
								
							}
						}
						
				
				
				}
					
			//}
				

			}
			
			
		}
		clave_sutituye = 0;
		 System.out.println("Asi queda Mapa Transiciones:  " + map_transiciones);
		 return map_transiciones;
		 
	}
	
	
	
	
	
	public HashMap<Integer, ArrList_Estados> printMap() {
		copia_jerarquia = new HashMap<Integer, ArrList_Estados>(estados_jerarquia);
	    Iterator it = estados_jerarquia.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " ---> " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	   // System.out.println("Entre A Imprime Mapa Copia VT" + copia_jerarquia);
	    return (HashMap<Integer, ArrList_Estados>) copia_jerarquia;
	}

}
