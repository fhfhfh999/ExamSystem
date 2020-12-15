package test;

import server.ServerDao;
import server.ServerInitClose;
import server.ServerView;

import java.io.IOException;

public class ServerTest {

    public static void main(String[] args) {
        ServerInitClose sic = null;
        ServerDao sd = null;
        try {
            //声明ServerInitClose类型的引用指向该类型的对象
            sic = new ServerInitClose();
            sd = new ServerDao();
            //调用成员方法实现服务器的初始化操作
            sic.serverInit();
            //声明ServerView的引用指向该类型的对象
            ServerView serverView = new ServerView(sic, sd);
            serverView.serverReceive();
        } catch (IOException | ClassNotFoundException e) {
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
