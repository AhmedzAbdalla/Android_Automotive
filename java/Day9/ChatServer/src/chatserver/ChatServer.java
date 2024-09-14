/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Vector;

/**
 *
 * @author hamad
 */
public class ChatServer {

    
ServerSocket serverSocket;
public ChatServer()
{
    try{
        serverSocket = new ServerSocket(5005);
        while(true)
        {
            Socket s = serverSocket.accept();
            new ChatHandler(s);
        }
    }
    catch(IOException e)
    {
         e.printStackTrace();
    }
    
}
public static void main(String[] args)
{
    new ChatServer();
}
    
}


class ChatHandler extends Thread
{
    DataInputStream dis;
    PrintStream ps;
    static Vector<ChatHandler> clientsVector = new Vector<ChatHandler>();
    public ChatHandler(Socket cs)
    {
        try{
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            ChatHandler.clientsVector.add(this);
            start();
        }
        catch(IOException e)
        {
            e.printStackTrace();

        }
        
        
    }
    public void run()
    {
        while(true)
        {
            try{
                // remeber this is blocking function whichs means the next line will not be executed till there is a recieved data
                String str = dis.readLine();
                sendMessageToAll(str);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            
            
        }
    }
    
    void sendMessageToAll(String msg)
    {
    // for(ChatHandler ch : clientsVector)
        for(int i=0 ; i<clientsVector.size() ; i++)
        {
            clientsVector.get(i).ps.println(msg);
            
        }
    }
    
}