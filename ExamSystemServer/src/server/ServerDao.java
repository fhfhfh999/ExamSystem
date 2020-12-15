package server;

import model.User;

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
        return "admin".equals(user.getUserName()) && "123456".equals(user.getPassword());
    }
}
