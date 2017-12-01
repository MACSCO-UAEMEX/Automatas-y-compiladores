package estados_na;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import librerias_ena.*;

public class Verifica_Transicion {
	
	private Map<Integer, String> Estados_HM = new HashMap<Integer, String>();
	private Map<Integer, HashMap<Integer, String>> Transiciones_HM = new HashMap<Integer, HashMap<Integer, String>>();
	private Map<Integer, String> EstadosI_HM = new HashMap<Integer, String>();
	private Map<Integer, String> EstadosEF_HM = new HashMap<Integer, String>();
	private Map<Integer, String> no_iniciales_finales = new HashMap<Integer, String>();
	private Map<Integer, Integer> determina_no_alcanzables = new HashMap<Integer, Integer>();
	private ManejoArchivos ma;
	private String Path_Salida;

	public Verifica_Transicion(Map<Integer, String> Estados_HM, Map<Integer, HashMap<Integer, String>> Transiciones_HM, Map<Integer, String> EstadosI_HM, Map<Integer, String> EstadosEF_HM, String Path_Salida) {

		this.Estados_HM = Estados_HM;
		this.Transiciones_HM = Transiciones_HM;
		this.EstadosI_HM = EstadosI_HM;
		this.EstadosEF_HM = EstadosEF_HM;
		this.Path_Salida = Path_Salida;

	}
	 
	
	public void verificaTransicion() {

		int contador= 0;
			System.out.println("NIF" + no_iniciales_finales);
			for (int i = 0; i < Estados_HM.size(); i++) {
				if (no_iniciales_finales.containsValue(Estados_HM.get(i))) {
				//	System.out.println("Entre: " + Estados_HM.get(i));
					for (int x = 0; x < Transiciones_HM.size(); x++) {
						if (Transiciones_HM.get(x).containsValue(Estados_HM.get(i))) {
						//	System.out.println(Estados_HM.get(i));
							contador++;
							determina_no_alcanzables.put(i, contador);
						}
						
						
					}
					
				}
				contador=0;
			}
			
			System.out.println(determina_no_alcanzables);
	}
	
	
	public void quita_iniciales() {
		
		for(int i = 0; i < EstadosI_HM.size(); i++) {
				for (int j = 0; j < no_iniciales_finales.size(); j++) {
					if( no_iniciales_finales.get(j).contains(EstadosI_HM.get(i))) {
						no_iniciales_finales.remove(j);
						
					}
					
				}
			
		}
		//System.out.println(no_iniciales_finales);
		//System.out.println("Original"+Estados_HM);
		
		
	}
	
	public void quita_finales() {
		//System.out.println("Original" + Estados_HM);
		no_iniciales_finales = new HashMap<Integer, String>(Estados_HM);

		// no_iniciales_finales = Estados_HM;
		for (int k = 0; k < EstadosEF_HM.size(); k++) {
			for (int l = 0; l < no_iniciales_finales.size(); l++) {
				if (no_iniciales_finales.get(l).contains(EstadosEF_HM.get(k))) {
					no_iniciales_finales.remove(l);

				}

			}
			//System.out.println(no_iniciales_finales);
			//System.out.println("Original" + Estados_HM);

		}
	}

    public void determina_no_alcanzables() {
   	
    	Iterator it = determina_no_alcanzables.keySet().iterator();
    	while(it.hasNext()){
    	  Integer key = (Integer) it.next();
    	  if ( determina_no_alcanzables.get(key) < 2) {
			//	System.out.println("Entre");
			//	System.out.println("Clave: " + key + " -> Valor: " + determina_no_alcanzables.get(key));
				System.out.println("El estado: " + (key+1)+ " Es Inalcanzable");
			//	ma.AddToPath(Path_Salida, "Salida_noAlcanzables");
				
		  }
    	  
    	}
    	
    }

}
