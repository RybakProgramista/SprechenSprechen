/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gitcompany.sprechensprechen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kkile
 */
public class Client {
    private String SERVER_ADDRESS = "localhost";
    private int SERVER_PORT = 69420;
    
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
    public boolean canConnect(){
        try {
            clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public String sendMessage(String msg){
        try {
            out.println(msg);
            String resp = in.readLine();
            return resp;
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void stopConnection(){
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
