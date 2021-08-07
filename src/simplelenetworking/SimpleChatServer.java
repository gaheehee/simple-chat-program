package simplelenetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleChatServer {
    ArrayList<PrintWriter> list = new ArrayList<>();
    PrintWriter   writer;
    public void go() {
        try {
            ServerSocket server =  new ServerSocket(4242);
            while(true) {
                Socket s = server.accept(); //새로운 소켓
                echoMessages(s);
            }
        } catch(Exception ex) {

        }
    }

    private synchronized void echoMessages(Socket sock) {
        new Thread(() -> {
            BufferedReader reader;
            try {
                writer = new PrintWriter(sock.getOutputStream());
                list.add(writer);
                reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                while(true) {
                    String s = reader.readLine();
                    for( int i = 0 ; i < list.size() ; i++) {
                        PrintWriter writer = list.get(i);
                        writer.println(s);
                        writer.flush();
                    }
                }
            } catch (Exception ex) {
            }
        }).start();

    }
}