package test;

import server.ServerInitClose;

import java.io.IOException;

public class ServerTest {

    public static void main(String[] args) {
        ServerInitClose sic = null;
        try {
            //声明ServerInitClose类型的引用指向该类型的对象
            sic = new ServerInitClose();
            //调用成员方法实现服务器的初始化操作
            sic.serverInit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 调用成员方法实现服务器的关闭操作
                sic.serverClose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
