package client;

import model.ManagerRequest;
import model.User;
import model.UserRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerView {
    private ClientInitClose cic;

    private User user = null;

    Scanner input = ClientScanner.getScanner();
    private List<User> users = null;


    public ManagerView() {
    }

    public ManagerView(User user, ClientInitClose cic){
        this.user = user;
        this.cic = cic;
    }

    public void startManagerPage() throws IOException, ClassNotFoundException {
        ManagerRequest request = null;
        while (true) {
            System.out.println("\t\t\t\t\t 管理员系统界面 \t\t\t\t\t");
            System.out.println("1. 添加学员");
            System.out.println("2. 删除学员");
            System.out.println("3. 修改学员信息");
            System.out.println("4. 查询学员信息");
            System.out.println("5. 增加试题");
            System.out.println("6. 删除试题");
            System.out.println("7. 修改试题");
            System.out.println("8. 查询试题");
            System.out.println("9. 导入试题");
            System.out.println("0. 退出");
            int type = input.nextInt();
            switch (type) {
                case 1:
                    System.out.println("请输入学员的账号:");
                    String username = input.next();
                    System.out.println("请输入学员密码：");
                    String password = input.next();
                    user = new User(username, password);
                    request = new ManagerRequest("addUser");
                    request.setUser(user);
                    ////////////////////////////////////////新的User要发送到服务器储存////////////////////////////////////////////
                    break;
                case 2:
                    System.out.println("请输入要删除的学员账号:");
                    username = input.next();
                    user = new User(); // 用于储存要删除的学员账号号码
                    user.setUserName(username); // 删除对象不需要密码,故直接set name
                    request = new ManagerRequest("deleteUser");
                    request.setUser(user);
                    //////////////////////////////////////向服务器查询,如果不存在返回false,存在就删除并返回true//////////////////////////
                    /////////////////////////////////////根据返回值打印是否成功删除学员///////////////////////////////////////////
                    break;
                case 3:
                    // 该处三种做法
                    // 一种是将所有学员信息导入,修改后再发送给服务器
                    // 第二种是将原学员信息和修改后学员信息放到一个List中,一起发给服务器(没有原学员的话无法识别,至少要有原学员的id)
                    // 第三种,使用User里面闲置的密码来完成修改,即修改学员信息放到密码里,但是没有辨识度
                    // 由于目前功能单一,理论上修改学员信息只有修改密码,修改学员id可以用删除学员再添加完成,但是为了以后更多功能,比如电话号等,决定使用第二种方法
                    System.out.println("修改学员信息");
                    String uName;
                    String uPassword;
                    System.out.println("请输入原学员信息:");
                    System.out.println("请输入原学员用户名:");
                    uName = input.next();
                    System.out.println("请输入原学员密码:");
                    uPassword = input.next();
                    user = new User(uName, uPassword);
                    users = new ArrayList<>();
                    users.add(user); // 将原学员信息放进List中(用于确认需要修改的学员,理论上可以只放用户名)
                    System.out.println("请输入新学员用户名:");
                    uName = input.next();
                    System.out.println("请输入新学员密码:");
                    uPassword = input.next();
                    System.out.println("正在修改...");
                    user = new User(uName, uPassword);
                    users.add(user); // 将修改后的学员信息放进List中
                    request = new ManagerRequest("changeUser");
                    request.setUsers(users);
                    break;
                case 4:
                    System.out.println("查询学员信息");
                    // 查询不需要发送特定的内容,但是需要接收
                    request = new ManagerRequest("showAllUsers");
                    break;
                case 5:
                    System.out.println("增加试题");
                    break;
                case 6:
                    System.out.println("删除试题");
                    break;
                case 7:
                    System.out.println("修改试题");
                    break;
                case 8:
                    System.out.println("查询试题");
                    break;
                case 9:
                    System.out.println("导入试题");
                    break;
                case 0:
                    System.out.println("退出");
                    request.setRequestType("exit");
                    cic.getOos().writeObject(request);
                    return;
                default:
                    System.out.println("输入错误，请重新选择");
                    continue;
            }
            cic.getOos().writeObject(request);
            System.out.println("客户端成功发送信息");
            request = (ManagerRequest) cic.getOis().readObject();
            System.out.println("客户端接收到服务器的回复");
            switch (type){
                case 1:
                    if (request.isSuccessfulTranslate()){
                        System.out.println("学员添加成功");
                    }else {
                        System.out.println("学员添加失败");
                    }
                    break;
                case 2:
                    if (request.isSuccessfulTranslate()){
                        System.out.println("该学员已删除");
                    }else {
                        System.out.println("学员删除失败,请检查该学员是否存在");
                    }
                    break;
                case 3:
                    if (request.isSuccessfulTranslate()){
                        System.out.println("学员修改成功");
                    }else {
                        System.out.println("学员修改失败");
                    }
                    break;
                case 4:
                    users = request.getUsers();
                    for (User u: users){
                        System.out.println("学员姓名: " + u.getUserName() + "\n学员密码:" + u.getPassword());
                    }
                    break;
                case 5:
                    System.out.println("该功能暂未完成");
                    break;
                case 6:
                    System.out.println("该功能暂未完成");
                    break;
                case 7:
                    System.out.println("该功能暂未完成");
                    break;
                case 8:
                    System.out.println("该功能暂未完成");
                    break;
                case 9:
                    System.out.println("该功能暂未完成");
                    break;
                default:
                    System.out.println("输入错误，请重新选择");
            }
        }
    }
}
