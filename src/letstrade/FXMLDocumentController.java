/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstrade;

import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.awt.print.*;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.print.Printer;
import java.text.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import static letstrade.javaconnect.disconnect;

/**
 *
 * @author AvoidIshti
 */
public class FXMLDocumentController implements Initializable {

    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public static Stage stage_of_FXML;

    @FXML
    private TextField Sale_Product_ID;
    @FXML
    private TextField Sale_Product_Name;
    @FXML
    private MenuBar menubar;
    @FXML
    private TextField Sale_Product_Pcs_InStock;
    @FXML
    private TextField Sale_Product_PcsInbun;
    @FXML
    private TextField RatePerBun;
    @FXML
    private TextField SaleInBun;
    @FXML
    private TextField SaleInPcs;
    @FXML
    private TextField TotalPcs;
    @FXML
    public AnchorPane main_content;
    @FXML
    private TextField delivery_charge;
    @FXML
    private TextField Sub_Total;
    @FXML
    private TextField Discount;
    @FXML
    private TextField Outstanding;
    @FXML
    private TextField Paid;
    @FXML
    private Label you_hv_no_stk;
    @FXML
    private TextArea m_name;
    @FXML
    private TextArea m_pcs;
    @FXML
    private TextArea m_price;
    @FXML
    private TextArea m_amount;
    @FXML
    private TextField change;
    @FXML
    private TextField Total;

