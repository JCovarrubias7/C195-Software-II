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
    
    //Search for a customer by string
    public static ObservableList<Customer> lookUpCustomer(String customerName) {
        ObservableList<Customer> searchedCustomer = FXCollections.observableArrayList();
        if (customerName.length() == 0) {
            searchedCustomer = customerList;
        }
        for (Customer customer : getAllCustomers()) {
            if (customer.getName().toLowerCase().contains(customerName.toLowerCase())) {
                searchedCustomer.add(customer);
            }
        }
        return searchedCustomer;
    }
}
