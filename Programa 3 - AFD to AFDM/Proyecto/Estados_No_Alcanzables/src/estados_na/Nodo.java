package estados_na;


public class Nodo <T>{
		
		private T Objeto;
		private Nodo nextNodo;
		//private Nodo nodoAnterior;
		
		
		public Nodo (T Objeto) {
			this.Objeto = Objeto;
			this.nextNodo = null;
		}
		
		public void putNext( Nodo n) {
			//System.out.println(n);
			this.nextNodo = n;
		}
		
		public Nodo getNext() {
			return this.nextNodo;
		}
		
		public T getObjeto() {
			return Objeto;
		}
}


