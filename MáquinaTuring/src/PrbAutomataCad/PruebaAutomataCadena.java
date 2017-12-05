package PrbAutomataCad;

import Funciones.MyListArgs;
import Funciones.MySintaxis;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Maestria
 */
public class PruebaAutomataCadena {

    public String Q, A, R, q, B, F, T, OUT, CAD, FWORK;
    public TreeMap<String, String> cinta;
    Character c = 0x03B2;

    public PruebaAutomataCadena(String[] args) {
        // TODO code application logic here
        String ConfigFile = "";
        MyListArgs Param;
        Param = new MyListArgs(args);
        ConfigFile = Param.ValueArgsAsString("-CONFIG", "");
        if (!ConfigFile.equals("")) {
            Param.AddArgsFromFile(ConfigFile);
        }//fin if
        String Sintaxis = "-Q:str -A:str [-R:str] [-B:str] -EI:str  -F:str -T:str -OUT:str -CAD:str -FWORK:str";
        MySintaxis Review = new MySintaxis(Sintaxis, Param);
        //PARAMETROS FORZOSOS                  
        Q = Param.ValueArgsAsString("-Q", "");
        A = Param.ValueArgsAsString("-A", "");
        R = Param.ValueArgsAsString("-R", "");
        q = Param.ValueArgsAsString("-EI", "");
        B = Param.ValueArgsAsString("-B", c.toString());
        F = Param.ValueArgsAsString("-F", "");
        T = Param.ValueArgsAsString("-T", "");
        OUT = Param.ValueArgsAsString("-OUT", "");
        CAD = Param.ValueArgsAsString("-CAD", "").replace("\\", "/");
        FWORK = Param.ValueArgsAsString("-FWORK", "").replace("\\", "/");
    }

    public void Funcion() {
        TreeMap<String, ArrayList> map_Au = new TreeMap<>(); //#Se guardan los estados por kes y los values con las posibles trasendencias
        ArrayList<String> aL_Datos = new ArrayList<>(); //#Almacenaje de todas las líneas de dentro del archivo de texto de transiciones
        HashMap<Integer, String> aL_Alt = new HashMap<>();
        ArrayList<Automata> AL = new ArrayList<>(); //#Almacenaje de las líneas de datos en forma de automata
        String[] datos; //#Auxiliar en la lectura de cada una de las líneas de aL_Datos
        String cadTXT; //Lectura de cada cadena de texto del archivo de transiciones
        String ctrEdos = "0"; //#Control de cada uno de los estados que contenga el automata y es el dato almacenado dentro de la 'key' del treemap
        Automata a; //#objeto que será almacenado dentro como value del treemap
        try {
            String[] lASCadenas = new ManejoArchivos().Read_Text_File_NoNull(FWORK + "/" + T);
            for (int i = 0; i < lASCadenas.length; i++) {
                aL_Datos.add(lASCadenas[i].trim());
            }

            F = new ManejoArchivos().Read_Text_File_NoNull(FWORK + "/" + F)[0].trim();
            Collections.sort(aL_Datos);//#ordenamiento de la colección de cada uno de los datos
            for (String linea : aL_Datos) {//#Almacenaje de las transiciones del automata en el treemap
                datos = linea.replace("[", "").replace("]", "").replace(" ", "").split(",");
                aL_Alt.put(aL_Alt.size(), datos[1].trim());
                a = new Automata(datos[0], datos[1], datos[2], datos[3], datos[4]);
                if (!ctrEdos.equals(datos[0])) {
                    map_Au.put(ctrEdos, AL);
                    ctrEdos = datos[0];
                    AL = new ArrayList<>();
                }
                AL.add(a);
            }
            map_Au.put(ctrEdos, AL);
            //#Fin de almacenaje de transiciones del automata
            String lSSalida = "", cd, strcinta;

            if (ValidarAlfabeto(aL_Alt, new ManejoArchivos().Read_Text_File_NoNull(FWORK + "/" + A))) {
                for (String str : CAD.split(",")) {//Comienza el recorrido de cada una de las cadenas a validar
                    str = str.trim();//#Quitar espacios
                    cd = (str.equals("") ? "[Vacío]" : "[" + str + "]") + (ValidarCad(str, map_Au) ? " true" : " false");
                    Iterator it = cinta.entrySet().iterator();
                    strcinta = "[";
                    while (it.hasNext()) {
                        Map.Entry me = (Map.Entry) it.next();
                        strcinta += me.getValue() + " ";
                    }
                    strcinta = strcinta.trim() + "]";
                    lSSalida += "Cadena original\r\n" + cd + "\r\nCadena de la cinta\r\n" + strcinta + "\r\n\r\n";
                    //#Impresión de cadena válida o no mediante el método de validación
                }
                System.out.println(lSSalida);
            } else {
                System.out.println("El alfabeto del automata, no corresponde.");
            }

            if (!new ManejoArchivos().ExisteCarpetaArchivo(OUT)) {
                new ManejoArchivos().CrearCarpetas(OUT);
            }

            new ManejoArchivos().Write_String_File(OUT + "/Salida_cadenas.txt", lSSalida);

        } catch (Exception e) {
            System.out.println("Error en almacenaje de automata del archivo:\t" + e.getMessage());
        }
    }

