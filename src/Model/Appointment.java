package Model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author JorgeCovarrubias
 */
public class Appointment {
    //Declare Fields
    private int appId, customerId, userId;
    private String customerName, userName, title, descriptions, location, contact, type, url, 
                   start, end, createdBy, createdDate;
    //private Date createdDate;
    
    
    //Create Constructor
    public Appointment(int appId, int customerId, String customerName, int userId, String userName, 
            String title, String description, String location, String contact, String type, 
            String url, String start, String end, String createdBy, String createdDate) {
        
        this.appId = appId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.descriptions = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.start = start;
        this.end = end;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    
    //Setters

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    
    //Getters    

    public int getAppId() {
        return appId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getUserId() {
        return userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getCreatedDate() {
        return createdDate;
    }
    
    
}