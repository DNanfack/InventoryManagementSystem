package InventoryManagementSystem.Controllers;

import InventoryManagementSystem.Models.Inventory;
import InventoryManagementSystem.Models.Part;
import InventoryManagementSystem.Models.Product;
import InventoryManagementSystem.Properties;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable{

    //FXML Labels:
    @FXML private Label errorMessage, applicationTitleLabel, applicationVersionLabel;
    //FXML Buttons:
    @FXML private Button exitButton;
    //FXML TextFields:
    @FXML private TextField partQueryTextField, productQueryTextField;
    //FXML Tables:
    @FXML private TableView<Part> partTable;
    @FXML private TableView<Product> productTable;
    //FXML Product Table Columns:
    @FXML private TableColumn<Product,Integer> productTableIdColumn;
    @FXML private TableColumn<Product,String> productTableNameColumn;
    @FXML private TableColumn<Product,Integer> productTableInventoryColumn;
    @FXML private TableColumn<Product,Double> productTablePriceColumn;
    //FXML Part Table Columns:
    @FXML private TableColumn<Part,Integer> partTableIdColumn;
    @FXML private TableColumn<Part,String> partTableNameColumn;
    @FXML private TableColumn<Part,Integer> partTableInventoryColumn;
    @FXML private TableColumn<Part,Double> partTablePriceColumn;

    /**
     * @param location
     * @param resources 
     * Usage: Called when the scene is loaded.
     *        Initializes the scene.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Prepare labels
        applicationTitleLabel.setText(Properties.getAPPLICATION_NAME());
        applicationVersionLabel.setText(Properties.getAPPLICATION_VERSION());
        errorMessage.setVisible(false);
        
        //Map part table columns to values
        partTableIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partId"));
        partTableNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("inv"));
        partTablePriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        
        //Map product table columns to values
        productTableIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productId"));
        productTableNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productTableInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("inv"));
        productTablePriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        
        //Populate tables
        productTable.getItems().setAll(populateProductTable());
        productTable.setPlaceholder(new Label(Properties.getTABLE_PLACEHOLDER_MESSAGE()));
        partTable.getItems().setAll(populatePartTable());
        partTable.setPlaceholder(new Label(Properties.getTABLE_PLACEHOLDER_MESSAGE()));
    }

    /**
     * @param event 
     * Usage: Called when the user presses the Exit button on the main scene. Closes the program with an exit code of 0.
     */
    @FXML
    public void OnExitButtonPress(ActionEvent event){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        System.exit(0);
    }
    
    /**
     * @param event 
     * @throws IOException if the resources are not available when accessed.
     * Usage: Called when the user presses the Add part button on the main scene. 
     *        Opens the Add Part scene.
     */
    @FXML
    public void onAddPartButtonPress(ActionEvent event) throws IOException{
        Parent AddPartParent = FXMLLoader.load(getClass().getResource("/InventoryManagementSystem/Views/AddPart.fxml"));
        Scene AddPartScene = new Scene(AddPartParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AddPartScene);
        AddPartScene.getRoot().requestFocus();
        window.show();
    }
    
    /**
     * @param event
     * @throws IOException if the resources are not available when accessed.
     * Usage: Called when the user presses the Modify part button on the main scene. 
     *        Opens the Modify Part scene, if the user selects a part.
     */
    @FXML
    public void onModifyPartButtonPress(ActionEvent event) throws IOException{
        if(partTable.getSelectionModel().getSelectedItem() != null){
            ModifyPartController.setPartToBeModified(partTable.getSelectionModel().getSelectedItem());
            Parent ModifyPartParent = FXMLLoader.load(getClass().getResource("/InventoryManagementSystem/Views/ModifyPart.fxml"));
            Scene ModifyPartScene = new Scene(ModifyPartParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ModifyPartScene);
            ModifyPartScene.getRoot().requestFocus();
            window.show();
        }else if(partTable.getItems().isEmpty() != true){
            tellUserToSelectTable();
        }else{
            tellUserToPopulateTable();
        }      
    } 
    
    /**
     * @param event 
     * Usage: Called when the user presses the Delete button on the main scene. 
     *        Deletes the selected part, if the user has selected a part.
     */
    @FXML
    public void onDeletePartButtonPress(ActionEvent event){
        if(partTable.getSelectionModel().getSelectedItem() != null && partTable.getItems() != null){
           Part partToBeDeleted = partTable.getSelectionModel().getSelectedItem();
           
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle(Properties.getAPPLICATION_NAME_AND_VERSION());
           alert.setHeaderText(Properties.getDIALOG_HEADER());
           alert.setContentText(Properties.getDELETE_CONFIRMATION_PROMPT());
           Optional<ButtonType> result = alert.showAndWait();
           if (result.get() == ButtonType.OK){
                Inventory.deletePart(partToBeDeleted);
                partTable.getItems().setAll(populatePartTable()); 
                for(int i = 0; i < Inventory.getProducts().size(); i++){
                   if(Inventory.getProducts().get(i).getAssociatedParts().contains(partToBeDeleted)){
                        Inventory.getProducts().get(i).removeAssociatedPart(partToBeDeleted);
                   }
                }
            }
        }else if(partTable.getItems().isEmpty() != true){
            tellUserToSelectTable();
        }else{
            tellUserToPopulateTable();
        }
    }
    
    /**
     * @param event
     * @throws IOException if the resources are not available when accessed.
     * Usage: Called when the user presses the Add product button in the main scene.
     *        Opens the add product scene, if the user has parts.
     */
    @FXML
    public void onAddProductButtonPress(ActionEvent event) throws IOException{
        if(partTable.getItems().isEmpty()){
            tellUserToCreatePartsFirst();
        }else{
          Parent AddProductParent = FXMLLoader.load(getClass().getResource("/InventoryManagementSystem/Views/AddProduct.fxml"));
            Scene AddProductScene = new Scene(AddProductParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(AddProductScene);
            AddProductScene.getRoot().requestFocus();
            window.show();  
        }
    }
    
    /**
     * @param event
     * @throws IOException if the resources are not available when accessed. 
     * Usage: Called when the user presses the Modify product button.
     *        Opens the modify product scene if the user has selected a product.
     */
    @FXML
    public void onModifyProductButtonPress(ActionEvent event) throws IOException{    
        if(productTable.getSelectionModel().getSelectedItem() != null){
            ModifyProductController.setProductToModify(productTable.getSelectionModel().getSelectedItem());
            Parent ModifyProductParent = FXMLLoader.load(getClass().getResource("/InventoryManagementSystem/Views/ModifyProduct.fxml"));
            Scene ModifyProductScene = new Scene(ModifyProductParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ModifyProductScene);
            ModifyProductScene.getRoot().requestFocus();
            window.show();
        }else if(productTable.getItems().isEmpty() != true){
            tellUserToSelectTable();
        }else{
            tellUserToPopulateTable();
        }      
    }
    
    /**
     * @param event 
     * Usage: Called when the user presses the Delete product button.
     *        If the user has a product selected and the product does not have any associated parts, it is deleted.
     */
    @FXML
    public void onDeleteProductButtonPress(ActionEvent event){
        if(productTable.getSelectionModel().getSelectedItem() != null && productTable.getItems() != null){
           Product productToBeDeleted = productTable.getSelectionModel().getSelectedItem();
           if(productToBeDeleted.getAssociatedParts().isEmpty()==false){
               errorMessage.setText("ERROR: You may not delete this Product because it has an associated part.");
               errorMessage.setVisible(true);
           }else{
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle(Properties.getAPPLICATION_NAME_AND_VERSION());
               alert.setHeaderText(Properties.getDIALOG_HEADER());
               alert.setContentText(Properties.getDELETE_CONFIRMATION_PROMPT());

               Optional<ButtonType> result = alert.showAndWait();
               if (result.get() == ButtonType.OK){
                    Inventory.removeProduct(productToBeDeleted);
                    productTable.getItems().setAll(populateProductTable());
               }
           }
        }else if(productTable.getItems().isEmpty() != true){
            tellUserToSelectTable();
        }else{
            tellUserToPopulateTable();
        }
    }
    
    /**
     * Usage: Tells the user to select an element in the table.
     */
    private void tellUserToSelectTable(){
        errorMessage.setText(Properties.getTELL_USER_TO_SELECT_ELEMENT_MESSAGE());
        errorMessage.setVisible(true);
    }
    
    /**
     * Usage: Tells the user to populate the table.
     */
    private void tellUserToPopulateTable(){
        errorMessage.setText("ERROR: Please add an element to the table before trying to perform that action.");
        errorMessage.setVisible(true);
    }
    
    /**
     * Usage: Tells the user to create parts before doing that action.
     */
    private void tellUserToCreatePartsFirst(){
        errorMessage.setText("ERROR: Please add at least one part before trying to perform that action.");
        errorMessage.setVisible(true);
    }    

    /**
     * @return an arraylist of parts.
     * Usage: Populates the part table with parts from the inventory.
     */
    private List<Part> populatePartTable() {
        ArrayList<Part> list = new ArrayList<Part>();
        for(int i = 0; i < Inventory.getParts().size(); i++){
            list.add(Inventory.getParts().get(i));
        }
        return list;
    }
    
    /**
     * @return an arraylist of products.
     * Usage: Populates the product table with products from the inventory.
     */
    private List<Product> populateProductTable() {
        ArrayList<Product> list = new ArrayList<Product>();
        for(int i = 0; i < Inventory.getProducts().size(); i++){
            list.add(Inventory.getProducts().get(i));
        }
        return list;
    }
    
    /**
     * Usage: Called when the user presses the part search button.
     *        Filters the part table based on the user's query.
     */
    @FXML public void onPartSearchButtonPress(){
        String query = partQueryTextField.getText();
        if(query.isEmpty()){
            partTable.getItems().setAll(populatePartTable());
        }else{
            ObservableList<Part>  queryMatches =  FXCollections.observableArrayList();
            for(int i = 0; i < Inventory.getParts().size(); i++){
                if(Inventory.getParts().get(i).getName().toLowerCase().contains(query.toLowerCase())){
                    queryMatches.add(Inventory.getParts().get(i));
                }
            }
            partTable.getItems().setAll(queryMatches);
        }
    }
    
    /**
     * Usage: Called when the user presses the product search button.
     *        Filters the product table based on the user's query.
     */
    @FXML public void onProductSearchButtonPress(){
        String query = productQueryTextField.getText();
        if(query.isEmpty()){
            productTable.getItems().setAll(populateProductTable());
        }else{
            ObservableList<Product>  queryMatches =  FXCollections.observableArrayList();
            for(int i = 0; i < Inventory.getProducts().size(); i++){
                if(Inventory.getProducts().get(i).getName().toLowerCase().contains(query.toLowerCase())){
                    queryMatches.add(Inventory.getProducts().get(i));
                }
            }
            productTable.getItems().setAll(queryMatches);
        }
    }
}
