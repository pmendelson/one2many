
package org.avaje.one2many.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.avaje.ebean.annotation.CacheStrategy;

@Entity
@Table(name = "LOCATION")
@CacheStrategy(readOnly=true,warmingQuery="order by country, state")
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String state;
    private String country;

    public Location() {}
    
    public Location(Long id, String state, String country) {
        this.id = id;
        this.state = state;
        this.country = country;
    }

    @Id
    @Column(name = "LOCATION_ID")
    @GeneratedValue(generator = "LOCATION_SEQ", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name = "LOCATION_SEQ", sequenceName = "LOCATION_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
