package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;



/**
 *
 * @author Jorge Covarrubias
 */
public class DBManager {
    //Create Fields
    private static String currentUser;
    private static PreparedStatement prepStmt;
    private static Statement createStmt;
    
    //JDBC Url Parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U05JnY";
    
    //JDBC Url
    private static final String jdbcUrl = protocol + vendorName + ipAddress;
    
    //Driver and connection interface reference
    private static final String mySQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    //Username and password
    private static final String DBusername = "U05JnY";
    private static String DBpassword = "53688519034";
    
    public static void currentUser(String userName) {
        currentUser = userName;
    }
    
    //Create connection method to the DB
    public static Connection startConnection() {
        try {
            Class.forName(mySQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcUrl, DBusername, DBpassword);
            System.out.println("Connection Succesful");
        }
        catch(ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    //Create a statement from the connection
     public static Statement preparedStatement(String query){
        startConnection();
        try {
            prepStmt = conn.prepareStatement(query);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return prepStmt;
    }
     
     //Create a statement from the connection
     public static Statement createStatement(){
        startConnection();
        try {
            createStmt = conn.createStatement();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return prepStmt;
    }
    
    //Close the connection to the DB
    public static void closeConnection() {
        try {
            conn.close();
            System.out.println("Connection closed");
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Method to check if the user exist in the DB to log in
    public static boolean checkCredentials(String userName, String userPassword ) {
        int userId = getUserId(userName);
        boolean correctPassword = checkPassword(userId, userPassword);
        
        if (correctPassword == true) {
            currentUser(userName);
            return true;
        }
        else {
            return false;
        }
    }
    
    //Create a method to get the login user Id number from the DB
    private static int getUserId(String userName) {
        int userId = -1;
        String query = "SELECT userId FROM user WHERE userName = ? ";
        preparedStatement(query);
        
        try {
            prepStmt.setString(1, userName);
            //Get from DB userId for the username
            ResultSet userIdSet = prepStmt.executeQuery();
            //Set the userId to the value from the result set
            if (userIdSet.next()) {
                userId = userIdSet.getInt("userId");
            }
            userIdSet.close();
            prepStmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userId;
    }
    
    //Create a method to check that the password for the user trying to log is correct
    private static boolean checkPassword(int userId, String userPassword) {
        String passwordFromDB = null;
        String query = "SELECT password FROM user WHERE userId = ? ";
        
        preparedStatement(query);
        try {
            prepStmt.setInt(1, userId);
            //Get from DB the password for the user with that specific userId
            ResultSet passwordSet = prepStmt.executeQuery();
            if (passwordSet.next()) {
                passwordFromDB = passwordSet.getString("password");
            }
            else {
                return false;
            }
            passwordSet.close();
            prepStmt.close();
            conn.close();
            
            if(passwordFromDB.equals(userPassword)) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    //Create a method to check whether the customer already exist in the DB
    //and check if it is active or not. If not active, it will activate.
    public static void addNewCustomerChecks(String name, String address, String address2,
            String phone, String city, String postalCode, String country) {
        try {
            int countryId = getCountryId(country);
            int cityId = getCityId(city, countryId);
            int addressId = getAddressId(address, address2, cityId, postalCode, phone);
            
            //Check if the customer already exist in the DB
            //If the customer exist and not active, set as active
            //If the customer doesn't exit, create customer
            if (checkIfCustomerExist(name, addressId)) {
                String query = "SELECT active FROM customer WHERE customerName = ? "
                        + "AND addressId = ? ";
                preparedStatement(query);
                try {
                    prepStmt.setString(1, name);
                    prepStmt.setInt(2, addressId);
                    ResultSet activeCustomerSet = prepStmt.executeQuery();
                    activeCustomerSet.next();
                    int active = activeCustomerSet.getInt("active");
                     
                    if (active == 1) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "The customer already exist in the Database and is marked as active.");
                        alert.setTitle("Customer in DB");
                        alert.showAndWait();
                    }
                    else if (active == 0) {
                        setCustomerToActive(name, addressId);
                    }
                }
                catch (SQLException e) {
                System.out.println(e.getMessage());
                }
            }
            else {
                addCustomer(name, addressId);
            }
                
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Get the countryId from the country and if it doesn't exist, create one
    public static int getCountryId(String country) {
        int countryId = -1;
        String query = "SELECT countryId FROM country WHERE country = ? ";

        preparedStatement(query);
        try {
            prepStmt.setString(1, country);
            //Get the countryId from the DB
            ResultSet countryIdSet = prepStmt.executeQuery();
            if (countryIdSet.next()) {
                countryId = countryIdSet.getInt("countryId");
                countryIdSet.close();
                prepStmt.close();
                conn.close();

                return countryId;
            } 
            else {
                countryIdSet.close();
                query = "SELECT countryId FROM country ORDER BY countryId";
                Statement createStmt = conn.createStatement();
                //Get all countryId from the DB so we can add a new one at the end
                ResultSet checkCountryIdSet = createStmt.executeQuery(query);
                if (checkCountryIdSet.last()) {
                    countryId = checkCountryIdSet.getInt("countryId");
                    countryId++;
                    checkCountryIdSet.close();
                    createStmt.close();
                } 
                else {
                    checkCountryIdSet.close();
                    createStmt.close();
                    countryId = 1;
                }
                query = "INSERT INTO country VALUES (?, ?, CURRENT_DATE, '" + currentUser + "',"
                        + " CURRENT_TIMESTAMP, '" + currentUser + "')";
                preparedStatement(query);
                prepStmt.setInt(1, countryId);
                prepStmt.setString(2, country);
                prepStmt.executeUpdate();
                prepStmt.close();
                conn.close();

                return countryId;
            }
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    //Get the cityId from the city and if it doesn't exist, create one
    public static int getCityId(String city, int countryId) {
        int cityId = -1;
        String query = "SELECT cityId FROM city WHERE city = ? ";
    
        preparedStatement(query);
        try {
            prepStmt.setString(1, city);
            //Get the cityId from the DB
            ResultSet cityIdSet = prepStmt.executeQuery();
            if (cityIdSet.next()) {
                cityId = cityIdSet.getInt("cityId");
                cityIdSet.close();
                prepStmt.close();
                conn.close();
                
                return cityId;
            }
            else {
                cityIdSet.close();
                query = "SELECT cityId FROM city ORDER BY cityId";
                Statement createStmt = conn.createStatement();
                //Get all cityID from the DB so we can add a new one at the end
                ResultSet checkCityIdSet = createStmt.executeQuery(query);
                if (checkCityIdSet.last()) {
                    cityId = checkCityIdSet.getInt("cityId");
                    cityId++;
                    checkCityIdSet.close();
                    createStmt.close();
                }
                else {
                    checkCityIdSet.close();
                    createStmt.close();
                    cityId = 1;
                }
                query = "INSERT INTO city VALUES (?, ?, ?, CURRENT_DATE, '" + currentUser + "',"
                        + " CURRENT_TIMESTAMP, '" + currentUser + "')";
                preparedStatement(query);
                prepStmt.setInt(1, cityId);
                prepStmt.setString(2, city);
                prepStmt.setInt(3, countryId);
                prepStmt.executeUpdate();
                prepStmt.close();
                conn.close();
                
                return cityId;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    //Get the addressId from the address and if it doesn't exist, create one
    public static int getAddressId(String address, String address2, int cityId, String postalCode, 
        String phone) {
        int addressId = -1;
        String query = "SELECT addressId FROM address WHERE address = ? AND address2 = ?"
                + " And cityId = ? AND postalCode = ? AND phone = ? ";
    
        preparedStatement(query);
        try {
            prepStmt.setString(1, address);
            prepStmt.setString(2, address2);
            prepStmt.setInt(3, cityId);
            prepStmt.setString(4, postalCode);
            prepStmt.setString(5, phone);
            //prepStmt.setString(6, state);
            //Get the addressId from the DB
            ResultSet addressIdSet = prepStmt.executeQuery();
            if (addressIdSet.next()) {
                addressId = addressIdSet.getInt("addressId");
                addressIdSet.close();
                prepStmt.close();
                conn.close();
                
                return addressId;
            }
            else {
                addressIdSet.close();
                query = "SELECT addressId FROM address ORDER BY addressId";
                Statement createStmt = conn.createStatement();
                //Get all cityID from the DB so we can add a new one at the end
                ResultSet checkAddressIdSet = createStmt.executeQuery(query);
                if (checkAddressIdSet.last()) {
                    addressId = checkAddressIdSet.getInt("addressId");
                    addressId++;
                    checkAddressIdSet.close();
                    createStmt.close();
                }
                else {
                    checkAddressIdSet.close();
                    createStmt.close();
                    addressId = 1;
                }
                query = "INSERT INTO address VALUES (?, ?, ?, ?, ?, ?, CURRENT_DATE, '" + currentUser + "',"
                        + " CURRENT_TIMESTAMP, '" + currentUser + "')";
                preparedStatement(query);
                prepStmt.setInt(1, addressId);
                prepStmt.setString(2, address);
                prepStmt.setString(3, address2);
                prepStmt.setInt(4, cityId);
                prepStmt.setString(5, postalCode);
                prepStmt.setString(6, phone);
                //prepStmt.setString(7, state);
                prepStmt.executeUpdate();
                prepStmt.close();
                conn.close();
                
                return addressId;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    private static boolean checkIfCustomerExist(String name, int addressId) throws SQLException {
            String query = "SELECT customerId FROM customer WHERE customerName = ? "
                    + "AND addressId = ?";
            preparedStatement(query);
            
            prepStmt.setString(1, name);
            prepStmt.setInt(2, addressId);
            ResultSet checkCustomerSet = prepStmt.executeQuery();
            if (checkCustomerSet.next()){
                checkCustomerSet.close();
                return true;
            }
            else {
                checkCustomerSet.close();
                return false;
            }
    }
    
    public static void setCustomerToActive(String name, int addressId) {
        String query = "UPDATE customer SET active = 1, lastUpdate = CURRENT_TIMESTAMP,"
                + "lastUpdateBy = '"+currentUser+"' WHERE customerName = ? "
                + "AND addressId = ?";
        preparedStatement(query);
        
        try {
            prepStmt.setString(1, name);
            prepStmt.setInt(2, addressId);
            
            //Create a confirmation dialog box to confirm setting customer to active
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The customer exist in the Database but set \"inactive\". Do you want to set the customer to \"active\"?");
            alert.setTitle("Activate Customer");
            Optional<ButtonType> result = alert.showAndWait();
            //Set the cutomer to active if Ok button is clicked
            if (result.get() == ButtonType.OK) {
                prepStmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void addCustomer(String name, int addressId) {
        String query = "SELECT customerId FROM customer ORDER BY customerId";
        int customerId;
        startConnection();
        try {
           Statement createStmt = conn.createStatement();
           ResultSet allCustomerIdsSet = createStmt.executeQuery(query);
           if (allCustomerIdsSet.last()) {
               customerId = allCustomerIdsSet.getInt("customerId");
               customerId++;
               allCustomerIdsSet.close();
               createStmt.close();
           }
           else {
               allCustomerIdsSet.close();
               createStmt.close();
               customerId = 1;
           }
           query = "INSERT INTO customer VALUES (?, ?, ?, 1, CURRENT_DATE,"
                   + "'"+ currentUser + "', CURRENT_TIMESTAMP, '" + currentUser + "')";
           preparedStatement(query);
           prepStmt.setInt(1, customerId);
           prepStmt.setString(2, name);
           prepStmt.setInt(3, addressId);
           prepStmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Populate the customer list from the DB
    public static void updateCustomerList() {
        //Create the list to populate to
        ObservableList<Customer> customerList = CustomerList.getAllCustomers();
        //Make sure it is empty since we are going to populate the list from the DB
        customerList.clear();
        String query = "SELECT customerId FROM customer WHERE active = 1";
        ArrayList<Integer> activeCustomerIdList = new ArrayList<>();
        startConnection();
        try {
            Statement createStmt= conn.createStatement();
            ResultSet activeCustomerSet = createStmt.executeQuery(query);
            while(activeCustomerSet.next()) {
                activeCustomerIdList.add(activeCustomerSet.getInt("customerId"));
            }
            for (int customerId : activeCustomerIdList) {
                //Create a query to retrieve customer name, id , and active from
                //customer table. We already have the ID from the previous query.
                query = "SELECT customerName, addressId, active FROM customer WHERE"
                        + " customerId = '"+ customerId +"' ";
                ResultSet customerSet = createStmt.executeQuery(query);
                customerSet.next();
                //Set variables with the data from the Database
                String name = customerSet.getString("customerName");
                int addressId = customerSet.getInt("addressId");
                byte active = customerSet.getByte("active");
                
                //Create a query to get the address information for the current customer
                query = "SELECT address, address2, cityId, postalCode, phone FROM address "
                        + " WHERE addressId = '"+ addressId +"' ";
                ResultSet addressSet = createStmt.executeQuery(query);
                addressSet.next();
                //Set variables with the data from the DB
                String address = addressSet.getString("address");
                String address2 = addressSet.getString("address2");
                int cityId = addressSet.getInt("cityId");
                String postalCode = addressSet.getString("postalCode");
                String phone = addressSet.getString("phone");
                
                //Create a query to get the city information for the current customer
                query = "SELECT city, countryId FROM city WHERE cityId = '"+ cityId + "'";
                ResultSet citySet = createStmt.executeQuery(query);
                citySet.next();
                //Set variables with the data from the DB
                String city = citySet.getString("city");
                int countryId = citySet.getInt("countryId");
                
                //Create a query to get the country information for the current customer
                query = "SELECT country FROM country WHERE countryId = '"+ countryId + "'";
                ResultSet countrySet = createStmt.executeQuery(query);
                countrySet.next();
                //Set variables with the data from the DB
                String country = countrySet.getString("country");
                
                //Close resultsets and connection
                activeCustomerSet.close();
                customerSet.close();
                addressSet.close();
                citySet.close();
                countrySet.close();
                createStmt.close();
                conn.close();
                
                //Create customer with constructor
                Customer customer = new Customer(customerId, name, addressId, active, 
                        address, address2, cityId, postalCode, phone, city, countryId, country);
                
                //Add customer to the observableList customer
                customerList.add(customer);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Create a method to check whether the customer already exist in the DB
    //and check if it is active or not. If not active, it will activate.
    public static void modifyCustomerChecks(int id, String name, String address, String address2,
            String phone, String city, String postalCode, String country) {
        try {
            int countryId = getCountryId(country);
            int cityId = getCityId(city, countryId);
            int addressId = getAddressId(address, address2, cityId, postalCode, phone);
            
            //Check if the customer already exist in the DB
            //If the customer exist and not active, set as active
            //If the customer doesn't exit, create customer
            if (checkIfCustomerExist(name, addressId)) {
                String query = "SELECT active FROM customer WHERE customerName = ? "
                        + "AND addressId = ? ";
                preparedStatement(query);
                try {
                    prepStmt.setString(1, name);
                    prepStmt.setInt(2, addressId);
                    ResultSet activeCustomerSet = prepStmt.executeQuery();
                    activeCustomerSet.next();
                    int active = activeCustomerSet.getInt("active");
                     
                    if (active == 1) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "The customer already exist in the Database and is marked as active.");
                        alert.setTitle("Customer in DB");
                        alert.showAndWait();
                    }
                    else if (active == 0) {
                        setCustomerToActive(name, addressId);
                    }
                }
                catch (SQLException e) {
                System.out.println(e.getMessage());
                }
            }
            else {
                modifyCustomer(id, name, addressId);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void modifyCustomer(int id, String name, int addressId) {
        String query = "UPDATE customer SET customerName = ?,addressId = ? WHERE customerId = ?";
        preparedStatement(query);
        try {
           prepStmt.setString(1, name);
           prepStmt.setInt(2, addressId);
           prepStmt.setInt(3, id);
           prepStmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    //Set customer to inactive
    public static void setCustomerToInactive(Customer customer) {
        //Create varibales with customer information
        int customerId = customer.getId();
        String customerName = customer.getName();
        int addressId = customer.getAddressId();
        String query = "UPDATE customer SET active = 0, lastUpdate = CURRENT_TIMESTAMP,"
                + "lastUpdateBy = '" + currentUser + "' WHERE customerId = '" + customerId + "' "
                + "AND customerName = '" + customerName + "' AND addressId = '" + addressId + "' ";
        createStatement();

        //Create a confirmation dialog box to confirm deletion/inactive
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete/set inactive customer " + customerName + "? Press OK to delete");
        alert.setTitle("Confirm deletion/incative");
        alert.showAndWait().ifPresent((response -> {  //quick lambda
            if (response == ButtonType.OK) {
                try {
                    createStmt.executeUpdate(query);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                alert.close();
            }
        }));
    }
    
    public static void addNewAppointmentCheck(int customerId, String title, String description, 
            String location, String contact, String type, String url, String startTime, String endTime) {
        
        //TODO
        //MAke sure time doesn't overlapt with another appointment
        addAppointment(customerId, title, description, location, contact, type, url, startTime, endTime);
        
       
    }
    
    private static void addAppointment(int customerId, String title, String description, String location, 
            String contact, String type, String url, String startTime, String endTime) {
        
        String query = "SELECT appointmentId from appointment ORDER BY appointmentId";
        int appointmentId;
        int userId = getUserId(currentUser);
        createStatement();
        try {
            ResultSet allAppointmentIds = createStmt.executeQuery(query);
            if (allAppointmentIds.last()) {
                appointmentId = allAppointmentIds.getInt("appointmentId");
                appointmentId++;
                allAppointmentIds.close();
                createStmt.close();
            }
            else {
                allAppointmentIds.close();
                createStmt.close();
                appointmentId = 1;
            }
            query = "INSERT INTO appointment VALUES ('"+ appointmentId + "', '"+ customerId +"', "
                    + " '"+ userId +"', ?, ?, ?, ?, ?, ?, '"+ startTime +"', '"+ endTime +"',"
                    + "CURRENT_DATE, '"+ currentUser +"', CURRENT_TIMESTAMP, '"+ currentUser +"')";
            preparedStatement(query);
            prepStmt.setString(1, title);
            prepStmt.setString(2, description);
            prepStmt.setString(3, location);
            prepStmt.setString(4, contact);
            prepStmt.setString(5, type);
            prepStmt.setString(6, url);
            prepStmt.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}