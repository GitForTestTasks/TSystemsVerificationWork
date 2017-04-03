package ru.andrei.tsystemsverificationwork.web.editors;

import ru.andrei.tsystemsverificationwork.database.models.ClientAddress;

import java.beans.PropertyEditorSupport;


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
