package org.vaadin.example.Backend.Servicios.Usuario;

import org.springframework.stereotype.Service;
import org.vaadin.example.Backend.Bean.Usuario;
import org.vaadin.example.Backend.DB.Conexion;

@Service
public class GuardarUsuario {
    private Conexion db = null;

    public GuardarUsuario(){
        db = new Conexion();
    }

    public Integer save(Usuario usr){
        Integer resultado = 0;
        try{
            db.conectar();
            String dml = "{? = call dummy2.PCK.F_ADD_USUARIO(?,?,?,?,?,?,?,?) }";
            String number = db.llamarFuncion(dml,
                    usr.getRowid(),
                    usr.getPrimerNombre(),
                    usr.getSegundoNombre(),
                    usr.getPrimerApellido(),
                    usr.getSegundoApellido(),
                    usr.getEmail(),
                    usr.getTelefono(),
                    usr.isAcepTerminos()?"1":"0");
            db.getConecction().commit();
            resultado = Integer.valueOf(number);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.desconectar();
        }
        return resultado;
    }
}
