package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jorge Covarrubias
 */
public class AppointmentList {
    
    //Create a static list that will be populated from the DB
    private static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    
    //Get all appointments
    public static ObservableList<Appointment> getAllAppointments() {
        return appointmentList;
    }
    
}