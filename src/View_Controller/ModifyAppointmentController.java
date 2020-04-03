/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Appointment;
import Model.Customer;
import Model.CustomerList;
import static Model.DBManager.modAppointmentCheck;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
 * @author Se7en
 */
public class ModifyAppointmentController implements Initializable {
    
    //Define Fields
    Stage stage;
    Parent scene;
    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();
    ObservableList<Customer> associatedList = FXCollections.observableArrayList();
    String hourAfterConvertion;
    int customerId = 0;
    int appointmentId;

    @FXML
    private TextField modApptTypeField;
    @FXML
    private TextField modApptTitleField;
    @FXML
    private TextArea modApptDescriptionField;
    @FXML
    private TextField modApptLocationField;
    @FXML
    private TextField modApptContactField;
    @FXML
    private TextField modApptURLField;
    @FXML
    private DatePicker modApptDateField;
    @FXML
    private ComboBox<String> modApptStartHourCB;
    @FXML
    private ComboBox<String> modApptStartMinuteCB;
    @FXML
    private ChoiceBox<String> modApptStartAMPMChoice;
    @FXML
    private ComboBox<String> modApptEndHourCB;
    @FXML
    private ComboBox<String> modApptEndMinuteCB;
    @FXML
    private ChoiceBox<String> modApptEndAMPMChoice;
    @FXML
    private TextField modApptSearchField;
    @FXML
    private TableView<Customer> modApptCustomerTableView;
    @FXML
    private TableColumn<Customer, String> customersNameCol;
    @FXML
    private TableColumn<Customer, String> customersCityCol;
    @FXML
    private TableColumn<Customer, String> customersCountryCol;
    @FXML
    private TableColumn<Customer, String> customersPhoneCol;
    @FXML
    private TableView<Customer> modApptAssociatedCustomer;
    @FXML
    private TableColumn<Customer, String> associatedNameCol;
    @FXML
    private TableColumn<Customer, String> associatedCityCol;
    @FXML
    private TableColumn<Customer, String> associatedCountryCol;
    @FXML
    private TableColumn<Customer, String> associatedPhoneCol;
    @FXML
    private Button modApptDeleteButton;
    
