package org.vaadin.example.Frontend;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.PWA;
import org.vaadin.example.Frontend.UI.MainDB;
import org.vaadin.example.Frontend.UI.MainView;
import org.vaadin.example.Frontend.UI.UsuarioList;

import java.util.LinkedList;

@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")

public class MainLayout extends AppLayout
        implements RouterLayout, BeforeLeaveListener, AfterNavigationListener {
    H5 title;
    Nav nav;

    public MainLayout(){
        super.setPrimarySection(Section.DRAWER);
        super.addToNavbar(createHeaderContent());
        super.addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent(){
        title = new H5("Bienvenido");
        title.getElement().getStyle().set("magin-top","0 !important");
        DrawerToggle toggle = new DrawerToggle();
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        HorizontalLayout lh = new HorizontalLayout(toggle,title);
        Header header = new Header(lh);
        //header.addClassName("view-header");
        return header;
    }

    private Component createDrawerContent(){
        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(createNavBar());
        return section;
    }

    private Component createNavBar(){
        nav = new Nav();

        nav.add(addMenuElement(MainView.class,"Inicio",VaadinIcon.HOME));
        nav.add(addMenuElement(MainDB.class,"Datos",VaadinIcon.DATABASE));
        nav.add(addMenuElement(UsuarioList.class,"Usuarios",VaadinIcon.USERS));

        return nav;
    }

    private Component addMenuElement(Class<? extends Component> target, String title, VaadinIcon icon){
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setFlexDirection(FlexLayout.FlexDirection.ROW);
        flexLayout.add(icon.create(),new Label(title));
        flexLayout.addClickListener(flexLayoutClickEvent -> {
            UI.getCurrent().navigate(target);
            this.title.setText(title);
        });
        return flexLayout;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {

    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {

    }
}
