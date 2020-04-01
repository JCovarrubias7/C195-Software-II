package Model;

/**
 *
 * @author JorgeCovarrubias
 */
public class Customer {
    //Declare Fields
    private byte active;
    private int id, addressId, cityId, countryId;
    private String name, address, address2, postalCode, phone, city, state, country;

    
    //Declare Constructor
    public Customer(int id, String name, int addressId, byte active, String address, 
            String address2, int cityId, String postalCode, String phone, String city, 
            int countryId, String country) {
        this.id = id;
        this.name = name;
        this.addressId = addressId;
        this.active = active;
        this.address = address;
        this.address2 = address2;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
        this.city = city;
        this.countryId = countryId;
        this.country = country;
    }
    
    public Customer() {
    }
    
    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }
     
    //Getters
    public int getId() {
        return id;
    }

    public byte getActive() {
        return active;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getCityId() {
        return cityId;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
    
    //Customer Validation
    public static String customerValidation(String name, String address, String address2, String phone, 
            String city, String postalCode, String country) {
        
        //Create a string to hold the error mesages
        String validationMessage = "";
        if (name.length() == 0) {
            validationMessage += "NAME: the field is empty. Please input a customer name. \n";
        }
        else if (name.length() > 45) {
            validationMessage += "NAME: name is too long. Please alter customer name. \n";
        }
        else if (!name.matches("[a-zA-Z\\s]+")) {
            validationMessage += "NAME: numbers or special characters are not allowed. Please use letters. \n";    
        }
        
        if (address.length() == 0 ) {
            validationMessage += "ADDRESS: the field is empty. Please input an address. \n";
        }
        else if (address.length() > 50 ) {
            validationMessage += "ADDRESS: address is too long. Please alter the address. \n";
        }
        else if (!address.matches("[\\w\\s]+")) {
            validationMessage += "ADDRESS: special characters, including periods, are not allowed. Please use letters and numbers only. \n";    
        }
        
        if (address2.length() > 50 ) {
            validationMessage += "ADDRESS 2: address is too long. Please alter the address. \n";
        }
        if (!address2.isEmpty() && !address2.matches("[\\w\\s]+")) {
            validationMessage += "ADDRESS 2: special characters, including periods, are not allowed. Please use letters and numbers only. \n";    
        }
        
        if (phone.length() == 0 ) {
            validationMessage += "PHONE: the field is empty. Please input a phone number. \n";
        }
        else if (phone.length() > 20 ) {
            validationMessage += "PHONE: phone number is too long. Please alter the phone number. \n";
        }
        else {
            try {
                int phoneNumber = Integer.parseInt(phone);
            }
            catch (NumberFormatException e) {
                if(!phone.matches("[\\d]+")) {
                    validationMessage += "PHONE: letters or special characters are not allowed. Please use numbers only. \n";
                }
            }
        }
//        if (!phone.matches("[\\d]+")) {
//            validationMessage += "The Phone field should not have letters or special characters. Please use numbers only. \n";    
//        }

        if (city.length() == 0 ) {
            validationMessage += "CITY: the field is empty. Please input a city name. \n";
        }
        else if (city.length() > 50 ) {
            validationMessage += "CITY: city name is too long. Please alter the city name. \n";
        }
        else if (!city.matches("[a-zA-Z\\s]+")) {
            validationMessage += "CITY: numbers or special characters are not allowed. Please use letters only. \n";    
        }
        
        if (postalCode.length() == 0 ) {
            validationMessage += "POSTAL CODE: the field is empty. Please input a postal code. \n";
        }
        else if (postalCode.length() > 10 ) {
            validationMessage += "POSTAL CODE: the postal code is too long. Please alter the postal code. \n";
        }
        else {
            try {
                int postalCodeNumber = Integer.parseInt(postalCode);
            }
            catch (NumberFormatException e) {
                if(!postalCode.matches("[\\d]+")) {
                    validationMessage += "POSTAL CODE: letters or special characters are not allowed. Please use numbers only. \n";
                }
            }
        }
        
        if (country.length() == 0 ) {
            validationMessage += "COUNTRY: the field is empty. Please input a country name. \n";
        }
        else if (country.length() > 50 ) {
            validationMessage += "COUNTRY: the country name is too long. Please alter the country name. \n";
        }
        else if (!country.matches("[a-zA-Z\\s]+")) {
            validationMessage += "COUNTRY: numbers or special characters are not allowed. Please use letters only. \n";    
        }
        return validationMessage;
    }
    
}