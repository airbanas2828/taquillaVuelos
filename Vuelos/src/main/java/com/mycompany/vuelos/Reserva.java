
package com.mycompany.vuelos;

import java.util.ArrayList;

public class Reserva {
    
    private ArrayList<Vuelo> arrayListVuelos;
    private ArrayList<Pasajero> arrayListPasajeros;

    public Reserva() {
    }

    public Reserva(ArrayList<Vuelo> arrayListVuelos, ArrayList<Pasajero> arrayListPasajeros) {
        this.arrayListVuelos = arrayListVuelos;
        this.arrayListPasajeros = arrayListPasajeros;
    }

    public ArrayList<Vuelo> getArrayListVuelos() {
        return arrayListVuelos;
    }

    public void setArrayListVuelos(ArrayList<Vuelo> arrayListVuelos) {
        this.arrayListVuelos = arrayListVuelos;
    }

    public ArrayList<Pasajero> getArrayListPasajeros() {
        return arrayListPasajeros;
    }

    public void setArrayListPasajeros(ArrayList<Pasajero> arrayListPasajeros) {
        this.arrayListPasajeros = arrayListPasajeros;
    }
    
    
    
}
