package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	private String name;
	private String lastname;
	private String cpf;
	private String email;
	private String adress;
	private String password;
	
	public User(){
		
	}
	


	public User(String name, String lastname, String cpf, String email, String adress, String password) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.cpf = cpf;
		this.email = email;
		this.adress = adress;
		this.password = password;
	}

	public User(String name, String lastname, String cpf, String password, String email) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.cpf = cpf;
		this.password = password;
		this.email = email;
	}

	@Column(name = "lastname")
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "email", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "adress")
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@Column(name = "cpf", nullable = false, unique = true)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
