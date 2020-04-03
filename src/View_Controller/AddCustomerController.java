package View_Controller;

import Model.Customer;
import static Model.DBManager.addNewCustomerChecks;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Covarrubias
 */
public class AddCustomerController implements Initializable {
    
    //Declare Fields
    Stage stage;
    Parent scene;

    @FXML
    private TextField addCustomerIdField;
    @FXML
    private TextField addCustomerNameField;
    @FXML
    private TextField addCustomerAddressField;
    @FXML
    private TextField addCustomerAddress2Field;
    @FXML
    private TextField addCustomerPhoneField;
    @FXML
    private TextField addCustomerCityField;
    @FXML
    private TextField addCustomerPostalCodeField;
    @FXML
    private TextField addCustomerCountryField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addCustomerOnActionSaveBtn(ActionEvent event) throws IOException {
        //int id = Integer.parseInt(addCustomerIdField.getText());
        String name = addCustomerNameField.getText();
        String address = addCustomerAddressField.getText();
        String address2 = addCustomerAddress2Field.getText();
        String phone = addCustomerPhoneField.getText();
        String city = addCustomerCityField.getText();
        String postalCode = addCustomerPostalCodeField.getText();
        String country = addCustomerCountryField.getText();
        
        //Customer input validation
        String validationMessage = Customer.customerValidation(name, address, address2, phone, city, postalCode, country); 
        
        //Create an Alert if customerValidation returns a string
        if (validationMessage.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR ADDING CUSTOMER");
            alert.setContentText(validationMessage);
            alert.getDialogPane().setMaxWidth(850);
            alert.showAndWait();
            return;
        }
        
        addNewCustomerChecks(name, address, address2, phone, city, postalCode, country);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointment System - Main Menu");
        stage.show();
    }

    @FXML
    private void addCustomerOnActionCancelBtn(ActionEvent event) {
         //Create the dialog box on exit
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel adding a customer? Press OK to exit");
        alert.setTitle("Exit to Main Screen");
        alert.showAndWait().ifPresent((response -> {  //Quick response lambda
            if (response == ButtonType.OK) {
                try {
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainMenu.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.setTitle("Appointment System - Main Menu");
                    stage.show();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } 
            else {
                alert.close();
            }
        }));
    }
}