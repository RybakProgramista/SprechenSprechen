package com.gitcompany.sprechensprechen;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private String nickname;
    private MainFrame mainFrame;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String nickname, MainFrame mainFrame) {
        this.nickname = nickname;
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 42069);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println(nickname);

            String message;
            while ((message = in.readLine()) != null) {
                mainFrame.appendMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
                if (in != null) in.close();
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        out.println(nickname + ": " + message);
    }
}
