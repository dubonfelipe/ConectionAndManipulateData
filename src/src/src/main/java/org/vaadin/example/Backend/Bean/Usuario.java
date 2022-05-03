package org.vaadin.example.Backend.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private String rowid;
    private Integer correlativo;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String email;

    private Integer telefono;

    private boolean acepTerminos;

    private LocalDate fecha;

    public boolean validFormulario(){
        if((this.primerNombre!=null && this.primerNombre.isEmpty()) ||
           (this.segundoNombre!=null && this.segundoNombre.isEmpty()) ||
           (this.primerApellido!=null && this.primerApellido.isEmpty()) ||
                (this.segundoApellido!= null && this.segundoApellido.isEmpty()) ||
                (this.email!=null && this.email.isEmpty()) ||
                (this.telefono!=null && this.telefono < 10000000)
        ){
            return false;
        }

        return true;
    }
}
