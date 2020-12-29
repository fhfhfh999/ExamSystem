package client;

import model.User;

import java.util.Scanner;

public class UserView {
    private User user = null;

    Scanner input = ClientScanner.getScanner();

    public UserView() {
    }

    public UserView(User user){
        this.user = user;
    }

    public void startUserPage() {
        while (true) {
            System.out.println("\t\t\t\t\t 学员系统界面 \t\t\t\t\t");
            System.out.println("1. 修改密码");
            System.out.println("2. 参加考试");
            System.out.println("3. 导出成绩");
            System.out.println("4. 查询成绩");
            System.out.println("5. 退出");
            switch (input.nextInt()) {
                case 1:
                    System.out.println("请输入修改后的密码：");
                    user.setPassword(input.next());
                    ////////////////////////////////////////////////////////需要将修改后的密码发回服务器//////////////////////////////////////////////////////
                    System.out.println("密码修改成功");
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
                case 5:
                    System.out.println("退出");
                    return;
                default:
                    System.out.println("输入错误，请重新选择");
            }
        }
    }
}
