/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynotepad_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

/**
 *
 * @author hamad
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextArea MyTextArea;
    private DialogPane dialogPane;
    //private Dialog MyDialog;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    FileChooser myfilechooser = new FileChooser();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createNewFile(ActionEvent event) {
        MyTextArea.clear();
    }

    @FXML
    private void openFile(ActionEvent event) {

        File file = myfilechooser.showOpenDialog(null);
        if(null != file)
        {
            try
            {
                FileInputStream FIS = new FileInputStream(file);
                /*Returns an estimate of the number of remaining bytes that can be read (or skipped over) from this input stream
                without blocking by the next invocation of a method for this input stream.*/
                int size = FIS.available();
                byte [] buff = new  byte [size];
                FIS.read(buff);
                MyTextArea.setText(new String(buff));
                FIS.close();
            }   
            
            catch(FileNotFoundException e)
            {
                  e.printStackTrace();
            }
            catch(IOException e)
            {
                  e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveFile(ActionEvent event) {
        File file = myfilechooser.showSaveDialog(null);
        if(null != file)
        {
            try
            {
                FileOutputStream FOS = new FileOutputStream(file);
                
                byte[] size = MyTextArea.getText().getBytes();
                //byte [] buff = new  byte [size];
                FOS.write(size);
                FOS.close();
            }   
            
            catch(FileNotFoundException e)
            {
                  e.printStackTrace();
            }
            catch(IOException e)
            {
                  e.printStackTrace();
            }
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void openFileHigh(ActionEvent event) {
        File file = myfilechooser.showOpenDialog(null);
        if(null != file)
        {
            try
            {
                FileInputStream FIS = new FileInputStream(file);
                DataInputStream DIS = new DataInputStream(FIS);
                
                int size = (int) file.length();
                
                byte [] buff = new byte[size];
                int len = DIS.read(buff);
                
               String str =  new String(buff , 0, len);
                MyTextArea.setText(str);
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @FXML
    private void saveFileHigh(ActionEvent event) {
        File file = myfilechooser.showSaveDialog(null);
        if(null != file)
        {
            try
            {
                FileOutputStream FIS = new FileOutputStream(file);
                DataOutputStream DOS = new DataOutputStream(FIS);
                
                
                byte[] buff = MyTextArea.getText().getBytes();
                
                String str =  new String(buff);
                
                DOS.writeBytes(str);
                
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            
        }
    }

    @FXML
    private void cutHandle(ActionEvent event) {
        MyTextArea.cut();
    }

    @FXML
    private void copyHandle(ActionEvent event) {
        MyTextArea.copy();
    }

    @FXML
    private void pasteHandle(ActionEvent event) {
        MyTextArea.paste();
    }

    @FXML
    private void deleteHandle(ActionEvent event) {
        MyTextArea.deleteText(MyTextArea.getSelection());
    }

    @FXML
    private void selectAllHandle(ActionEvent event) {
        MyTextArea.selectAll();
    }

    @FXML
    private void showAbout(ActionEvent event) {
      //MyDialog.show();
      
      //dialogPane.setVisible(true);
      
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setHeaderText("Program Info");
       alert.setContentText("Version: NotePad 1.0 \nThank you");
       alert.setResizable(true);
       alert.show();
        
    }
    
}
