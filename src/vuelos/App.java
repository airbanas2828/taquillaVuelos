
package vuelos;

import java.sql.Date;
import java.util.ArrayList;


public class App {


    public static void main(String[] args) {
        
        Pasajero pasajero=new Pasajero();
        Vuelo vuelo = new Vuelo();
        Reserva reserva=new Reserva();
        
        verMenu(vuelo, reserva, pasajero);
   
    }
    private static void verMenu(Vuelo vuelo, Reserva reserva,Pasajero pasajero){
        
       
        switch (vistaApp.getMenu()) {
            case "1":mostarVuelos(vuelo.listaVuelos());
            break;
            case "2":reservar(pasajero,vuelo,reserva);
            break;
            case "3":mostarReservas(reserva);
            break;
            default:vistaApp.setText("Opcion no valida");
                break;
        }
    
    
    }

    private static void mostarVuelos(ArrayList<Vuelo>arrayListVuelos) {
        for (int i = 0; i < arrayListVuelos.size(); i++) {
            
            String datos []=new String[5];
            
            datos[0] = arrayListVuelos.get(i).getNumero();
            datos[1] = String.valueOf(arrayListVuelos.get(i).getFechaVuelo());
            datos[2] = arrayListVuelos.get(i).getCiudadOrigen();
            datos[3] = arrayListVuelos.get(i).getCiudadDestino();
            datos[4] = arrayListVuelos.get(i).isDisponible() ? "Disponible" : "No disponible";
            
            vistaApp.setMostrarVuelo(datos);
            
        }
    }

    

    private static void mostarReservas(Reserva reserva) {
             for (int i = 0; i < reserva.getArrayListPasajeros().size(); i++) {
            
            String datos []=new String[5];
            
            datos[0] = reserva.getArrayListVuelos().get(i).getNumero();
            datos[1] = String.valueOf(reserva.getArrayListPasajeros().get(i).getCedula());
            datos[2] = reserva.getArrayListPasajeros().get(i).getNombre();
            datos[3] = reserva.getArrayListPasajeros().get(i).getApellido();
            datos[4] = reserva.getArrayListPasajeros().get(i).getTipo();
            
                 switch (reserva.getArrayListPasajeros().get(i).getTipo()) {
                     case "1" : datos[4]="bebe";
                         break;
                     case "2" : datos[4]="Niño";
                         break;
                     case "3" : datos[4]="Adulto";
                         break;

                 }
            
            vistaApp.setMostrarReserva(datos);
            
        }
    }
    private static void reservar(Pasajero pasajero,Vuelo vuelo,Reserva reserva ) {
       
        String ciudadOrigen= vistaApp.getEntrada("Ingrese la ciudad de origen");
        String ciudadDestino= vistaApp.getEntrada("Ingrese la ciudad de destino");
        String dia= vistaApp.getEntrada("ingrese el dia del vuelo");
        String mes= vistaApp.getEntrada("Ingrese el mes del vuelo");
        String año= vistaApp.getEntrada("Ingrese el año del vuelo");
        String cedula= vistaApp.getEntrada("Ingrese la cedula");
        String nombre= vistaApp.getEntrada("Ingrese el nombre");
        String apellido= vistaApp.getEntrada("Ingrese el apellido");
        String tipo= vistaApp.getEntrada("Ingrese el tipo 1. bebe, 2. Niño 0 3. Adulto");
        
       if (!ciudadOrigen.equals("") && !ciudadDestino.equals("") && !dia.equals("") &&
               !mes.equals("") && !año.equals("") && !cedula.equals("") && 
               !nombre.equals("") && !apellido.equals("") && !tipo.equals("") ) {
           Date fecha =Date.valueOf(año+"-"+mes+"-"+dia);
            
            if (vueloDisponible(fecha,ciudadOrigen,ciudadDestino,vuelo.listaVuelos())) {
                
               vistaApp.setText("Vuelo disponible");
               
               vuelo.setNumero(buscarVuelo(fecha,ciudadOrigen,ciudadDestino,vuelo.listaVuelos()));
               vuelo.setFechaVuelo(fecha);
               vuelo.setCiudadOrigen(ciudadOrigen);
               vuelo.setCiudadOrigen(ciudadOrigen);
               vuelo.setCiudadDestino(ciudadDestino);
               
               pasajero.setCedula(cedula);
               pasajero.setNombre(nombre);
               pasajero.setApellido(apellido);
               pasajero.setTipo(tipo);
               
                while (true) {                    
                    if (pasajeroRegistrado(pasajero)) {
                        
                        vistaApp.setText("Psajero Encontrado");
                        
                        if (hacerReserva(pasajero, vuelo, reserva)) {
                            mostarReservas(reserva);
                            vistaApp.setText("Reserva realizada con exito");
                            break;
                        }else{
                            vistaApp.setText("Error al realizar la reserva");
                            break;
                        
                        }
                        
                    }else{
                        if (Registrarpasajero(pasajero)) {
                            vistaApp.setText("pasajero registrado con exito");
                        }else{
                            vistaApp.setText("Error al registrar pasajero");
                            break;
                        
                        }
                    
                    }
                }
               
           } else {
                vistaApp.setText("no hay vuelo disponible para la fecha y localidad suministrada ");
           }
       }else{
           vistaApp.setText("No se permiten campos vacios");
       
       }
           
   
    }

    private static boolean vueloDisponible(Date fecha, String ciudadOrigen, String ciudadDestino, ArrayList<Vuelo> arrayListVuelos) {
        
        for (int i = 0; i < arrayListVuelos.size(); i++) {
            if (arrayListVuelos.get(i).getFechaVuelo().equals(fecha) && 
                    arrayListVuelos.get(i).getCiudadOrigen().equals(ciudadOrigen) &&
                    arrayListVuelos.get(i).getCiudadDestino().equals(ciudadDestino)) {
                return true;
            }
        }
        return false;
        
    }

    private static boolean pasajeroRegistrado(Pasajero pasajero) {
        return true;
    }
    private static boolean Registrarpasajero(Pasajero pasajero) {
        return true;
    }

    private static boolean hacerReserva(Pasajero pasajero, Vuelo vuelo, Reserva reserva) {
        
        ArrayList<Vuelo> elVuelo =new ArrayList<>();
        elVuelo.add(vuelo);
        
        ArrayList<Pasajero> elPsajero = new ArrayList<>();
        elPsajero.add(pasajero);
        
        reserva.setArrayListVuelos(elVuelo);
        reserva.setArrayListPasajeros(elPsajero);
        
        return true;
        
        
    }

    private static String buscarVuelo(Date fecha, String ciudadOrigen, String ciudadDestino, ArrayList<Vuelo> arrayListVuelos) {
        
        for (int i = 0; i < arrayListVuelos.size(); i++) {
            if (arrayListVuelos.get(i).getFechaVuelo().equals(fecha) && 
                    arrayListVuelos.get(i).getCiudadOrigen().equals(ciudadOrigen) &&
                    arrayListVuelos.get(i).getCiudadDestino().equals(ciudadDestino)) {
                
                return arrayListVuelos.get(i).getNumero();
            }
        }
        return "";
    }
    
}
