/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PrbAutomataCad;

import java.io.*;

/**
 * Esta clase se utiliza para sobreescribir el filtro para los nombres de archivos
 * @author RENE
 */
public class Filtro implements FilenameFilter{
    
    String extension;

    /**
     * Constructor que recibe la extensión del archivo que se quiere filtrar
     * @param extension Cadena con la extesión (incluyendo el punto) del nombre del archivo a filtrar
     */
    Filtro(String extension){
        this.extension=extension;
    }
    /**
     * Esta función sobreescribe la función de la clase <code>FilenameFilter</code> y
     * recibe el directorio y el nombre del archivo que va ser procesado
     * en caso de tener la extensión deseado se regresa <i>TRUE</i>, de lo contrario <i>FALSE</i>
     * @param dir Directorio donde está el archivo
     * @param name Nombre del archivo
     * @return <i>TRUE</i> en caso de tener la extensión deseada
     */
    @Override
	public boolean accept(File dir, String name){
        return name.endsWith(extension);
    }
}
