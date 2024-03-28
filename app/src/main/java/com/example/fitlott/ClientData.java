package com.example.fitlott;

public class ClientData {

    String clientEmail;
    String clientName;

    public ClientData(String clientName, String clientEmail){
        this.clientEmail = clientEmail;
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }
    public String getClientName() {
        return clientName;
    }
}
