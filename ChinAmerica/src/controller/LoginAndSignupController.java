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
		UserDAO database = new UserDAO();

		try{
			user.setCpf(loginCpf);
			if(user.alreadyExists()){
				user = database.getUserByCpf(user.getCpf());
				
				if(loginPassword.equals(user.getPassword())){
					setLoggedUser(user);
				} else {
					RootController.alert("Senha incorreta", "Verifique sua senha e tente novamente", AlertType.WARNING);
				}
			} else {
				RootController.alert("Usuário não cadastrado", "Verifique seus dados, ou cadastre-se abaixo.", AlertType.WARNING);
			}
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			RootController.alert("CPF inválido!", "Verifique seu CPF e tente novamente!", AlertType.WARNING);
		} catch (Exception ex) {
			ex.printStackTrace();
			RootController.alert("OOPS!", "Algo deu errado! Contate nossos desenvolvedores para conseguir ajuda!", AlertType.ERROR);
		}		
	}
	
	@FXML
	public void signupUser(){
		String cpf = cpfSignupField.getText();
		String password = passwordSignupField.getText();
		String confirmPassword = confirmPasswordSignupField.getText();
		User client = new User();
		UserDAO database = new UserDAO();
		client.setUserType(UserType.CLIENT);
		
		try{

			client.setCpf(cpf);
			if(client.alreadyExists()){	
				RootController.alert("Usuário já cadastrado!", "Faça seu login ou, clique em 'esqueci minha senha' se esqueceu sua senha!", AlertType.WARNING);
			} else {
				client.setPassword(password, confirmPassword);
				database.addUser(client);
				RootController.alert("Usuário cadastrado com sucesso!","Bem vindo ao ChinAmérica!",AlertType.CONFIRMATION);			
				setLoggedUser(client);
			}
			
		} catch (IllegalArgumentException ex){
			ex.printStackTrace();
			RootController.alert("Dados inválidos!", "Verifique CPF e senha e tente novamente!", AlertType.WARNING);
		} catch (Exception ex){
			ex.printStackTrace();
			RootController.alert("OOPS!", "Algo deu errado! Entre em contato com nossos desenvolvedores para que eles demorem meses para resolver o problema", AlertType.ERROR);
		}
		
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
