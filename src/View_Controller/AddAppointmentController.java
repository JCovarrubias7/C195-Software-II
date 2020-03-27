package View_Controller;

import Model.Customer;
import Model.CustomerList;
import static Model.DBManager.addNewAppointmentCheck;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Covarrubias
 */
public class AddAppointmentController implements Initializable {
    
    //Define Fields
    Stage stage;
    Parent scene;
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    ObservableList<Customer> associatedList = FXCollections.observableArrayList();
    String hourAfterConvertion;
    int customerId = 0;

    @FXML
    private TextField addApptTypeField;
    @FXML
    private TextField addApptTitleField;
    @FXML
    private TextArea addApptDescriptionField;
    @FXML
    private TextField addApptLocationField;
    @FXML
    private TextField addApptContactField;
    @FXML
    private TextField addApptURLField;
    @FXML
    private DatePicker addApptDateField;
    @FXML
    private ComboBox<String> addApptStartHourCB;
    @FXML
    private ComboBox<String> addApptStartMinuteCB;
    @FXML
    private ChoiceBox<String> addApptStartAMPMChoice;
    @FXML
    private ComboBox<String> addApptEndHourCB;
    @FXML
    private ComboBox<String> addApptEndMinuteCB;
    @FXML
    private ChoiceBox<String> addApptEndAMPMChoice;
    @FXML
    private TextField addApptSearchField;
    @FXML
    private TableView<Customer> addApptCustomerTableView;
    @FXML
    private TableColumn<Customer, String> customersNameCol;
    @FXML
    private TableColumn<Customer, String> customersCityCol;
    @FXML
    private TableColumn<Customer, String> customersCountryCol;
    @FXML
    private TableColumn<Customer, String> customersPhoneCol;
    @FXML
    private TableView<Customer> addApptAssociatedCustomer;
    @FXML
    private TableColumn<Customer, String> associatedNameCol;
    @FXML
    private TableColumn<Customer, String> associatedCityCol;
    @FXML
    private TableColumn<Customer, String> associatedCountryCol;
    @FXML
    private TableColumn<Customer, String> associatedPhoneCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Set the hours and minutes for the Combo boxes
        hours.addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        minutes.addAll("00", "15", "30", "45");
        
        //Set the lists to the ComboBox
        addApptStartHourCB.setItems(hours);
        addApptStartMinuteCB.setItems(minutes);
        addApptEndHourCB.setItems(hours);
        addApptEndMinuteCB.setItems(minutes);
        
        //Set the tableView to the customer list already created
        addApptCustomerTableView.setItems(CustomerList.getAllCustomers());
        addApptAssociatedCustomer.setItems(associatedList);
        
        //Set up the customers columns to go get the specific info from the customer object
        customersNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customersCityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        customersCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        customersPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        //Set up the associated columns to go get the specific info from the customer object
        associatedNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedCityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        associatedCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        associatedPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
    }    

    @FXML
    private void addApptSearchOnActionButton(ActionEvent event) {
        String partSearchFieldTxt = addApptSearchField.getText();
        //Create warning dialog box for empty search field
        if (partSearchFieldTxt.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter an name of a customer to search");
            alert.setTitle("ERROR SEARCHING");
            alert.showAndWait();
        } 
        else {
            boolean found = false;
            ObservableList<Customer> filteredCustomerList = FXCollections.observableArrayList();
            filteredCustomerList = CustomerList.lookUpCustomer(partSearchFieldTxt);
            
            if (filteredCustomerList.size() > 0) {
                found = true;
                addApptCustomerTableView.setItems(filteredCustomerList);
            }
            if (found == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "The item searched was not found in Inventory");
                alert.setTitle("NO RESULTS");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void addApptAddCustomerButton(ActionEvent event) {
        Customer customer = addApptCustomerTableView.getSelectionModel().getSelectedItem();
         //Create a dialog box warning if no custer was selected to associate with the product
        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a customer");
            alert.setTitle("Customer Not Selected");
            alert.showAndWait();
        }
        else {
            if (associatedList.size() == 0) {
                //add customer to the associatedList
                associatedList.add(customer);
                //Set customerId
                customerId = customer.getId();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "A customer has already been added");
                alert.setTitle("Too Many Customers");
                alert.showAndWait();
            }
        }
    }
    
    @FXML
    private void addApptDeleteAssociatedButton(ActionEvent event){ 
        Customer customer = addApptAssociatedCustomer.getSelectionModel().getSelectedItem();
        //Create dialog box for no customer selected to delete
        if(customer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "There are no customers to delete or select a customer to delete");
            alert.setTitle("Nothing To Delete");
            alert.showAndWait();
        } 
        else {
             //Create the dialog box confirmation of deleting the associated customer from the appointment
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the associated customer from the appointment? Press OK to delete");
            alert.setTitle("Delete Associated Part");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                //Remove customer from associatedList
                associatedList.remove(customer);
                //Clear customerId
                customerId = 0;
            } else {
                alert.close();
            }
