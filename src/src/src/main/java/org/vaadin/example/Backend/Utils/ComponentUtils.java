package org.vaadin.example.Backend.Utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;

public class  ComponentUtils {
    public static final Component getField(String fieldText, String name, Integer typeField, boolean isRequired){
        FormLayout temp = new FormLayout();
        if(typeField == 1){
            TextField primerNombre = new TextField(fieldText);
            primerNombre.setRequiredIndicatorVisible(isRequired);
            primerNombre.setRequired(isRequired);
            primerNombre.setId(name);
            primerNombre.addValueChangeListener(event->{
                primerNombre.setValue(event.getValue().toUpperCase());

            });

            temp.add(primerNombre);
            temp.add(primerNombre, 2);
            return primerNombre;
        }else if(typeField == 2){
            IntegerField telefono = new IntegerField(fieldText);
            telefono.setMax(99999999);
            telefono.setMin(10000000);
            telefono.setId(name);
            temp.add(telefono);
            temp.add(telefono,1);
            return telefono;
        }else if(typeField == 3){
            Checkbox checkbox = new Checkbox("Acepta Terminos");
            checkbox.setId(name);
            checkbox.setRequiredIndicatorVisible(isRequired);

            temp.add(checkbox);
            temp.add(checkbox,4);
            return checkbox;
        }else if(typeField == 4){
            DatePicker date = new DatePicker("Fecha");
            return date;
        }
        return temp;
    }
}
