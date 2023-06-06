/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstrade;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AvoidIshti
 */
public class Add_ProductController implements Initializable {

    public static Stage stage_of_addproduct2;

    Connection conn;
    @FXML
    private AnchorPane add_product_content;

    @FXML
    private TextField New_Product_Pcs;
    @FXML
    private TextField New_Product_ADD;
    @FXML
    private Label Current_Stock;

    @FXML
    private TextField Latest_stock;

    public Add_ProductController() {
        javaconnect.Connecrdb();
    }

    @FXML
    private TextField New_Product_ID;
    @FXML
    private TextField New_Product_Name;

    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // Add_ProductController ab = new Add_ProductController();
    }

    @FXML
    private void adding_product_and_save(ActionEvent event) {

        String spl = "Select * from Productinfo where proid = ?";
        try {
            pst = javaconnect.Connecrdb().prepareStatement(spl);;
            pst.setString(1, New_Product_ID.getText());
            rs = pst.executeQuery();

            if (rs.next()) {

                New_Product_Name.setText(rs.getString("proname"));

                New_Product_Pcs.setText(rs.getString("PreStock"));

             //   JOptionPane.showMessageDialog(null, "Working");
            }

            rs.close();
            //pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Add_ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*finally {

            try {
                conn.close();
                rs.close();
                pst.close();
            } catch (SQLException e) {
            }

        }*/
    }

    @FXML
    private void UpdatingProduct(ActionEvent event) {

        String f = New_Product_ID.getText();
        String nw = New_Product_ADD.getText();
        Double NW = Double.parseDouble(nw);

        if (NW < 0) {
            JOptionPane.showMessageDialog(null, "Invalid Input!!");
            Platform.exit();
            System.exit(0);
        } else {
            String spl = "update Productinfo Set PreStock= PreStock+? WHERE proid ='" + f + "' ";
            try {
                pst = javaconnect.Connecrdb().prepareStatement(spl);
                pst.setString(1, nw);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Updated");
                //rs.close();
                pst.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        /*finally {

            try {
                conn.close();
                rs.close();
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }*/
        {

            String sql = "Select * from Productinfo where proid = ?";
            try {
                pst = javaconnect.Connecrdb().prepareStatement(sql);

                pst.setString(1, New_Product_ID.getText());
                rs = pst.executeQuery();

                if (rs.next()) {
                    Latest_stock.setText(rs.getString("PreStock"));

                }
               // JOptionPane.showMessageDialog(null, "Showed");
                New_Product_ID.requestFocus();
                rs.close();
                // pst.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
          {
           // New_Product_ID.setText(" ");
            //New_Product_Name.setText(" ");

            //New_Product_Pcs.setText(" ");

            New_Product_ADD.setText("0 ");

            //Latest_stock.setText(" ");
            {

            }
        
        }
    }

    @FXML
    private void Clear_Fields(ActionEvent event) throws SQLException, IOException {

        New_Product_ID.setText("0 ");
        New_Product_Name.setText("0 ");
        New_Product_Pcs.setText(" 0");
        New_Product_ADD.setText(" 0");
        Latest_stock.setText(" 0");
        New_Product_ID.requestFocus();
        //  Add_ProductController.stage_of_addproduct2.close();

        //   rs.close();
        // pst.close();
        /*   {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Product.fxml"));
            Parent root = loader.load();

            //Get controller of scene2
            Add_ProductController scene2Controller = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            //scene2Controller.transferMessage(view.getText());

            stage_of_addproduct2 = new Stage();
            stage_of_addproduct2.setScene(new Scene(root));
            stage_of_addproduct2.show();
            //stage.setTitle("Third Window");
            //Image icon = new Image(getClass().getResourceAsStream("index.png"));
            //stage.getIcons().add(icon);

            //stage_of_addproduct2.initModality(Modality.APPLICATION_MODAL);
            //stage_of_addproduct2.showAndWait();
        }*/
    }

    @FXML
    private void Close_App(ActionEvent event) {

        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void Back_To_Main(ActionEvent event) throws IOException {

        Add_ProductController.stage_of_addproduct2.close();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
root.isResizable();
        Scene scene = new Scene(root);
        // LetsTrade.stage.close();
         FXMLDocumentController.stage_of_FXML .setScene(scene);
         FXMLDocumentController.stage_of_FXML .setTitle("ADD NEW ITEM");
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
         FXMLDocumentController.stage_of_FXML .getIcons().add(icon);
         //FXMLDocumentController.stage_of_FXML .setResizable(true);
         FXMLDocumentController.stage_of_FXML .show();

    }

    @FXML
    private void key_pressed(KeyEvent event) {

        if (event.getCode().equals(KeyCode.ENTER)) {

            String spl = "Select * from Productinfo where proid = ?";
            try {
                pst = javaconnect.Connecrdb().prepareStatement(spl);;
                pst.setString(1, New_Product_ID.getText());
                rs = pst.executeQuery();

                if (rs.next()) {

                    New_Product_Name.setText(rs.getString("proname"));

                    New_Product_Pcs.setText(rs.getString("PreStock"));

                    //JOptionPane.showMessageDialog(null, "Working");
                }
                New_Product_ADD.requestFocus();
                rs.close();
                //pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(Add_ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @FXML
    private void EIP_Pressed(KeyEvent event) {

        String f = New_Product_ID.getText();
        String nw = New_Product_ADD.getText();

        if (event.getCode().equals(KeyCode.ENTER)) {
            Double NW = Double.parseDouble(nw);
            if (NW < 0) {
                JOptionPane.showMessageDialog(null, "Invalid Input!!");
                Platform.exit();
                System.exit(0);
            } else {
                String spl = "update Productinfo Set PreStock= PreStock+? WHERE proid ='" + f + "' ";
                try {
                    pst = javaconnect.Connecrdb().prepareStatement(spl);
                    pst.setString(1, nw);

                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Updated");
                    //rs.close();
                    
                    pst.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            /*finally {

            try {
                conn.close();
                rs.close();
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }*/
            {

                String sql = "Select * from Productinfo where proid = ?";
                try {
                    pst = javaconnect.Connecrdb().prepareStatement(sql);

                    pst.setString(1, New_Product_ID.getText());
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        Latest_stock.setText(rs.getString("PreStock"));

                    }
                  //  JOptionPane.showMessageDialog(null, "Showed");

                    rs.close();
                    //pst.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            {
                //New_Product_ID.setText(" 0");
                //New_Product_Name.setText("0 ");

                //New_Product_Pcs.setText(" 0");
                   New_Product_ADD.setText("0");
                //Latest_stock.setText("0 ");
                {

                }

            }
            New_Product_ID.requestFocus();
        }

    }
}
/*   try {
            String spl = "INSERT INTO Productinfo (NewAdd) VALUES(?)";
            pst = javaconnect.Connecrdb().prepareStatement(spl);

            //pst.setString(1, New_Product_ID.getText());
            //pst.setString(2, New_Product_Name.getText());
            //pst.setString(3, New_Product_Pcs.getText());
            pst.setString(1, New_Product_ADD.getText());
            //pst.setString(5, New_Product_TotalStock.getText());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Saved");
        } catch (SQLException ex) {
            Logger.getLogger(Add_ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
