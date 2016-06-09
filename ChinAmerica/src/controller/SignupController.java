package controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import model.User;
import model.UserType;

public class SignupController {
	@FXML private Pane SignupPane;
	@FXML private TextField nameLoginField;
	@FXML private TextField lastnameLoginField;
	@FXML private TextField emailLoginField;
	@FXML private TextField adressLoginField;
	@FXML private PasswordField passwordSignupLoginField;
	@FXML private Button finishSignupButton;
	
	private RootController root;
	
	@FXML
	public void finishSignupUser(){
	
		User user;
		user = root.getLoggedUser();
		
		if(nameLoginField.getText() != "" && passwordSignupLoginField.getText() != "" && adressLoginField.getText() != "" && emailLoginField.getText() != "" && lastnameLoginField.getText() != "" )
		{
			user.setPassword(passwordSignupLoginField.getText());
			user.setName(nameLoginField.getText());
			user.setAdress(adressLoginField.getText());
			user.setEmail(emailLoginField.getText());
			user.setLastname(lastnameLoginField.getText());
		}
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