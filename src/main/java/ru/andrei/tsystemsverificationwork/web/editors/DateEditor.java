package ru.andrei.tsystemsverificationwork.web.editors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * String to date mapper
 */
public class DateEditor extends PropertyEditorSupport {


    /**
     * SLF4J logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateEditor.class);

    @Override
    public void setAsText(String value) {

        if (value != null) {
            try {
                this.setValue(new SimpleDateFormat("dd.MM.yyyy").parse(value));
            } catch (ParseException e) {
                LOGGER.info(e.getMessage());
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
