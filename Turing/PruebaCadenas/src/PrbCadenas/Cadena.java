/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrbCadenas;

import Funciones.MyListArgs;
import Funciones.MySintaxis;
import java.util.regex.Pattern;
/**
 *
 * @author Maestria
 */
public class Cadena {

    public String CAD, EXP;

    public Cadena(String[] args) {
        String ConfigFile = "";

        MyListArgs Param;

        Param = new MyListArgs(args);
        ConfigFile = Param.ValueArgsAsString("-CONFIG", "");
        if (!ConfigFile.equals("")) {
            Param.AddArgsFromFile(ConfigFile);
        }//fin if

        String Sintaxis = "-CAD:str -EXP:str";
        MySintaxis Review = new MySintaxis(Sintaxis, Param);
        //PARAMETROS FORZOSOS                  

        CAD = Param.ValueArgsAsString("-CAD", "");
        EXP = Param.ValueArgsAsString("-EXP", "");
    }

    public void VerificarCadena() {
        String cads[] = CAD.split(",");
        System.out.println("Expresión:\t" + EXP);
        for (String cadena : cads) {
            System.out.println("La Cadena '" + cadena + (Pattern.matches("^" + EXP + "$", cadena) ? "'\t" : "'\tNo ") + "es aceptada");
        }
    }
}
