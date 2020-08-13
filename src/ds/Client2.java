package ds;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket sock1 = new Socket("localhost", 6000); // 连接指定服务器和端口
        Socket sock2 = new Socket("localhost", 6001); // 连接指定服务器和端口
        try (InputStream input1 = sock1.getInputStream(); InputStream input2 = sock2.getInputStream()) {
            try (OutputStream output1 = sock1.getOutputStream(); OutputStream output2 = sock2.getOutputStream()) {
                handle(input1, input2, output1, output2);
            }
        }
        sock1.close();
        sock2.close();
        System.out.println("disconnected.");
    }


    private static void handle(InputStream input1, InputStream input2, OutputStream output1, OutputStream output2)
            throws IOException, InterruptedException {
        BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(output1, StandardCharsets.UTF_8));
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(input1, StandardCharsets.UTF_8));
        BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(output2, StandardCharsets.UTF_8));
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(input2, StandardCharsets.UTF_8));
        System.out.println("[server] " + reader1.readLine());
        System.out.println("[server] " + reader2.readLine());
        while (true) {
            Thread.sleep(3000);
            writer1.write("5");
            writer1.newLine();
            writer1.flush();
            String resp1 = reader1.readLine();
            System.out.println("current money: " + resp1);
            if (resp1.equals("bye")) {
                break;
            }
            writer2.write("5");
            writer2.newLine();
            writer2.flush();
            String resp2 = reader2.readLine();
            System.out.println("current money: " + resp2);
            if (resp2.equals("bye")) {
                break;
            }
        }
    }
}