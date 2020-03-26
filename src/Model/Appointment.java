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
                   createdBy;
    private Date start, end, createdDate;
    
    
    //Create Constructor
    public Appointment(int appId, int customerId, String customerName, int userId, String userName, 
            String title, String description, String location, String contact, String type, 
            String url, Date start, Date end, String createdBy, Date createdDate) {
        
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

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setCreatedDate(Date createdDate) {
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

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    
    
}