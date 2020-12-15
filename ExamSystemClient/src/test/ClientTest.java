package test;

import client.ClientInitClose;
import client.ClientScanner;
import client.ClientView;

import java.io.IOException;

public class ClientTest {

    public static void main(String[] args) {

        ClientInitClose clientInitClose = null;
        try {
            clientInitClose = new ClientInitClose();
            clientInitClose.clientInit();
            // 加载界面
            ClientView cv = new ClientView(clientInitClose);
            cv.clientMainPage();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientInitClose.clientClose();
                ClientScanner.closeScanner();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
