package AFD_to_AFDM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import estados_na.Parametros;
import librerias_ena.*;

public class Minimiza_Automata {
	private Map<Integer, HashMap<Integer, String>> map_transiciones;
	private Map<Integer, ArrList_Estados> estados_jerarquia;
	private Map<Integer, HashMap<Integer, String>> copia_transiciones;
	private Map<Integer, String> EstadosI_HM;
	private Map<Integer, String> EstadosEF_HM;
	private ManejoArchivos ma;
	
	
	public Minimiza_Automata(Map<Integer, ArrList_Estados> estados_jerarquia, Map<Integer, HashMap<Integer, String>> map_transiciones, Map<Integer, String> EstadosI_HM,  Map<Integer, String> EstadosEF_HM) {
		this.estados_jerarquia = estados_jerarquia;
		this.map_transiciones = map_transiciones;
		this.EstadosI_HM = EstadosI_HM;
		this.EstadosEF_HM = EstadosEF_HM;
	}
	
	public void minimiza() {
		String estados = "";
		ma = new ManejoArchivos();
		System.out.println("\n\n\n Entre a Minimiza Automata?");

		copia_transiciones = new HashMap<Integer, HashMap<Integer, String>>(map_transiciones);
		for (Entry<Integer, HashMap<Integer, String>> entry : copia_transiciones.entrySet()) {
			int key = entry.getKey();
			HashMap<Integer, String> mapa = entry.getValue();
			System.out.println("Mapa: " + key + "   Valor---> " + mapa);
			
			Iterator it = estados_jerarquia.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				int key1 = (int) pair.getKey();
				//for( int i=0; i < estados_jerarquia.get(key1).getN_estado().size(); i++) {
				//System.out.println("Voy a imprimir valor:  " + estados_jerarquia.get(key1).getN_estado().get(i));
				
				
				for (int x = 1; x < estados_jerarquia.get(key1).getN_estado().size(); x++) {
					String elemento_arl = estados_jerarquia.get(key1).getN_estado().get(x);
					String elemento_sustituir = estados_jerarquia.get(key1).getN_estado().get(0);
					estados =  elemento_sustituir;
					if (mapa.containsValue(elemento_arl)) {
						if (!EstadosI_HM.containsValue(elemento_arl)) {
							map_transiciones.remove(key);
							System.out.println("Entre:  " + estados_jerarquia.get(key1) + "---->)" + elemento_arl);
						}
					}
				}
				
			}
		}
	}
	
	public void eliminaRepetidos() {
		copia_transiciones = new HashMap<Integer, HashMap<Integer, String>>(map_transiciones);
		String transiciones= "";
		
		for (Entry<Integer, HashMap<Integer, String>> compara : copia_transiciones.entrySet()) {
			int key_comapra = compara.getKey();
			for (Entry<Integer, HashMap<Integer, String>> recorre : copia_transiciones.entrySet()) {
				int key_recorre = recorre.getKey();
				if (key_comapra > key_recorre) {
					if (copia_transiciones.get(key_comapra).equals(copia_transiciones.get(key_recorre))) {
						map_transiciones.remove(key_recorre);
						transiciones= transiciones + copia_transiciones.get(key_recorre)+ "\n" ;
								
						
						

					}
					
					
				}
				
				
			}
		}
		ma.UntilCrearCarpetas(Parametros.Path_Salida);
		ma.Write_String_File(ma.AddToPath(Parametros.Path_Salida, "FT.txt"),transiciones.replaceAll("\\{", "\\[")
				.replaceAll("\\}", "\\]"));
		System.out.println("Asi queda Mapa:  " + map_transiciones);
	}
	
	
	public void printEstadosAutomata() {
		copia_transiciones = new HashMap<Integer, HashMap<Integer, String>>(map_transiciones);
		Set<String> estados_set= new HashSet<String>(); 
		
		for (Entry<Integer, HashMap<Integer, String>> recorre_transiciones : copia_transiciones.entrySet()) {
			int key = recorre_transiciones.getKey();
			estados_set.add(recorre_transiciones.getValue().get(0));

		}
		ma.Write_String_File(ma.AddToPath(Parametros.Path_Salida, "EstadosT.txt"), estados_set.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
	
	}
	
	public void printEstadosAutomataFinales() {
		copia_transiciones = new HashMap<Integer, HashMap<Integer, String>>(map_transiciones);
		Set<String> estados_finales_set= new HashSet<String>(); 
		
		for (Entry<Integer, HashMap<Integer, String>> recorre_transiciones : copia_transiciones.entrySet()) {
			int key_tran = recorre_transiciones.getKey();
			for (Entry <Integer, String> recorre_finales : EstadosEF_HM.entrySet()) {
				int key_finals = recorre_finales.getKey();
			//	System.out.println("Estados FINALESSSSSSSSSS:   "+ EstadosEF_HM);
				if(recorre_transiciones.getValue().containsValue(EstadosEF_HM.get(key_finals))) {
					estados_finales_set.add(EstadosEF_HM.get(key_finals));
				}
			}
				

		}
		ma.Write_String_File(ma.AddToPath(Parametros.Path_Salida, "Finales.txt"), estados_finales_set.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
	
	}
		
}
	

