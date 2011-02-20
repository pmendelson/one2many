package org.avaje.one2many.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avaje.ebean.annotation.CacheStrategy;

@Entity
@Table(name = "PERMISSION")
@CacheStrategy(readOnly=true)
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long permissionId;
    private PermissionType permissionType;
    private Role role;

    @Id
    @Column(name = "PERMISSION_ID")
    public Long getId() {

        return permissionId;
    }

    public void setId(Long id) {
        this.permissionId = id;
    }

    @ManyToOne()
    @JoinColumn(name = "permission_type_id",referencedColumnName="permission_type_id")
    public PermissionType getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((permissionType == null) ? 0 : permissionType.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Permission))
            return false;
        Permission other = (Permission) obj;
        if (permissionType == null) {
            if (other.permissionType != null)
                return false;
        } else if (!permissionType.equals(other.permissionType))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }
}
