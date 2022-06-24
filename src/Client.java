import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class JThread extends Thread {

    JThread(String name) {
        super(name);
    }

    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
    }
}

public class Client {

    public static void main(String[] args) {
        new JThread("JThread").start();
        String host = "127.0.0.1";
        int port = 8080;

        try (Socket clientSocket = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("java student");
            String receiving = in.readLine();
            System.out.println(receiving);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}