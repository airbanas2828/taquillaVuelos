
package com.mycompany.vuelos.dao;

import com.mycompany.vuelos.Reserva;
import com.mycompany.vuelos.Vuelo;
import java.sql.Date;
import java.util.ArrayList;

public interface IDaoVuelo  {
    
    boolean crearVuelo(Vuelo vuelo);
    
    ArrayList<Vuelo> verVuelo();
    
    boolean vueloCreado(String numero, Vuelo vuelo);
    
    boolean vueloDisponible(Date fecha, String ciudadOrigen,String ciudadDestino,Vuelo vuelo);
    
    Reserva infoVuelo(String numero);    
}
