package View_Controller;

import static Model.DBManager.appointmentWithin15Minutes;
import static Model.DBManager.checkCredentials;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Covarrubias
 */
public class LoginScreenController implements Initializable {
    
    //Declare Fields
    Stage stage;
    Parent scene;
    private static String noCredentialsLabel;
    private static String incorrectCredentialsLabel;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField userTxtField;
    @FXML
    private PasswordField passwordTxtField;
    @FXML
    private Button loginButton;
    
    //Set the language method
    private void setLanguage(){
        ResourceBundle rb = ResourceBundle.getBundle("Resources/LoginLanguage", Locale.getDefault());
        usernameLabel.setText(rb.getString("usernameLabel"));
        passwordLabel.setText(rb.getString("passwordLabel"));
        loginButton.setText(rb.getString("loginButton"));
        noCredentialsLabel = rb.getString("noCredentialsLabel");
        incorrectCredentialsLabel = rb.getString("incorrectCredentialsLabel");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setLanguage(); 
    }

  @FXML
    void loginOnEnterKey(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            loginButton.fire();
        }
    }

    @FXML
    private void loginBtn(ActionEvent event) throws IOException {
        String userName = userTxtField.getText();
        String userPassword = passwordTxtField.getText();
        
        //Create dialog message if the username and/or password is empty
        if(userName.isEmpty() || userPassword.isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.WARNING, noCredentialsLabel);
            alert.setTitle("Error");
            alert.showAndWait();
        }
        else {
            if(checkCredentials(userName, userPassword) == true) {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.setTitle("Appointment System - Main Menu");
                stage.show();
                
                //Check to see if there is an appointment in the next 15 minutes
                appointmentWithin15Minutes();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, incorrectCredentialsLabel);
                alert.setTitle("Error");
                alert.showAndWait();
            }
        }
    }
    
}