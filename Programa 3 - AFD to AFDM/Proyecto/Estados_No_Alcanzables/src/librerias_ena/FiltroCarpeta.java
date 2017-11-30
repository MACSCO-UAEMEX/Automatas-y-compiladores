/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package librerias_ena;

import java.io.*;

/**
 * Esta clase se utiliza para sobreescribir el filtro para los archivos que son carpetas
 * @author RENE
 */
public class FiltroCarpeta implements FilenameFilter{
    String extension;
 /**
  * Constructor que recibe la extensión del archivo que se quiere filtrar
  * @param extension Cadena con la extensión (incluyendo el punto) del nombre del archivo a filtrar
  */
    FiltroCarpeta(String extension){
        this.extension=extension;
    }
 /**
  * Esta función sobreescribe la función de la clase FilenameFilter y
  * recibe el directorio y el nombre del archivo que va ser procesado.
  * En caso de que el nombre del direcotrio junto con el del archivo se trate
  * de un directorio regresa TRUE, de lo contrario FALSE
  * @param dir Directorio donde está el archivo
  * @param name Nombre del archivo
  * @return TRUE en caso de tratarse de un directorio
  */
    @Override
	public boolean accept(File dir, String name){
        return  (new File(dir.getAbsolutePath() + File.separator + name)).isDirectory();
                
                
    }
}
