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
        while(true) {
            UserMessage tum = (UserMessage) serverInitClose.getOis().readObject();
            System.out.println("接收到的消息是：" + tum);
            String type = tum.getType();
            if ("manageCheck".equals(type)) {
                // 调用方法实现管理员账号和密码信息的校验
                if (sd.serverManagerCheck(tum.getUser())) {
                    tum.setType("success");
                } else {
                    tum.setType("fail");
                }
            } else if ("userCheck".equals(type)) {
                // 调用方法实现用户账号和密码信息的校验
                if (sd.serverUserCheck(tum.getUser())) {
                    tum.setType("success");
                } else {
                    tum.setType("fail");
                }
            } else if ("exit".equals(type)) {
                System.out.println("退出");
                return;
            } else {
                // 既不是manageCheck也不是UserCheck，说明出现错误（应该不会出现这个结果）
                System.out.println("出错");
            }

            serverInitClose.getOos().writeObject(tum);
            System.out.println("服务器发送校验结果成功");
            if ("manageCheck".equals(type)) {
                ServerManagerView managerView = new ServerManagerView(serverInitClose, sd);
                managerView.receiveMessage();
            } else if ("userCheck".equals(type)) {
                ServerUserView userView = new ServerUserView(serverInitClose, sd);
                userView.receiveMessage();
            }
        }
    }
}
