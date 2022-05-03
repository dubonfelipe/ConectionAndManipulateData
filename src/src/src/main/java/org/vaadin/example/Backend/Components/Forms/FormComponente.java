package org.vaadin.example.Backend.Components.Forms;

import com.vaadin.flow.component.Component;

public interface FormComponente {
    void crear(String title);
    void setVisible(boolean visible);
    void obligatoriedad(boolean obligatoriedad);
    void setValue(Object object);
    Object getValue();
    Component getFormComponent();
}
