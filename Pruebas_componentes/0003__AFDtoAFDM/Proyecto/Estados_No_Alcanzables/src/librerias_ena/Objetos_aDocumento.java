package librerias_ena;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Objetos_aDocumento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	
	void SaveToFile(String FileName) {
        File f = new File(FileName);
        ObjectOutputStream Salida = null;
        FileOutputStream fos;

        try {

            fos = new FileOutputStream(f);
            Salida = new ObjectOutputStream(fos);
        } catch (IOException ioException) {
            System.err.println("Error al abrir el archivo");
        }
        try {

            Salida.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo");
            System.out.println(e);
        }
        try {
            if (Salida != null) {
                Salida.close();
            }
        } catch (IOException ioException) {
            System.err.println("Error al cerrar el archivo");
        }
    }

}
