/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.parqueadero.cliente;

import com.unicauca.parqeuadero.transversal.Comunicacion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author alvarodanieleraso
 */
public class TicketController {
    
     NewJerseyClientTicket jerseyTicket = new NewJerseyClientTicket();
     
       public boolean crearTicket(int Codigo, String hora_entrada, String hora_salida){
        
        
        //para formatear la fecha recibida en string
        //string hora_entrada
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");   
        Date date_hora_entrada;
         try {
             date_hora_entrada = formatter.parse(hora_entrada);
             //System.out.println(date.toString());
              String rta = jerseyTicket.create_JSON(new Ticket(10,date_hora_entrada,date_hora_entrada,Codigo));
              System.out.println("esta es la respuesta ");
              System.out.println(rta);
              
              
              
               //decodificamos la respuesta
                JSONObject jsonObject = new JSONObject(rta);
                //System.out.println(jsonObject.getString("ok"));
                String respuesta = jsonObject.getString("ok");
                System.out.println(respuesta);
                if(respuesta.equals("false")){
                   Comunicacion.mensajeError(jsonObject.getString("errores"), "Vehiculo");
                   return false;
                }else{
                    return true;
                }
         } catch (ParseException ex) {
             Logger.getLogger(TicketController.class.getName()).log(Level.SEVERE, null, ex);
         }
           
       return true;
    }
    
}
