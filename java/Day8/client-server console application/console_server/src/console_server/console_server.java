/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console_server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asem
 */
public class console_server {

    /**
     * @param args the command line arguments
     */
    ServerSocket myServerSocket;
    Socket s;
    DataInputStream dis;
    PrintStream ps;

    public static void main(String[] args) {
        // TODO code application logic here
        new console_server();
    }

    public console_server() {
        try {
            myServerSocket = new ServerSocket(5005);
            s = myServerSocket.accept();
            dis = new DataInputStream(s.getInputStream());
            ps = new PrintStream(s.getOutputStream());
            String msg = dis.readLine();
            System.out.println("server: "+msg);
            ps.println("Hello from Server");
        } catch (IOException ex) {
            Logger.getLogger(console_server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                dis.close();
                s.close();
                myServerSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(console_server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }
}
