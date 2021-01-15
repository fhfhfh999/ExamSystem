package server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserInfo {
    private static List<User> users = null;

    public static List<User> getUsers(){
        if (null == users){
            users = new ArrayList<>();
        }
        return users;
    }

    public static void saveUserInfo() {
        ObjectOutputStream oos = null;
        File file = new File("StudentInfo.txt");
        if (!file.exists()){
            file.mkdir();
        }
        try {
            oos = new ObjectOutputStream(new FileOutputStream("StudentInfo.txt", false));
            oos.writeObject(users);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != oos){
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadUserInfo() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("StudentInfo.txt"));
            Object obj = null;
            try {
                obj = ois.readObject();
                if (obj instanceof List) users = (List<User>) obj;
            } catch (ClassNotFoundException e) {
                users = null;
                System.out.println("无已保存的用户列表");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ois) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
