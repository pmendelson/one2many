package org.avaje.one2many.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import org.apache.commons.beanutils.BeanUtils;


@Entity
@Table(name = "USER_T")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int PASSWORD_MAX_LENGTH = 100;
    public static final int USERNAME_MAX_LENGTH = 50;
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int SECRET_ANSWER_MAX_LENGTH = 100;
    public static final int FIRST_NAME_MAX_LENGTH = 50;
    public static final int LAST_NAME_MAX_LENGTH = 50;
    public static final int EMAIL_MAX_LENGTH = 320;
    public static final int PHONE_MAX_LENGTH = 50;
    public static final int PHONE_MIN_LENGTH = 10;

    private Long id;
    private String username;
    private String password;
    private AccountStatus status;
    private String firstName;
    private String lastName;
    private String email;
    private String businessPhone;
    private String altPhone;
    private String fax;
    private MailingAddress address = new MailingAddress();
    private String secretQuestion;
    private String secretQuestionAnswer;
    private Date registerDate;
    private Date termsAcceptedDate;
    private Date lastLoginDate;
    private Date passwordUpdateDate;
    private Date firstFailedLoginDate;
    private Date updateEmailSentDate;
    private Date approvedDate;
    private int failedLoginCount;
    private boolean passwordChangeRequired;
    private boolean incomplete;
    private Organization organization;
    private AuthorizationSchedule authorizationSchedule;

    public User() {
    }

    public User(Long id, String username, String password, AccountStatus status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
    }

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(generator = "USER_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "BUSINESS_PHONE")
    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    @Column(name = "ALT_PHONE")
    public String getAltPhone() {
        return altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    @Column(name = "FAX")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Transient
//    @Embedded
//    @AttributeOverrides({ @AttributeOverride(name = "address1", column = @Column(name = "address1")), //
//            @AttributeOverride(name = "address2", column = @Column(name = "address2")), //
//            @AttributeOverride(name = "city", column = @Column(name = "city")), //
//            @AttributeOverride(name = "state", column = @Column(name = "state")), //
//            @AttributeOverride(name = "zip", column = @Column(name = "zip")), //
//            @AttributeOverride(name = "country", column = @Column(name = "country")) //
//    })
    public MailingAddress getAddress() {
        return address;
    }

    public void setAddress(MailingAddress address) {
        address = address;
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

    @Column(name = "COUNTRY")
    public String getCountry() {
        return address.getCountry();
    }

    public void setCountry(String country) {
        address.setCountry(country);
    }

    @Column(name = "ZIP")
    public String getZip() {
        return address.getZip();
    }

    public void setZip(String zip) {
        address.setZip(zip);
    }

    @Column(name = "SECRET_QUESTION")
    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    @Column(name = "SECRET_QUESTION_ANSWER")
    public String getSecretQuestionAnswer() {
        return secretQuestionAnswer;
    }

    public void setSecretQuestionAnswer(String secretQuestionAnswer) {
        this.secretQuestionAnswer = secretQuestionAnswer;
    }

    @Column(name = "REGISTER_DATE")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Column(name = "TERMS_ACCEPTED_DATE")
    public Date getTermsAcceptedDate() {
        return termsAcceptedDate;
    }

    public void setTermsAcceptedDate(Date termsAcceptedDate) {
        this.termsAcceptedDate = termsAcceptedDate;
    }

    @Column(name = "LAST_LOGIN_DATE")
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Column(name = "PASSWORD_UPDATE_DATE")
    public Date getPasswordUpdateDate() {
        return passwordUpdateDate;
    }

    public void setPasswordUpdateDate(Date passwordUpdateDate) {
        this.passwordUpdateDate = passwordUpdateDate;
    }

    @Column(name = "FIRST_FAILED_LOGIN_DATE")
    public Date getFirstFailedLoginDate() {
        return firstFailedLoginDate;
    }

    public void setFirstFailedLoginDate(Date firstFailedLoginDate) {
        this.firstFailedLoginDate = firstFailedLoginDate;
    }

    @Column(name = "FAILED_LOGIN_COUNT")
    public int getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(int failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    /**
     * Is this user required to change her password upon next login?
     */
    @Column(name = "PASSWORD_CHANGE_REQUIRED")
    public boolean isPasswordChangeRequired() {
        return passwordChangeRequired;
    }

    public void setPasswordChangeRequired(boolean passwordChangeRequired) {
        this.passwordChangeRequired = passwordChangeRequired;
    }

    /**
     * Is this user amind added user? If so, it's incomplete and user needs to update profile upon first login.
     */
    @Column(name = "INCOMPLETE")
    public boolean isIncomplete() {
        return incomplete;
    }

    public void setIncomplete(boolean incompl) {
        this.incomplete = incompl;
    }

    @Column(name = "UPDATE_EMAIL_SENT_DATE")
    public Date getUpdateEmailSentDate() {
        return updateEmailSentDate;
    }

    public void setUpdateEmailSentDate(Date updateEmailSentDate) {
        this.updateEmailSentDate = updateEmailSentDate;
    }

    @Column(name = "APPROVED_DATE")
    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    @ManyToOne
    @JoinTable(name = "USER_ORGANIZATION", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ORGANIZATION_ID"))
    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /** The complete set of permissions assigned or pending assignment for this user */
    @OneToOne()
    @JoinColumn(name = "authorization_schedule_id", referencedColumnName="authorization_schedule_id", unique = true, nullable = false, updatable = true)
    public AuthorizationSchedule getAuthorizationSchedule() {
        return authorizationSchedule;
    }

    /** Setter for {@link #getAuthorizationSchedule()} */
    public void setAuthorizationSchedule(AuthorizationSchedule authorizationSchedule) {
        this.authorizationSchedule = authorizationSchedule;
    }

    /**
     * Returns the full name as <code>[lastName], [firstName]</code>.
     */
    @Transient
    public String getFullName() {
        return getLastName() + ", " + getFirstName();
    }

    /**
     * Copies all data from this User to a new User object.
     * <p>
     * This is a shallow copy, meaning collection elements are copied by reference.
     * 
     * @return The new copy.
     */
    public User copy() {
        try {
            return (User) BeanUtils.cloneBean(this);
        } catch (Exception e) {
            throw new IllegalStateException("Error while copying " + this, e);
        }
    }
//
//    @Override
//    public String toString() {
//        return LangUtils.newToStringBuilder(this).append("username", getUsername()).toString();
//    }

}
