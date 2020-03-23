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
    
}