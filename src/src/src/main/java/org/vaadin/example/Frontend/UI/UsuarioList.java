package org.vaadin.example.Frontend.UI;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.VaadinSessionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.Backend.Bean.Usuario;
import org.vaadin.example.Backend.Components.Tabla;
import org.vaadin.example.Backend.Servicios.Usuario.ObtenerUsuarios;
import org.vaadin.example.Backend.Utils.ComponentUtils;
import org.vaadin.example.Backend.Utils.SessionUtils;
import org.vaadin.example.Frontend.MainLayout;

@Route(value = "listaUsuario",layout = MainLayout.class)
@PageTitle("Lista de usuario")
public class UsuarioList extends FormLayout {
    @Autowired
    private ObtenerUsuarios obtenerUsuarios;
    private Tabla<Usuario> grid;
    private Usuario usuario;
    private Binder<Usuario> binder = new Binder<>();
    private FormLayout component = new FormLayout();
    public UsuarioList(){

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        body();
    }

    private void body(){
        Button btnNuevo = new Button("Agregar un nuevo registro");
        btnNuevo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnNuevo.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate(UsuarioForm.class);
        });
        grid = new Tabla<>("tabla-usuario");

        grid.addGridListener(
                grid1 -> {
                    grid1.addColumn(Usuario::getPrimerApellido).setHeader("Primer Apellido").setAutoWidth(true).setFlexGrow(0);
                    grid1.addColumn(Usuario::getPrimerNombre).setHeader("Primer Nombre").setAutoWidth(true).setFlexGrow(0);
                    grid1.addColumn(Usuario::getEmail).setHeader("Email").setAutoWidth(true).setFlexGrow(0);
                    grid1.addColumn(Usuario::getTelefono).setHeader("Telefono").setAutoWidth(true).setFlexGrow(0);
                    grid1.addSelectionListener(event-> event.getFirstSelectedItem().ifPresent(Us->{
                        binder.readBean(Us);
                        if(VaadinSession.getCurrent() == null) {
                            SessionUtils.setAttribute("UUSUARIO", Us);
                            UI.getCurrent().navigate(UsuarioForm.class);
                        }
                    }));
                }
        );
        actualizar();
        grid.render();
        VerticalLayout hl = new VerticalLayout();
        hl.add(btnNuevo,grid);
        FormLayout l = new FormLayout();
        l.add(hl);
        l.add(hl,2);


        Component i = null;
        i = ComponentUtils.getField("Primer Nombre","primerNombre",1,true);
        binder.bind((HasValue)i,Usuario::getPrimerNombre,Usuario::setPrimerNombre);
        component.add(i);
        component.add(i,1);

        i = ComponentUtils.getField("Segundo Nombre","segundoNombre",1,true);
        binder.bind((HasValue)i,Usuario::getSegundoNombre,Usuario::setSegundoNombre);
        component.add(i);
        component.add(i,1);

        i = ComponentUtils.getField("Primer Apellido","primerApellido",1,true);
        binder.bind((HasValue)i,Usuario::getPrimerApellido,Usuario::setPrimerApellido);
        component.add(i);
        component.add(i,1);

        i = ComponentUtils.getField("Segundo Apellido","segundoApellido",1,true);
        binder.bind((HasValue)i,Usuario::getSegundoApellido,Usuario::setSegundoApellido);
        component.add(i);
        component.add(i,1);

        i = ComponentUtils.getField("Email","email",1,true);
        binder.bind((HasValue)i,Usuario::getEmail,Usuario::setEmail);
        component.add(i);
        component.add(i,1);

        i = ComponentUtils.getField("Telefono","telefono",2,true);
        binder.bind((HasValue)i,Usuario::getTelefono,Usuario::setTelefono);
        component.add(i);
        component.add(i,1);

        i = ComponentUtils.getField("Acepta","acepta",3,true);
        binder.bind((HasValue)i,Usuario::isAcepTerminos,Usuario::setAcepTerminos);
        //binder.readBean(usuario);
        component.add(i);
        component.add(i,1);

        i = ComponentUtils.getField("Fecha","fecha",4,true);
        binder.bind((HasValue)i,Usuario::getFecha,Usuario::setFecha);
        //binder.readBean(usuario);
        component.add(i);
        component.add(i,1);

        FormLayout fl = new FormLayout();

        fl.add(hl);
        fl.add(hl,2);
        fl.add(component);
        fl.add(component,1);
        add(fl);
    }

    private void actualizar(){
        try{
            grid.llenar(obtenerUsuarios.obtenerUsuarios());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
