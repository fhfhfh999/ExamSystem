package model;

import java.io.Serializable;

public class UserRequest implements Serializable {
    private static final long serialVersionUID = 4603676162710345936L;

    private String requestType = null; // 用于识别发送请求类型
    /**
     * 已有的发送请求类型:
     * 1. 修改密码       changePassword
     * 2. 参加考试       takeExam
     * 3. 导出成绩       getScore
     * 4. 查询成绩       findScore
     * 5. 退出          exit
     */
    private User user;

    private boolean successfulTranslate = false;

    private Object Exam;

    public UserRequest() {
    }

    public UserRequest(String requestType, User user) {
        this.requestType = requestType;
        this.user = user;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSuccessfulTranslate() {
        return successfulTranslate;
    }

    public void setSuccessfulTranslate(boolean successfulTranslate) {
        this.successfulTranslate = successfulTranslate;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "requestType='" + requestType + '\'' +
                ", user=" + user +
                '}';
    }
}
