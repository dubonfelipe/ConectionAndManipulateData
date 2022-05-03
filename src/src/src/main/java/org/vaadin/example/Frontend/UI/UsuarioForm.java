package org.vaadin.example.Frontend.UI;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToBigIntegerConverter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.Backend.Bean.Usuario;
import org.vaadin.example.Backend.Factory.Forms.base.FormBase;
import org.vaadin.example.Backend.Factory.Forms.concreto.CampoTexto;
import org.vaadin.example.Backend.Servicios.Usuario.GuardarUsuario;
import org.vaadin.example.Backend.Utils.ComponentUtils;
import org.vaadin.example.Backend.Utils.SessionUtils;
import org.vaadin.example.Frontend.MainLayout;

import java.lang.reflect.Field;

@Route(value = "formularioUsuario",layout = MainLayout.class)
@PageTitle("Ingresar usuario")
public class UsuarioForm extends FormLayout {
    private Binder<Usuario> binder = new Binder<>(Usuario.class);
    private Usuario usuario ;
    @Autowired
    private GuardarUsuario servicio;
    public UsuarioForm(){

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        usuario = SessionUtils.getAttribute(Usuario.class,"UUSUARIO");

        if(usuario == null){
            System.out.println("NUEVO FORM");
            usuario = new Usuario();
        }
        Button btnSave = new Button("Guardar");

        btnSave.addClickListener(buttonClickEvent -> {
            try {
                binder.writeBean(usuario);
                // A real application would also save
                // the updated person
                // using the application's backend
                System.out.println(usuario);
                Guardar();
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        });
        FlexLayout fl = new FlexLayout();
        fl.add(btnSave);
        //add(fl);
        //add(fl,4);
        Nav nav = new Nav();
        nav.setTitle("DAFSDAFDSF");
        add(fl);
        body();
        binder.readBean(usuario);
        getElement().getStyle().set("margin","25px");
    }

    private void body(){
        Component i = null;
        Class c = Usuario.class;
        Field[] l = c.getDeclaredFields();
        for(Field temp : l){
            System.out.println(temp.getGenericType().getTypeName());
            System.out.println(temp.getName());
        }
        l = c.getFields();
        for(Field temp : l){
            System.out.println(temp.getGenericType().getTypeName());
            System.out.println(temp.getName());
        }
        i = ComponentUtils.getField("Primer Nombre","primerNombre",1,true);
        binder.bind((HasValue)i,Usuario::getPrimerNombre,Usuario::setPrimerNombre);
        add(i);

        i = ComponentUtils.getField("Segundo Nombre","segundoNombre",1,true);
        binder.bind((HasValue)i,Usuario::getSegundoNombre,Usuario::setSegundoNombre);
        add(i);

        i = ComponentUtils.getField("Primer Apellido","primerApellido",1,true);
        binder.bind((HasValue)i,Usuario::getPrimerApellido,Usuario::setPrimerApellido);
        add(i);

        i = ComponentUtils.getField("Segundo Apellido","segundoApellido",1,true);
        binder.bind((HasValue)i,Usuario::getSegundoApellido,Usuario::setSegundoApellido);
        add(i);

        i = ComponentUtils.getField("Email","email",1,true);
        binder.bind((HasValue)i,Usuario::getEmail,Usuario::setEmail);
        add(i);

        i = ComponentUtils.getField("Telefono","telefono",2,true);
        binder.bind((HasValue)i,Usuario::getTelefono,Usuario::setTelefono);
        add(i);

        i = ComponentUtils.getField("Acepta","acepta",3,true);
        binder.bind((HasValue)i,Usuario::isAcepTerminos,Usuario::setAcepTerminos);
        //binder.readBean(usuario);
        add(i);

        i = ComponentUtils.getField("Fecha","fecha",4,true);
        binder.bind((HasValue)i,Usuario::getFecha,Usuario::setFecha);
        //binder.readBean(usuario);
        add(i);

    }



    private void Guardar(){
        try{
            System.out.println(servicio.save(usuario));
            UI.getCurrent().navigate(UsuarioList.class);
        }catch (Exception e){

        }
    }

}
