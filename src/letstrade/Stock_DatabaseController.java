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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AvoidIshti
 */
public class Stock_DatabaseController implements Initializable {

    public static Stage stage_of_Stock;

    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst;

    @FXML
    private TableView<ModelTable> Stock_Table;
    @FXML
    private TableColumn<ModelTable, String> TPID;
    @FXML
    private TableColumn<ModelTable, String> TPN;
    @FXML
    private TableColumn<ModelTable, String> TPS;

    public Stock_DatabaseController() {
        javaconnect.Connecrdb();
    }

    /**
     * Initializes the controller class.
     */
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ResultSet rs = javaconnect.Connecrdb().createStatement().executeQuery("select * from Productinfo ");

            while (rs.next()) {

                oblist.add(new ModelTable(rs.getString("proid"), rs.getString("proname"), rs.getString("PreStock")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Stock_DatabaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TPID.setCellValueFactory(new PropertyValueFactory<>("pid"));
        TPN.setCellValueFactory(new PropertyValueFactory<>("pn"));
        TPS.setCellValueFactory(new PropertyValueFactory<>("qis"));

        Stock_Table.setItems(oblist);
    }

    @FXML
    private void add_new_item(ActionEvent event) throws IOException {
        Stock_DatabaseController.stage_of_Stock.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_new_Item.fxml"));
        Parent root = loader.load();
        root.isResizable();
        //Get controller of scene2
        Add_new_ItemController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        //scene2Controller.transferMessage(view.getText());

        //Show scene 2 in new window            
        Add_new_ItemController.stage_of_add_new_item = new Stage();
        Add_new_ItemController.stage_of_add_new_item.setScene(new Scene(root));
        Add_new_ItemController.stage_of_add_new_item.setTitle("ADD NEW ITEM");
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        Add_new_ItemController.stage_of_add_new_item.getIcons().add(icon);
//Add_new_ItemController.stage_of_add_new_item.setResizable(true);
        //   stage.initModality(Modality.APPLICATION_MODAL);
        Add_new_ItemController.stage_of_add_new_item.show();

    }

    @FXML
    private void Back_To_Main(ActionEvent event) throws IOException {
        Stock_DatabaseController.stage_of_Stock.close();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        root.isResizable();
        Scene scene = new Scene(root);
        // LetsTrade.stage.close();
        FXMLDocumentController.stage_of_FXML.setScene(scene);
        FXMLDocumentController.stage_of_FXML.setTitle("Sale Product");
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        FXMLDocumentController.stage_of_FXML.getIcons().add(icon);
        //FXMLDocumentController.stage_of_FXML.setResizable(true);
        FXMLDocumentController.stage_of_FXML.show();
    }

    @FXML
    private void close_app(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}
