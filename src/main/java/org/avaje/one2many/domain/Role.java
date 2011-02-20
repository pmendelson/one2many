package org.avaje.one2many.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

//import org.hibernate.annotations.Type;
import com.avaje.ebean.annotation.CacheStrategy;

/**
 * This class contains the read-only reference data of the role, role_permission, and role_access_level tables.
 */
//@TypeDefs({
//        @TypeDef(name = "AccessLevelAppTypeCode", typeClass = GenericEnumUserType.class, parameters = {
//                @Parameter(name = "enumClassName", value = "org.avaje.one2many.domain.AccessLevelAppTypeCode"),
//                @Parameter(name = "identifierMethod", value = "getId"),
//                @Parameter(name = "valueOfMethod", value = "getValue") }),
//        @TypeDef(name = "AccessRestrictionType", typeClass = GenericEnumUserType.class, parameters = {
//                @Parameter(name = "enumClassName", value = "com.fedcsc.smoportal.client.domain.AccessRestrictionEntity"),
//                @Parameter(name = "identifierMethod", value = "getId") }) //
//})
@Entity
@Table(name = "ROLE")
@CacheStrategy(readOnly=true,warmingQuery="where FUNCTIONAL=true order by DISPLAY_ORDER,DISPLAY_NAME,ROLE_NAME,ROLE_ID")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String ROLE_SMOPORTAL_USER = "ROLE_SMOPORTAL_USER";
    public static final String ROLE_SMOPORTAL_USER_ADMIN = "ROLE_SMOPORTAL_USER_ADMIN";
    public static final String ROLE_SMOPORTAL_DATA_ADMIN = "ROLE_SMOPORTAL_DATA_ADMIN";

//    /**
//     * Transforms to displayName;
//     */
//    public static final Function<Role, String> FUNCTION_TO_DISPLAY_NAME = new Function<Role, String>() {
//        @Override
//        public String apply(Role from) {
//            return from.getDisplayName();
//        }
//    };

    @Id
    @Column(name = "ROLE_ID")
    @GeneratedValue(generator = "ROLE_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ")
    private Long id;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "FUNCTIONAL")
    private boolean functional;

    @Column(name = "ACCESS_LEVEL_APP_TYPE_CODE")
//    @Type(type = "AccessLevelAppTypeCode")
    private AccessLevelAppTypeCode accessLevelAppTypeCode;

    @Column(name = "URL")
    private String url;

    @Column(name = "DISPLAY_NAME")
    private String displayName;

    @Column(name = "DISPLAY_ORDER")
    private Integer displayOrder;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<Permission> permissions;

    @ElementCollection
    @CollectionTable(name = "ROLE_ACCESS_LEVEL_TYPE", joinColumns = @JoinColumn(name = "ROLE_ID"))
    @Column(name = "ACCESS_LEVEL_TYPE_ID", insertable = false, updatable = false)
//    @Type(type = "AccessRestrictionType")
    private List<AccessRestrictionEntity> accessLevelTypes;

    public Role() {
    }

    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role(Long id, String roleName, boolean functional, String url, String displayName, Integer displayOrder) {
        this(id, roleName, functional, url, displayName, displayOrder, null, null, null);
    }

    public Role(Long id, String roleName, boolean functional, String url, String displayName, Integer displayOrder,
            Set<Permission> permissions, AccessLevelAppTypeCode accessLevelTypeCode,
            List<AccessRestrictionEntity> accessLevelTypes) {
        this.id = id;
        this.roleName = roleName;
        this.functional = functional;
        this.url = url;
        this.displayName = displayName;
        this.displayOrder = displayOrder;
        this.permissions = permissions;
        this.accessLevelAppTypeCode = accessLevelTypeCode;
        this.accessLevelTypes = accessLevelTypes;
    }

    public Long getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    /**
     * Is this Role functional?
     * <p>
     * A functional role corresponds to a distinctive CLPSS application function. It has a URL, display name and display
     * order, which are used to render it as a link in SMO Portal application. A non-functional Role may be created for
     * ease of access control configuration. An example is {@link #ROLE_SMOPORTAL_USER}.
     * 
     * @return <code>true</code> if this Role is functional.
     */
    public boolean isFunctional() {
        return functional;
    }

    public AccessLevelAppTypeCode getAccessLevelAppTypeCode() {
        return accessLevelAppTypeCode;
    }

    /** Access Level Types that are possible for this role ordered by type name */
    public List<AccessRestrictionEntity> getAccessLevelTypes() {
        if (accessLevelTypes != null) {
            Collections.sort(accessLevelTypes, new Comparator<AccessRestrictionEntity>() {
                public int compare(AccessRestrictionEntity o1, AccessRestrictionEntity o2) {
                    return o1.toString().compareTo(o2.toString());
                }
            });
        }
        return accessLevelTypes;
    }

    /**
     * Gets the URL to an application page that corresponds to this Role.
     * <p>
     * The URL can be absolute (i.e. starts with "http"), or relative (to SMO Portal application).
     * 
     * @return May be <code>null</code> for a non-functional Role.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the display text of this Role.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the position of this Role relative to others when displayed.
     * 
     * @return May be <code>null</code> for a non-functional Role.
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    @Transient
    public List<PermissionType> getPermissionTypes() {
        Map<Long, PermissionType> r = new HashMap<Long, PermissionType>();
        Set<Permission> pset = getPermissions();
        if (pset != null) {
            for (Permission p : pset) {
                final Long pid = p.getPermissionType().getId();
                if (!r.containsKey(pid)) {
                    r.put(pid, p.getPermissionType());
                }
            }
        }
        List<PermissionType> r2 = new ArrayList<PermissionType>(r.values());
        Collections.sort(r2);
        return r2;
    }

    public Permission getPermission(PermissionType perm) {
        Set<Permission> pset = getPermissions();
        if (pset != null) {
            for (Permission p : pset) {
                if (p.getPermissionType().getId().equals(perm.getId())) {
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Role))
            return false;
        Role other = (Role) obj;
        if (roleName == null) {
            if (other.roleName != null)
                return false;
        } else if (!roleName.equals(other.roleName))
            return false;
        return true;
    }

}
