package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jorge Covarrubias
 */
public class CustomerList {
    
    //Create a static list that will populate from the DB
    private static ObservableList<Customer> customerList = FXCollections.observableArrayList();
    
    //Get all customers
    public static ObservableList<Customer> getAllCustomers() {
        return customerList;
    }
}
