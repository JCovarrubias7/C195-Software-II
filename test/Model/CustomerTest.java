/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jorge Covarrubias
 */
public class CustomerTest {
    
    // Set the good customer values here
    Customer goodCustomer = new Customer();
    String goodName = "Jim";
    String goodAddress = "123 W Chicago";
    String goodAddress2 = "# 2";
    String goodPostalCode = "70777";
    String goodPhone = "3125551234";
    String goodCity = "Chicago";
    String goodCountry = "USA";
    
    // Set the bad customer values here to test validation
    // Add special characters to name
    String badName = "JHIJ!@";
    // Add special characters to Address
    String badAddress = "#123 W. Chicago";
    // Add special characters to Address
    String badAddress2 = "apt-#2";
    // Add plus or hyphen to phone number
    String badPhone = "+123-555-1234";
    // Leave city field blank
    String badCity = "";
    // Add special characters to Postal Code
    String badPostalCode = "70.7777";
    // Add a number to country
    String badCountry = "USA1";

    
    public CustomerTest() {
    }

    /**
     * Test of setName method, of class Customer.
     */
    @Test
    public void testSetName() {
        System.out.println("setName Assertion Test");
        goodCustomer.setName(goodName);
        String expected = goodName;
        String actual = goodCustomer.getName();
        assertEquals(expected, actual);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual + "\n");
        
    }

    /**
     * Test of setAddress method, of class Customer.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress Assertion Test");
        goodCustomer.setAddress(goodAddress);
        String expected = goodAddress;
        String actual = goodCustomer.getAddress();
        assertEquals("Address set", expected, actual);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual + "\n");
    }

    /**
     * Test of setAddress2 method, of class Customer.
     */
    @Test
    public void testSetAddress2() {
        System.out.println("setAddress2 Assertion Test");
        goodCustomer.setAddress2(goodAddress2);
        String expected = goodAddress2;
        String actual = goodCustomer.getAddress2();
        assertEquals("Address2 set", expected, actual);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual + "\n");
    }

    /**
     * Test of setPostalCode method, of class Customer.
     */
    @Test
    public void testSetPostalCode() {
        System.out.println("setPostalCode Assertion Test");
        goodCustomer.setPostalCode(goodPostalCode);
        String expected = goodPostalCode;
        String actual = goodCustomer.getPostalCode();
        assertEquals("Postal Code set", expected, actual);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual + "\n");
    }

    /**
     * Test of setPhone method, of class Customer.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone Assertion Test");
        goodCustomer.setPhone(goodPhone);
        String expected = goodPhone;
        String actual = goodCustomer.getPhone();
        assertEquals("Phone set", expected, actual);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual + "\n");
    }

    /**
     * Test of setCity method, of class Customer.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity Assertion Test");
        goodCustomer.setCity(goodCity);
        String expected = goodCity;
        String actual = goodCustomer.getCity();
        assertEquals("City set", expected, actual);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual + "\n");
    }

    /**
     * Test of setCountry method, of class Customer.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry Assertion Test");
        goodCustomer.setCountry(goodCountry);
        String expected = goodCountry;
        String actual = goodCustomer.getCountry();
        assertEquals("Country set", expected, actual);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual + "\n");
    }
    
    /**
     * Test of getName method, of class Customer.
     */
    @Test
    public void testGetName() {
        System.out.println("getName Assertion Test");
        goodCustomer.setName(goodName);
        String expResult = goodName;
        String result = goodCustomer.getName();
        assertEquals("Retrieved name", expResult, result);
        System.out.println("Expected: " + expResult);
        System.out.println("Actual: " + result + "\n");
    }

    /**
     * Test of getAddress method, of class Customer.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress Assertion Test");
        goodCustomer.setAddress(goodAddress);
        String expResult = goodAddress;
        String result = goodCustomer.getAddress();
        assertEquals("Retrieved Address", expResult, result);
        System.out.println("Expected: " + expResult);
        System.out.println("Actual: " + result + "\n");
    }

    /**
     * Test of getAddress2 method, of class Customer.
     */
    @Test
    public void testGetAddress2() {
        System.out.println("getAddress2 Assertion Test");
        goodCustomer.setAddress2(goodAddress2);
        String expResult = goodAddress2;
        String result = goodCustomer.getAddress2();
        assertEquals("Retrieved Address 2", expResult, result);
        System.out.println("Expected: " + expResult);
        System.out.println("Actual: " + result + "\n");
    }

    /**
     * Test of getPostalCode method, of class Customer.
     */
    @Test
    public void testGetPostalCode() {
        System.out.println("getPostalCode Assertion Test");
        goodCustomer.setPostalCode(goodPostalCode);
        String expResult = goodPostalCode;
        String result = goodCustomer.getPostalCode();
        assertEquals("Retrieved Postal Code", expResult, result);
        System.out.println("Expected: " + expResult);
        System.out.println("Actual: " + result + "\n");
    }

    /**
     * Test of getPhone method, of class Customer.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone Assertion Test");
        goodCustomer.setPhone(goodPhone);
        String expResult = goodPhone;
        String result = goodCustomer.getPhone();
        assertEquals("Retrieved Phone", expResult, result);
        System.out.println("Expected: " + expResult);
        System.out.println("Actual: " + result + "\n");
    }

    /**
     * Test of getCity method, of class Customer.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity Assertion Test");
        goodCustomer.setCity(goodCity);
        String expResult = goodCity;
        String result = goodCustomer.getCity();
        assertEquals("Retrieved City", expResult, result);
        System.out.println("Expected: " + expResult);
        System.out.println("Actual: " + result + "\n");
    }

    /**
     * Test of getCountry method, of class Customer.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry Assertion Test");
        goodCustomer.setCountry(goodCountry);
        String expResult = goodCountry;
        String result = goodCustomer.getCountry();
        assertEquals("Retrieved Country", expResult, result);
        System.out.println("Expected: " + expResult);
        System.out.println("Actual: " + result + "\n");
    }

    /**
     * Test of customerValidation method, of class Customer.
     */
    @Test
    public void testCustomerValidation() {
        System.out.println("customerValidation Test");
        String name = badName;
        String address = badAddress;
        String address2 = badAddress2;
        String phone = badPhone;
        String city = badCity;
        String postalCode = badPostalCode;
        String country = badCountry;
        String expResult = "NAME: numbers or special characters are not allowed. Please use letters. \n"
                + "ADDRESS: special characters, including periods, are not allowed. Please use letters and numbers only. \n"
                + "ADDRESS 2: special characters, including periods, are not allowed. Please use letters and numbers only. \n"
                + "PHONE: letters or special characters are not allowed. Please use numbers only. \n"
                + "CITY: the field is empty. Please input a city name. \n"
                + "POSTAL CODE: letters or special characters are not allowed. Please use numbers only. \n"
                + "COUNTRY: numbers or special characters are not allowed. Please use letters only. \n";
        String result = Customer.customerValidation(name, address, address2, phone, city, postalCode, country);
        assertEquals(expResult, result);
    }
    
}
