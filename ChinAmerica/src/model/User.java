package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {

	private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	
	private UserType userType;
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

	@Column(name = "email", unique = true)
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

	@Column(name = "name")
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
		if(!isValidCPF(cpf)){
			throw new IllegalArgumentException();
		}
		
		this.cpf = cpf;
	}

   private static int calcularDigito(String str, int[] peso) {
      int soma = 0;
      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
         digito = Integer.parseInt(str.substring(indice,indice+1));
         soma += digito*peso[peso.length-str.length()+indice];
      }
      soma = 11 - soma % 11;
      return soma > 9 ? 0 : soma;
   }

   public static boolean isValidCPF(String cpf) {
      if ((cpf==null) || (cpf.length()!=11)) return false;
      
      if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
    	        cpf.equals("22222222222") || cpf.equals("33333333333") ||
    	        cpf.equals("44444444444") || cpf.equals("55555555555") ||
    	        cpf.equals("66666666666") || cpf.equals("77777777777") ||
    	        cpf.equals("88888888888") || cpf.equals("99999999999"))
    	       return(false);

      Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
      Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
      return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
   }

   @Enumerated(EnumType.ORDINAL)
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	   
}
