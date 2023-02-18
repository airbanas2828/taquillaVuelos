
package com.mycompany.vuelos.dao;

import com.mycompany.vuelos.Pasajero;

public interface IDaoPasajero {
    
    boolean pasajeroRegistrado(Pasajero pasajero);
    
    boolean registrarPasajero(Pasajero pasajero);
    
}
