package client;

import model.User;
import model.UserRequest;

import java.io.IOException;
import java.util.Scanner;

public class UserView {
    private User user = null;
    private ClientInitClose cic;

    Scanner input = ClientScanner.getScanner();

    public UserView() {
    }

    public UserView(User user, ClientInitClose cic){
        this.user = user;
        this.cic = cic;
    }

    public void startUserPage() throws IOException, ClassNotFoundException {
        UserRequest request = null;
        while (true) {
            System.out.println("\t\t\t\t\t 学员系统界面 \t\t\t\t\t");
            System.out.println("1. 修改密码");
            System.out.println("2. 参加考试");
            System.out.println("3. 导出成绩");
            System.out.println("4. 查询成绩");
            System.out.println("5. 退出");
            /**
             * 由于以下每项指令差距过大,考虑两种方法来发送数据
             * 方法一: 在发送给服务器的(UserRequest)request里面增加一项用于储存密码之类的数据,type在发送前确定,发送前发送后都不发生改变
             * 接收到服务器的消息后统一识别type,并打印出不同的type对应的结果
             * 方法二: 在每个case下详细写发送和接收消息及反应方式,因此每次从服务器返回无需进行二次判断,而是在同一个case下进行
             * 代码会变得冗杂
             * 修改成方法一,从服务器返回的request部分在试题系统完善后需要包含考试试题,目前的request不满足要求
             */
            int type = input.nextInt();
            switch (type) {
                case 1:
                    System.out.println("请输入修改后的密码：");
                    user.setPassword(input.next());
                    ////////////////////////////////////////////////////////需要将修改后的密码发回服务器//////////////////////////////////////////////////////
                    request = new UserRequest("changePassword", user);
                    // 将修改后的内容发送到服务器
                    break;
                case 2:
                    System.out.println("参加考试");
                    request = new UserRequest("takeExam", user);
                    /**
                     * 如果最终功能不包含"针对不同的学员予以不同的考试试题,此处可以不将user传至服务器,直接设置requestType,然后等待返回的考试试题
                     * 如果确认不传回user的信息,则ServerUserView中tum不包含user,需要修改
                     */
                    break;
                case 3:
                    System.out.println("导出成绩");
                    request = new UserRequest("getScore", user);
                    break;
                case 4:
                    System.out.println("查询成绩");
                    request = new UserRequest("findScore", user);
                    break;
                case 5:
                    System.out.println("退出");
                    request.setRequestType("exit");
                    cic.getOos().writeObject(request);
                    return;
                default:
                    System.out.println("输入错误，请重新选择");

            }
            cic.getOos().writeObject(request);
            System.out.println("客户端成功发送信息");
            request = (UserRequest)cic.getOis().readObject();
            System.out.println("客户端接收到服务器的回复");
            switch (type){
                case 1:
                    if (request.isSuccessfulTranslate()){
                        System.out.println("密码修改成功");
                    }else {
                        System.out.println("密码修改失败");
                        // 考虑到该界面在已登录后呈现,不考虑该账号不存在的情况
                    }
                    break;
                case 2:
                    System.out.println("参加考试");
                    break;
                case 3:
                    System.out.println("导出成绩");
                    break;
                case 4:
                    System.out.println("查询成绩");
                    break;
                default:
                    System.out.println("输入错误，请重新选择");
            }

        }
    }
}
