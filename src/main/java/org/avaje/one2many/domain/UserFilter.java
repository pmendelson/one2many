
package org.avaje.one2many.domain;

public class UserFilter {

    private AccountStatus status;
    private String usernameContains;
    private String lastNameInitial;
    private Role functionalRole;
    private Boolean checked;

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    /**
     * Gets the sub string to search for in userName. Case-insensitive.
     * 
     * @return
     */
    public String getUsernameContains() {
        return usernameContains;
    }

    public void setUsernameContains(String usernameContains) {
        this.usernameContains = usernameContains;
    }

	public void setLastNameInitial(String lastNameInitial) {
		this.lastNameInitial = lastNameInitial;
	}

	public String getLastNameInitial() {
		return lastNameInitial;
	}

	public void setFunctionalRole(Role functionalRole) {
		this.functionalRole = functionalRole;
	}

	public Role getFunctionalRole() {
		return functionalRole;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getChecked() {
		return checked;
	}

}
