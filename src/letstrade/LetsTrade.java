/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstrade;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author AvoidIshti
 */
public class LetsTrade extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();

        //Get controller of scene2
        FXMLDocumentController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        //scene2Controller.transferMessage(view.getText());

        //Show scene 2 in new window            
        FXMLDocumentController.stage_of_FXML = new Stage();
        FXMLDocumentController.stage_of_FXML.setScene(new Scene(root));
        FXMLDocumentController.stage_of_FXML.setTitle("SaleProduct");
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        FXMLDocumentController.stage_of_FXML.getIcons().add(icon);
        //FXMLDocumentController.stage_of_FXML.setResizable(false);
    //     FXMLDocumentController.stage_of_FXML.setFullScreen(false);
      //    FXMLDocumentController.stage_of_FXML.
        //  Add_ProductController.stage_of_addproduct2.initModality(Modality.APPLICATION_MODAL);
        FXMLDocumentController.stage_of_FXML.show();
        // Add_ProductController.stage_of_addproduct2.setTitle("LetsTrade");

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
