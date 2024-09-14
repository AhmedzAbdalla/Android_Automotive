/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Contact;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.jdbc.ClientDriver;

/**
 *
 * @author hamad
 */
public class DAO {
    
    private static int counter;    
    private static int rec_ccount;

    
    public static void get_count()
    {
        try{
            DriverManager.registerDriver(new ClientDriver());
        
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ContactList", "root", "root");
            PreparedStatement pstate = conn.prepareStatement("SELECT COUNT(*) AS total FROM CONTACTTABLE");
            
            ResultSet rs = pstate.executeQuery();
            
            //rec_ccount = pstate.executeUpdate();
            
            if (rs.next()) {
                rec_ccount = rs.getInt("total");  // Get the count from the "total" column
            }
            
            //System.out.println("record num:" + rec_ccount);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }
            
    public static int newContact(Contact contact) throws SQLException
    {
        int insertStatus = 0;
        DriverManager.registerDriver(new ClientDriver());
        
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ContactList", "root", "root");
        PreparedStatement pstate = conn.prepareStatement("INSERT INTO CONTACTTABLE values (?, ?, ?, ?, ?, ?) ");
        
        pstate.setInt(1,contact.getID());
        pstate.setString(2, contact.getFName());
        pstate.setString(3, "sc dhvbsh");
        pstate.setString(4, contact.getLName());
        pstate.setString(5, contact.getEmail());
        pstate.setString(6,contact.getMobile());
        
        insertStatus = pstate.executeUpdate();
        
        conn.close();
        
        return insertStatus;
    }
    
    public static int deleteContact(Contact contact) throws SQLException
    {
        int insertStatus = 0;
        DriverManager.registerDriver(new ClientDriver());
        
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ContactList", "root", "root");
        PreparedStatement pstate = conn.prepareStatement("DELETE FROM CONTACTTABLE WHERE ID=? ");
        
        pstate.setInt(1,contact.getID());

        
        insertStatus = pstate.executeUpdate();
        
        conn.close();
        pstate.close();
        
        return insertStatus;
    }
    
    public static int updateContact(Contact contact) throws SQLException
    {
        
        int insertStatus = 0;
        
        DriverManager.registerDriver(new ClientDriver());
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ContactList", "root", "root");
            PreparedStatement pstate = conn.prepareStatement("UPDATE CONTACTTABLE\n" +
                                                                "SET FNAME=?, MNAME=?, LNAME=?, EMAIL=?, MOBILE=?"
                    +"WHERE ID=?");
           
        pstate.setInt(6,contact.getID());
        pstate.setString(1, contact.getFName());
        pstate.setString(2, "sc dhvbsh");
        pstate.setString(3, contact.getLName());
        pstate.setString(4, contact.getEmail());
        pstate.setString(5,contact.getMobile());
        
        
        
        insertStatus = pstate.executeUpdate();
        
        System.out.println(insertStatus);
        
        conn.close();
        pstate.close();
        
        return insertStatus;
    }
    
    public static int firstContact(Contact contact) throws SQLException {
    int status = 0;  // This variable seems to be unused; consider using it appropriately or removing it

    // Ensure that the JDBC driver is properly loaded
    // Connection to the database (ensure database URL, username, and password are correct)
    try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ContactList", "root", "root");
         // Prepare the SQL query to fetch the first contact
         PreparedStatement pstate = conn.prepareStatement("SELECT * FROM CONTACTTABLE ORDER BY ID FETCH FIRST ROW ONLY"); // Adjusted for Derby DB
         ResultSet rs = pstate.executeQuery()) {

        // Check if a result exists and then access the first row
        if (rs.next()) {
            // Assuming the table has columns: ID, FNAME, LNAME, EMAIL, MOBILE
            int id = rs.getInt("ID");
            contact.setID(id);
            contact.setFName(rs.getString("FNAME"));
            contact.setLName(rs.getString("LNAME"));
            contact.setEmail(rs.getString("EMAIL"));
            contact.setMobile(rs.getString("MOBILE"));
            status = 1;  // Consider setting status to 1 to indicate success
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw e;  // Optionally rethrow or handle the exception as needed
    }

    return status;
    }
    
    //=====================================================================
    
    public static int lastContact(Contact contact) throws SQLException {
    int status = 0;  // Variable to indicate whether a record was found and processed

    // Connection to the database (ensure database URL, username, and password are correct)
    try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ContactList", "root", "root");
         // Prepare the SQL query to fetch the last contact by ordering in descending order and fetching the first row
         PreparedStatement pstate = conn.prepareStatement("SELECT * FROM CONTACTTABLE ORDER BY ID DESC FETCH FIRST ROW ONLY"); // Adjusted for Derby DB
         ResultSet rs = pstate.executeQuery()) {
        get_count();
        // Check if a result exists and then access the first row (which is the last in the original order)
        if (rs.next()) {
            // Assuming the table has columns: ID, FNAME, LNAME, EMAIL, MOBILE
            int id = rs.getInt("ID");
            contact.setID(id);
            contact.setFName(rs.getString("FNAME"));
            contact.setLName(rs.getString("LNAME"));
            contact.setEmail(rs.getString("EMAIL"));
            contact.setMobile(rs.getString("MOBILE"));
            status = 1;  // Set status to 1 to indicate success
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw e;  // Optionally rethrow or handle the exception as needed
    }

    return status;
}
    
    //==================================================================
    
        public static int NextContact(Contact contact) throws SQLException {
    int status = 0;  // Variable to indicate whether a record was found and processed

    // Connection to the database (ensure database URL, username, and password are correct)
    try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ContactList", "root", "root");
         // Prepare the SQL query to fetch the last contact by ordering in descending order and fetching the first row
         PreparedStatement pstate = conn.prepareStatement("SELECT * FROM CONTACTTABLE ORDER BY ID" , ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); // Adjusted for Derby DB
         ResultSet rs = pstate.executeQuery()) {

        // Check if a result exists and then access the first row (which is the last in the original order)
        get_count();
        
        if(!(counter > rec_ccount))
        {
            if (rs.absolute(++(counter))) {
            // Assuming the table has columns: ID, FNAME, LNAME, EMAIL, MOBILE
            int id = rs.getInt("ID");
            contact.setID(id);
            contact.setFName(rs.getString("FNAME"));
            contact.setLName(rs.getString("LNAME"));
            contact.setEmail(rs.getString("EMAIL"));
            contact.setMobile(rs.getString("MOBILE"));
            status = 1;  // Set status to 1 to indicate success
             }
        }


    } catch (SQLException e) {
        e.printStackTrace();
        throw e;  // Optionally rethrow or handle the exception as needed
    }

    return status;
}
        
    public static int PreviousContact(Contact contact) throws SQLException {
    int status = 0;  // Variable to indicate whether a record was found and processed

    // Connection to the database (ensure database URL, username, and password are correct)
    try (Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/ContactList", "root", "root");
         // Prepare the SQL query to fetch the last contact by ordering in descending order and fetching the first row
         PreparedStatement pstate = conn.prepareStatement("SELECT * FROM CONTACTTABLE ORDER BY ID" , ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY); // Adjusted for Derby DB
         ResultSet rs = pstate.executeQuery()) {

        // Check if a result exists and then access the first row (which is the last in the original order)
        get_count();
        
        
        
        if((0 < rec_ccount))
        {
            if(counter >= 1)
            {
                if (rs.absolute(--(counter))) {
                // Assuming the table has columns: ID, FNAME, LNAME, EMAIL, MOBILE
                int id = rs.getInt("ID");
                contact.setID(id);
                contact.setFName(rs.getString("FNAME"));
                contact.setLName(rs.getString("LNAME"));
                contact.setEmail(rs.getString("EMAIL"));
                contact.setMobile(rs.getString("MOBILE"));
                status = 1;  // Set status to 1 to indicate success
             }
            }
            
        }


    } catch (SQLException e) {
        e.printStackTrace();
        throw e;  // Optionally rethrow or handle the exception as needed
    }

    return status;
}

    
    
}
