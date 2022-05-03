package org.vaadin.example.Backend.Factory.Forms.base;

import com.vaadin.flow.component.Component;
import org.vaadin.example.Backend.Components.Forms.FormComponente;

public abstract class FormBase {
    private FormComponente formComponente;

    public void renderizarForm(String title){
        formComponente = crearFormComponent();
        formComponente.crear(title);
    }

    public abstract FormComponente crearFormComponent();

    public void setValue(Object object){
        formComponente.setValue(object);
    }

    public Object getValue(){
        return formComponente.getValue();
    }

    public void setObligatoriedad(boolean obligatoriedad){
        formComponente.obligatoriedad(obligatoriedad);
    }

    public void setVisible(boolean visible){
        formComponente.setVisible(visible);
    }

    public Component getFormComponent(){
        return formComponente.getFormComponent();
    }
}
