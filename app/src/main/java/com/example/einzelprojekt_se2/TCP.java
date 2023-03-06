package com.example.einzelprojekt_se2;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Observable;

public class TCP extends Observable implements Runnable {

    private String serverAddress;
    private int serverPort;
    private String payload;
    private String response = null;
    private TextChange txtOut = null;


    public TCP(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public TCP(String serverAddress, int serverPort, TextChange txtOut) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.txtOut = txtOut;
    }
    public void setPayload(String message) {
        payload = message;
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket(serverAddress, serverPort);

            DataOutputStream out = new DataOutputStream (clientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.US_ASCII));

            out.writeBytes(payload + "\n");

            response = in.readLine();

            out.close();
            in.close();
            clientSocket.close();

            if(txtOut != null)
                txtOut.updateText(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        return response;
    }
}
