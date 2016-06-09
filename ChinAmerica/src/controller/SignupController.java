package controller;

import database.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import model.User;

public class SignupController {
	
	@FXML private Pane SignupPane;
	@FXML private TextField nameLoginField;
	@FXML private TextField lastnameLoginField;
	@FXML private TextField emailLoginField;
	@FXML private TextField adressLoginField;
	@FXML private PasswordField passwordSignupLoginField;
	@FXML private PasswordField passwordConfirmSignupLoginField;
	@FXML private Button finishSignupButton;
	
	private RootController root;
	
	private User user;
		
	@FXML
	public void finishSignupUser(){
	
		user = root.getLoggedUser();
		
		//TODO: Alertar usuário de erros	
		if(fieldsAreValid()){
			setUserFields();
			UserDAO database = new UserDAO();
			database.updateUser(user);
		} else {
			RootController.alert("ERRO", "ERRO", AlertType.ERROR);
		}
		
	}
	
	private void setUserFields(){
		user.setPassword(passwordSignupLoginField.getText(), passwordConfirmSignupLoginField.getText());
		user.setName(nameLoginField.getText());
		user.setAdress(adressLoginField.getText());
		user.setEmail(emailLoginField.getText());
		user.setLastname(lastnameLoginField.getText());
	}
	
	//TODO: Testar melhor os fields
	private boolean fieldsAreValid(){
		return 
		!nameLoginField.getText().isEmpty() &&
		!passwordSignupLoginField.getText().isEmpty() &&
		!passwordConfirmSignupLoginField.getText().isEmpty() &&
		!adressLoginField.getText().isEmpty() &&
		!emailLoginField.getText().isEmpty() &&
		!lastnameLoginField.getText().isEmpty();
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