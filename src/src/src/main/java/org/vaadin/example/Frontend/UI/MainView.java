package org.vaadin.example.Frontend.UI;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.*;
import com.vaadin.flow.spring.SpringVaadinServletService;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;
import org.vaadin.example.Backend.DB.Conexion;
import org.vaadin.example.Backend.Reporteria.JasperReportGenerate;
import org.vaadin.example.Backend.Utils.ServletUtils;
import org.vaadin.example.Frontend.GreetService;
import org.vaadin.example.Frontend.MainLayout;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

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

@Route(value="", layout = MainLayout.class)
@PageTitle("Inicio")
public class MainView extends VerticalLayout {
    private Conexion con =null;
    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired GreetService service) {
        con = new Conexion();
        // Use TextField for standard text input
        TextField textField = new TextField("Tu nombre");
        textField.addThemeName("bordered");
        VerticalLayout hl = new VerticalLayout();
        hl.setWidthFull();
        hl.setHeightFull();
        // Button click listeners can be defined as lambda expressions
        Button button = new Button("Say hello",
                e -> {Notification.show(service.greet(textField.getValue()));
                    VaadinSession.getCurrent().setAttribute("UNO",textField.getValue());
        });
        Image image = new Image();
        image.setWidthFull();
        image.setHeightFull();
        Anchor c = new Anchor(new StreamResource("filename.pdf", ()->{
            //JasperReportGenerate report = new JasperReportGenerate("uno", VaadinServletRequest.getCurrent(),con);
            JasperReportGenerate report = new JasperReportGenerate("Intento", VaadinServletRequest.getCurrent(),con);
            InputStream inputStream = null;
            try{
                report.addParameter("SUBTITULO", "SUBTITULO DESDE CASA");
                report.addParameter("TITULO", "Mascess");
                report.addParameter("IMG",ServletUtils.getFilePath("WEB-INF/IMG/",VaadinServletRequest.getCurrent()));
                //report.addParameter("TEMP", "Mascess");
                report.execute();
                inputStream = new ByteArrayInputStream(report.getBytes());
            }catch (Exception t){t.printStackTrace();}
            System.out.println(report.getBytes().toString());

            return inputStream;
        }),"");
        c.addComponentAsFirst(new Button("DDD"));

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");
        ;

       // image.setSrc(ServletUtils.getFilePath("WEB-INF/IMG",VaadinServletRequest.getCurrent())+"/abstract.png");

        image.setWidthFull();
        image.setHeightFull();
        StreamResource imageResource = new StreamResource(
                "icon.png",
                () -> {
                    System.out.println("urle: "+getClass().getResourceAsStream("/META-INF/resources/icons/icon.png"));
                    return getClass().getResourceAsStream("/META-INF/resources/icons/icon.png");});
        hl.add(image);
        image.setSrc(imageResource);
        hl.setHeightFull();
        hl.setWidthFull();

        StreamResource pdf = new StreamResource(
                "uno.pdf",
                ()->getClass().getResourceAsStream("/REPORTS/uno.jasper")
        );
        File file = new File(pdf.toString());
        if(file.exists()){
            System.out.println("path"+file.getAbsolutePath());
        }else{
            //System.out.println("-_-:"+pdf.);
        }
                System.out.println(ServletUtils.getFilePath("resources/META-INF/resources/icons/icon.png", VaadinServletRequest.getCurrent()));


        add(textField, button,hl,c);
    }

}