    public FXMLDocumentController() {
        javaconnect.Connecrdb();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @FXML
    private void CloseAPP(ActionEvent event) {

        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void AddProduct(ActionEvent event) throws IOException {
       FXMLDocumentController.stage_of_FXML.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Product.fxml"));
        Parent root = loader.load();
root.isResizable();
        //Get controller of scene2
        Add_ProductController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        //scene2Controller.transferMessage(view.getText());

        //Show scene 2 in new window            
        Add_ProductController.stage_of_addproduct2 = new Stage();
        Add_ProductController.stage_of_addproduct2.setScene(new Scene(root));
        Add_ProductController.stage_of_addproduct2.setTitle("Add Product");
        Image icon = new Image(getClass().getResourceAsStream("icon.png"));
        Add_ProductController.stage_of_addproduct2.getIcons().add(icon);
 //Add_ProductController.stage_of_addproduct2.setResizable(true);
        //  Add_ProductController.stage_of_addproduct2.initModality(Modality.APPLICATION_MODAL);
        Add_ProductController.stage_of_addproduct2.show();
        // Add_ProductController.stage_of_addproduct2.setTitle("LetsTrade");

    }

    @FXML
    private void AddCustomer(ActionEvent event) throws IOException {
FXMLDocumentController.stage_of_FXML.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Customer.fxml"));
        Parent root = loader.load();
root.isResizable();
        //Get controller of scene2
        Add_CustomerController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        //scene2Controller.transferMessage(view.getText());

        //Show scene 2 in new window            
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        //stage.setTitle("Third Window");
        //Image icon = new Image(getClass().getResourceAsStream("index.png"));
        //stage.getIcons().add(icon);
        stage.setResizable(true);
        stage.show();
    }

    @FXML
    private void AddSupplier(ActionEvent event) throws IOException {
        FXMLDocumentController.stage_of_FXML.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Supplier.fxml"));
        Parent root = loader.load();
root.isResizable();
        //Get controller of scene2
        Add_SupplierController scene2Controller = loader.getController();
        //Pass whatever data you want. You can have multiple method calls here
        //scene2Controller.transferMessage(view.getText());

        //Show scene 2 in new window            
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        //stage.setTitle("Third Window");
        //Image icon = new Image(getClass().getResourceAsStream("index.png"));
        //stage.getIcons().add(icon);
         stage.setResizable(true);
        stage.show();

    }

    @FXML
    private void show_information(ActionEvent event) {
        String sql = "Select * from Productinfo where proid = ?  AND PreStock > 0";
        try {
            pst = javaconnect.Connecrdb().prepareStatement(sql);

            pst.setString(1, Sale_Product_ID.getText());
            rs = pst.executeQuery();

            if (rs.next()) {
                Sale_Product_Name.setText(rs.getString("proname"));
                Sale_Product_Pcs_InStock.setText(rs.getString("PreStock"));
                Sale_Product_PcsInbun.setText(rs.getString("PcsInBun"));
                SaleInBun.setText("0");
                SaleInPcs.setText("0");
                TotalPcs.setText("0");
                RatePerBun.setText("0");

                //   JOptionPane.showMessageDialog(null, "Showed");
                //   conn.close();
                //     pst.close();
                //SaleInBun.requestFocus();
                rs.close();
            }

            // pst.close();
        } catch (SQLException ex) {
            // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        /* finally{
                
                try{
                //pst.close();
                //rs.close();
                }catch(Exception e){e.printStackTrace();}
                }*/

    }

    @FXML
    private void calculatingPCS(ActionEvent event) throws SQLException {

        String B = SaleInBun.getText();
        String P = SaleInPcs.getText();
        String H = Sale_Product_PcsInbun.getText();
        int BB = Integer.parseInt(B);
        int PP = Integer.parseInt(P);
        int HH = Integer.parseInt(H);

        int bh = BB * HH;
        int bhp = bh + PP;

        String f = String.valueOf(bhp);
        TotalPcs.setText(f);
        RatePerBun.requestFocus();
    }

    @FXML
    private void ShowInMEMO(ActionEvent event) throws SQLException {

        String aaaa = Sale_Product_PcsInbun.getText();
        String T = TotalPcs.getText();
        String R = RatePerBun.getText();

        if (aaaa.isEmpty() && T.isEmpty() && R.isEmpty()) {
            //JOptionPane.showMessageDialog(null, "Invalid Input!!");
            Platform.exit();
            System.exit(0);
        } else {
            Double aaaaa = Double.parseDouble(aaaa);
            Double TT = Double.parseDouble(T);
            Double RR = Double.parseDouble(R);

            String W = Sale_Product_Pcs_InStock.getText();
            Double WW = Double.parseDouble(W);
            if (TT > WW) {
                JOptionPane.showMessageDialog(null, "Invalid Input!!");
                Platform.exit();
                System.exit(0);
            } else {
                Double RRFF = TT / aaaaa;
                Double eee = RRFF * RR;

                double x = 123.45678;
                DecimalFormat df = new DecimalFormat("#.##");
                String dx = df.format(eee);
                eee = Double.valueOf(dx);
                String RF = String.valueOf(eee);

                String a1 = Sale_Product_Name.getText();
                String a2 = TotalPcs.getText();
                String a3 = RatePerBun.getText();

                {
                    m_name.appendText(a1);
                    m_name.appendText("\n");

                    m_pcs.appendText(a2);
                    m_pcs.appendText("\n");

                    m_price.appendText(a3);
                    m_price.appendText("\n");

                    m_amount.appendText(RF);
                    m_amount.appendText("\n");
                }
                {
                    String f = Sale_Product_ID.getText();
                    String nw = TotalPcs.getText();
                    String spl = "update Productinfo Set PreStock= PreStock-? WHERE proid ='" + f + "' AND PreStock > 0 ";
                    try {
                        pst = javaconnect.Connecrdb().prepareStatement(spl);
                        pst.setString(1, nw);

                        pst.executeUpdate();

                        //   JOptionPane.showMessageDialog(null, "Updated");
                        //conn.close();
                        //  pst.close();
                        //rs.close();
                    } catch (HeadlessException | SQLException e) {
                        e.printStackTrace();
                    } finally {

                        try {
                            pst.close();
                            javaconnect.conn.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
                {
                    String www = Sub_Total.getText();
                    Double WWW = Double.parseDouble(www);
                    Double ABCWWW = WWW + eee;
                    Sub_Total.setText(String.valueOf(ABCWWW));

                }
                {
                    Sale_Product_ID.setText("0 ");
                    Sale_Product_Name.setText(" 0");
                    Sale_Product_PcsInbun.setText(" 0");
                    Sale_Product_Pcs_InStock.setText(" 0");
                    SaleInBun.setText("0 ");
                    SaleInPcs.setText("0 ");
                    TotalPcs.setText("0 ");
                    RatePerBun.setText("0 ");
                }
                Sale_Product_ID.requestFocus();

            }
        }
    }

    @FXML
    private void Show_Stock_DB(ActionEvent event) throws IOException {

        FXMLDocumentController.stage_of_FXML.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Stock_Database.fxml"));
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
 // Stock_DatabaseController.stage_of_Stock.setResizable(true);
        //  Add_ProductController.stage_of_addproduct2.initModality(Modality.APPLICATION_MODAL);
        Stock_DatabaseController.stage_of_Stock.show();

    }

    @FXML
    private void clear_data_in_sale_product(ActionEvent event) throws SQLException {

        {
            String sut = Sub_Total.getText();
            Double st = Double.parseDouble(sut);
            String dec = delivery_charge.getText();
            Double dc = Double.parseDouble(dec);
            String disc = Discount.getText();
            Double discc = Double.parseDouble(disc);
            String o = Outstanding.getText();
            Double ooo = Double.parseDouble(o);
            Double hahaa = st + dc + ooo;
            Double hah = hahaa - discc;

            Total.setText(String.valueOf(hah));
            Paid.requestFocus();
        }

        /* {
        
         //Sub_Total.getText();
String www =Sub_Total.getText();
         
         int WWW = Integer.parseInt(www);
         
         String abc= RatePerBun.getText();
        int ABC = Integer.parseInt(abc);
        
        int ABCWWW =WWW + ABC;
        
        
         Sub_Total.setText(String.valueOf(ABCWWW));
        
        }*/
 /*{
            Sale_Product_ID.setText("0 ");
            Sale_Product_Name.setText(" 0");
            Sale_Product_PcsInbun.setText("0 ");
            Sale_Product_Pcs_InStock.setText(" 0");
            RatePerBun.setText("0 ");
            SaleInBun.setText("0 ");
            SaleInPcs.setText("0 ");
            TotalPcs.setText(" 0");
              Sale_Product_ID.requestFocus();

        }*/
    }

    @FXML
    private void key(KeyEvent event) throws SQLException {

        if (event.getCode().equals(KeyCode.ENTER)) {
            String sql = "Select * from Productinfo where proid = ?  AND PreStock > 0";
            try {
                pst = javaconnect.Connecrdb().prepareStatement(sql);

                pst.setString(1, Sale_Product_ID.getText());
                rs = pst.executeQuery();

                if (rs.next()) {
                    Sale_Product_Name.setText(rs.getString("proname"));
                    Sale_Product_Pcs_InStock.setText(rs.getString("PreStock"));
                    Sale_Product_PcsInbun.setText(rs.getString("PcsInBun"));
                    SaleInBun.setText("0");
                    SaleInPcs.setText("0");
                    TotalPcs.setText("0");
                    RatePerBun.setText("0");

                    // JOptionPane.showMessageDialog(null, "Showed");
                    //   conn.close();
                    //     pst.close();
                    SaleInBun.requestFocus();
                    rs.close();
                }

                // pst.close();
            } catch (SQLException ex) {
                // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }

    }

    @FXML
    private void SIB_Pressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            SaleInPcs.requestFocus();
        }
    }

    @FXML
    private void SIP_pressed(KeyEvent event) {
        String B = SaleInBun.getText();
        String P = SaleInPcs.getText();
        String H = Sale_Product_PcsInbun.getText();
        int BB = Integer.parseInt(B);
        int PP = Integer.parseInt(P);
        int HH = Integer.parseInt(H);
        if (event.getCode().equals(KeyCode.ENTER)) {

            int bh = BB * HH;
            int bhp = bh + PP;

            String f = String.valueOf(bhp);
            TotalPcs.setText(f);
            RatePerBun.requestFocus();
        }
    }

    @FXML
    private void RB_Pressed(KeyEvent event) {
        String aaaa = Sale_Product_PcsInbun.getText();
        String T = TotalPcs.getText();
        String R = RatePerBun.getText();
        if (event.getCode().equals(KeyCode.ENTER)) {

            if (aaaa.isEmpty() && T.isEmpty() && R.isEmpty()) {

                //JOptionPane.showMessageDialog(null, "Invalid Input!!");
                Platform.exit();
                System.exit(0);
            } else {
                Double aaaaa = Double.parseDouble(aaaa);
                Double TT = Double.parseDouble(T);
                Double RR = Double.parseDouble(R);

                String W = Sale_Product_Pcs_InStock.getText();
                Double WW = Double.parseDouble(W);
                if (TT > WW) {
                    JOptionPane.showMessageDialog(null, "Invalid Input!!");
                    Platform.exit();
                    System.exit(0);
                } else {
                    Double RRFF = TT / aaaaa;
                    Double eee = RRFF * RR;

                    double x = 123.45678;
                    DecimalFormat df = new DecimalFormat("#.##");
                    String dx = df.format(eee);
                    eee = Double.valueOf(dx);
                    String RF = String.valueOf(eee);

                    String a1 = Sale_Product_Name.getText();
                    String a2 = TotalPcs.getText();
                    String a3 = RatePerBun.getText();

                    m_name.appendText(a1);
                    m_name.appendText("\n");

                    m_pcs.appendText(a2);
                    m_pcs.appendText("\n");

                    m_price.appendText(a3);
                    m_price.appendText("\n");

                    m_amount.appendText(RF);
                    m_amount.appendText("\n");
                    {
                        String f = Sale_Product_ID.getText();
                        String nw = TotalPcs.getText();
                        String spl = "update Productinfo Set PreStock= PreStock-? WHERE proid ='" + f + "' AND PreStock > 0 ";
                        try {
                            pst = javaconnect.Connecrdb().prepareStatement(spl);
                            pst.setString(1, nw);

                            pst.executeUpdate();

                            //JOptionPane.showMessageDialog(null, "Updated");
                            //conn.close();
                            //  pst.close();
                            //rs.close();
                        } catch (HeadlessException | SQLException e) {
                            e.printStackTrace();
                        } finally {

                            try {
                                pst.close();
                                javaconnect.conn.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    {
                        String www = Sub_Total.getText();
                        Double WWW = Double.parseDouble(www);
                        Double ABCWWW = WWW + eee;
                        Sub_Total.setText(String.valueOf(ABCWWW));
                    }
                    {
                        Sale_Product_ID.setText("0 ");
                        Sale_Product_Name.setText(" 0");
                        Sale_Product_PcsInbun.setText(" 0");
                        Sale_Product_Pcs_InStock.setText(" 0");
                        SaleInBun.setText("0 ");
                        SaleInPcs.setText("0 ");
                        TotalPcs.setText("0 ");
                        RatePerBun.setText("0 ");
                    }
                }
            }
            Sale_Product_ID.requestFocus();
        }
    }

    @FXML
    private void DC_Change_pressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Discount.requestFocus();
        }
    }

    @FXML
    private void Dis_Pressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            Outstanding.requestFocus();
        }
    }

    @FXML
    private void outstanding_pressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {

            String sut = Sub_Total.getText();
            Double st = Double.parseDouble(sut);
            String dec = delivery_charge.getText();
            Double dc = Double.parseDouble(dec);
            String disc = Discount.getText();
            Double discc = Double.parseDouble(disc);
            String o = Outstanding.getText();
            Double ooo = Double.parseDouble(o);
            Double hahaa = st + dc + ooo;
            Double hah = hahaa - discc;

            Total.setText(String.valueOf(hah));
            Paid.requestFocus();
        }
    }

    @FXML
    private void paid_pressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            {
                //String sut = Sub_Total.getText();
                //Double st = Double.parseDouble(sut);
                //String dec = delivery_charge.getText();
                //Double dc = Double.parseDouble(dec);
                //String disc = Discount.getText();
                //Double discc = Double.parseDouble(disc);
                //String o = Outstanding.getText();
                //Double ooo = Double.parseDouble(o);
                String p = Paid.getText();
                Double pp = Double.parseDouble(p);

                //Double hahaa = st + dc + ooo;
                //Double hah = hahaa - discc;
                String vv = Total.getText();
                Double vvv = Double.parseDouble(vv);
                Double lol = pp - vvv;

                double x = 123.45678;
                DecimalFormat df = new DecimalFormat("#.##");
                String dx = df.format(lol);
                lol = Double.valueOf(dx);
                change.setText(String.valueOf(lol));
                Sale_Product_ID.requestFocus();
            }
        }
    }

    @FXML
    private void PerformClean(ActionEvent event) {
        Sale_Product_ID.setText("0 ");
        Sale_Product_Name.setText(" 0");
        Sale_Product_PcsInbun.setText(" 0");
        Sale_Product_Pcs_InStock.setText(" 0");
        SaleInBun.setText("0 ");
        SaleInPcs.setText("0 ");
        TotalPcs.setText("0 ");
        RatePerBun.setText("0 ");
        Sub_Total.setText("0");
        delivery_charge.setText("0");
        Discount.setText("0");
        Outstanding.setText("0");
        Paid.setText("0");
        change.setText("0");
        Total.setText("0");

        m_name.setText("");

        m_pcs.setText("");

        m_price.setText("");

        m_amount.setText("");
        Sale_Product_ID.requestFocus();
    }

    @FXML
    private void about(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "  Developed by \n Syed Ishtiak Rahman \n Bsc in CSE, UIU. \n Email : Ishtiakrahman15@gmail.com \n Contact : 01623181997", "Info", JOptionPane.INFORMATION_MESSAGE);

    }

    @FXML
    private void paid_change(ActionEvent event) {

        String p = Paid.getText();
        Double pp = Double.parseDouble(p);

        //Double hahaa = st + dc + ooo;
        //Double hah = hahaa - discc;
        String vv = Total.getText();
        Double vvv = Double.parseDouble(vv);
        Double lol = pp - vvv;
        change.setText(String.valueOf(lol));
        Sale_Product_ID.requestFocus();
    }
}
