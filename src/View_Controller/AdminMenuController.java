/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import static Model.DBManager.addNewUser;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Se7en
 */
public class AdminMenuController implements Initializable {
    
    //Stage and scence
    Stage stage;
    Parent scene;

    @FXML
    private TextField adminUsernameField;
    @FXML
    private PasswordField adminUserPasswordField;
    @FXML
    private PasswordField adminUserVerifyPasswordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void adminUserOnActionCreateBtn(ActionEvent event) {
        //Get the values from the text and password fields
        String name = adminUsernameField.getText();
        String password = adminUserPasswordField.getText();
        String verifyPassword = adminUserVerifyPasswordField.getText();
        
        //Check that username is not empty
        if(name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR WITH USERNAME");
            alert.setContentText("Username is empty");
            alert.showAndWait();
            return;
        }
        
        //Verify that the password matches
        if(!password.equals(verifyPassword)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR WITH PASSWORD");
            alert.setContentText("Password Does Not Match");
            alert.showAndWait();
            return;
        }
        
        // Call method to add user
        addNewUser(name, password, verifyPassword);
        
        // Clear fields for a new user
        adminUsernameField.setText("");
        adminUserPasswordField.setText("");
        adminUserVerifyPasswordField.setText("");
    }

    @FXML
    private void adminUserOnActionCancelBtn(ActionEvent event) {
        //Create the dialog box on exit
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to leave? Press OK to exit");
        alert.setTitle("Exit to Login Screen");
        alert.showAndWait().ifPresent((response -> {  //Quick response lambda
            if (response == ButtonType.OK) {
                try {
                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/View_Controller/LoginScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.setTitle("Appointment System");
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
