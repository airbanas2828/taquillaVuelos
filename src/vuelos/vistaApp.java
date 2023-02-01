package vuelos;

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
    
    public static void setText(String texto){
        System.out.println(texto);
    
    }
    
}
