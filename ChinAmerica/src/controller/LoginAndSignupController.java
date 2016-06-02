package controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.User;
import model.UserType;

public class LoginAndSignupController implements Initializable {
	
	@FXML private Pane loginAndSignupPane;
	@FXML private TextField cpfLoginField;
	@FXML private PasswordField passwordLoginField;
	@FXML private TextField cpfSignupField;
	@FXML private PasswordField passwordSignupField;
	@FXML private PasswordField confirmPasswordSignupField;
	private RootController root;
	
	@FXML
	public void loginUser(){
		User user = new User();
		String loginCpf = cpfLoginField.getText();
		String loginPassword = passwordLoginField.getText();
		
		user.setCpf(loginCpf);
		
		if(user.alreadyExists()){
			UserDAO dao = new UserDAO();
			user = dao.getUserByCpf(user.getCpf());
			
			if( user.getPassword().equals(loginPassword) ){
				setLoggedUser(user);
			} else {
				alert("Senha incorreta", "Verifique sua senha e tente novamente", AlertType.WARNING);
			}
		} else {
			alert("Usuário não cadastrado", "Verifique seus dados, ou cadastre-se abaixo.", AlertType.WARNING);
		}
	}
	
	@FXML
	public void signupUser(){
		String cpf = cpfSignupField.getText();
		String password = passwordSignupField.getText();
		String confirmPassword = confirmPasswordSignupField.getText();
		User client = new User();
		UserDAO userDao = new UserDAO();
		
		client.setCpf(cpf);
		
		if(!client.alreadyExists()){	
			try{				
				if(!password.isEmpty() && password.equals(confirmPassword)){
					client.setPassword(password);
					client.setUserType(UserType.CLIENT);
					userDao.addUser(client);
					
					alert("Usuário cadastrado com sucesso!","Bem vindo ao ChinAmérica!",AlertType.CONFIRMATION);
					
					setLoggedUser(client);
				} else {
					alert("Erro na senha", "Verifique suas senhas e tente novamente.", AlertType.WARNING);
				}		
				
			} catch(IllegalArgumentException ex) {
				ex.printStackTrace();
				alert("CPF inválido!", "Verifique seu CPF e tente novamente! ", AlertType.WARNING);
			}  catch(Exception ex) {
				ex.printStackTrace();
				alert("Oops!", "Algo deu errado! Verifique suas informações e tente novamente!", AlertType.ERROR);
			}
		} else {
			alert("Usuário já cadastrado!", "Faça seu login ou, clique em 'esqueci minha senha' se esqueceu sua senha!", AlertType.WARNING);
		}
	}
	
	public void alert(String alertTitle, String alertText, AlertType alertType){
		Alert alert = new Alert(alertType);
		alert.setTitle(alertTitle);
		alert.setHeaderText(null);
		alert.setContentText(alertText);
		alert.showAndWait();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setLoggedUser(User loggedUser) {
		root.setLoggedUser(loggedUser);
	}

	public RootController getRoot() {
		return root;
	}

	public void setRoot(RootController root) {
		this.root = root;
	}

}
