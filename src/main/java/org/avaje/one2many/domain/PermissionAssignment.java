package org.avaje.one2many.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "permission_assignment")
public class PermissionAssignment{
    private static final long serialVersionUID = 1L;
    private Long id;
    private RoleAssignment userRole;
    private Permission permission;
    private Set<AccessLevel> accessLevels;
//    private LifecycleState lifecycleState;

    @Id
    @Column(name = "PERMISSION_ASSIGNMENT_ID")
    @GeneratedValue(generator = "USER_PERMISSION_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_PERMISSION_SEQ", sequenceName = "USER_PERMISSION_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "role_assignment_id")
    public RoleAssignment getUserRole() {
        return userRole;
    }

    public void setParentRole(RoleAssignment userRole) {
        this.userRole = userRole;
    }

    @ManyToOne
    @JoinColumn(name = "PERMISSION_ID",referencedColumnName="PERMISSION_ID")
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @OneToMany(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "parent_uperm_id", referencedColumnName="PERMISSION_ASSIGNMENT_ID")
    public Set<AccessLevel> getAccessLevels() {
        return accessLevels;
    }

    public void setAccessLevels(Set<AccessLevel> v) {
        this.accessLevels = v != null ? v : new HashSet<AccessLevel>();
    }

//    @Override
//    @Transient
//    public LifecycleState getLifecycleState() {
//        return lifecycleState != null ? lifecycleState : (getId() != null ? LifecycleState.LEGACY : LifecycleState.NEW);
//    }
//
//    @Override
//    public void setLifecycleState(LifecycleState v) {
//        lifecycleState = v;
//    }

    public boolean isMatch(PermissionType perm) {
        return true;// getPermission().getPermissionType().getId().equals(perm.getId());
    }

    // public boolean isMatch(Permission perm) {
    // return getPermission().getId().equals(perm.getId());
    // }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((permission == null) ? 0 : permission.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PermissionAssignment))
            return false;
        PermissionAssignment other = (PermissionAssignment) obj;
        if (permission == null) {
            if (other.permission != null)
                return false;
        } else if (!permission.equals(other.permission))
            return false;
        return true;
    }

}
