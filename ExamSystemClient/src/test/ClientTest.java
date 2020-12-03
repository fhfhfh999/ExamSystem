package test;

import client.ClientInitClose;

import java.io.IOException;

public class ClientTest {

    public static void main(String[] args) {

        ClientInitClose clientInitClose = null;
        try {
            clientInitClose = new ClientInitClose();
            clientInitClose.clientInit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientInitClose.clientClose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
