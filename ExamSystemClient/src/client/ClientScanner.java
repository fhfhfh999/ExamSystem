package client;

import java.util.Scanner;

/**
 * 实现扫描器工具类的封装，可以任意位置使用
 */
public class ClientScanner {
    private static Scanner input = new Scanner(System.in);

    public static Scanner getScanner(){
        return input;
    }

    /**
     * 自定义成员方法实现扫描器的关闭
     */
    public static void closeScanner(){
        input.close();
    }
}
