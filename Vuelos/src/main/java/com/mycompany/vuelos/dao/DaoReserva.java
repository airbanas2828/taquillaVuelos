
package com.mycompany.vuelos.dao;

import com.mycompany.vuelos.Pasajero;
import com.mycompany.vuelos.Reserva;
import com.mycompany.vuelos.Vuelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DaoReserva extends Conexion implements IDaoReserva{

    @Override
    public boolean hacerReserva(Reserva reserva) {
        
        
        
        String sql="INSERT INTO "+ Constantes.T_RESERVA+"( "+
                Constantes.TR_ID+","+
                Constantes.TR_CEDULA+","+
                Constantes.TR_NUMERO+","+
                Constantes.TR_ESTADO+")"+") VALUES(?,?,?,?)";
        
        try {
            PreparedStatement ps = getConexion().prepareStatement(sql);
            ps.setString(1,reserva.getId);
            ps.setString(3, reserva.getCedula());
            ps.setString(4, reserva.getNumero());
            ps.setBoolean(5, reserva.isDisponible());
            ps.executeUpdate();
            
            System.out.println("vuelo creado");
            
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error al crear el vuelo"+ e.getMessage());
            return false;
        }finally{
            try {
                getConexion().close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion"+e.getMessage());
            }
        
        }
    }

    @Override
    public ArrayList<Reserva> verReserva() {
        
    
        ArrayList<Reserva> arrayListreReservas=new ArrayList<>();
        
        String sql="SELECT * FROM"+
                Constantes.T_RESERVA+
                " ORDER BY "+Constantes.TR_ID+"DESC";
        
        try {
            PreparedStatement ps=getConexion().prepareStatement(sql);
            ResultSet resultSet=ps.executeQuery();
            
            while (resultSet.next()) {                
                Reserva reserva =new Reserva();
                reserva.(resultSet.getInt(Constantes.TR_ID));
                reserva.setNumero(resultSet.getString(Constantes.TR_NUMERO)); 
                reserva.setCedula(resultSet.getString(Constantes.TR_CEDULA));
                reserva.setEstado(resultSet.getBoolean(Constantes.TR_ESTADO));
                
                
                arrayListreReservas.add(Reserva);
                
            }
            return arrayListreReservas;
            
        } catch (SQLException e) {
            System.out.println("Error al leer los datos"+e.getMessage());
            return arrayListreReservas;
        }finally{
            try {
                getConexion().close();
                
            } catch (SQLException e) {
                System.out.println("error al cerrar la conexion"+e);
            }
            
        
        }
    
    }
}
