package org.vaadin.example.Backend.Servicios.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.example.Backend.Bean.LovDTO;
import org.vaadin.example.Backend.Bean.Usuario;
import org.vaadin.example.Backend.DB.Conexion;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;

@Service
public class ObtenerUsuarios {
    private  Conexion db = null;

    public ObtenerUsuarios(){

    }

    public LinkedList<Usuario> obtenerUsuarios(){
        LinkedList<Usuario> resultado = new LinkedList<>();
        try {
            db = new Conexion();
            db.conectar();
            String sql = "select ROWID, UE_CORRELATIVO ,\n" +
                    "    UE_PRIMER_NOMBRE ,\n" +
                    "    UE_SEGUNDO_NOMBRE ,\n" +
                    "    UE_PRIMER_APELLIDO ,\n" +
                    "    UE_SEGUNDO_APELLIDO ,\n" +
                    "    UE_EMAIL ,\n" +
                    "    UE_TELEFONO ,\n" +
                    "    UE_ACEP_TERMINOS  from USUARIO_ENCUESTA";
            ResultSet rs = db.consulta(sql);
            while(rs.next()){
                resultado.add(new Usuario(rs.getString(1),rs.getInt(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                        rs.getInt(8),rs.getString(9).equalsIgnoreCase("1"),new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.desconectar();
        }
        return resultado;
    }
}
