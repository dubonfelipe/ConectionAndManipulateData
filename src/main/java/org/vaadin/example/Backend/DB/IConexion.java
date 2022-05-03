package org.vaadin.example.Backend.DB;

import java.sql.Connection;

public interface IConexion {
    public void conectar();
    public void desconectar();

    public Connection getConecction();
}
