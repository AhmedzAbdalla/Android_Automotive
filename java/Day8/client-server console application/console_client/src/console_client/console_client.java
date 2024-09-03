/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console_client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asem
 */
public class console_client {

    /**
     * @param args the command line arguments
     */
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    public static void main(String[] args) {
        // TODO code application logic here
        new console_client();
    }
    public console_client(){
        try {
            mySocket = new Socket("127.0.0.1",5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
            ps.println("Hellor from client");
            String replyMsg = dis.readLine();
            System.out.println("client: "+replyMsg);
        } catch (IOException ex) {
            Logger.getLogger(console_client.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try{
                ps.close();
                dis.close();
                mySocket.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
    }
    
}
