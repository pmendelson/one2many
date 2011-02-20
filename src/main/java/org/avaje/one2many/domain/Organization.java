package org.avaje.one2many.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "ORGANIZATION")
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<Role> roles;
    private MailingAddress address=new MailingAddress();
    private String regionCode;
    private String labCode;

    private AuthorizationSchedule authorizationSchedule;

    private Long id;

    private String organizationName;

    public Organization() {
    }

    public Organization(Long id, String organizationName) {
        this.id = id;
        this.organizationName = organizationName;
    }

    @Id
    @Column(name = "ORGANIZATION_ID")
    @GeneratedValue(generator = "ORGANIZATION_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ORGANIZATION_SEQ", sequenceName = "ORGANIZATION_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ORGANIZATION_NAME")
    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /** The complete set of permissions assigned or pending assignment for this user */
    @OneToOne()
    @JoinColumn(name = "authorization_schedule_id", unique = true, nullable = false, updatable = true)
    public AuthorizationSchedule getAuthorizationSchedule() {
        return authorizationSchedule;
    }

    /** Setter for {@link #getAuthorizationSchedule()} */
    public void setAuthorizationSchedule(AuthorizationSchedule authorizationSchedule) {
        this.authorizationSchedule = authorizationSchedule;
    }

    @Transient
    public MailingAddress getAddress() {
        return address;
    }

    @Transient
    public void setAddress(MailingAddress address) {
        this.address = address;
    }

    @Column(name = "ADDRESS1")
    public String getAddress1() {
        return address.getAddress1();
    }

    public void setAddress1(String address1) {
        address.setAddress1(address1);
    }

    @Column(name = "ADDRESS2")
    public String getAddress2() {
        return address.getAddress2();
    }

    public void setAddress2(String address2) {
        address.setAddress2(address2);
    }

    @Column(name = "CITY")
    public String getCity() {
        return address.getCity();
    }

    public void setCity(String city) {
        address.setCity(city);
    }

    @Column(name = "STATE")
    public String getState() {
        return address.getState();
    }

    public void setState(String state) {
        address.setState(state);
    }

    @Column(name = "ZIP")
    public String getZip() {
        return address.getZip();
    }

    public void setZip(String zip) {
        address.setZip(zip);
    }

    @Column(name = "REGION_CODE")
    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Column(name = "LAB_CODE")
    public String getLabCode() {
        return labCode;
    }

    public void setLabCode(String labCode) {
        this.labCode = labCode;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return address.getCountry();
    }

    public void setCountry(String v) {
        address.setCountry(v);
    }

    // /**
    // * Returns all Roles of this Organization.
    // */
    // @OneToMany
    // @JoinTable(name="ORG_DEFAULT_ROLE",
    // joinColumns = @JoinColumn( name="ORGANIZATION_ID"),
    // inverseJoinColumns = @JoinColumn( name="ROLE_ID"))
    // public Set<Role> getRoles() {
    // if (roles == null) {
    // roles = new LinkedHashSet<Role>();
    // }
    // return roles;
    // }
    //
    // public void setRoles(Set<Role> roles) {
    // this.roles = roles;
    // }
}
