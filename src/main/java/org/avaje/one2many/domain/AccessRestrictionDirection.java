package org.avaje.one2many.domain;

//import com.fedcsc.persistence.CodableEnum;
//import com.fedcsc.persistence.CodableEnumUtils;

/**
 * 
 * @version $Revision: 28962 $, $Date: 2011-02-07 16:53:35 -0500 (Mon, 07 Feb 2011) $
 * @since 1.1.2, 2011-01
 * @author pmendelson
 *
 */
    public enum AccessRestrictionDirection  {
    INCLUDE(1),EXCLUDE(0);
    private int code;
    private AccessRestrictionDirection(int code) {
        this.code=code;
    }
    public Integer getId() {
        return code;
    }
//    public static AccessRestrictionDirection valueOf(Integer code) {
//        return (AccessRestrictionDirection) CodableEnumUtils.valueOf(code,INCLUDE);
//    }
}
