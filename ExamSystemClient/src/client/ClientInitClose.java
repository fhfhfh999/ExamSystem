package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientInitClose {

    Socket s = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;

    public void clientInit() throws IOException {
        // 创建Socket类型的对象并指定服务器的通信地址和端口号
        s = new Socket(InetAddress.getLocalHost(), 8888);
        System.out.println("连接服务器成功");
        // 使用输入输出流通信
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        System.out.println("服务器连接成功");
        // 关闭Socket并释放有关的资源
    }

    public void clientClose() throws IOException {
        ois.close();
        oos.close();
        s.close();
        System.out.println("客户端关闭");
    }
}
