
package vuelos;

import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        
        Pasajero pasajero=new Pasajero();
        Vuelo vuelo = new Vuelo();
        Reserva reserva=new Reserva();
        
        verMenu();
   
    }
    private static void verMenu(){
        
       
        switch (vistaApp.getMenu()) {
            case "1":mostarVuelos();
            break;
            case "2":reservar();
            break;
            case "3":mostarReservas();
            break;
            default:vistaApp.setText("Opcion no valida");
                break;
        }
    
    
    }

    private static void mostarVuelos() {
       
    }

    private static void reservar() {
       
    }

    private static void mostarReservas() {
        
    }
    
}