//            if (product.getAllAssociatedParts().isEmpty()) {
//                addProductDeleteBtn.setDisable(true);
//            }
        }
    }
    
    @FXML
    private void addAppSaveButton(ActionEvent event) throws IOException{ 
        //Check for customerId, if nothing, create a warning
        if(customerId == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "There is no customer added to create the appointment");
            alert.setTitle("No Customer Associated");
            alert.showAndWait();
        }
        
        String type = addApptTypeField.getText();
        String title = addApptTitleField.getText();
        String description = addApptDescriptionField.getText();
        String location = addApptLocationField.getText();
        String contact = addApptContactField.getText();
        String url = addApptURLField.getText();
        LocalDate date = addApptDateField.getValue();
        String startHour = addApptStartHourCB.getValue();
        String startMinutes = addApptStartMinuteCB.getValue();
        String startAMPM = addApptStartAMPMChoice.getValue();
        String endHour = addApptEndHourCB.getValue();
        String endMinutes = addApptEndMinuteCB.getValue();
        String endAMPM = addApptEndAMPMChoice.getValue();
        
        //Convert the hours from a 12hour format to a 24hour format
        convertToTwentyFourHours(startHour, startAMPM);
        //Set the dateTime for the start of the appointment
        LocalDateTime startLdt = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), Integer.parseInt(hourAfterConvertion), Integer.parseInt(startMinutes));
        //Obtain the ZonedDateTime version of LocalDateTime
        ZonedDateTime startLocalzdt = ZonedDateTime.of(startLdt, ZoneId.systemDefault());
        //Obtain the UTC ZoneDateTime of the ZoneDateTime version of LocalDateTime
        ZonedDateTime startUtcZdt = startLocalzdt.withZoneSameInstant(ZoneOffset.UTC);
        //Convert ZoneDateTime to string
        String stringStartZDT = startUtcZdt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        //Convert the hours from a 12hour format to a 24hour format
        convertToTwentyFourHours(endHour, endAMPM);
       //Set the dateTime for the end of the appointment
        LocalDateTime endLdt = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), Integer.parseInt(hourAfterConvertion), Integer.parseInt(endMinutes));
        //Obtain the ZonedDateTime version of LocalDateTime
        ZonedDateTime endLocalzdt = ZonedDateTime.of(endLdt, ZoneId.systemDefault());
        //Obtain the UTC ZoneDateTime of the ZoneDateTime version of LocalDateTime
        ZonedDateTime endUtcZdt = endLocalzdt.withZoneSameInstant(ZoneOffset.UTC);
        //Convert ZoneDateTime to string
        String stringEndZDT = endUtcZdt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        
        addNewAppointmentCheck(customerId, title, description, location, contact, type, url, stringStartZDT, stringEndZDT);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointment System - Main Menu");
        stage.show();
    }
    
    @FXML
    private void addAppCancelButton(ActionEvent event){
        //Create a confirmation dialog box to confirm cancelation of creating an appointment
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel creating an appointment? Press OK to cancel.");
        alert.setTitle("Confirm Cancelation");
        alert.showAndWait().ifPresent((response -> {  //quick lambda
            if (response == ButtonType.OK) {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                try {
                    scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainMenu.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(ModifyCustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.setScene(new Scene(scene));
                stage.setTitle("Appointment System - Main Menu");
                stage.show();
            } else {
                alert.close();
            }
        }));
    }
    
    private void convertToTwentyFourHours(String hour, String AMPM) {
        int intHour = Integer.valueOf(hour);
        if (AMPM.equals("PM")) {
            if (intHour == 12) {
                intHour = 12;
            } else {
                intHour = intHour + 12;
            }
        } else {
            if (intHour == 12) {
                intHour = 00;
            }
        }
        hourAfterConvertion = String.valueOf(intHour);
    }
    
}