/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstrade;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static letstrade.Add_ProductController.stage_of_addproduct2;

/**
 * FXML Controller class
 *
 * @author AvoidIshti
 */
public class Add_new_ItemController implements Initializable {

    public static Stage stage_of_add_new_item;
    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst;

    @FXML
    private TextField New_item_ID;
    @FXML
    private TextField New_item_name;
    @FXML
    private TextField New_item_stock;
    @FXML
    private TextField New_item_PPB;

    public Add_new_ItemController() {
        javaconnect.Connecrdb();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void adding_NewItem_and_save(ActionEvent event) {
     
        try {
            String spl = "INSERT INTO Productinfo (proid,proname,PreStock,PcsInBun) VALUES(?,?,?,?) ";
            pst = javaconnect.Connecrdb().prepareStatement(spl);

            pst.setString(1, New_item_ID.getText());
            pst.setString(2, New_item_name.getText());
            pst.setString(3, New_item_stock.getText());
            pst.setString(4, New_item_PPB.getText());
                       pst.execute();
            JOptionPane.showMessageDialog(null, "Saved");
            //  rs.close();
           // New_item_ID.requestFocus();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Add_new_ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }

        {   New_item_ID.setText("0 ");
        New_item_name.setText("0 ");
        New_item_stock.setText(" 0");
        New_item_PPB.setText(" 0");
          New_item_ID.requestFocus(); 
        }
    }

    @FXML
    private void Clear_Fields(ActionEvent event) throws SQLException, IOException {

        New_item_ID.setText("0 ");
        New_item_name.setText("0 ");
        New_item_stock.setText("0 ");
        New_item_PPB.setText(" 0");
 New_item_ID.requestFocus();
       // Add_new_ItemController.stage_of_add_new_item.close();
        /*{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_new_Item.fxml"));
        Parent root = loader.load();

        //Get controller of scene2
        Add_new_ItemController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        //scene2Controller.transferMessage(view.getText());

        stage_of_addproduct2 = new Stage();
        stage_of_addproduct2.setScene(new Scene(root));
        stage_of_addproduct2.show();
        }*/
    }

    @FXML
    private void PID_pressed(KeyEvent event) {
        
        if (event.getCode().equals(KeyCode.ENTER)){
        New_item_name.requestFocus();
        
        }
    }

    @FXML
    private void PN_Pressed(KeyEvent event) {
        
         if (event.getCode().equals(KeyCode.ENTER)){
        
         New_item_stock.requestFocus();
        }
    }

    @FXML
    private void S_Pressed(KeyEvent event) {
        
         if (event.getCode().equals(KeyCode.ENTER)){
        
        New_item_PPB.requestFocus();
        }
    }

    @FXML
    private void PB_Pressed(KeyEvent event) {
        
         if (event.getCode().equals(KeyCode.ENTER)){
        
           try {
            String spl = "INSERT INTO Productinfo (proid,proname,PreStock,PcsInBun) VALUES(?,?,?,?) ";
            pst = javaconnect.Connecrdb().prepareStatement(spl);

            pst.setString(1, New_item_ID.getText());
            pst.setString(2, New_item_name.getText());
            pst.setString(3, New_item_stock.getText());
            pst.setString(4, New_item_PPB.getText());
           
            pst.execute();

            JOptionPane.showMessageDialog(null, "Saved");
            //  rs.close();
        //    New_item_ID.requestFocus();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Add_new_ItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
           {   New_item_ID.setText("0 ");
        New_item_name.setText("0 ");
        New_item_stock.setText(" 0");
        New_item_PPB.setText(" 0");
           New_item_ID.requestFocus();
        }
    }
    }

    @FXML
    private void back_button(ActionEvent event) throws IOException {
        
        Add_new_ItemController.stage_of_add_new_item.close();
        
    /*  { Parent root = FXMLLoader.load(getClass().getResource("Stock_DatabaseController.fxml"));

        Scene scene = new Scene(root);
       
          Stock_DatabaseController.stage_of_Stock.setScene(scene);
       //   Stock_DatabaseController.stage_of_Stock.initModality(Modality.APPLICATION_MODAL);
       Stock_DatabaseController.stage_of_Stock.show();}*/
        
        
         {FXMLLoader loader = new FXMLLoader(getClass().getResource("Stock_Database.fxml"));
        Parent root = loader.load();
root.isResizable();
        //Get controller of scene2
        Stock_DatabaseController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        //scene2Controller.transferMessage(view.getText());

        //Show scene 2 in new window            
        Stock_DatabaseController.stage_of_Stock = new Stage();
        Stock_DatabaseController.stage_of_Stock.setScene(new Scene(root));
        Stock_DatabaseController.stage_of_Stock.setTitle("Stock");
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        Stock_DatabaseController.stage_of_Stock.getIcons().add(icon);
 //Stock_DatabaseController.stage_of_Stock.setResizable(true);
        //  Add_ProductController.stage_of_addproduct2.initModality(Modality.APPLICATION_MODAL);
        Stock_DatabaseController.stage_of_Stock.show();
    }
    }

    @FXML
    private void exit_button(ActionEvent event) { 
        Platform.exit();
        System.exit(0);
    }
}
