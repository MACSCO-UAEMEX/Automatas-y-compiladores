package estados_na;


import java.nio.file.Path;
import java.util.HashMap;
import librerias_ena.*;
import java.util.Map;

public class Get_Datos_Automata {
	
	private String Path_Trabajo;
	private String Estados;
	private String Alfabeto;
	private String Estado_inicial;
	private String Estados_Finales;
	private String Transiciones;
	

	public Get_Datos_Automata(String Path_Trabajo , String Estados, String Alfabeto, String Estado_inicial, String Estados_Finales, String Transciciones) {

		this.Path_Trabajo = Path_Trabajo;
		this.Estados = Estados;
		this.Alfabeto = Alfabeto;
		this.Estado_inicial = Estado_inicial;
		this.Estados_Finales = Estados_Finales;
		this.Transiciones = Transciciones;

	}
	 
	
	public Map<Integer, String> getEstados() {
		 ManejoArchivos ma = new ManejoArchivos();
		//System.out.println("Los estados son: " +Estados+ "  El alfabeto es: "+ Alfabeto+ " El estado inicial es: "+ Estado_inicial+ 
				//" Los estados Finales son: "+ Estados_Finales+ " Las transiciones son: " +Transiciones +"\n");
		//System.out.println("Valores archivo:  "+ Estados);
		
		Map<Integer, String> Estados_HM = new HashMap<Integer, String>();
		//System.out.println("Hola soy Path Trabajo"+Path_Trabajo);
		Estados= ma.AddToPath(Path_Trabajo, Estados);
		String[] leeA = new ManejoArchivos().Read_Text_File(Estados);
		//System.out.println("Valores archivo:  "+ leeA[0]);
		
		for(int x = 0; x < leeA.length; x++) {
			String[] parteE = leeA[x].split(",");
			for ( int i = 0; i < parteE.length; i++) {
				//System.out.print(","+ parteE[i]);	
				Estados_HM.put(i, parteE[i].trim());
			}
		}
		
		//Path_Trabajo ="";
		System.out.println("Estados del Automata:  "+ Estados_HM);
		return Estados_HM;		
	}
	
	public Map<Integer, String> get_Alfabeto() {
		 ManejoArchivos ma = new ManejoArchivos();
		//System.out.println("Valores archivo:  "+ Alfabeto);
		Alfabeto= ma.AddToPath(Path_Trabajo, Alfabeto);
		String[] leeA = new ManejoArchivos().Read_Text_File(Alfabeto);
		Map<Integer, String> Alfabeto_HM = new HashMap<Integer, String>();
		
		
		for(int x = 0; x < leeA.length; x++) {
			//System.out.println("Alfabeto impresion: " + leeA[x]);
			String[] parteA = leeA[x].split(",");
			ma.Write_String_File(ma.AddToPath(Parametros.Path_Salida, "Alfabeto_Espanol.txt"), leeA[x]);
		for ( int i = 0; i < parteA.length; i++) {
			//System.out.print(","+ parteA[i]);
			Alfabeto_HM.put(i, parteA[i].trim());
		}
		}
		System.out.println("Alfabeto del Automata:  "+ Alfabeto_HM);
		//Path_Trabajo ="";
		return Alfabeto_HM;
	}
	
	public Map<Integer, String> get_estadoInicial() {
		 ManejoArchivos ma = new ManejoArchivos();
		
		Map<Integer, String> EstadosI_HM = new HashMap<Integer, String>();
		
		String[] parteEI = Estado_inicial.split(",");
		for ( int i = 0; i < parteEI.length; i++) {
			//System.out.print(","+ parteEI[i]);
			EstadosI_HM.put(i, parteEI[i].trim());
		}
		return EstadosI_HM;
	}
	
	
	public Map<Integer, String> get_estadosFinales() {
		 ManejoArchivos ma = new ManejoArchivos();
		Estados_Finales= ma.AddToPath(Path_Trabajo, Estados_Finales);
		String[] leeA = new ManejoArchivos().Read_Text_File(Estados_Finales);
		Map<Integer, String> EstadosEF_HM = new HashMap<Integer, String>();
		
		
		for(int x = 0; x < leeA.length; x++) {
		String[] parteEF = leeA[x].split(",");
		
		for ( int i = 0; i < parteEF.length; i++) {
			//System.out.print(","+ parteEF[i]);
			EstadosEF_HM.put(i, parteEF[i].trim());
		}
		}
		System.out.println("Estados Finales:  "+ EstadosEF_HM);
		//Path_Trabajo ="";
		return EstadosEF_HM;
	}
	
	
	public Map<Integer, HashMap<Integer, String>> get_Transiciones() {
		 ManejoArchivos ma = new ManejoArchivos();
		Transiciones= ma.AddToPath(Path_Trabajo, Transiciones);
		String[] leeA = new ManejoArchivos().Read_Text_File(Transiciones);
		Operaciones_Lista opl= new Operaciones_Lista();
		int num_transiciones = 0;
		Map<Integer, HashMap<Integer, String>> map_transiciones = new HashMap<Integer, HashMap<Integer, String>>();
		HashMap<Integer, String> Transiciones_HM = new HashMap<Integer, String>();
		
		

		for(int x = 0; x < leeA.length; x++) {
			String[] parteT = leeA[x].split("\\n");
			//System.out.println("ENTRE: " +leeA[x]);
			
			for ( int i = 0; i < parteT.length; i++) {
				//if(x==i) {
				parteT[i] = parteT[i].replaceAll("\\[", "").replaceAll("\\]", "");
				
				
				String[] parteT2 = parteT[i].split(",");
				for (int j= 0; j < parteT2.length; j++) {
					
					Transiciones_HM.put(j, parteT2[j]);
				}
				//System.out.println(""+ Transiciones_HM);
				//map_transiciones.put( i ,  Transiciones_HM);
				
				//Transiciones_HM.clear();
		//	}
			}
			opl.AddListaHMtoHM(x, Transiciones_HM);
			
		}
			map_transiciones = opl.getListaHMtoHM(); 
			System.out.println(map_transiciones);
			
			System.out.println("Estados del Automata:  "+ map_transiciones);
			return map_transiciones;
	}
	
	public void get_TransicionesLista() {
		//Lista <HashMap<Integer, String>> lista= new Lista <HashMap<Integer, String>>();
		Operaciones_Lista opl= new Operaciones_Lista();
		Map<Integer, HashMap<Integer, String>> map_transiciones = new HashMap<Integer, HashMap<Integer, String>>();
		HashMap<Integer, String> Transiciones_HM = new HashMap<Integer, String>();
		Transiciones = Transiciones.replaceAll("\\[", "").replaceAll("\\]", "");
		


			String[] parteT = Transiciones.split("\\.");
			for ( int i = 0; i < parteT.length; i++) {
				//System.out.println("ENTRE" +parteT[i]);
				String[] parteT2 = parteT[i].split(",");
				for (int j= 0; j < parteT2.length; j++) {
					
					Transiciones_HM.put(j, parteT2[j]);
				}
				
				//lista.add(Transiciones_HM); 
				//System.out.println(Transiciones_HM +"\n");
				//System.out.println(""+ Transiciones_HM);
				map_transiciones.put( i ,  Transiciones_HM);
				//opl.AddListaHMtoHM(i, Transiciones_HM);
				//Transiciones_HM.clear();
				
			}
			//map_transiciones = opl.getListaHMtoHM(); 
			//System.out.println(map_transiciones);
			//return map_transiciones;
			//lista.imprime_lista_enlazada();
	}

}
