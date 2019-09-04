/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Raul
 */
public class Impresora {
    
    Font fuente= new Font("Calibri", Font.PLAIN, 11);
    PrintJob printjob;
   Graphics pagina;
   
   	Impresora(){
		printjob = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "SCAT", null);
	}
        
    
         public void imprimir(String Cadena)
	{
		//LO COLOCO EN UN try/catch PORQUE PUEDEN CANCELAR LA IMPRESION
		try
		{
			pagina = printjob.getGraphics();
			pagina.setFont(fuente);
			pagina.setColor(Color.black);
 
			pagina.drawString(Cadena, 60, 60);
 
			pagina.dispose();
			printjob.end();
		}catch(Exception e)
		{
			System.out.println("LA IMPRESION HA SIDO CANCELADA...");
                        JOptionPane.showMessageDialog(null, "La impresión ha sido cancelada");
		}
	}
        
}
