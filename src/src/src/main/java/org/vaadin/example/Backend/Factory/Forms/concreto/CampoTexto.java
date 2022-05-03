package org.vaadin.example.Backend.Factory.Forms.concreto;

import org.vaadin.example.Backend.Components.Forms.FormComponente;
import org.vaadin.example.Backend.Factory.Forms.base.FormBase;

public class CampoTexto extends FormBase {
    @Override
    public FormComponente crearFormComponent() {
        return new org.vaadin.example.Backend.Components.Forms.CampoTexto();
    }
}
