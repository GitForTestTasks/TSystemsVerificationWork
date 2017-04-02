package ru.tsystemsverificationwork.web.editors;

import ru.tsystemsverificationwork.database.models.Category;
import ru.tsystemsverificationwork.database.models.ClientAddress;

import java.beans.PropertyEditorSupport;

/**
 * Created by Andrei on 4/2/2017.
 */
public class ClientAddressEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        ClientAddress clientAddress = new ClientAddress();
        clientAddress.setClientAddressId(Long.parseLong(text));
        this.setValue(clientAddress);
    }

    @Override
    public String getAsText() {
        ClientAddress clientAddress = (ClientAddress) this.getValue();

        if (clientAddress == null) {
            return "";
        } else return clientAddress.toString();
    }
}
