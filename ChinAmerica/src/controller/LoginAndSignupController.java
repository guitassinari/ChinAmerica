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
		User client = new User();
		
		try{
			client.setCpf(cpf);
			
			if(password.equals(confirmPassword)){
				client.setPassword(password);
				client.setUserType(UserType.CLIENT);
				UserDAO userDao = new UserDAO();
				userDao.addUser(client);
				
				setLoggedUser(client);
				enableClientButtons();
				showUserView();
			} else {
				alert("Senhas incompativeis!", "Suas senhas não conferem, por favor, verifique-as e tente novamente", AlertType.WARNING);
			}		
			
		} catch(IllegalArgumentException ex) {
			ex.printStackTrace();
			alert("CPF inválido!", "Verifique seu CPF e tente novamente! ", AlertType.WARNING);
		}  catch(Exception ex) {
			ex.printStackTrace();
			alert("Oops!", "Algo deu errado! Verifique suas informações e tente novamente!", AlertType.ERROR);
		} 
	}
	
	public void alert(String alertTitle, String alertText, AlertType alertType){
		Alert alert = new Alert(alertType);
		alert.setTitle(alertTitle);
		alert.setHeaderText(null);
		alert.setContentText(alertText);
		alert.showAndWait();
	}
	
	public void showUserView(){
		//TODO Criar pagina de usuario (edição de informações como endereço,senha, e etc...
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	public void setLoggedUser(User loggedUser) {
		RootController.setLoggedUser(loggedUser);
		
	}

}
