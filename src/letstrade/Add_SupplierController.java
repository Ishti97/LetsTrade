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
public class Add_SupplierController implements Initializable {

    Connection conn;

    @FXML
    private AnchorPane add_supplier_content;

    ResultSet rs = null;
    PreparedStatement pst;
    @FXML
    private TextField newsuppid;
    @FXML
    private TextField newsuppname;
    @FXML
    private TextField newsuppcont;
    @FXML
    private TextField newsuppcom;
    @FXML
    private TextField newsuppadd;
    @FXML
    private TextField newsuppout;

    public Add_SupplierController() {
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
    private void save_supplier_info(ActionEvent event) {

        try {
            String sql = "INSERT INTO Supplier (sid,sn,cont,com,address,out) VALUES (?,?,?,?,?,?)";

            pst = javaconnect.Connecrdb().prepareStatement(sql);

            pst.setString(1, newsuppid.getText());
            pst.setString(2, newsuppname.getText());
            pst.setString(3, newsuppcont.getText());
            pst.setString(4, newsuppcom.getText());
            pst.setString(5, newsuppadd.getText());
            pst.setString(6, newsuppout.getText());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Saved");
        } catch (SQLException ex) {
            Logger.getLogger(Add_SupplierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
