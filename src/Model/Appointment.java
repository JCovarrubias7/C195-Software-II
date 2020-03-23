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
    private String title, descriptions, location, contact, type, url, dateString, 
                   startString, endString, createdBy, createdDateString;
    private Timestamp startTime, endTime;
    private Date startDate, endDate, createdDate;
    
    
    //Create Constructor
    public Appointment(int appId, int customerId, int userId, String title, String description,
                       String location, String contact, String type, String url, Timestamp startTime,
                       Timestamp endTime, Date startDate, Date endDate, String createdBy, Date createdDate) {
        this.appId = appId;
        this.customerId = customerId;
        this.userId = userId;
        this.title = title;
        this.descriptions = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.url = url;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public void setStartString(String startString) {
        this.startString = startString;
    }

    public void setEndString(String endString) {
        this.endString = endString;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedDateString(String createdDateString) {
        this.createdDateString = createdDateString;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getDateString() {
        return dateString;
    }

    public String getStartString() {
        return startString;
    }

    public String getEndString() {
        return endString;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedDateString() {
        return createdDateString;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
    
    
}
