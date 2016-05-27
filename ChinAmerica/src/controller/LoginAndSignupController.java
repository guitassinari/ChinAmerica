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
import model.Client;
import model.User;

public class LoginAndSignupController implements Initializable {
	
	@FXML private Pane loginAndSignupPane;
	@FXML private TextField cpfSignup;
	@FXML private PasswordField passwordSignup;
	@FXML private PasswordField passwordConfirmSignup;
	private User  loggedUser;
	
	@FXML
	public void loginUser(){
		
	}
	
	@FXML
	public void signupUser(){
		String cpf = cpfSignup.getText();
		String password = passwordSignup.getText();
		String confirmPassword = passwordConfirmSignup.getText();
		Client client = new Client();
		
		try{
			client.setCpf(cpf);
			
			if(password.equals(confirmPassword)){
				client.setPassword(password);
				UserDAO userDao = new UserDAO();
				userDao.addUser(client);
				
				setLoggedUser(client);
			} else {
				alert("Senhas incompativeis!", "Suas senhas não conferem, por favor, verifique-as e tente novamente", AlertType.WARNING);

			}		
			
		} catch(IllegalArgumentException ex) {
			
		}  catch(Exception ex) {
			ex.printStackTrace();
			
			alert("Oops!", "Algo deu errado! Verifique suas informações e tente novamente!", AlertType.ERROR);
			
			//TODO: Problema com cadastros seguidos de usuarios
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
		// TODO Auto-generated method stub
		
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

}
