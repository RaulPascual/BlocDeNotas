/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author Raul
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
       
       ponerInfo();
       try{
        setIconImage(new ImageIcon(getClass().getResource("../imagenes/icon.png")).getImage());
       }catch(Exception e){}
       comprobaciones();
        ponerTitulo();
       
    }
    
    
    private void ponerTitulo(){
    
    this.setTitle("Nuevo documento");
    }
    
    private void comprobaciones(){
      if(ajusteLinea.isSelected()){
       notas.setLineWrap(true);
       }else{
       notas.setLineWrap(false);
       }
    
    }
    
    private void ponerInfo(){
    notas.addCaretListener(new CaretListener() {
        @Override
        public void caretUpdate(CaretEvent e) {
            int pos = e.getDot();
                   try {
                       if(esp.isSelected()){
           int row = notas.getLineOfOffset( pos ) + 1;
           int col = pos - notas.getLineStartOffset( row - 1 ) + 1;
           info.setText("Línea: " + row + " Columna: " + col + " Numero de palabras: " + contarPalabras(notas.getText()));
       }else{
            int    row = notas.getLineOfOffset( pos ) + 1;
           int col = pos - notas.getLineStartOffset( row - 1 ) + 1;
           info.setText("Line: " + row + " Column: " + col + " Number of words: " + contarPalabras(notas.getText()));       
                       }
                   }
                       
       catch( BadLocationException exc ){
           System.out.println(exc);
       }
        }
    });
    }
    
    private void abrirArchivo(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
          if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
            File archivo = fileChooser.getSelectedFile();
                FileReader lector = null;
              try {
                
                
                lector = new FileReader(archivo);
                BufferedReader bfReader = new BufferedReader(lector);
                
            String lineaFichero;
            StringBuilder contenidoFichero = new StringBuilder();
            this.setTitle(archivo.getName());
            
            // Recupera el contenido del fichero
            while ((lineaFichero = bfReader.readLine()) != null) {
                contenidoFichero.append(lineaFichero);
                contenidoFichero.append("\n");
            }
            
            notas.setText(contenidoFichero.toString());
            modificado = false;
             
            
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
       
            } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                lector.close();
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
           
          }//fin if
    
    
    }//fin del metodo
    
    
    private void guardarArchivo(){
        JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    
     if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
        File archivo = fileChooser.getSelectedFile();
        FileWriter escritor = null;
        
      
            try {
                escritor = new FileWriter(archivo + ".txt");
                 escritor.write(notas.getText());
                guardado = true;
                 
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
            try {
                escritor.close();
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            }
     
     }else{ //opcion de cancelar al guardar el archvio
      setDefaultCloseOperation(Ventana.DO_NOTHING_ON_CLOSE); //Que no se cierre, por el evento windowClosing
     
     }
    
    } //fin del metodo
    
    
        private String getFechaHoraESP(java.util.GregorianCalendar fechaHora) {
        String fecha = "";
        switch (fechaHora.get(java.util.Calendar.DAY_OF_WEEK)) {
            case java.util.Calendar.MONDAY:
                fecha = "Lunes, ";
                break;
            case java.util.Calendar.TUESDAY:
                fecha = "Martes, ";
                break;
            case java.util.Calendar.WEDNESDAY:
                fecha = "Miércoles, ";
                break;
            case java.util.Calendar.THURSDAY:
                fecha = "Jueves, ";
                break;
            case java.util.Calendar.FRIDAY:
                fecha = "Viernes, ";
                break;
            case java.util.Calendar.SATURDAY:
                fecha = "Sabado, ";
                break;
            case java.util.Calendar.SUNDAY:
                fecha = "Domingo, ";
        }
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        fecha = fecha + formatoFecha.format(fechaHora.getTime());
        return fecha;
    }//fin metodo fecha
        
         private String getFechaHoraING(java.util.GregorianCalendar fechaHora) {
        String fecha = "";
        switch (fechaHora.get(java.util.Calendar.DAY_OF_WEEK)) {
            case java.util.Calendar.MONDAY:
                fecha = "Monday, ";
                break;
            case java.util.Calendar.TUESDAY:
                fecha = "Tuesday, ";
                break;
            case java.util.Calendar.WEDNESDAY:
                fecha = "Wednesday, ";
                break;
            case java.util.Calendar.THURSDAY:
                fecha = "Thursday, ";
                break;
            case java.util.Calendar.FRIDAY:
                fecha = "Friday, ";
                break;
            case java.util.Calendar.SATURDAY:
                fecha = "Saturday, ";
                break;
            case java.util.Calendar.SUNDAY:
                fecha = "Sunday, ";
        }
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        fecha = fecha + formatoFecha.format(fechaHora.getTime());
        return fecha;
    }//fin metodo fecha
    
         
         private static int contarPalabras(String notas){
         notas.trim();
         int cont = 1;
         int posicion;
         if(notas.isEmpty()){
         cont =0;
         }else{
        cont = notas.split("\\s+|\n|,").length;
         }
         
         return cont;
         }
        

