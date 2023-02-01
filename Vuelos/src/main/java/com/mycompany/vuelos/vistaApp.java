package com.mycompany.vuelos;

import java.util.Scanner;
import javax.security.auth.callback.TextOutputCallback;


public class vistaApp {
    
    public static String getMenu(){
        
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("***Aerolinea***"+
                "\n1. Mostrar Vuelos"+
                "\n2. Reservar"+
                "\n3. Mostrar Reserva");
        
        return teclado.nextLine();
    
    }
    public static String getEntrada(String texto){
        
        Scanner teclado = new Scanner(System.in);
        
        System.out.println(texto);
        
        return teclado.nextLine() ;
    
    }
    
    public static void setText(String texto){
        System.out.println(texto);
    
    }
    public static void setMostrarVuelo(String[] datos){
        System.out.println("\nVuelo: "+datos[0]+" - Fecha: "+datos[1]+
                    "\nOrigen: "+datos[2]+" - Destino: "+datos[3]+
                    "\nEstado: "+datos[4]);
    
    }
     public static void setMostrarReserva(String[] datos){
        System.out.println("\nNumero: "+datos[0]+" - Cedula: "+datos[1]+
                    "\nNombre: "+datos[2]+" - Apellido: "+datos[3]+
                    "\nTipo: "+datos[4]);
    
    }
    
}
