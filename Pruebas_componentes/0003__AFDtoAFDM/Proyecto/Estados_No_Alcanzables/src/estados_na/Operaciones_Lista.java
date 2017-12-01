/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estados_na;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

/**
 *
 * @author Server
 */
public class Operaciones_Lista {
    private ArrayList<String>Oraciones_st=new ArrayList<String>();
    private ArrayList<String>arr_oraciones_st=new ArrayList<String>();
    private List<ArrayList<Integer>>Lista_Oraciones2=new ArrayList<ArrayList<Integer>>();
    private List<ArrayList<Double>>Lista_Oraciones_Double=new ArrayList<ArrayList<Double>>();
    private ArrayList<ArrayList> arr_oraciones= new ArrayList<ArrayList>();
    private List<ArrayList<String>>Lista_OracionesST=new ArrayList<ArrayList<String>>();
    private List<List<ArrayList<String>>>Lista_Lista_Objeto=new ArrayList<List<ArrayList<String>>>();
    private List<List<ArrayList<Integer>>>Lista_Lista_Objeto_int=new ArrayList<List<ArrayList<Integer>>>();
    Map<Integer, HashMap<Integer, String>> HMtoHM = new HashMap<Integer, HashMap<Integer, String>>();
    Map<Integer, ArrayList<String>> HMtoHM_Arl = new HashMap<Integer, ArrayList<String>>();
    Map<Integer, String> HM = new HashMap<Integer, String>();
    

    public Operaciones_Lista(){
       // this.Lista_Oraciones2= Lista_Oraciones2;
    }
    
    public void AddListaHMtoHM(int i, HashMap<Integer, String> map){
        HMtoHM.put(i, new HashMap<Integer, String>(map));
       //System.out.println("Agregue Oraciones"+lista); 
    }
    
    public Map<Integer, HashMap<Integer, String>> getListaHMtoHM(){
		return HMtoHM;
    }
    
    public void AddHMtoArayList(int i,ArrayList<String> arl){
    	HMtoHM_Arl.put(i, new ArrayList<String>(arl));
       //System.out.println("Agregue Oraciones"+lista); 
    }
    
    public Map<Integer, ArrayList<String>> getHMtoArrayList(){
		return HMtoHM_Arl;
    }
    
    public void AddArrayListST(String oracion){
               
        Oraciones_st.add(oracion);
       // System.out.println("Agregue Oracion"+oracion); 
    }
    
       public void AddnewLine_ArrayListST(int fila, String oracion){
               
        Oraciones_st.add(fila, oracion);
       // System.out.println("Agregue Oracion"+oracion); 
    }
    public void addALofAl(){
        arr_oraciones.add(Oraciones_st);
    }  
    
     public void addALofAlIndex(int index, ArrayList lista){
        arr_oraciones.add(index,new ArrayList(lista));
    }  
    public void AddListaArrParam(ArrayList lista){
        arr_oraciones.add(new ArrayList(lista));
       //System.out.println("Agregue Oraciones"+lista); 
    } 
    
    public void AddListaArrParam2(ArrayList lista){
        Lista_Oraciones2.add(new ArrayList(lista));
       //System.out.println("Agregue Oraciones"+lista); 
    } 
    
    public void AddListaArrParamDouble(ArrayList lista){
        Lista_Oraciones_Double.add(new ArrayList(lista));
       //System.out.println("Agregue Oraciones"+lista); 
    } 
    
    public void AddListaArrParamST(ArrayList lista){
        Lista_OracionesST.add(new ArrayList(lista));
       //System.out.println("Agregue Oraciones"+lista); 
    } 
    
    public void AddLista_de_Listas_Param(List lista){
        Lista_Lista_Objeto.add(new ArrayList(lista));
       //System.out.println("Agregue Oraciones"+lista); 
    } 
    
    public void AddLista_de_Listas(){
        Lista_Lista_Objeto.add(new ArrayList(Lista_OracionesST));
       //System.out.println("Agregue Oraciones"+lista); 
    } 
    
     public void AddLista_de_Listas_int(){
        Lista_Lista_Objeto_int.add(new ArrayList(Lista_Oraciones2));
       //System.out.println("Agregue Oraciones"+lista); 
    } 
    public ArrayList getListaArr(){
          return this.arr_oraciones;
        //System.out.println("Agregue Oraciones"+Oraciones); 
    }
    
    public List<ArrayList<Double>> getListaDouble(){
          return this.Lista_Oraciones_Double;
        //System.out.println("Agregue Oraciones"+Oraciones); 
    }
    
    public List<ArrayList<Integer>> getListaInt(){
          return this.Lista_Oraciones2;
        //System.out.println("Agregue Oraciones"+Oraciones); 
    }
    
    public List<ArrayList<String>> getListaST(){
          return this.Lista_OracionesST;
        //System.out.println("Agregue Oraciones"+Oraciones); 
    }
    
    public List<List<ArrayList<String>>> getListaDeListasST(){
          return this.Lista_Lista_Objeto;
        //System.out.println("Agregue Oraciones"+Oraciones); 
    }
    
    public List<List<ArrayList<Integer>>> getListaDeListas_int(){
          return this.Lista_Lista_Objeto_int;
        //System.out.println("Agregue Oraciones"+Oraciones); 
    }

    public void LimpiaST(){
        Oraciones_st.clear();
        //arr_oraciones.clear();
    }
    
     public void Limpia_Lista_Double(){
        Lista_Oraciones_Double.clear();
        //arr_oraciones.clear();
    }
      
      
    public void Limpia_Lista_arraList(){
        Lista_OracionesST.clear();
        //arr_oraciones.clear();
    }      
    
     public void Limpia_Lista_de_Lista_int(){
        Lista_Oraciones2.clear();
        //arr_oraciones.clear();
    }   
    
    @Override
    public String toString(){
        int i=0;
        for (ArrayList<String> Oraciones_pre : arr_oraciones) {
            System.out.print(Oraciones_pre);
            i++;
        }
        return "El vector "+(i+1) +"Contiene ";
    }
    
    
    public String print_lista(){
        int i=0;
        for (List Oraciones : Lista_Oraciones2) {
            System.out.println(Oraciones);
            i++;
        }
        return "El vector "+(i+1) +"Contiene ";
    }
    
     public String print_listaST(){
        int i=0;
        for (List Oraciones : Lista_OracionesST) {
            System.out.println(Oraciones);
            i++;
        }
        return "El vector "+(i+1) +"Contiene ";
    }
    
    
     public String print_arrlist(){
        int i=0;
        for (String oraciones : Oraciones_st) {
            System.out.print(oraciones);
            i++;
        }
        return "El vector "+(i+1) +"Contiene ";
    }
     
      public String print_lista_listas(){
        int i=0;
        for (List<ArrayList<String>> oraciones : Lista_Lista_Objeto) {
            for (List<String> oracione : oraciones) {
                 System.out.print(oracione);          
            }
            i++;
        }
        return "El vector "+(i+1) +"Contiene ";
    }
      
    public String print_lista_listas_int(){
        int i=0;
        for (List<ArrayList<Integer>> oraciones : Lista_Lista_Objeto_int) {
            for (List<Integer> oracione_int : oraciones) {
                 System.out.print(oracione_int);          
            }
            i++;
        }
        return "El vector "+(i+1) +"Contiene ";
    }
   
}
