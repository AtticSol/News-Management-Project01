package by.itac.project01.bean;

import java.io.Serializable;
import java.util.Objects;

public class NewUserInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idNewUserInfo=0;
	private String name;
	private String login;
	private String password;	
	private String confirmPassword;
	private String email;
	
	public NewUserInfo() {
	}

	public NewUserInfo(String name, String login, String password, String confirmPassword,
			String email) {
		this.name = name;
		this.login = login;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}

	public Integer getIdNewUserInfo() {
		return idNewUserInfo;
	}

	public void setIdNewUserInfo(Integer idNewUserInfo) {
		this.idNewUserInfo = idNewUserInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(confirmPassword, email, idNewUserInfo, login, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewUserInfo other = (NewUserInfo) obj;
		return Objects.equals(confirmPassword, other.confirmPassword) && Objects.equals(email, other.email)
				&& Objects.equals(idNewUserInfo, other.idNewUserInfo) && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "NewUserInfo [idNewUserInfo=" + idNewUserInfo + ", name=" + name + ", login=" + login + ", password="
				+ password + ", confirmPassword=" + confirmPassword + ", email=" + email + "]";
	}
		
}
