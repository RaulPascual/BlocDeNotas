/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funciones;

import java.text.SimpleDateFormat;

/**
 *
 * @author Raul
 */
public class getFecha {
    
    
        public String getFechaHoraESP(java.util.GregorianCalendar fechaHora) {
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

    public String getFechaHoraING(java.util.GregorianCalendar fechaHora) {
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
    }
    
    
    
}
