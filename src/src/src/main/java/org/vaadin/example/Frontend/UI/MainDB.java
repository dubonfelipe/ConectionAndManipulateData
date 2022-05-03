package org.vaadin.example.Frontend.UI;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.Backend.Bean.LovDTO;
import org.vaadin.example.Backend.Servicios.Usuario.ObtenerUsuarios;
import org.vaadin.example.Frontend.GreetService;
import org.vaadin.example.Frontend.MainLayout;

import java.util.LinkedList;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */

@Route(value="DB/Vista", layout = MainLayout.class)
@PageTitle("Vista de Datos")
public class MainDB extends VerticalLayout {
    @Autowired
    private ObtenerUsuarios obtenerUsuarios;
    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainDB(@Autowired GreetService service) {

        // Use TextField for standard text input
        TextField textField = new TextField("Usuario");
        textField.addThemeName("bordered");
        String uno = String.valueOf(VaadinSession.getCurrent().getAttribute("UNO"));
        textField.setValue(uno);
        Div div = new Div();
        // Button click listeners can be defined as lambda expressions
        Button button = new Button("Obtener Usuarios",
                e -> {Notification.show(service.greet(textField.getValue()));

        });

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_SUCCESS);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(textField, button, div);
    }

}
