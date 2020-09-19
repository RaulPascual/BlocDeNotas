/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import principal.Ventana;

/**
 *
 * @author Raul
 */
public class GuardarArchivo {
    
    public static File guardar(boolean guardado, JTextArea notas, JFrame ventana){
               JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
            File archivo = fileChooser.getSelectedFile();
            FileWriter escritor = null;

            try {

                if (archivo.getName().contains(".")) {
                    escritor = new FileWriter(archivo);
                    escritor.write(notas.getText());
                    guardado = true;
                } else {

                    escritor = new FileWriter(archivo + ".txt");
                    escritor.write(notas.getText());
                    guardado = true;
                    ventana.setTitle(archivo.getName());
                }

            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    escritor.close();
                } catch (IOException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else { //opcion de cancelar al guardar el archvio
            setDefaultCloseOperation(Ventana.DO_NOTHING_ON_CLOSE); //Que no se cierre, por el evento windowClosing

        }
        return fileChooser.getSelectedFile();
    }

    private static void setDefaultCloseOperation(int DO_NOTHING_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
