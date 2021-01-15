package server;

import model.User;
import model.UserRequest;

import java.io.IOException;
import java.util.List;

public class ServerUserView {

    private ServerInitClose serverInitClose;
    private ServerDao sd;

    public ServerUserView(ServerInitClose serverInitClose, ServerDao sd){
        this.serverInitClose = serverInitClose;
        this.sd = sd;
    }

    public void receiveMessage() throws IOException, ClassNotFoundException {
        while(true) {
            UserRequest tum = (UserRequest) serverInitClose.getOis().readObject();
            System.out.println("接收到的消息:" + tum);
            String type = tum.getRequestType();
            if ("changePassword".equals(type)) {
                for (User user : UserInfo.getUsers()) {
                    if (user.getUserName().equals(tum.getUser().getUserName())) {
                        user.setPassword(tum.getUser().getPassword());
                        tum.setSuccessfulTranslate(true);
                        System.out.println("密码修改成功");
                    }
                }
            } else if ("takeExam".equals(type)) {
                System.out.println("用户" + tum.getUser().getUserName() + "申请开始考试");
                // 然后进入考试页面
                ///////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////
                tum.setSuccessfulTranslate(true);
            } else if ("getScore".equals(type)) {
                System.out.println("用户" + tum.getUser().getUserName() + "申请导出成绩");
                tum.setSuccessfulTranslate(true);
            } else if ("findScore".equals(type)) {
                System.out.println("用户" + tum.getUser().getUserName() + "申请查询成绩");
                tum.setSuccessfulTranslate(true);
            } else if ("exit".equals(type)) {
                System.out.println("退出");
                return;
            }
            serverInitClose.getOos().writeObject(tum);
            System.out.println("消息发回");
        }
    }
}
