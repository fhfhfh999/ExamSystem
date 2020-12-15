package server;

import model.UserMessage;

import java.io.IOException;

/**
 * 实现服务器的主功能
 */
public class ServerView {

    private ServerInitClose serverInitClose;
    private ServerDao sd;

    public ServerView(ServerInitClose serverInitClose, ServerDao sd){
        this.serverInitClose = serverInitClose;
        this.sd = sd;
    }

    public void serverReceive() throws IOException, ClassNotFoundException {
        UserMessage tum = (UserMessage)serverInitClose.getOis().readObject();
        System.out.println("接收到的消息是：" + tum);
        // 调用方法实现管理员账号和密码信息的校验
        if (sd.serverManagerCheck(tum.getUser())){
            tum.setType("success");
        } else {
            tum.setType("fail");
        }
        serverInitClose.getOos().writeObject(tum);
        System.out.println("服务器发送校验结果成功");
    }
}
