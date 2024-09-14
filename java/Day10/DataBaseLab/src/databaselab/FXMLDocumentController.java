/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaselab;

import DAO.DAO;
import DTO.Contact;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.derby.jdbc.ClientDriver;
import java.sql.DriverManager;

/**
 *
 * @author hamad
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField TextFieldFname;
    @FXML
    private TextField TextFieldLname;
    @FXML
    private TextField TextFieldMobile;
    @FXML
    private TextField TextFieldEmail;
    @FXML
    private TextField TextFieldID;
    @FXML
    private Button BTNUpdate;
    @FXML
    private Button BTNDelete;
    @FXML
    private Button BTNFirst;
    @FXML
    private Button BTNPrevious;
    @FXML
    private Button BTNNext;
    @FXML
    private Button BTNNew;
    @FXML
    private Button BTNLast;
    @FXML
    private TextField TextFieldMname;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Contact mycontact = new Contact();
        
    }    

    @FXML
    private void handleBTNUpdate(ActionEvent event) {
        
        try{
            
            int insertStatus = 0;
            
            Contact contact = new Contact();
            
            contact.setID( Integer.parseInt(TextFieldID.getText()));
            
            contact.setFName(TextFieldFname.getText());
            contact.setLName(TextFieldLname.getText());
            contact.setMName("fdfdf");

            contact.setMobile(TextFieldMobile.getText());
            contact.setEmail(TextFieldEmail.getText());
            
            
            DAO.updateContact(contact);
       
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }

    }

    @FXML
    private void handleBTNDelete(ActionEvent event) {
        
        try
        {
            Contact mycontact = new Contact();
            mycontact.setID( Integer.parseInt(TextFieldID.getText()));
            DAO.deleteContact(mycontact);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        
    }

    @FXML
    private void handleBTNNext(ActionEvent event) {
        
        Contact contact = new Contact();
        try
        {
            DAO.NextContact(contact);
            TextFieldID.setText(Integer.toString(contact.getID()));
            TextFieldFname.setText(contact.getFName());
            TextFieldLname.setText(contact.getLName());
            TextFieldEmail.setText(contact.getEmail());
            TextFieldMobile.setText(contact.getMobile());
            
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBTNNew(ActionEvent event) {

        
        try
        {
            Contact mycontact = new Contact();
            
            mycontact.setID( Integer.parseInt(TextFieldID.getText()));
            mycontact.setFName(TextFieldFname.getText());
            mycontact.setLName(TextFieldLname.getText());
            mycontact.setMobile(TextFieldMobile.getText());
            mycontact.setEmail(TextFieldEmail.getText());
            
            DAO.newContact(mycontact);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void handleBTNLast(ActionEvent event) {
        
        Contact contact = new Contact();
        try
        {
            DAO.lastContact(contact);
            TextFieldID.setText(Integer.toString(contact.getID()));
            TextFieldFname.setText(contact.getFName());
            TextFieldLname.setText(contact.getLName());
            TextFieldEmail.setText(contact.getEmail());
            TextFieldMobile.setText(contact.getMobile());
            
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBTNFirst(ActionEvent event) {
        
        Contact contact = new Contact();
        try
        {
            DAO.firstContact(contact);
            TextFieldID.setText(Integer.toString(contact.getID()));
            TextFieldFname.setText(contact.getFName());
            TextFieldLname.setText(contact.getLName());
            TextFieldEmail.setText(contact.getEmail());
            TextFieldMobile.setText(contact.getMobile());
            
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        
    }

    @FXML
    private void handleBTNPrevious(ActionEvent event) {
        Contact contact = new Contact();
        try
        {
            DAO.PreviousContact(contact);
            TextFieldID.setText(Integer.toString(contact.getID()));
            TextFieldFname.setText(contact.getFName());
            TextFieldLname.setText(contact.getLName());
            TextFieldEmail.setText(contact.getEmail());
            TextFieldMobile.setText(contact.getMobile());
            
            
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
