package org.avaje.one2many.domain;

import com.avaje.ebean.annotation.EnumValue;

public enum AssignmentStatus {
    REJECTED(1), APPROVED(3), PENDING(5);
    private int code;

    private AssignmentStatus(int code) {
        this.code=code;
    }

    /** @see CodableEnum#getId() */
    public Integer getId() {
        return code;
    }
}