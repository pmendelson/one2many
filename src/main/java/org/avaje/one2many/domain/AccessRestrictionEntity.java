package org.avaje.one2many.domain;

/**
 * 
 * @version $Revision: 28962 $, $Date: 2011-02-07 16:53:35 -0500 (Mon, 07 Feb 2011) $
 * @since 1.1.2, 2011-01
 * @author pmendelson
 *
 */
    public enum AccessRestrictionEntity {
    ORGANIZATION(1),SITE(2),DELIVERABLE(3),PROJECT(4);
    private int code;
    private AccessRestrictionEntity(int code) {
        this.code=code;
    }
    public Integer getId() {
        return code;
    }
}