    public boolean ValidarAlfabeto(HashMap<Integer, String> aL_Val, String[] Alfebo) {
        try {
            HashMap<Integer, String> aL = new HashMap<>();
            String[] cadVals = Alfebo[0].split(",");
            for (int i = 0; i < cadVals.length; i++) {
                aL.put(aL.size(), cadVals[i].trim());
            }

            for (Map.Entry<Integer, String> objEntry : aL_Val.entrySet()) {
                if (!aL.containsValue(objEntry.getValue().trim())) {
                    return false;
                }
            }
            return true;

        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean ValidarCad(String cad, TreeMap<String, ArrayList> m) {
        String edoA = this.q;
        ArrayList<Automata> aL_Au;
        Integer cont = 0, pos = 0, a = 0, i = 0;
        String subcad[] = cad.split(" ");
        a = subcad.length;
        cinta = new TreeMap<>();
        cinta.put("-1", B);
        for (i = 0; i < subcad.length; i++) {
            cinta.put(i.toString(), subcad[i]);
        }
        cinta.put(a.toString(), B);
        //for (String sc : subcad) {//#Recorrido por cada una de las subcadenas a validar
        while (true) {
            aL_Au = m.get(edoA);
            if (aL_Au == null && F.contains(edoA)) {
                return 1 == 1;
            }
            if ((aL_Au == null && !F.contains(edoA)) || (aL_Au != null && F.contains(edoA))) {
                return 0 == 1;
            }
            for (Automata auVal : aL_Au) {
                //r2 = auVal.getTransicion().length();
                try {
                    if (cinta.get(pos.toString()).equals(auVal.getTransicion())) {
                        //r1 = r2 + r1;
                        edoA = auVal.getEdosig();//#Colocar el siguiente estado como estado actual
                        //subcad[pos] = auVal.getElemescrito();
                        cinta.put(pos.toString(), auVal.getElemescrito());
                        if (auVal.getDir().toLowerCase().equals("d")) {
                            pos++;
                        } else if (auVal.getDir().toLowerCase().equals("i")) {
                            pos--;
                        } else {
                            System.out.println("Error en la dirección de la Máquina de Turning");
                            return 1 != 1;
                        }
                        cont = 0;//#Reiniciar el contador de posibles transiciones no ejecutadas
                        break;
                    } else {
                        cont++;//#Se hace conteo por cada una de las posibles transiciones que no pudo pasar el caracter
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    return 1 != 1;//#En caso de que la cadena exceda el número de caracteres, es porque ya no es una cadena válida
                }
            }
            if (cont == aL_Au.size()) {//#En caso de no haber pasado por ninguna transición posible, se condiciona con el tamaño del ArrayList, si son iguales es porque ya no existe transición
                return 1 != 1;
            }
        }

        /*for (String edoF : F.split(",")) {//#Validar si el estado actual al finalizar la cadena, se encuentra en un estado final y es aceptada
            if (edoA.equals(edoF.trim())) {
                return 1 == 1;
            }
        }
        return 1 != 1;*/
    }
}
