package org.vaadin.example.Backend.DB;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
@Service
public class Conexion extends BaseDatos
implements IConexion{
    @Value("(description=(address=(host=192.168.1.10)(protocol=tcp)(port=1521))(connect_data=(sid=xe)(service_name=xepdb1)(server=DEDICATED)))")
    private String canedaConexion;

    @Value("dummy2")
    private String usr;

    @Value("dummy2")
    private String pass;

    public Conexion(){
        System.out.println(canedaConexion);
        System.out.println(usr);
        System.out.println(pass);
    }
    @Override
    public void conectar(){
        try {
            System.out.println(canedaConexion);
            System.out.println(usr);
            System.out.println(pass);
            super.conectarDB(canedaConexion, usr, pass, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void desconectar(){
        super.desconectarBDD();
    }

    @Override
    public Connection getConecction() {
        return super.conn;
    }
}
