package InventoryManagementSystem.Controllers;

import InventoryManagementSystem.Models.InhousePart;
import InventoryManagementSystem.Models.Inventory;
import InventoryManagementSystem.Models.OutsourcedPart;
import InventoryManagementSystem.Properties;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddPartController implements Initializable{
    
    //FXML Labels
    @FXML private Label errorLabel;
    //FXML TextFields
    @FXML private TextField idTextField, nameTextField, priceCostTextField, maxTextField, minTextField,
                            invTextField, companyNameTextField, machineIdTextField;
    //FXML HBoxes
    @FXML private HBox companyNameHBox, machineIDHBox;
    //FXML Radio Buttons
    @FXML private RadioButton inHouseRadioButton;
    
    /**
     * @param location
     * @param resources 
     * Usage: Called when the scene is loaded.
     *        Initializes the scene.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int id = Inventory.getParts().size()+1;
        inHouseRadioButton.setSelected(true);
        companyNameHBox.setVisible(false);
        machineIDHBox.setVisible(true);
        idTextField.setText(String.valueOf(id));
        errorLabel.setVisible(false);
    }

    /**
     * @param event
     * @throws IOException if the resources are not available when accessed.
     * Usage: Called when the user presses the cancel button.
     *        If the user confirms, the user's changes are lost.
     */
    @FXML
    public void onCancelButtonPress(ActionEvent event) throws IOException{
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(Properties.getAPPLICATION_NAME_AND_VERSION());
        alert.setHeaderText(Properties.getDIALOG_HEADER());
        alert.setContentText(Properties.getCANCEL_CONFIRMATION_PROMPT());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            goToMainView(event);
        }
    }

    /**
     * @param event 
     * Usage: Called when the user selects a radio button in the partType toggle group.
     *        Changes the fields and labels that are displayed.
     */
    @FXML 
    public void onPartTypeToggleChange(ActionEvent event){
        if(inHouseRadioButton.isSelected()){
            companyNameHBox.setVisible(false);
            machineIDHBox.setVisible(true);
        }else{
            companyNameHBox.setVisible(true);
            machineIDHBox.setVisible(false);
        }
    }
    
    /**
     * @param event
     * Usage: Called when the user presses the save button.
     *        If the entered values are valid, the part is saved.
     */
    @FXML
    public void onSaveButtonPress(ActionEvent event){
        
        try{
            int id = Integer.parseInt(idTextField.getText());
            String name = nameTextField.getText();
            int inv = Integer.parseInt(invTextField.getText());
            double priceCost = Double.parseDouble(priceCostTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            
            if(min > max){
                errorLabel.setText("ERROR: Please correct your Max/Min values (min must be less than max).");
                errorLabel.setVisible(true);
            }else if(inv > max || inv < min){
                    errorLabel.setText("ERROR: Please correct your inv value (inv must be between min and max).");
                    errorLabel.setVisible(true);
            }else{
                if(inHouseRadioButton.isSelected()){
                    int machineId = Integer.parseInt(machineIdTextField.getText());
                    InhousePart newPart = new InhousePart(id,name,priceCost,inv,min,max,machineId);
                    Inventory.addPart(newPart);
                    goToMainView(event);
                }else{
                    String companyName = companyNameTextField.getText();
                    OutsourcedPart newPart = new OutsourcedPart(id,name,priceCost,inv,min,max,companyName);
                    Inventory.addPart(newPart);
                    goToMainView(event);
                }    
            }   
        }catch (Exception e){
            errorLabel.setText("ERROR: Please correct your values, they contain invalid characters.");
            errorLabel.setVisible(true);
        }
    }
    
    /**
     * @param event
     * @throws IOException if the resources are not available when accessed.
     * Usage: Changes scene to the main scene.
     */
    private void goToMainView(ActionEvent event) throws IOException{
        Parent MainParent = FXMLLoader.load(getClass().getResource("/InventoryManagementSystem/Views/Main.fxml"));
        Scene MainScene = new Scene(MainParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MainScene);
        MainScene.getRoot().requestFocus();
        window.show();
    }
}
