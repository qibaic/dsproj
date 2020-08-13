package ds;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Lfd1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        while(true){
            if (isAlive(6000)){
                System.out.println("server is alive");
            }else{
                System.out.println("Waiting for server");
            }
            Thread.sleep(2000);
        }
    }

    public static boolean isAlive(int portNum) throws IOException, InterruptedException {
        Boolean isAlive = false;
        SocketAddress socketAddress = new InetSocketAddress("localhost", portNum);
        Socket sock = new Socket();
        try{
            sock.connect(socketAddress);
            isAlive = true;
            sock.close();
        }catch (IOException e){
        }
        return isAlive;
    }
}