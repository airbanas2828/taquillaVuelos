
package com.mycompany.vuelos.dao;

import com.mycompany.vuelos.Reserva;
import java.util.ArrayList;

public interface IDaoReserva {
    
    boolean hacerReserva(Reserva reserva);
    
    ArrayList <Reserva> verReserva();
}
