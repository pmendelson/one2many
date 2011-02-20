package org.avaje.one2many.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.avaje.one2many.tools.NullSafeComparison;

@Entity
@Table(name = "PERMISSION_TYPE")
public class PermissionType implements Serializable, Comparable<PermissionType> {
    private static final long serialVersionUID = 1L;
    private static final String[] REQUIRED_TYPES = new String[] { "view" };
    private Long permissionTypeId;
    private String code;
    private String displayName;
    private Integer displayOrder;

    @Id
    @Column(name = "permission_type_id")
    public Long getId() {

        return permissionTypeId;
    }

    public void setId(Long id) {
        this.permissionTypeId = id;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "DISPLAY_NAME")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Column(name = "DISPLAY_ORDER")
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /** If true this PermissionType must be assigned to a user who holds a role that has this PermissionType */
    @Transient
    public boolean isRequired() {
        for (String label : REQUIRED_TYPES) {
            if (label.equalsIgnoreCase(getDisplayName()))
                return true;
        }
        return false;
    }

    public int compareTo(PermissionType o) {
        if (o != null) {
            NullSafeComparison r = new NullSafeComparison();
            boolean unCertain = r.needsMore(this.getDisplayOrder(), o.getDisplayOrder())
                    && r.needsMore(this.getDisplayName(), o.getDisplayName())
                    && r.needsMore(this.getCode(), o.getCode()) //
                    && r.needsMore(this.getId(), o.getId());
            return unCertain ? 0 : r.getComparison();
        } else {
            return 1;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PermissionType))
            return false;
        PermissionType other = (PermissionType) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

}
