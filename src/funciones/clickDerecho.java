/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Raul
 */
public class clickDerecho {

    public static void cortar(JPopupMenu popDerecho) {
        try {
            Robot robot = new Robot();

            // Simula la pulsacion de la tecla
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_X);

            robot.keyRelease(KeyEvent.VK_X);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            popDerecho.setVisible(false);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void copiar(JPopupMenu popDerecho) {
        try {
            Robot robot = new Robot();

            // Simulate a key press
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);

            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        popDerecho.setVisible(false);
    }
    
    public static void pegar(JPopupMenu popDerecho){
              try {
            Robot robot = new Robot();

            // Simulate a key press
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        popDerecho.setVisible(false);
    }


}
