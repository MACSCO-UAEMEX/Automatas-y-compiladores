/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrbAutomataCad;

import Funciones.MyListArgs;
import Funciones.MySintaxis;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 *
 * @author Maestria
 */
public class PruebaAutomataCadena {

    public String MT, Q, A, R, q, B, F, T, OUT, CAD;

    public PruebaAutomataCadena(String[] args) {
        // TODO code application logic here
        String ConfigFile = "";
        MyListArgs Param;
        Param = new MyListArgs(args);
        ConfigFile = Param.ValueArgsAsString("-CONFIG", "");
        if (!ConfigFile.equals("")) {
            Param.AddArgsFromFile(ConfigFile);
        }//fin if
        String Sintaxis = "-Q:str -A:str [-R:str] -EI:str [-B:str] -F:str -T:str [-OUT:str] [-CAD:str]";
        MySintaxis Review = new MySintaxis(Sintaxis, Param);
        //PARAMETROS FORZOSOS                  
        MT = Param.ValueArgsAsString("-MT", "");
        Q = Param.ValueArgsAsString("-Q", "");
        A = Param.ValueArgsAsString("-A", "");
        R = Param.ValueArgsAsString("-R", "");
        q = Param.ValueArgsAsString("-EI", "");
        B = Param.ValueArgsAsString("-Q", "");
        F = Param.ValueArgsAsString("-F", "");
        T = Param.ValueArgsAsString("-T", "");
        OUT = Param.ValueArgsAsString("-OUT", "");
        CAD = Param.ValueArgsAsString("-CAD", "");
    }

    public void Funcion() {
        TreeMap<String, ArrayList> map_Au = new TreeMap<>(); //#Se guardan los estados por kes y los values con las posibles trasendencias
        ArrayList<String> aL_Datos = new ArrayList<>(); //#Almacenaje de todas las líneas de dentro del archivo de texto de transiciones
        ArrayList<String> aL_Alt = new ArrayList<>();
        ArrayList<Automata> AL = new ArrayList<>(); //#Almacenaje de las líneas de datos en forma de automata
        String[] datos; //#Auxiliar en la lectura de cada una de las líneas de aL_Datos
        String cadTXT; //Lectura de cada cadena de texto del archivo de transiciones
        String ctrEdos = "0"; //#Control de cada uno de los estados que contenga el automata y es el dato almacenado dentro de la 'key' del treemap
        Automata a; //#objeto que será almacenado dentro como value del treemap
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(T)));
            cadTXT = br.readLine();
            while (cadTXT != null) {
                aL_Datos.add(cadTXT);
                cadTXT = br.readLine();
            }
            br.close();//#Fin de lectura de archivo de texto
            br = new BufferedReader(new FileReader(new File(F)));
            F = br.readLine();
            Collections.sort(aL_Datos);//#ordenamiento de la colección de cada uno de los datos
            for (String linea : aL_Datos) {//#Almacenaje de las transiciones del automata en el treemap
                datos = linea.replace("[", "").replace("]", "").replace(" ", "").split(",");
                aL_Alt.add(datos[1]);
                a = new Automata(datos[0], datos[1], datos[2]);
                if (!ctrEdos.equals(datos[0])) {
                    map_Au.put(ctrEdos, AL);
                    ctrEdos = datos[0];
                    AL = new ArrayList<>();
                }
                AL.add(a);
            }
            map_Au.put(ctrEdos, AL);
            //#Fin de almacenaje de transiciones del automata
            if (ValidarAlfabeto(aL_Alt, new BufferedReader(new FileReader(new File(A))))) {
                for (String str : CAD.split(",")) {//Comienza el recorrido de cada una de las cadenas a validar
                    str = str.trim();//#Quitar espacios
                    System.out.println((str.equals("") ? "(Vacío)" : "(" + str + ")") + " " + (ValidarCad(str, map_Au) ? "true" : "false"));
                    //#Impresión de cadena válida o no mediante el método de validación
                }
            } else {
                System.out.println("El alfabeto del automata, no corresponde.");
            }
        } catch (IOException e) {
            System.out.println("Error en almacenaje de automata del archivo:\t" + T);
            System.out.println(e);
        }
    }

    public boolean ValidarAlfabeto(ArrayList<String> aL_Val, BufferedReader Alfebo) {
        try {
            String cadVal = "";
            ArrayList<String> aL = new ArrayList();
            cadVal = Alfebo.readLine();
            for (String str : cadVal.replace("\n", "").replace("\r", "").split(",")) {
                aL.add(str);
            }
            for (String c : aL_Val) {
                if (!aL.contains(c)) {
                    return false;
                }
            }
            return true;

        } catch (IOException e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean ValidarCad(String cad, TreeMap<String, ArrayList> m) {
        String edoA = this.q;
        ArrayList<Automata> aL_Au;
        Integer cont = 0, r1 = 0, r2;
        String subcad = cad.replace(" ", "");
        while (r1 < subcad.length()) {//#Recorrido por cada una de las subcadenas a validar
            aL_Au = m.get(edoA);
            if (aL_Au == null) {
                return 1 == 0;
            }
            for (Automata auVal : aL_Au) {
                r2 = auVal.getTransicion().length();
                try {
                    if (subcad.substring(r1, r2 + r1).equals(auVal.getTransicion())) {
                        r1 = r2 + r1;
                        edoA = auVal.getEdosig();//#Colocar el siguiente estado como estado actual
                        cont = 0;//#Reiniciar el contador de posibles transiciones no ejecutadas
                        break;
                    } else {
                        cont++;//#Se hace conteo por cada una de las posibles transiciones que no pudo pasar el caracter
                    }
                } catch (java.lang.StringIndexOutOfBoundsException ex) {
                    return 1 != 1;//#En caso de que la cadena exceda el número de caracteres, es porque ya no es una cadena válida
                }
            }
            if (cont == aL_Au.size()) {//#En caso de no haber pasado por ninguna transición posible, se condiciona con el tamaño del ArrayList, si son iguales es porque ya no existe transición
                return 1 != 1;
            }
        }
        for (String edoF : F.split(",")) {//#Validar si el estado actual al finalizar la cadena, se encuentra en un estado final y es aceptada
            if (edoA.equals(edoF.trim())) {
                return 1 == 1;
            }
        }
        return 1 != 1;
    }
}
