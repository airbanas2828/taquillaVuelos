package com.mycompany.vuelos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    private Connection connection=null;
    
    public Connection geConnection(){
        
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            connection= DriverManager.getConnection(Constantes.URL+Constantes.DATABASE, Constantes.USER, Constantes.PASSWORD);
            if (Conexion!=null) {
                System.out.println("conexion establecida");
            }
        } catch (SQLException e) {
            System.out.println("error en la conexion");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    return connection;
    
    }
}
