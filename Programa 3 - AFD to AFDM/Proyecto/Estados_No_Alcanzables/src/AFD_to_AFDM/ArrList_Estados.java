package AFD_to_AFDM;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrList_Estados {
	private ArrayList<String> n_estado; 
	
	public ArrList_Estados( ArrayList<String> n_estado) {
		this.n_estado = n_estado;
	}
	
	public ArrList_Estados() {
		//this.n_estado = n_estado;
	}


	public ArrayList<String> getN_estado() {
		return n_estado;
	}

	public void setN_estado(ArrayList<String> n_estado) {
		this.n_estado = n_estado;
	}
	
	public void limpia_estado() {
		n_estado.clear();
	}
	
	@Override
	public String toString() {
		String item = "";
		for (Iterator<String> it = n_estado.iterator(); it.hasNext();) {
		    item = item + it.next() + ",";
		}
		return item;
	}

	public int size() {
		// TODO Auto-generated method stub
		int size;
		size= n_estado.size();
		return size;
	}
	
	
	
	


}
