package org.avaje.one2many.domain;

import com.avaje.ebean.annotation.EnumValue;

public enum AccessLevelAppTypeCode {
    @EnumValue("O")
    OPTIONAL("O"), //
    @EnumValue("S")
    SUGGESTED("S"), //
    @EnumValue("NA")
    NOT_APPLICABLE("NA");
    private String id;

    private AccessLevelAppTypeCode(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
