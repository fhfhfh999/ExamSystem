package server;

import model.ManagerRequest;
import model.User;

import java.io.IOException;
import java.util.List;

public class ServerManagerView {

    private ServerInitClose serverInitClose;
    private ServerDao sd;

    private User user = null;
    private List<User> users = UserInfo.getUsers();

    public ServerManagerView(ServerInitClose serverInitClose, ServerDao sd) {
        this.serverInitClose = serverInitClose;
        this.sd = sd;
    }

    public void receiveMessage() throws IOException, ClassNotFoundException {
        while(true) {
            ManagerRequest tum = (ManagerRequest) serverInitClose.getOis().readObject();
            System.out.println("接收到的消息:" + tum);
            String type = tum.getRequestType();
            if ("addUser".equals(type)) {
                user = tum.getUser();
                if (!users.contains(user)) {
                    users.add(user);
                    System.out.println("添加用户:" + user);
                    tum.setSuccessfulTranslate(true);
                    System.out.println("现有学员:" + users);
                }
            } else if ("deleteUser".equals(type)) {
                user = tum.getUser();
                String name = user.getUserName();
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserName().equals(name)) {
                        users.remove(i);
                        tum.setSuccessfulTranslate(true);
                        System.out.println("用户" + user.getUserName() + "已删除");
                    }
                }
            } else if ("changeUser".equals(type)) {
                user = tum.getUsers().get(0);
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserName().equals(user.getUserName()) && users.get(i).getPassword().equals(user.getPassword())) {
                        users.remove(i);
                        users.add(tum.getUsers().get(1));
                        tum.setSuccessfulTranslate(true);
                        System.out.println("用户信息已修改");
                    }
                }
            } else if ("showAllUsers".equals(type)) {
                System.out.println("展示所有学员:" + users);
                tum.setUsers(users);
                tum.setSuccessfulTranslate(true);
            } else if ("addQuestion".equals(type)) {
                System.out.println("该功能暂未完成");
            } else if ("deleteQuestion".equals(type)) {
                System.out.println("该功能暂未完成");
            } else if ("changeQuestion".equals(type)) {
                System.out.println("该功能暂未完成");
            } else if ("showAllQuestions".equals(type)) {
                System.out.println("该功能暂未完成");
            } else if ("leadIn".equals(type)) {
                System.out.println("该功能暂未完成");
            } else if ("exit".equals(type)){
                UserInfo.saveUserInfo();
                System.out.println("退出");
                return;
            }
            serverInitClose.getOos().writeObject(tum);
            serverInitClose.getOos().reset();
            System.out.println("服务器返回消息给客户端");
        }
    }
}
