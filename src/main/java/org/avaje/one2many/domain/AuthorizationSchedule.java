package org.avaje.one2many.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.apache.commons.lang.StringUtils;

@Entity
@Table(name = "authorization_schedule")
/**
 * Models a set of authorizations (role, permission, and access level) that is assigned to some entity
 * (User, Organization, etc.) that requires authorized credentials.
 * @since 1.1.2, 2011-02
 * @version $Revision: 29523 $, $Date: 2011-02-18 16:02:30 -0500 (Fri, 18 Feb 2011) $
 * @author pmendelson
 */
public class AuthorizationSchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    // private Long parentId;
    private AccountStatus status;
    private Set<RoleAssignment> assignedRoles;
    private Integer version;

    public AuthorizationSchedule() {
    }

    public AuthorizationSchedule(Long id, AccountStatus status) {
        this.id = id;
        this.status = status;
    }

    @Id
    @Column(name = "authorization_schedule_id")
    // @GeneratedValue(generator = "USER_SEQ", strategy=GenerationType.SEQUENCE)
    // @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ")
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

    // /** ID of object that owns this schedule */
    // @Column(name = "parent_id")
    // public void setParentId(Long parentId) {
    // this.parentId = parentId;
    // }
    //
    // /**
    // * @return the parentId
    // */
    // public Long getParentId() {
    // return parentId;
    // }

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    /**
     * 
     * While this collection is not immutable, it should be considered read-only. If you want to add or remove roles,
     * modify the set and pass it to {@link UserManager#updateUserRoles(User, Set)}.
     * 
     * @deprecated Working with roles directly is nice for the registration page, but the rest of the application needs
     *             to work with UserRoles. For consistency, the registration page should, too.
     * @return all roles of this user
     */
    @Transient
    @Deprecated
    public Set<Role> getRoles() {
        return null;//getAssignedRoles();
//        return Sets.newHashSet(Collections2.transform(getAssignedRoles(), new Function<RoleAssignment, Role>() {
//            @Override
//            public Role apply(RoleAssignment from) {
//                return from.getRole();
//            }
//        }));
    }

    @Transient
    public Set<Role> getApprovedRoles() {
        Set<Role> approvedRoles = new HashSet<Role>();

        for (RoleAssignment userRole : getAssignedRoles()) {
            Role role = userRole.getRole();
            if (AssignmentStatus.APPROVED.equals(userRole.getStatus())) {
                approvedRoles.add(role);
            }
        }
        return approvedRoles;
    }

    public void addRole(Role r) {
        if (!hasRole(r.getRoleName())) {
            getAssignedRoles().add(new RoleAssignment(this, r));
        }
    }

    /**
     * Does this user have the specified role?
     */
    public boolean hasRole(String roleName) {
        for (Role role : getRoles()) {
            if (StringUtils.equals(roleName, role.getRoleName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all functional Roles of this User. While this collection is not immutable, it should be considered
     * read-only. If you want to add or remove roles, modify the set and pass it to @link
     * {@link UserManager#updateUserRoles(User, Set)}.
     * 
     * @return A list of functional Roles sorted by displayOrder.
     */
    @Transient
    public List<Role> getFunctionalRoles() {
//        List<Role> list = Lists.newArrayList(Collections2.filter(getRoles(), new Predicate<Role>() {
//            @Override
//            public boolean apply(Role input) {
//                return input.isFunctional();
//            }
//        }));
        return null;//list;
    }

    @Transient
    public List<Role> getApprovedFunctionalRoles() {
//        List<Role> list = Lists.newArrayList(Collections2.filter(getApprovedRoles(), new Predicate<Role>() {
//            public boolean apply(Role input) {
//                return input.isFunctional();
//            }
//        }));
        return null;//list;
    }

    /**
     * Gets all functional roles as a String, with each role's displayName separated by "\n";
     */
    @Transient
    public String getFunctionalRolesAsString() {
        Collection<String> displayNames = null;//Collections2.transform(getFunctionalRoles(), Role.FUNCTION_TO_DISPLAY_NAME);
        return StringUtils.join(displayNames, "\n");
    }

    @OneToMany(mappedBy = "parent")//, cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<RoleAssignment> getAssignedRoles() {
        return assignedRoles;
    }

    public void setUserRoles(Set<RoleAssignment> userRoles) {
        this.assignedRoles = userRoles;
    }
    //
    // @Transient
    // public List<UserRole> getFunctionalUserRoles() {
    // List<UserRole> list = Lists.newArrayList(Collections2.filter(getUserRoles(), new Predicate<UserRole>() {
    // public boolean apply(UserRole input) {
    // return input.getRole().isFunctional();
    // }
    // }));
    // return list;
    // }
    //
    // /**
    // * Returns the full name as <code>[lastName], [firstName]</code>.
    // */
    // @Transient
    // public String getFullName() {
    // return getLastName() + ", " + getFirstName();
    // }
    //
    // /**
    // * Copies all data from this User to a new User object.
    // * <p>
    // * This is a shallow copy, meaning collection elements are copied by
    // * reference.
    // *
    // * @return The new copy.
    // */
    // public AuthorizationSchedule copy() {
    // try {
    // return (AuthorizationSchedule)BeanUtils.cloneBean(this);
    // } catch (Exception e) {
    // throw new IllegalStateException("Error while copying " + this, e);
    // }
    // }
    //
    // @Override
    // public String toString() {
    // return LangUtils.newToStringBuilder(this)
    // .append("username", getUsername())
    // .toString();
    // }
    //

}
