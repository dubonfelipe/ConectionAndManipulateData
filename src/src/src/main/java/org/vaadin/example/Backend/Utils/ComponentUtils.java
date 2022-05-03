 public static Component getComponent(Columna columna){
        Component component = null;
        if(columna.getTipoColumna() == 1){
            TextField textField = new TextField(columna.getNombreColumna());
            textField.setRequired(columna.isObligatorio());
            textField.setRequiredIndicatorVisible(columna.isObligatorio());
            textField.addValueChangeListener(event->{
               //textField.setValue(event.getValue().toUpperCase());
            });
            textField.setAutocapitalize(Autocapitalize.CHARACTERS);
            textField.setMaxLength(columna.getMax());
            component = textField;
        }else if(columna.getTipoColumna() == 2){
            IntegerField integerField = new IntegerField(columna.getNombreColumna());
            integerField.setRequiredIndicatorVisible(columna.isObligatorio());
            integerField.setMax(columna.getMax());
            component = integerField;
        }else if(columna.getTipoColumna() == 3){
            NumberField numberField = new NumberField(columna.getNombreColumna());
            numberField.setRequiredIndicatorVisible(columna.isObligatorio());
            numberField.setStep(0.01);
            numberField.setMax(columna.getMax());
            component = numberField;
        }else if(columna.getTipoColumna() == 4){
            DatePicker datePicker = new DatePicker(columna.getNombreColumna());
            datePicker.setRequired(columna.isObligatorio());
            datePicker.setRequiredIndicatorVisible(columna.isObligatorio());
            component = datePicker;
        }
        return component;
    }
