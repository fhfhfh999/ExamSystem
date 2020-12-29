package server;

import model.User;

import java.util.List;

/**
 * 数据的存取
 */
public class ServerDao {

    /**
     * 校验管理员账号和密码
     * @param user
     * @return
     */
    public boolean serverManagerCheck(User user){
        // 管理员账户 admin 密码 123456
        return "admin".equals(user.getUserName()) && "123456".equals(user.getPassword());
    }

    public boolean serverUserCheck(User user){
        // 以下内容用于检查客户数据
        List users = UserInfo.getUsers();
        return users.contains(user);
    }
}