private void buscarpalabra(JTextArea notas, String texto) {
     if(texto != null) {   
    if (texto.length() >= 1) {
            DefaultHighlighter.DefaultHighlightPainter subrayador = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
            Highlighter h = notas.getHighlighter();
            h.removeAllHighlights();
            String text = notas.getText();
            String caracteres = texto;
            Pattern p = Pattern.compile("(?i)" + caracteres);
            Matcher m = p.matcher(text);
            if (m.find()) {
                try {
                    h.addHighlight(m.start(), m.end(), subrayador);
                } catch (BadLocationException ex) {
                   
                }
            }else{
                JOptionPane.showMessageDialog(this, "No se ha enconrado esa palabra", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
             JOptionPane.showMessageDialog(this, "La palabra no puede estar vacía", "Informacion", JOptionPane.INFORMATION_MESSAGE);
     }
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idioma_btnGroup = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        notas = new javax.swing.JTextArea();
        info = new javax.swing.JLabel();
        MenuSuperior = new javax.swing.JMenuBar();
        archivo = new javax.swing.JMenu();
        Nuevo = new javax.swing.JMenuItem();
        Abrir = new javax.swing.JMenuItem();
        Guardar = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();
        Acerca = new javax.swing.JMenuItem();
        Editar = new javax.swing.JMenu();
        ColorFondo = new javax.swing.JMenuItem();
        ColorLetras = new javax.swing.JMenuItem();
        fuentes = new javax.swing.JMenu();
        Arial = new javax.swing.JMenuItem();
        Impact = new javax.swing.JMenuItem();
        tahoma = new javax.swing.JMenuItem();
        georgia = new javax.swing.JMenuItem();
        jokerman = new javax.swing.JMenuItem();
        inkFree = new javax.swing.JMenuItem();
        buscar = new javax.swing.JMenuItem();
        fecha = new javax.swing.JMenuItem();
        Formato = new javax.swing.JMenu();
        ajusteLinea = new javax.swing.JCheckBoxMenuItem();
        idioma = new javax.swing.JMenu();
        esp = new javax.swing.JRadioButtonMenuItem();
        ing = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
        });

        notas.setColumns(20);
        notas.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        notas.setRows(5);
        notas.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        notas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notasMouseClicked(evt);
            }
        });
        notas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                notasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                notasKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(notas);

        info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        MenuSuperior.setBackground(new java.awt.Color(153, 255, 153));
        MenuSuperior.setForeground(new java.awt.Color(51, 0, 51));
        MenuSuperior.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        archivo.setText("Archivo");
        archivo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Nuevo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Nuevo.setText("Nuevo");
        Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoActionPerformed(evt);
            }
        });
        archivo.add(Nuevo);

        Abrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        Abrir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Abrir.setText("Abrir");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        archivo.add(Abrir);

        Guardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        Guardar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        archivo.add(Guardar);

        Salir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        archivo.add(Salir);

        Acerca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Acerca.setText("Acerca de...");
        Acerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaActionPerformed(evt);
            }
        });
        archivo.add(Acerca);

        MenuSuperior.add(archivo);

        Editar.setText("Editar");
        Editar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ColorFondo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ColorFondo.setText("Color de fondo");
        ColorFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorFondoActionPerformed(evt);
            }
        });
        Editar.add(ColorFondo);

        ColorLetras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ColorLetras.setText("Color de las letras");
        ColorLetras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorLetrasActionPerformed(evt);
            }
        });
        Editar.add(ColorLetras);

        fuentes.setText("Fuentes");
        fuentes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        Arial.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        Arial.setText("Arial");
        Arial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArialActionPerformed(evt);
            }
        });
        fuentes.add(Arial);

        Impact.setFont(new java.awt.Font("Impact", 0, 15)); // NOI18N
        Impact.setText("Impact");
        Impact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImpactActionPerformed(evt);
            }
        });
        fuentes.add(Impact);

        tahoma.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tahoma.setText("Tahoma");
        tahoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tahomaActionPerformed(evt);
            }
        });
        fuentes.add(tahoma);

        georgia.setFont(new java.awt.Font("Georgia", 0, 15)); // NOI18N
        georgia.setText("Georgia");
        georgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                georgiaActionPerformed(evt);
            }
        });
        fuentes.add(georgia);

        jokerman.setFont(new java.awt.Font("Jokerman", 0, 15)); // NOI18N
        jokerman.setText("Jokerman");
        jokerman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jokermanActionPerformed(evt);
            }
        });
        fuentes.add(jokerman);

        inkFree.setFont(new java.awt.Font("Ink Free", 0, 15)); // NOI18N
        inkFree.setText("Ink Free");
        inkFree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inkFreeActionPerformed(evt);
            }
        });
        fuentes.add(inkFree);

        Editar.add(fuentes);

        buscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        buscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        Editar.add(buscar);

        fecha.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fecha.setText("Fecha y hora");
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });
        Editar.add(fecha);

        MenuSuperior.add(Editar);

        Formato.setText("Formato");
        Formato.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ajusteLinea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ajusteLinea.setSelected(true);
        ajusteLinea.setText("Ajuste de Linea");
        ajusteLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajusteLineaActionPerformed(evt);
            }
        });
        Formato.add(ajusteLinea);

        MenuSuperior.add(Formato);

        idioma.setText("Idioma");
        idioma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        idioma_btnGroup.add(esp);
        esp.setSelected(true);
        esp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/esp.gif"))); // NOI18N
        esp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espActionPerformed(evt);
            }
        });
        idioma.add(esp);

        idioma_btnGroup.add(ing);
        ing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ing.jpg"))); // NOI18N
        ing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingActionPerformed(evt);
            }
        });
        idioma.add(ing);

        MenuSuperior.add(idioma);

        setJMenuBar(MenuSuperior);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
         // TODO add your handling code here:
         guardarArchivo();

         
    }//GEN-LAST:event_GuardarActionPerformed

    private void NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoActionPerformed
         // TODO add your handling code here:
         notas.setText("");
         ponerTitulo();
    }//GEN-LAST:event_NuevoActionPerformed

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
         // TODO add your handling code here:
         
         abrirArchivo();
    }//GEN-LAST:event_AbrirActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
         // TODO add your handling code here
         System.exit(0);
         
    }//GEN-LAST:event_SalirActionPerformed

    private void ajusteLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajusteLineaActionPerformed
         // TODO add your handling code here:
        
        if(ajusteLinea.isSelected()){
           
        notas.setLineWrap(true);
        }else{
        notas.setLineWrap(false);
        }
    }//GEN-LAST:event_ajusteLineaActionPerformed

    private void AcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaActionPerformed
        // TODO add your handling code here:
        if(esp.isSelected()){
    JOptionPane.showMessageDialog(this, "Aplicación creada por Raul Pascual", "Informacion", INFORMATION_MESSAGE);
        }else{
        JOptionPane.showMessageDialog(this, "Application made by Raul Pascual", "Information", INFORMATION_MESSAGE);
        }

       
    }//GEN-LAST:event_AcercaActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
         // TODO add your handling code here:
         
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         // TODO add your handling code here:
         
         if(modificado == true && guardado == false){
         int opciones = JOptionPane.showConfirmDialog(null, "¿Guardar el archivo?");  //0=si 1=no 2=cancelar
          
         if(opciones == 0){
          guardarArchivo();
         }else if(opciones == JOptionPane.CANCEL_OPTION){
          setDefaultCloseOperation(Ventana.DO_NOTHING_ON_CLOSE);
         }else{
         System.exit(0);
         }
         
         }
         
       
    }//GEN-LAST:event_formWindowClosing

    private void notasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_notasKeyTyped
        // TODO add your handling code here:
        
        modificado = true;
                
    }//GEN-LAST:event_notasKeyTyped

    private void notasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_notasKeyPressed
          // TODO add your handling code here:
         
         ponerInfo();
        try{
        popDerecho.setVisible(false);
        }catch(Exception e){
         Exception NullException;
        }
       
        contarPalabras(notas.getText());
        
    }//GEN-LAST:event_notasKeyPressed

    
    private void cortar(){
                      try {
        Robot robot = new Robot();

        // Simulate a key press
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_X);
       

         robot.keyRelease(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        popDerecho.setVisible(false);
        } catch (AWTException e) {
        e.printStackTrace();
        }
                      
        
    }
    
    
        private void copiar(){
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
        
        
                private void pegar(){
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
    
    private void notasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notasMouseClicked
         // TODO add your handling code here:
                  
         if(evt.getClickCount() == 2 ){
           Highlighter h = notas.getHighlighter();
            h.removeAllHighlights();
         }
         

        if(evt.getButton()==MouseEvent.BUTTON3){
          
           if(popDerecho != null){
           popDerecho.setVisible(false);
           }
          popDerecho = new JPopupMenu();
         copiarpop = new JMenuItem("Copiar");
         pegarpop = new JMenuItem("Pegar");
         cortarpop = new JMenuItem("Cortar");
         popDerecho.add(copiarpop);
         popDerecho.add(pegarpop);
         popDerecho.add(cortarpop);
     
   
      popDerecho.setLocation(evt.getLocationOnScreen());
      popDerecho.setVisible(true);
        
      
      //Al hacer clic en el boton de cortar
      cortarpop.addMouseListener(new MouseListener() {
              @Override
              public void mouseClicked(MouseEvent me) {
                  //metodo cortar
                   cortar();
              }

              @Override
              public void mousePressed(MouseEvent me) {
                 //metodo cortar
                 cortar();
              }

              @Override
              public void mouseReleased(MouseEvent me) {
                //metodo cortar
                cortar();
              }

              @Override
              public void mouseEntered(MouseEvent me) {
                 //metodo cortar
         
              }

              @Override
              public void mouseExited(MouseEvent me) {
                 //metodo cortar
              }
          });
      
      
            //Al hacer clic en el boton de copiar
           copiarpop.addMouseListener(new MouseListener() {
              @Override
              public void mouseClicked(MouseEvent me) {
                  //metodo copiar
                  copiar();
              }

              @Override
              public void mousePressed(MouseEvent me) {
                 //metodo copiar
                 copiar();
              }

              @Override
              public void mouseReleased(MouseEvent me) {
                //metodo copiar
                copiar();
              }

              @Override
              public void mouseEntered(MouseEvent me) {
               
         
              }

              @Override
              public void mouseExited(MouseEvent me) {
                 
              }
          });
      
      
           //Al hacer clic en el boton de pegar
              pegarpop.addMouseListener(new MouseListener() {
              @Override
              public void mouseClicked(MouseEvent me) {
                  //metodo pegar
                  pegar();
              }

              @Override
              public void mousePressed(MouseEvent me) {
                 //metodo pegar
                 pegar();
              }

              @Override
              public void mouseReleased(MouseEvent me) {
                //metodo pegar
                pegar();
              }

              @Override
              public void mouseEntered(MouseEvent me) {
                 
         
              }

              @Override
              public void mouseExited(MouseEvent me) {
                 
              }
          });
           
         if(popDerecho.isVisible() && evt.getButton() == MouseEvent.BUTTON1){
         popDerecho.setVisible(false);
         
         }
              
              
        }//fin del if del clic derecho
        
        
        if(evt.getButton() == MouseEvent.BUTTON1){
           try{
            if(popDerecho.isVisible()){
                   popDerecho.setVisible(false);
            }
               }catch(Exception e){
                   System.out.println("No esta visible ahora");
               
           
           }
        
        }
         
    }//GEN-LAST:event_notasMouseClicked

    private void espActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espActionPerformed
        // TODO add your handling code here:
          archivo.setText("Archivo");
          Nuevo.setText("Nuevo");
          Abrir.setText("Abrir");
          Guardar.setText("Guardar");
          Salir.setText("Salir");
          Acerca.setText("Acerca...");
          
          Editar.setText("Editar");
          ColorFondo.setText("Color de fondo");
          ColorLetras.setText("Color de las letras");
          fuentes.setText("Fuentes");
          buscar.setText("Buscar");
          fecha.setText("Fecha y hora");
          
          Formato.setText("Formato");
          ajusteLinea.setText("Ajuste de linea");
          
          idioma.setText("Idioma");
        
        
    }//GEN-LAST:event_espActionPerformed

    private void ingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingActionPerformed
        // TODO add your handling code here:
        archivo.setText("File");
          Nuevo.setText("New");
          Abrir.setText("Open");
          Guardar.setText("Save");
          Salir.setText("Exit");
          Acerca.setText("About...");
          
          Editar.setText("Edit");
          ColorFondo.setText("Background Color");
          ColorLetras.setText("Word Color");
          fuentes.setText("Fonts");
          buscar.setText("Find");
          
          fecha.setText("Date and hour");
          
          Formato.setText("Format");
          ajusteLinea.setText("Word wrap");
          
          idioma.setText("Language");

     
    }//GEN-LAST:event_ingActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
        fechaHora = new java.util.GregorianCalendar();
        if(esp.isSelected()){
            notas.setText(notas.getText() + getFechaHoraESP(fechaHora));
        }else{
            notas.setText(notas.getText() + getFechaHoraING(fechaHora));
        }

    }//GEN-LAST:event_fechaActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:

        try{
            if(esp.isSelected()){
              String palabra = JOptionPane.showInputDialog(this, "Palabra a buscar");

            buscarpalabra(notas, palabra);
            }else{
              String palabra = JOptionPane.showInputDialog(this, "Search word");

            buscarpalabra(notas, palabra);
            }
          
        }catch(NullPointerException ex){
            Exception NullException;
        }

    }//GEN-LAST:event_buscarActionPerformed

    private void inkFreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inkFreeActionPerformed
        // TODO add your handling code here:
        Font fuente = new Font("Ink Free", 3, 20);
        notas.setFont(fuente);
    }//GEN-LAST:event_inkFreeActionPerformed

    private void jokermanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jokermanActionPerformed
        // TODO add your handling code here:
        Font fuente = new Font("Jokerman", 3, 20);
        notas.setFont(fuente);
    }//GEN-LAST:event_jokermanActionPerformed

    private void georgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_georgiaActionPerformed
        // TODO add your handling code here:
        Font fuente = new Font("Georgia", 3, 20);
        notas.setFont(fuente);
    }//GEN-LAST:event_georgiaActionPerformed

    private void tahomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tahomaActionPerformed
        // TODO add your handling code here:
        Font fuente = new Font("Tahoma", 3, 20);
        notas.setFont(fuente);
    }//GEN-LAST:event_tahomaActionPerformed

    private void ImpactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImpactActionPerformed
        // TODO add your handling code here:
        Font fuente = new Font("Impact", 3, 20);
        notas.setFont(fuente);
    }//GEN-LAST:event_ImpactActionPerformed

    private void ArialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArialActionPerformed
        // TODO add your handling code here:

        Font fuente = new Font("Arial", 3, 20);
        notas.setFont(fuente);

    }//GEN-LAST:event_ArialActionPerformed

    private void ColorLetrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorLetrasActionPerformed
        // TODO add your handling code here:

        Color initialcolor=Color.WHITE;
        Color color=JColorChooser.showDialog(this,"Selecciona un color",initialcolor);
        notas.setForeground(color);

    }//GEN-LAST:event_ColorLetrasActionPerformed

    private void ColorFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorFondoActionPerformed
        // TODO add your handling code here:

        Color initialcolor=Color.BLACK;
        Color color=JColorChooser.showDialog(this,"Selecciona un color",initialcolor);
        notas.setBackground(color);

    }//GEN-LAST:event_ColorFondoActionPerformed

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        // TODO add your handling code here:
        try{
        if(popDerecho.isVisible()){
        popDerecho.setVisible(false);
        }
        }catch(Exception e){
            System.out.println("No es visible");
        }
        
    }//GEN-LAST:event_formWindowDeactivated

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { //Para cambiar la interfaz grafica de los FileChooser, se cambia por la que tenga el sistema en ese momento
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new Ventana().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JMenuItem Acerca;
    private javax.swing.JMenuItem Arial;
    private javax.swing.JMenuItem ColorFondo;
    private javax.swing.JMenuItem ColorLetras;
    private javax.swing.JMenu Editar;
    private javax.swing.JMenu Formato;
    private javax.swing.JMenuItem Guardar;
    private javax.swing.JMenuItem Impact;
    private javax.swing.JMenuBar MenuSuperior;
    private javax.swing.JMenuItem Nuevo;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JCheckBoxMenuItem ajusteLinea;
    private javax.swing.JMenu archivo;
    private javax.swing.JMenuItem buscar;
    private javax.swing.JRadioButtonMenuItem esp;
    private javax.swing.JMenuItem fecha;
    private javax.swing.JMenu fuentes;
    private javax.swing.JMenuItem georgia;
    private javax.swing.JMenu idioma;
    private javax.swing.ButtonGroup idioma_btnGroup;
    private javax.swing.JLabel info;
    private javax.swing.JRadioButtonMenuItem ing;
    private javax.swing.JMenuItem inkFree;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem jokerman;
    private javax.swing.JTextArea notas;
    private javax.swing.JMenuItem tahoma;
    // End of variables declaration//GEN-END:variables
   private java.util.GregorianCalendar fechaHora;
   boolean modificado = false;
   boolean guardado = false;
   private JComboBox size;
   private JPopupMenu popDerecho;
   private JMenuItem cortarpop,copiarpop,pegarpop;
}
