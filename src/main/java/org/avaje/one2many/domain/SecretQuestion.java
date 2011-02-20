
package org.avaje.one2many.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SECRET_QUESTION")
public class SecretQuestion  {

    private Long id;
    private String value;

    public SecretQuestion() {}
    
    public SecretQuestion(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    @Id
    @Column(name = "SECRET_QUESTION_ID")
    @GeneratedValue(generator = "SECRET_QUESTION_SEQ", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SECRET_QUESTION_SEQ", sequenceName = "SECRET_QUESTION_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "VALUE")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
