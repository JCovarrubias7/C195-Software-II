package View_Controller;

import Model.Customer;
import static Model.DBManager.modifyCustomerChecks;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Covarrubias
 */
public class ModifyCustomerController implements Initializable {
    
    //Declare Fields
    Stage stage;
    Parent scene;

    @FXML
    private TextField modCustomerIdField;
    @FXML
    private TextField modCustomerNameField;
    @FXML
    private TextField modCustomerAddressField;
    @FXML
    private TextField modCustomerAddress2Field;
    @FXML
    private TextField modCustomerPhoneField;
    @FXML
    private TextField modCustomerCityField;
    @FXML
    private TextField modCustomerPostalCodeField;
    @FXML
    private TextField modCustomerCountryField;
    
    public void sendCustomer(Customer customer) {
        modCustomerIdField.setText(String.valueOf(customer.getId()));
        modCustomerNameField.setText(customer.getName());
        modCustomerAddressField.setText(customer.getAddress());
        modCustomerAddress2Field.setText(customer.getAddress2());
        modCustomerPhoneField.setText(customer.getPhone());
        modCustomerCityField.setText(customer.getCity());
        modCustomerPostalCodeField.setText(customer.getPostalCode());
        modCustomerCountryField.setText(customer.getCountry());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modCustomerOnActionSaveBtn(ActionEvent event) throws IOException {
        int id = Integer.parseInt(modCustomerIdField.getText());
        String name = modCustomerNameField.getText();
        String address = modCustomerAddressField.getText();
        String address2 = modCustomerAddress2Field.getText();
        String phone = modCustomerPhoneField.getText();
        String city = modCustomerCityField.getText();
        String postalCode = modCustomerPostalCodeField.getText();
        String country = modCustomerCountryField.getText();
        
        //Customer input validation
        String validationMessage = Customer.customerValidation(name, address, address2, phone, city, postalCode, country); 
        
        //Create an Alert if customerValidation returns a string
        if (validationMessage.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR ADDING CUSTOMER");
            alert.setContentText(validationMessage);
            alert.getDialogPane().setMinWidth(650);
            alert.getDialogPane().setMaxWidth(850);
            alert.showAndWait();
            return;
        }
        
        modifyCustomerChecks(id, name, address, address2, phone, city, postalCode, country);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointment System - Main Menu");
        stage.show();
    }

    @FXML
    private void modCustomerOnActionCancelBtn(ActionEvent event) {
        //Create a confirmation dialog box to confirm cancelation of modifying customer
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel modifying customer? Press OK to cancel.");
        alert.setTitle("Confirm Modification Cancelation");
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