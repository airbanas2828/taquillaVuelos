
package com.mycompany.vuelos.dao;

import com.mycompany.vuelos.Pasajero;
import com.mycompany.vuelos.Reserva;
import com.mycompany.vuelos.Vuelo;
import com.mysql.cj.protocol.Resultset;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


public class DaoVuelo extends Conexion implements IDaoVuelo{

    @Override
    public boolean crearVuelo(Vuelo vuelo) {
        
        String sql="INSERT INTO "+ Constantes.T_VUELO+"( "+
                Constantes.TV_NUMERO+","+
                Constantes.TV_FECHA+","+
                Constantes.TV_CIUDADORIGEN+","+
                Constantes.TV_CIUDADDESTINO+","+
                Constantes.TV_DISPONIBLE+") VALUES(?,?,?,?,?)";
        
        try {
            PreparedStatement ps = getConexion().prepareStatement(sql);
            ps.setString(1, vuelo.getNumero());
            ps.setDate(2, vuelo.getFechaVuelo());
            ps.setString(3, vuelo.getCiudadOrigen());
            ps.setString(4, vuelo.getCiudadDestino());
            ps.setBoolean(5, vuelo.isDisponible());
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
    public ArrayList<Vuelo> verVuelo() {
        
        ArrayList<Vuelo> arrayListVuelo=new ArrayList<>();
        
        String sql="SELECT * FROM"+
                Constantes.T_VUELO+
                " ORDER BY "+Constantes.TV_ID+"DESC";
        
        try {
            PreparedStatement ps=getConexion().prepareStatement(sql);
            ResultSet resultSet=ps.executeQuery();
            
            while (resultSet.next()) {                
                Vuelo vuelo =new Vuelo();
                vuelo.setId(resultSet.getInt(Constantes.TV_ID));
                vuelo.setNumero(resultSet.getString(Constantes.TV_NUMERO)); 
                vuelo.setFechaVuelo(resultSet.getDate(Constantes.TV_FECHA));
                vuelo.setCiudadOrigen(resultSet.getString(Constantes.TV_CIUDADORIGEN));
                vuelo.setCiudadDestino(resultSet.getString(Constantes.TV_CIUDADDESTINO));
                vuelo.setDisponible(resultSet.getBoolean(Constantes.TV_DISPONIBLE));
                
                arrayListVuelo.add(vuelo);
                
            }
            return arrayListVuelo;
            
        } catch (SQLException e) {
            System.out.println("Error al leer los datos"+e.getMessage());
            return arrayListVuelo;
        }finally{
            try {
                getConexion().close();
                
            } catch (SQLException e) {
                System.out.println("error al cerrar la conexion"+e);
            }
            
        
        }
        
    }

    @Override 
    public boolean vueloCreado(String numero, Vuelo vuelo) {
        
        String sql="SELEC * FROM "+
                Constantes.T_VUELO+
                "WHERE"+Constantes.TV_NUMERO+"='"+numero+"'";
        
        try {
            PreparedStatement ps=getConexion().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {                
                vuelo.setId(resultSet.getInt(Constantes.TV_ID));
                vuelo.setNumero(resultSet.getString(Constantes.TV_NUMERO));
                vuelo.setFechaVuelo(resultSet.getDate(Constantes.TV_FECHA));
                vuelo.setCiudadOrigen(resultSet.getString(Constantes.TV_CIUDADORIGEN));
                vuelo.setCiudadDestino(resultSet.getString(Constantes.TV_CIUDADDESTINO));
                vuelo.setDisponible(resultSet.getBoolean(Constantes.TV_DISPONIBLE));
                
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            System.out.println("Error al leer los datos "+e.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean vueloDisponible(Date fecha, String ciudadOrigen, String ciudadDestino, Vuelo vuelo) {
        String sql = "SELECT * FROM"+
                Constantes.T_VUELO+
                " WHERE "+Constantes.TV_FECHA+"='"+fecha+"'"+
                " AND "+Constantes.TV_CIUDADORIGEN+"='"+ciudadOrigen+"'"+
                " AND "+Constantes.TV_CIUDADDESTINO+"='"+ciudadDestino+"'";
        try {
            PreparedStatement ps=getConexion().prepareStatement(sql);
            ResultSet resultSet =ps.executeQuery();
            while (resultSet.next()) {                
                vuelo.setId(resultSet.getInt(Constantes.TV_ID));
                vuelo.setNumero(resultSet.getString(Constantes.TV_NUMERO));
                vuelo.setFechaVuelo(resultSet.getDate(Constantes.TV_FECHA));
                vuelo.setCiudadOrigen(resultSet.getString(Constantes.TV_CIUDADORIGEN));
                vuelo.setCiudadDestino(resultSet.getString(Constantes.TV_CIUDADDESTINO));
                vuelo.setDisponible(resultSet.getBoolean(Constantes.TV_DISPONIBLE));
                
                
                
                return true;
            }
            return false;
            
        } catch (Exception e) {
            System.out.println("Error al leer los datos "+e.getMessage());
            return false;
        }finally{
            try {
                getConexion().close();
            } catch (Exception e) {
                System.out.println("error al cerrar la conexion"+e);
            }
        }
    }

    @Override
    public Reserva infoVuelo(String numero) {
        
        ArrayList <Vuelo> arrayListVuelos =new ArrayList<>();
        ArrayList <Pasajero> arrayListPasajeros=new ArrayList<>();
        
        String sql ="SELECT"+Constantes.T_VUELO+".*,"+Constantes.T_PASAJERO+".*"+
                "FROM"+Constantes.T_RESERVA+
                "JOIN"+Constantes.T_VUELO+
                "ON"+Constantes.T_VUELO+"."+Constantes.TV_NUMERO+"="+Constantes.T_RESERVA+"."+Constantes.TR_NUMERO+
                "JOIN"+Constantes.T_PASAJERO+
                "ON"+Constantes.T_PASAJERO+"."+Constantes.TP_CEDULA+"="+Constantes.T_RESERVA+"."+Constantes.TR_CEDULA+
                "WHERE"+Constantes.T_RESERVA+"."+Constantes.TR_NUMERO+"='"+numero+"'"+
                "ORDER BY"+Constantes.T_RESERVA+"."+Constantes.TR_ID+"DESC";
        try {
            PreparedStatement ps=getConexion().prepareStatement(sql);
            ResultSet resultSet=ps.executeQuery();
            
            while (resultSet.next()) {
                Vuelo vuelo =new Vuelo();
                vuelo.setId(resultSet.getInt(Constantes.TV_ID));
                vuelo.setNumero(resultSet.getString(Constantes.TV_NUMERO));
                vuelo.setFechaVuelo(resultSet.getDate(Constantes.TV_FECHA));
                vuelo.setCiudadOrigen(resultSet.getString(Constantes.TV_CIUDADORIGEN));
                vuelo.setCiudadDestino(resultSet.getString(Constantes.TV_CIUDADDESTINO));
                vuelo.setDisponible(resultSet.getBoolean(Constantes.TV_DISPONIBLE));
                
                arrayListVuelos.add(vuelo);
                
               Pasajero pasajero =new Pasajero();
                pasajero.setId(resultSet.getInt(Constantes.TP_ID));
                pasajero.setCedula(resultSet.getString(Constantes.TP_CEDULA));
                pasajero.setNombre(resultSet.getString(Constantes.TP_NOMBRE));
                pasajero.setApellido(resultSet.getString(Constantes.TP_APELLIDO));
                pasajero.setTipo(resultSet.getString(Constantes.TP_TIPO));
                
                
                arrayListPasajeros.add(pasajero);
            }
            return new Reserva(arrayListVuelos, arrayListPasajeros);
            
        } catch (SQLException e) {
            System.out.println("Error al leer los datos"+e.getMessage());
            return new Reserva(arrayListVuelos, arrayListPasajeros);
        }finally{
            try {
                getConexion().close();
            } catch (SQLException e) {
                System.out.println("error al cerrar la conexion "+e.getMessage());
            }
        }
    }
    
}
