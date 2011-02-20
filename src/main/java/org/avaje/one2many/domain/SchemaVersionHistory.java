
package org.avaje.one2many.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Represents a schema version history entry.
 * 
 * @author ywang29
 * 
 */
@Entity
@Table(name = "SCHEMA_VERSION_HISTORY")
public class SchemaVersionHistory  {

    private Long id;
    private String version;
    private Date updateDate;
    private String comments;

    @Id
    @Column(name = "SCHEMA_VERSION_HISTORY_ID")
    @GeneratedValue(generator = "SCHEMA_VERSION_HISTORY_SEQ", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SCHEMA_VERSION_HISTORY_SEQ", sequenceName = "SCHEMA_VERSION_HISTORY_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "VERSION")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "UPDATE_DATE")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "COMMENTS")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