    public void sendAppt(Appointment appointment) {
        modApptTypeField.setText(appointment.getType());
        modApptTitleField.setText(appointment.getTitle());
        modApptDescriptionField.setText(appointment.getDescriptions());
        modApptLocationField.setText(appointment.getLocation());
        modApptContactField.setText(appointment.getContact());
        modApptURLField.setText(appointment.getUrl());
        
        //Set the start time instant from the UTC ZoneDateTime in the appointment
        Instant startInstant = appointment.getZdtStart().toInstant();
 
        //Convert the UTC date to local time and get the Date
        LocalDateTime startLocalDateTime = LocalDateTime.ofInstant(startInstant, ZoneId.systemDefault());
        int startYear = startLocalDateTime.getYear();
        Month startMonth = startLocalDateTime.getMonth();
        int startDay = startLocalDateTime.getDayOfMonth();
        LocalDate startLocalDate = LocalDate.of(startYear, startMonth, startDay);
        
        //Create the format to get the 12hour version of the hour from the LocalDateTime
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("hh");
        //Create the format to get the minutes from the LocalDateTime
        DateTimeFormatter minutesFormatter = DateTimeFormatter.ofPattern("mm");
        //Create the format to get the AM PM from the LocalDateTime
        DateTimeFormatter AmPmFormatter = DateTimeFormatter.ofPattern("a");
        
        modApptDateField.setValue(startLocalDate);
        modApptStartHourCB.setValue(String.valueOf(hourFormatter.format(startLocalDateTime)));
        modApptStartMinuteCB.setValue(String.valueOf(minutesFormatter.format(startLocalDateTime)));
        modApptStartAMPMChoice.setValue(AmPmFormatter.format(startLocalDateTime));
        
        //Set the start time instant from the UTC ZoneDateTime in the appointment
        Instant endInstant = appointment.getZdtEnd().toInstant();
        //Convert the UTC date to local time and get the Date
        LocalDateTime endLocalDateTime = LocalDateTime.ofInstant(endInstant, ZoneId.systemDefault());
        
        modApptEndHourCB.setValue(String.valueOf(hourFormatter.format(endLocalDateTime)));
        modApptEndMinuteCB.setValue(String.valueOf(minutesFormatter.format(endLocalDateTime)));
        modApptEndAMPMChoice.setValue(String.valueOf(AmPmFormatter.format(endLocalDateTime)));
        
        //Set the customer Id from the appointment 
        customerId = appointment.getCustomerId();
        //Create the list to populate to retrieve the customerName from
        ObservableList<Customer> customerList = CustomerList.getAllCustomers();
        customerList.forEach(customer -> {  //a simple iteration lambda
            if (customer.getId() == customerId) {
                associatedList.add(customer);
                //Set the Delete Button to be enabled
                modApptDeleteButton.setDisable(false);
            }
        });
        
        //Set appintmentId
        appointmentId = appointment.getAppId();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //Set the hours and minutes for the Combo boxes
        hours.addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        minutes.addAll("00", "15", "30", "45");
        
        //Set the lists to the ComboBox
        modApptStartHourCB.setItems(hours);
        modApptStartMinuteCB.setItems(minutes);
        modApptEndHourCB.setItems(hours);
        modApptEndMinuteCB.setItems(minutes);
        
        //Set the tableView to the customer list already created
        modApptCustomerTableView.setItems(CustomerList.getAllCustomers());
        modApptAssociatedCustomer.setItems(associatedList);
        
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
    private void modApptSearchOnActionButton(ActionEvent event) {
        String partSearchFieldTxt = modApptSearchField.getText();
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
                modApptCustomerTableView.setItems(filteredCustomerList);
            }
            if (found == false) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "The item searched was not found in Inventory");
                alert.setTitle("NO RESULTS");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void modApptAddCustomerButton(ActionEvent event) {
        Customer customer = modApptCustomerTableView.getSelectionModel().getSelectedItem();
         //Create a dialog box warning if no custer was selected to associate with the product
        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a customer");
            alert.setTitle("Customer Not Selected");
            alert.showAndWait();
        }
        else {
            if (associatedList.isEmpty()) {
                //add customer to the associatedList
                associatedList.add(customer);
                //Set customerId
                customerId = customer.getId();
                //Set the delete button to be enabled
                modApptDeleteButton.setDisable(false);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "A customer has already been added");
                alert.setTitle("Too Many Customers");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void modApptDeleteAssociatedButton(ActionEvent event) {
        Customer customer = modApptAssociatedCustomer.getSelectionModel().getSelectedItem();
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
                //Set the Delete button to be disabled
                modApptDeleteButton.setDisable(true);
            } else {
                alert.close();
            }
//            if (product.getAllAssociatedParts().isEmpty()) {
//                addProductDeleteBtn.setDisable(true);
//            }
        }
    }

    @FXML
    private void modAppSaveButton(ActionEvent event) throws IOException {
        //Check for customerId, if nothing, create a warning
        if(customerId == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "There is no customer added to create the appointment");
            alert.setTitle("No Customer Associated");
            alert.showAndWait();
        }
        
        String type = modApptTypeField.getText();
        String title = modApptTitleField.getText();
        String description = modApptDescriptionField.getText();
        String location = modApptLocationField.getText();
        String contact = modApptContactField.getText();
        String url = modApptURLField.getText();
        LocalDate date = modApptDateField.getValue();
        String startHour = modApptStartHourCB.getValue();
        String startMinutes = modApptStartMinuteCB.getValue();
        String startAMPM = modApptStartAMPMChoice.getValue();
        String endHour = modApptEndHourCB.getValue();
        String endMinutes = modApptEndMinuteCB.getValue();
        String endAMPM = modApptEndAMPMChoice.getValue();
        
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
        
        modAppointmentCheck(appointmentId, customerId, title, description, location, contact, type, url, stringStartZDT, stringEndZDT);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointment System - Main Menu");
        stage.show();
    }

    @FXML
    private void modAppCancelButton(ActionEvent event) {
        //Create a confirmation dialog box to confirm cancelation of modifying an appointment
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel modifying an appointment? Press OK to cancel.");
        alert.setTitle("Confirm Cancelation");
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
