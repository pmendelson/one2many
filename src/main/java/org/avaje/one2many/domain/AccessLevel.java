package org.avaje.one2many.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Represents a single access level that is part of some PermissionSet.
 * 
 * @version $Revision: 29457 $, $Date: 2011-01-27 16:22:08 -0500 (Thu, 27 Jan 2011) $
 * @since 1.1.2, 2011-01
 * @author pmendelson
 * 
 */
// @TypeDefs({ //
// @TypeDef(name = "AccessRestrictionType", typeClass = GenericEnumUserType.class, parameters = {
// @Parameter(name = "enumClassName", value = "com.fedcsc.smoportal.client.domain.AccessRestrictionEntity"),
// @Parameter(name = "identifierMethod", value = "getId") }), //
// @TypeDef(name = "RestrictionDirectionType", typeClass = GenericEnumUserType.class, parameters = {
// @Parameter(name = "enumClassName", value = "com.fedcsc.smoportal.client.domain.AccessRestrictionDirection"),
// @Parameter(name = "identifierMethod", value = "getId") }) //
// })
@Entity
@Table(name = "Access_Level")
public class AccessLevel {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long parentUserRoleId;
    private Long parentUserPermissionId;
    private AccessRestrictionDirection direction;
    private AccessRestrictionEntity entityQual;
//    private CodableLabel<Long> entity;

    // Derived elements
//    private LifecycleState lifecycleState;
    private Focus focus;
//    @Transient
//    private PersistentEntity parent;

    public enum Focus {
        ROLE, PERMISSION
    };

    @Id
    @Column(name = "Access_Level_ID")
    @GeneratedValue(generator = "Access_Level_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Access_Level_SEQ", sequenceName = "Access_Level_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ACCESS_LEVEL_ENTITY_QUAL")
    public AccessRestrictionEntity getEntityQual() {
        return entityQual;
    }

    public void setEntityQual(AccessRestrictionEntity entityQual) {
        this.entityQual = entityQual;
    }

//    // @Embedded
//    // @AttributeOverrides({ @AttributeOverride(name = "code", column = @Column(name = "ACCESS_LEVEL_ENTITY_ID")),
//    // @AttributeOverride(name = "display", column = @Column(name = "ACCESS_LEVEL_ENTITY_DISPLAY")) })
//    @Transient
//    public CodableLabel<Long> getEntity() {
//        return entity;
//    }

//    public void setEntity(CodableLabel<Long> v) {
//        this.entity = v;
//    }

    @Column(name = "ACCESS_LEVEL_ENTITY_ID")
    public void setEntityVal(Long v) {
//        if (entity != null)
//            entity = new CodableLabel<Long>();
//        entity.setCode(v);
    }

    public Long getEntityVal() {
//        if (entity != null) {
//            return entity.getCode();
//        }
        return null;
    }

    @Column(name = "ACCESS_LEVEL_ENTITY_DISPLAY")
    public void setEntityDisplay(String v) {
//        if (entity != null)
//            entity = new CodableLabel<Long>();
//        entity.setDisplay(v);
    }

    public String getEntityDisplay() {
//        if (entity != null) {
//            return entity.getDisplay();
//        }
        return null;
    }

    public void setDirection(AccessRestrictionDirection direction) {
        this.direction = direction;
    }

    @Column(name = "restriction_type_id")
    public AccessRestrictionDirection getDirection() {
        return direction;
    }

    /**
     * @return the parentUserRoleId
     */
    @Column(name = "parent_urole_id")
    public Long getParentUserRoleId() {
        return parentUserRoleId;
    }

    /** Setter for {@link #getParentUserRoleId()} */
    public void setParentUserRoleId(Long v) {
        this.parentUserRoleId = v;
        if (v != null)
            this.focus = Focus.ROLE;
    }

    /**
     * @return the parentUserRoleId
     */
    @Column(name = "parent_uperm_id")
    public Long getParentUserPermissionId() {
        return parentUserPermissionId;
    }

    /** Setter for {@link #getParentUserRoleId()} */
    public void setParentUserPermissionId(Long v) {
        this.parentUserPermissionId = v;
        if (v != null)
            this.focus = Focus.PERMISSION;
    }

    @Transient
    public Focus getFocus() {
        return focus != null ? focus : Focus.ROLE;
    }

    public void setFocus(Focus v) {
        focus = v;
    }
//
//    @Transient
//    public PersistentEntity getParent() {
//        return parent;
//    }
//
//    @Transient
//    public void setParent(PersistentEntity parent) {
//        this.parent = parent;
//    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEntityVal() == null) ? 0 : getEntityVal().hashCode());
        result = prime * result + ((entityQual == null) ? 0 : entityQual.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AccessLevel))
            return false;
        AccessLevel other = (AccessLevel) obj;
        if (getEntityVal() == null) {
            if (other.getEntityVal() != null)
                return false;
        } else if (!getEntityVal().equals(other.getEntityVal()))
            return false;
        if (entityQual != other.entityQual)
            return false;
        return true;
    }
}
