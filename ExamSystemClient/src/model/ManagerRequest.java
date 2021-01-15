package model;

import java.io.Serializable;
import java.util.List;

public class ManagerRequest implements Serializable {

    private static final long serialVersionUID = -3317567401823454175L;
    private String requestType = null;
    private boolean successfulTranslate = false;
    /**
     * 已有请求类型:
     * 增加学员 addUser
     * 删除学员 deleteUser
     * 修改学员 changeUser
     * 查询学员 showAllUsers
     * 增加试题 addQuestion
     * 删除试题 deleteQuestion
     * 修改试题 changeQuestion
     * 查询试题 showAllQuestions
     * 导入试题 leadIn
     * 退出    exit
     */
    private User user = null;

    private List<User> users = null;

    private Question question = null;

    private List<Question> questions = null;

    public ManagerRequest() {
    }

    public ManagerRequest(String requestType) {
        this.requestType = requestType;
    }

    public boolean isSuccessfulTranslate() {
        return successfulTranslate;
    }

    public void setSuccessfulTranslate(boolean successfulTranslate) {
        this.successfulTranslate = successfulTranslate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "ManagerRequest{" +
                "requestType='" + requestType + '\'' +
                ", user=" + user +
                ", users=" + users +
                ", question=" + question +
                ", questions=" + questions +
                '}';
    }
}
