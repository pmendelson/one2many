package org.avaje.one2many.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

//@TypeDefs({ //
//    @TypeDef(name = "UserRoleStatusType", typeClass = GenericEnumUserType.class, parameters = {
//            @Parameter(name = "enumClassName", value = "org.avaje.one2many.domain.UserRoleStatus"),
//            @Parameter(name = "identifierMethod", value = "getId") }) //
//})
@Entity
@Table(name = "role_assignment")
public class RoleAssignment implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private AuthorizationSchedule parent;
    private Role role;
    private AssignmentStatus status;
    private AuthorizationSource authorizationSource;
    private Date lastUpdateDate;
    private String lastUpdateFullName;
    private List<PermissionAssignment> permissionAssignments = new ArrayList<PermissionAssignment>();
    private Set<AccessLevel> accessLevels;
    private Integer version;

    public RoleAssignment() {
        super();
    }

    /**
     * Creates a new User Role with the status of UserRoleStatus.PENDING
     * 
     * @param user
     * @param role
     */
    public RoleAssignment(AuthorizationSchedule user, Role role) {
        this();
        this.parent = user;
        setRole(role);
        this.status = AssignmentStatus.PENDING;
    }

    @Id
    @Column(name = "ROLE_ASSIGNMENT_ID")
    @GeneratedValue(generator = "USER_ROLE_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_ROLE_SEQ", sequenceName = "USER_ROLE_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @ManyToOne
    @JoinColumn(name = "PARENT_ID",referencedColumnName="authorization_schedule_id")
    public AuthorizationSchedule getParent() {
        return parent;
    }

    public void setParent(AuthorizationSchedule parent) {
        this.parent = parent;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "ASSIGNMENT_STATUS")
    @Enumerated(EnumType.STRING)
    public AssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "AUTHORIZATION_SOURCE")
    public AuthorizationSource getAuthorizationSource() {
        return authorizationSource;
    }

    public void setAuthorizationSource(AuthorizationSource authorizationSource) {
        this.authorizationSource = authorizationSource;
    }

    /** Shortcut for {@link #getAuthorizationSource()}=={@link AuthorizationSource#SELF} */
    @Transient
    public boolean isUserRequested() {
        return AuthorizationSource.SELF == getAuthorizationSource();
    }

    @Column(name = "LAST_UPDATE_DATE")
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Column(name = "LAST_UPDATE_FULL_NAME")
    public String getLastUpdateFullName() {
        return lastUpdateFullName;
    }

    public void setLastUpdateFullName(String lastUpdateUser) {
        this.lastUpdateFullName = lastUpdateUser;
    }

    @OneToMany(mappedBy="userRole", cascade = CascadeType.ALL)
    public List<PermissionAssignment> getPermissionAssignments() {
        return permissionAssignments;
    }

    public void setPermissionAssignments(List<PermissionAssignment> v) {
        this.permissionAssignments = v != null ? v : new ArrayList<PermissionAssignment>();
    }

    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "parent_urole_id", referencedColumnName="ROLE_ASSIGNMENT_ID")
    public Set<AccessLevel> getAccessLevels() {
        return accessLevels;
    }

    public void setAccessLevels(Set<AccessLevel> v) {
        this.accessLevels = v != null ? v : new HashSet<AccessLevel>();
    }

    /** Finds permisison of {@code PermissionType} regardless of {@link UserPermission#getLifecycleState()} */
    public PermissionAssignment getPermission(PermissionType perm) {
        if (permissionAssignments != null) {
            for (PermissionAssignment p : permissionAssignments) {
                if (true/* perm.equals(p.getPermission().getPermissionType()) */) {
                    return p;
                }
            }
        }
        return null;
    }

    public Boolean hasPermission(PermissionType perm) {
        return getPermission(perm) != null;
    }

    public void addPermission(PermissionType perm) {
        PermissionAssignment userPermission = getPermission(perm);
        if (userPermission == null) {
            userPermission = new PermissionAssignment();
            userPermission.setPermission(getRole().getPermission(perm));
            userPermission.setParentRole(this);
            if (permissionAssignments == null)
                permissionAssignments = new ArrayList<PermissionAssignment>();
            permissionAssignments.add(userPermission);
//        } else {
//            if (userPermission.getLifecycleState() == LifecycleState.RETIRED) {
//                userPermission.setLifecycleState(null);
//            }
        }
    }

    public void dropPermission(PermissionType perm) {
        if (perm != null) {
            PermissionAssignment togo = null;
            for (Iterator<PermissionAssignment> i = permissionAssignments.iterator(); togo == null && i.hasNext();) {
                PermissionAssignment userPermission = i.next();
                if (perm.equals(userPermission.getPermission().getPermissionType())) {
                    togo = userPermission;
//                    togo.setLifecycleState(LifecycleState.RETIRED);
                }
            }
        }
    }

    /** todo move into a ApplicationManagedPersistentEntity related collection utility class */
    @Transient
    public int getAccessLevelQty() {
        int r = 0;
//        for (AccessLevel l : getAccessLevels()) {
//            if (l.getLifecycleState() != LifecycleState.RETIRED)
//                r += 1;
//        }
//        for (PermissionAssignment userPermission : getPermissionAssignments()) {
//            if (userPermission.getAccessLevels() != null)
//                for (AccessLevel al : userPermission.getAccessLevels()) {
//                    if (al.getLifecycleState() != LifecycleState.RETIRED)
//                        r += 1;
//                }
//        }
        return r;
    }
}
