package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInitClose {

    ServerSocket ss = null;
    Socket s = null;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;

    public void serverInit() throws IOException {
        //1.创建ServerSocket类型的对象并提供端口号
        ss = new ServerSocket(8888);
        //2.等待客户端的连接请求，调用accept方法
        System.out.println("等待客户端的连接请求");
        s = ss.accept();
        System.out.println("客户端连接成功");
        //3.使用输入输出流进行通信
        ois = new ObjectInputStream(s.getInputStream());
        oos = new ObjectOutputStream(s.getOutputStream());
        System.out.println("服务器初始化成功！");
    }

    public void serverClose() throws IOException {
        //4.关闭Socket并释放有关的资源
        oos.close();
        ois.close();
        s.close();
        ss.close();
        System.out.println("服务器成功关闭");
    }
}
