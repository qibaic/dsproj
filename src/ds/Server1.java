package ds;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server1 {


    public static void main(String[] args) throws IOException {
        ServerSocket server1 = null;
        try{
            server1 = new ServerSocket(6000); // 监听指定端口
            System.out.println("server is running...");
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (;;) {
            try {
                Socket clinetSock1 = server1.accept();
                System.out.println("connected from " + clinetSock1.getRemoteSocketAddress());
                Thread t1 = new Handler(clinetSock1);
                t1.start();
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

    }
}

class Handler extends Thread {
    Socket sock;
    private int sum1 = 0; // 到时候做三个sum，一个thread做一个sum
    public Handler(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try (InputStream input = this.sock.getInputStream()) {
            try (OutputStream output = this.sock.getOutputStream()) {
                handle(input, output);
            }
        } catch (Exception e) {
            try {
                this.sock.close();
            } catch (IOException ioe) {
                System.out.println("client disconnected.");
            }

        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        writer.write("hello\n");
        writer.flush();
        for (;;) {
            String s = reader.readLine();
            if (s == null) {
                writer.write("  " + "wait for server" + "\n");
                writer.flush();
            }else {
                int deposit = Integer.parseInt(s);
                sum1 += deposit;
                writer.write("  " + String.valueOf(sum1) + "\n");
                writer.flush();
            }
        }
    }
}