package View_Controller;

import Model.Appointment;
import Model.AppointmentList;
import Model.Customer;
import Model.CustomerList;
import static Model.DBManager.closeConnection;
import static Model.DBManager.setCustomerToInactive;
import static Model.DBManager.updateCalendarList;
import static Model.DBManager.updateCustomerList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jorge Covarrubias
 */
public class MainMenuController implements Initializable {
    
    //Declare Fields
    Stage stage;
    Parent scene;

    @FXML
    private TableView<Appointment> calendarTableView;
    @FXML
    private TableColumn<Appointment, String> calendarUserCol;
    @FXML
    private TableColumn<Appointment, String> calendarCustomerCol;
    @FXML
    private TableColumn<Appointment, String> calendarTypeCol;
    @FXML
    private TableColumn<Appointment, String> calendarLocationCol;
    @FXML
    private TableColumn<Appointment, String> calendarStartTimeCol;
    @FXML
    private TableColumn<Appointment, String> calendarEndTimeCol;
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, String> customerNameCol;
    @FXML
    private TableColumn<Customer, String> customerAddressCol;
    @FXML
    private TableColumn<Customer, String> customerAddress2Col;
    @FXML
    private TableColumn<Customer, String> customerCityCol;
    @FXML
    private TableColumn<Customer, String> customerCountryCol;
    @FXML
    private TableColumn<Customer, String> customerPhoneCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // appointment withing 15 minutes of loggin in
        //Set the colums to go get the specific info from the customer object
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerAddress2Col.setCellValueFactory(new PropertyValueFactory<>("address2"));
        customerCityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        //Set the colums to get the specifc info from the appointment object
        calendarUserCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        calendarCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        calendarTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        calendarLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        calendarStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        calendarEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        
        //Set the table with the customerlist
        mainUpdateCustomerTableView();
        //Set the table with the appointmentlist
        mainUpdateCalendarTableView();
        
    }    
    
    public void mainUpdateCustomerTableView() {
        updateCustomerList();
        customerTableView.setItems(CustomerList.getAllCustomers());
    }
    public void mainUpdateCalendarTableView() {
        updateCalendarList();
        calendarTableView.setItems(AppointmentList.getAllAppointments());
    }

    @FXML
    private void mainAppointmentAdd(ActionEvent event) throws IOException {
        //Open the AddCustomer scene on button press
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointment System - Create Appointment");
        stage.show();
    }

    @FXML
    private void mainAppointmentModify(ActionEvent event) {
    }

    @FXML
    private void mainAppointmentDelete(ActionEvent event) {
    }

    @FXML
    private void mainReports(ActionEvent event) {
    }

    @FXML
    private void mainCustomerAdd(ActionEvent event) throws IOException {
        //Open the AddCustomer scene on button press
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.setTitle("Appointment System - Add Customer");
        stage.show();
    }

    @FXML
    private void mainCustomerModify(ActionEvent event) throws IOException {
        //Grab the selected customer from the tableView
        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        //Create a warning dialog box letting the user know that no customer has been selected to delete
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "There is no customer selected to modify");
            alert.setTitle("No Customer Selected");
            alert.showAndWait();
        } 
        else {
            //Send the selected part to the PartModify controller
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View_Controller/ModifyCustomer.fxml"));
            loader.load();
            ModifyCustomerController MCController = loader.getController();
            MCController.sendCustomer(selectedCustomer);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.setTitle("Appointment System - Modify Customer");
            stage.show();
        }
    }

    @FXML
    private void mainCustomerDelete(ActionEvent event) {
        //Grab the selected customer from the tableView
        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        //Create a warning dialog box letting the user know that no customer has been selected to delete
        if (selectedCustomer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "There are no customers to delete or select a customer to delete");
            alert.setTitle("No Customer Selected");
            alert.showAndWait();
        }
        else {
            setCustomerToInactive(selectedCustomer);
            updateCustomerList();
        }
    }

    @FXML
    private void mainWeeklyView(ActionEvent event) {
    }

    @FXML
    private void mainMonthlyView(ActionEvent event) {
    }
    
    @FXML
    private void mainExit(ActionEvent event) {
        //Create the dialog box on exit
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to Exit? Press OK to exit");
        alert.setTitle("Exit Application");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            closeConnection();
            System.exit(0);
        }
        else {
            alert.close();
        }
    }
    
}
