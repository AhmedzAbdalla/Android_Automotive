/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author hamad
 */
public class FXMLDocumentController implements Initializable {
    
    Socket inivitation;
    DataInputStream ear;
    PrintStream mouth;
    
    private Label label;
    @FXML
    private Button sendBtn;
    @FXML
    private TextField textbox1;
    @FXML
    private TextArea textarea1;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try
        {
            inivitation = new Socket("127.0.0.1" , 5005);
            ear = new DataInputStream(inivitation.getInputStream());
            mouth = new PrintStream(inivitation.getOutputStream());
            
            new Thread(){
                @Override
                public void run() {
                    while(true)
                    {
                       try
                        {
                            String msg =  ear.readLine();
                            textarea1.appendText(msg + "\n");
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        } 
                    }
                  
                  
                }
            
            
            
            
            
            }.start();
        
        }
        catch(IOException e)
                {
                    e.printStackTrace();
                }
        
    }    

    @FXML
    private void sendAction(ActionEvent event) {
        
        if (textbox1.getText().isEmpty())
        {
            
        }
        else
        {
            String msg = textbox1.getText();
            mouth.println(msg);
            textbox1.clear();
        }
        
        
        
    }
    
}
