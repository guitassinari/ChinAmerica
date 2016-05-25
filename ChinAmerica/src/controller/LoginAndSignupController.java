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

public class LoginAndSignupController implements Initializable {
	
	@FXML private Pane loginAndSignupPane;
	@FXML private TextField cpfSignup;
	@FXML private PasswordField passwordSignup;
	@FXML private PasswordField passwordConfirmSignup;

	@FXML
	public void loginUser(){
		
	}
	
	@FXML
	public void signupUser(){
		String cpf = cpfSignup.getText();
		String password = passwordSignup.getText();
		String confirmPassword = passwordConfirmSignup.getText();
		
		User user = new User();
		
		try{
			user.setCpf(cpf);
			
			if(password.equals(confirmPassword)){
				user.setPassword(password);
				UserDAO userDao = new UserDAO();
				userDao.addUser(user);	
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Bem Vindo ao ChinAmérica!");
				alert.setHeaderText(null);
				alert.setContentText("Você foi cadastro com sucesso! Agora você já pode realizar seu login.");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Oops!");
				alert.setHeaderText(null);
				alert.setContentText("Suas senhas não conferem! Por favor, digite-as novamente.");
				alert.showAndWait();
			}		
			
		} catch(Exception ex){
			ex.printStackTrace();
			
			//Alerta caso qualquer coisa da errado
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Oops!");
			alert.setHeaderText(null);
			alert.setContentText("Algo deu errado! Verifique seus dados e tente novamente.");
			alert.showAndWait();
			
			//TODO: Problema com cadastros seguidos de usuarios
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
