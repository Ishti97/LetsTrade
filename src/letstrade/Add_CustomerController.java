/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstrade;

import java.awt.HeadlessException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AvoidIshti
 */
public class Add_CustomerController implements Initializable {

 
    
    
    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst=null;
    @FXML
    private AnchorPane add_customer_content;
    @FXML
    private TextField newcusid;
    @FXML
    private TextField newcusname;
    @FXML
    private TextField newcuscon;
    @FXML
    private TextField newcuscom;
    @FXML
    private TextField newcusadd;
    @FXML
    private TextField newcusout;

    public Add_CustomerController() {
        javaconnect.Connecrdb();
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save_customer_info(ActionEvent event){
        
         try {
            String sal = "INSERT INTO customerinfo (cid,cnm,ccon,ccom,cadd,cout) VALUES (?,?,?,?,?,?)";

            pst = javaconnect.Connecrdb().prepareStatement(sal);
            
            pst.setString(1, newcusid.getText());
            pst.setString(2, newcusname.getText());
            pst.setString(3, newcuscon.getText());
            pst.setString(4, newcuscom.getText());
            pst.setString(5, newcusadd.getText());
            pst.setString(6, newcusout.getText());
           
            pst.execute();

            JOptionPane.showMessageDialog(null, "Saved");

        } catch (SQLException ex) {
            Logger.getLogger(Add_CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
    
}
