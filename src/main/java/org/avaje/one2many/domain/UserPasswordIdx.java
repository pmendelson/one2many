
package org.avaje.one2many.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_PASSWORD_IDX")
public class UserPasswordIdx {

    private Long id;
    private User user;
    private String password;
    private Integer idx;
    public UserPasswordIdx() {}
    
    public UserPasswordIdx(Long id, User user, String password, Integer idx) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.setIdx(idx);
    }

    @Id
    @Column(name = "USER_PASSWORD_IDX_ID")
    @GeneratedValue(generator = "USER_PASSWORD_IDX_SEQ", strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name = "USER_PASSWORD_IDX_SEQ", sequenceName = "USER_PASSWORD_IDX_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "IDX")
	public Integer getIdx() {
		return idx;
	}

    public void setIdx(Integer idx) {
		this.idx = idx;
	}
    
}
