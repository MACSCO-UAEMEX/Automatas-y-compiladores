package estados_na;

public class Lista <T>{
	private Nodo inicio;
	
	public Lista() {
		inicio = null;		
	}
	
	/*public Lista(T objeto) {
		inicio = new Nodo (objeto);
	}*/
	
	public void add( T objeto) {
		Nodo elemento = new Nodo(objeto);
		
		if(inicio == null) {
			this.inicio = elemento;
		}else {
			Nodo aux;
			for (aux= inicio; aux.getNext() != null; aux= aux.getNext());
				aux.putNext(elemento);
			
		}
	}
	
	public void imprime_lista_enlazada() {
		for (Nodo aux = inicio; aux != null; aux = aux.getNext()) {
			System.out.println(aux.getObjeto().toString());
		}
	}
	

}
