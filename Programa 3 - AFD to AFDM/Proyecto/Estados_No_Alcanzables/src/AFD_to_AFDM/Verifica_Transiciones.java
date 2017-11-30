package AFD_to_AFDM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import estados_na.Operaciones_Lista;

public class Verifica_Transiciones {
	
    private Map<Integer, ArrList_Estados> copia_jerarquia ;
	private Map<Integer, ArrList_Estados> estados_jerarquia;
	private Map<Integer, HashMap<Integer, String>> map_transiciones;
	private Map<Integer, HashMap<Integer, String>> transiciones_participantes;
	private Map <String, ArrayList<String>> estados_conjunto2= new TreeMap<String, ArrayList<String>>();
	private Map <String, ArrayList<String>> copia_estados_conjunto2;
	private int nodo;

	
	public Verifica_Transiciones(Map<Integer, ArrList_Estados> estados_jerarquia, Map<Integer, HashMap<Integer, String>> map_transiciones) {
		
		this.estados_jerarquia = estados_jerarquia;
		this.map_transiciones = map_transiciones;
	}
	
	
	public void participantes() {

		copia_jerarquia = new HashMap<Integer, ArrList_Estados>(estados_jerarquia);
		transiciones_participantes = new HashMap<Integer, HashMap<Integer, String>>();		
		
		System.out.println("\n\n Vas a otros estados?");
		
		for (Entry<Integer, ArrList_Estados> entry : copia_jerarquia.entrySet()) {
			 nodo = entry.getKey();
				System.out.println("Soy nodo:  "+copia_jerarquia.get(nodo));
				for (int j = 0; j < entry.getValue().getN_estado().size(); j++) {
					String valor = entry.getValue().getN_estado().get(j);
					for (int i = 0; i < map_transiciones.size() ; i++) {
						if(map_transiciones.get(i).get(0).contains(valor)) {
							System.out.println(map_transiciones.get(i)+"  Valor: --->  "+i);
							transiciones_participantes.put(i, map_transiciones.get(i));						
					     }
					}
						vasa_otroEstado(valor);			
				}
				
				do {
					separa_recursivo(nodo);
					
			}while( separa_mismoestado(nodo) != false );
		}

	}
	
