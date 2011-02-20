
package org.avaje.one2many.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MailingAddress implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int ADDRESS_MAX_LENGTH = 200;
    public static final int CITY_MAX_LENGTH = 100;
    public static final int ZIP_MAX_LENGTH = 10;
    
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zip;
    
    public MailingAddress() {}

    @Column
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Column
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
