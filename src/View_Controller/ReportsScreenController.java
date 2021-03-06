package View_Controller;

import static Model.DBManager.runReport1;
import static Model.DBManager.runReport2;
import static Model.DBManager.runReport3;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Covarrubias
 */
public class ReportsScreenController implements Initializable {
    
    //Declare fields
    Stage stage;
    Parent scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reportOnActionButton1(ActionEvent event) {
        try {
            runReport1();
        } 
        catch (IOException ex) {
            ex.getMessage();
        }
        //Create a dialog box to inform of a sucessful report run
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Report 1 ran succesfully.");
        alert.setTitle("Report Successfully Created");
        alert.showAndWait();
    }

    @FXML
    private void reportOnActionButton2(ActionEvent event) {
        try {
            runReport2();
        }
        catch (IOException ex) {
            ex.getMessage();
        }
        //Create a dialog box to inform of a sucessful report run
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Report 2 ran succesfully.");
        alert.setTitle("Report Successfully Created");
        alert.showAndWait();
    }

    @FXML
    private void reportOnActionButton3(ActionEvent event) {
        try {
            runReport3();
        }
        catch (IOException ex) {
            ex.getMessage();
        }
        //Create a dialog box to inform of a sucessful report run
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Report 3 ran succesfully.");
        alert.setTitle("Report Successfully Created");
        alert.showAndWait();
    }

    @FXML
    private void reportOnActionExit(ActionEvent event) {
        //Create a confirmation dialog box to confirm cancelation of modifying an appointment
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to return to the Main Menu? Press OK to confirm.");
        alert.setTitle("Return to Main Menu");
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