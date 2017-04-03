package ru.andrei.tsystemsverificationwork.web.editors;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String value) {

        if (value != null) {
            try {
                this.setValue(new SimpleDateFormat("dd.MM.yyyy").parse(value));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getAsText() {
        if (getValue() != null)
            return new SimpleDateFormat("dd.MM.yyyy").format((Date) getValue());
        else return "";
    }

}