	public boolean vasa_otroEstado(String valor) {

	    boolean aun_hay_separaciones = false;
	    int contador =0;
	    String id_conjuntoS= "";
	    Operaciones_Lista opl = new Operaciones_Lista();
		ArrayList<String> estados_conjunto= new ArrayList<String>();
		
		Map<Integer, ArrayList< String>> pertenezoc_aconjunto2 = new HashMap<Integer, ArrayList<String>>();
		
		copia_jerarquia = new HashMap<Integer, ArrList_Estados>(estados_jerarquia);

		System.out.println("\n\n Vas a otros estados?");
		System.out.println("Transiciones:  "+copia_jerarquia);
			
					for (Entry<Integer, HashMap<Integer, String>> it_map : transiciones_participantes.entrySet() ) {
						int key_tp= it_map.getKey();
						if(transiciones_participantes.get(key_tp).get(0).contains(valor)) {											 
								for (Entry<Integer, ArrList_Estados> nodos_arraylist : copia_jerarquia.entrySet()) {
									int nodo = nodos_arraylist.getKey();
									for (int x = 0; x < nodos_arraylist.getValue().getN_estado().size(); x++) {
										String Estado_key= nodos_arraylist.getValue().getN_estado().get(x);										
										String valor_nodo_arl = nodos_arraylist.getValue().getN_estado().get(x);									
										if(transiciones_participantes.get(key_tp).get(2).contains(valor_nodo_arl)) {
											System.out.println("Clave:  "+Estado_key +"  Nodo:  "+(nodo+1));
											id_conjuntoS= String.valueOf(nodo+1);
											//System.out.println(" El nodo es --> " + (nodo+1) + "  El Valor es: ----> "+transiciones_participantes.get(key_tp).get(2));
											estados_conjunto.add(id_conjuntoS);
											
										}
									}
									opl.AddHMtoArayList(key_tp, estados_conjunto);
								}
								contador = key_tp;
					   }
					}
					pertenezoc_aconjunto2 = opl.getHMtoArrayList();
					estados_conjunto2.put(valor, pertenezoc_aconjunto2.get(contador));
					estados_conjunto.clear();

		System.out.println("Soy mapa 2: "+ estados_conjunto2);
		return aun_hay_separaciones;
		
	}

	
	private boolean separa_recursivo(int nodo) {
		
		int grupo =0, nodo_actual = 0, nodo_siguiente= 0;
		
		nodo_actual = nodo + 1;
		nodo_siguiente = nodo + 2;
		
		boolean hay_separaciones= false;
		grupo= estados_jerarquia.size();
		copia_estados_conjunto2 = new TreeMap<String, ArrayList<String>>(estados_conjunto2);
	
		System.out.println("\n\nEntre Separ Recursivo:  "+estados_conjunto2);
		System.out.println("Como queda Arbol  :  "+estados_jerarquia);
		copia_jerarquia = new HashMap<Integer, ArrList_Estados>(estados_jerarquia);
		ArrayList<String> nuevo_estado = new ArrayList<String>();
		
		for( Entry<String, ArrayList<String>> it_estados : copia_estados_conjunto2.entrySet()) {
			String key_sr = it_estados.getKey();
				System.out.println(key_sr+" ---> "+estados_conjunto2.get(key_sr) + "----->" + (nodo+1));
			if(estados_conjunto2.get(key_sr).contains(String.valueOf((nodo_siguiente)))) {
				System.out.println("Nodo: "+ (nodo_siguiente));
				hay_separaciones = true;
				nuevo_estado.add(estados_jerarquia.get(nodo).getN_estado().remove(estados_jerarquia.get(nodo).getN_estado().indexOf(String.valueOf(key_sr))));
				estados_conjunto2.remove(key_sr);
				estados_jerarquia.put(grupo, new ArrList_Estados(nuevo_estado));
				
			}
			
			String transicion_actual= copia_estados_conjunto2.get(key_sr).get(0);
			
		}
		System.out.println("Nuevo Estado"+ nuevo_estado);
		
		return hay_separaciones;
		
	}
	
	
	
	
private boolean separa_mismoestado(int nodo) {
		
		int grupo =0, nodo_actual = 0, nodo_siguiente= 0;
		
		nodo_actual = nodo + 1;
		nodo_siguiente = nodo + 2;
		
		boolean hay_separaciones= false;
		grupo= estados_jerarquia.size();
		copia_estados_conjunto2 = new TreeMap<String, ArrayList<String>>(estados_conjunto2);
	
		System.out.println("\n\nEntre Separ Recursivo Mismo Estado:  "+estados_conjunto2);
		System.out.println("Como queda Arbol  :  "+estados_jerarquia);
		copia_jerarquia = new HashMap<Integer, ArrList_Estados>(estados_jerarquia);
		ArrayList<String> nuevo_estado = new ArrayList<String>();
		
		for( Entry<String, ArrayList<String>> it_estados : copia_estados_conjunto2.entrySet()) {
			String key_sr = it_estados.getKey();
					
			String transicion_actual= copia_estados_conjunto2.get(key_sr).get(0);
			
			for( int x =0; x < it_estados.getValue().size(); x++) {

				if(!copia_estados_conjunto2.get(key_sr).get(x).contains(transicion_actual)) {
					System.out.println("Entre:  "+copia_estados_conjunto2.get(key_sr).get(x) +" ------>  "+ transicion_actual + "  Vas a remover:   ---> "+estados_jerarquia.get(nodo).getN_estado().indexOf(String.valueOf(key_sr)));
					nuevo_estado.add(estados_jerarquia.get(nodo).getN_estado().remove(estados_jerarquia.get(nodo).getN_estado().indexOf(String.valueOf(key_sr))));
					estados_conjunto2.remove(key_sr);
					estados_jerarquia.put(grupo, new ArrList_Estados(nuevo_estado));
					hay_separaciones = true;
				}
			}
		}
		System.out.println("Nuevo Estado"+ nuevo_estado);
		estados_conjunto2.clear();
		return hay_separaciones;
		
	}
	
	
	public HashMap<Integer, ArrList_Estados> printMap() {
		copia_jerarquia = new HashMap<Integer, ArrList_Estados>(estados_jerarquia);
	    Iterator it = estados_jerarquia.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " ---> " + pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    System.out.println("Entre A Imprime Mapa Copia VT" + copia_jerarquia);
	    return (HashMap<Integer, ArrList_Estados>) copia_jerarquia;
	}
}
