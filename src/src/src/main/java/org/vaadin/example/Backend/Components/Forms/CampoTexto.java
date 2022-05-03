package org.vaadin.example.Backend.Components.Forms;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.textfield.Autocapitalize;
import com.vaadin.flow.component.textfield.TextField;

public class CampoTexto implements FormComponente{
    private TextField textField;
    @Override
    public void crear(String title) {
        textField = new TextField(title);
        textField.setAutoselect(true);
        textField.setAutocapitalize(Autocapitalize.CHARACTERS);
    }

    @Override
    public void setVisible(boolean visible) {
        textField.setVisible(visible);
    }

    @Override
    public void obligatoriedad(boolean obligatoriedad) {
        textField.setRequired(obligatoriedad);
        textField.setRequiredIndicatorVisible(obligatoriedad);
    }

    @Override
    public void setValue(Object object) {
        textField.setValue(String.valueOf(object));
    }

    @Override
    public Object getValue() {
        return textField.getValue();
    }

    @Override
    public Component getFormComponent() {
        return textField;
    }
}
