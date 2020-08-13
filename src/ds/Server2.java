package ds;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server2 {

    public static void main(String[] args) throws IOException {
        ServerSocket server2 = null;

        try{
            server2 = new ServerSocket(6001); // 监听指定端口
            System.out.println("server is running...");
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        for (;;) {
            try {
                Socket clinetSock2 = server2.accept();
                System.out.println("connected from " + clinetSock2.getRemoteSocketAddress());
                Thread t2 = new Handler(clinetSock2);
                t2.start();
            }catch(IOException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
//
//class Handler extends Thread {
//    int portNum;
//    Socket sock = null;
//    private int sum1 = 0;
//    public Handler(int portNum) {
//        this.portNum = portNum;
//    }
//
//    @Override
//    public void run() {
//        ServerSocket ss = null; // 监听指定端口
//        try {
//            ss = new ServerSocket(portNum);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("server is running...");
//        for (;;) {
//            try {
//                sock = ss.accept();
//                System.out.println("connected from " + sock.getRemoteSocketAddress());
//                prepare((sock));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    private void prepare(Socket sock){
//        try (InputStream input = sock.getInputStream()) {
//            try (OutputStream output = sock.getOutputStream()) {
//                handle(input, output);
//            }
//        } catch (Exception e) {
//            try {
//                sock.close();
//            } catch (IOException ioe) {
//            }
//            System.out.println("client disconnected.");
//        }
//    }
//
//    private void handle(InputStream input, OutputStream output) throws IOException {
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
//        writer.write("hello\n");
//        writer.flush();
//        for (;;) {
//            String s = reader.readLine();
//            if (s.equals("bye")) {
//                writer.write("bye\n");
//                writer.flush();
//                break;
//            }
//            if (s.equals("Are you alive")) {
//                writer.write("I am alive\n");
//                writer.flush();
//                break;
//            }
//            int deposit = Integer.parseInt(s);
//            sum1 += deposit;
//            writer.write("  " + String.valueOf(sum1) + "\n");
//            writer.flush();
//        }
//    }
//}