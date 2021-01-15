package client;

import model.User;
import model.UserMessage;

import java.io.IOException;

public class ClientView {

    /**
     * 为了可以使用输入输出流，采用合成复用原则实现
     */
    private ClientInitClose cic;

    /**
     * 通过构造方法实现成员变量的初始化
     * @param cic
     */
    public ClientView(ClientInitClose cic){
        this.cic = cic;
    }
    /**
     * 自定义成员方法实现客户端主界面绘制
     * */

    public void clientMainPage() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("  \n\n\t\t\t在线考试系统");
            System.out.println("----------------------------------------------");
            System.out.print("   [1] 学员系统");
            System.out.println("     [2] 管理员系统");
            System.out.println("   [0] 退出系统");
            System.out.println("-----------------------------------------------");
            System.out.println("请选择要进行的业务编号：");
            int choose = ClientScanner.getScanner().nextInt();
            switch (choose) {
                case 1:
                    System.out.println("正在进入学员系统");
                    clientUserLogin();
                    break;
                case 2:
                    //System.out.println("正在进入管理员系统");
                    clientManagerLogin();
                    break;
                case 0:
                    exit();
//                    System.out.println("正在退出系统");
                    return;
                default:
                    System.out.println("输入错误，请重新选择");
            }
        }
    }

    private void exit() throws IOException {
        System.out.println("退出系统");
        UserMessage tum = new UserMessage();
        tum.setType("exit");
        cic.getOos().writeObject(tum);
        System.out.println("客户端退出");
    }

    /**
     * 自定义成员方法实现客户端管理员登录
     */
    private void clientManagerLogin() throws IOException, ClassNotFoundException {
        // 准备管理员信息
        System.out.println("请输入管理员的账号");
        String username = ClientScanner.getScanner().next();
        System.out.println("请输入管理员的密码信息");
        String password = ClientScanner.getScanner().next();
        UserMessage tum = new UserMessage("manageCheck", new User(username, password));
        //将UserMessage类型对象通过对象输出流发送给服务器
        cic.getOos().writeObject(tum);
        System.out.println("客户端发送管理员账户信息");
        //接收服务器的处理结果并给出提示
        tum = (UserMessage)cic.getOis().readObject();
        if ("success".equals(tum.getType())){
            System.out.println("管理员登录成功");
            ManagerView managerView = new ManagerView(tum.getUser(), cic); //进入管理员界面
            managerView.startManagerPage();
        }else {
            System.out.println("用户名或密码错误");
        }
    }

    private void clientUserLogin() throws IOException, ClassNotFoundException {
        System.out.println("请输入用户账户信息");
        String username = ClientScanner.getScanner().next();
        System.out.println("请输入用户密码");
        String password = ClientScanner.getScanner().next();
        UserMessage tum = new UserMessage("userCheck", new User(username, password));
        // 将用户信息发送给服务器
        cic.getOos().writeObject(tum);
        System.out.println("客户端发送用户账户信息");
        //接收服务器处理结果并给出提示
        tum = (UserMessage)cic.getOis().readObject();
        if ("success".equals(tum.getType())){
            System.out.println("用户登陆成功");
            UserView userView = new UserView(tum.getUser(), cic);
            userView.startUserPage();
        }else {
            System.out.println("用户名或密码错误");
        }
    }
}
