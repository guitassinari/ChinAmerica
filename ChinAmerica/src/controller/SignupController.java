package controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.User;

public class SignupController implements Initializable{
	
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
			try{
				setUserFields();
				UserDAO database = new UserDAO();
				database.updateUser(user);
				RootController.alert("Sucesso", "Seus dados foram salvos com sucesso!", AlertType.CONFIRMATION);	
			} catch (Exception e){
				RootController.alert("ERRO","Houve algo errado! Contate nossos desenvolvedores para resolver o problema",AlertType.ERROR);
				e.printStackTrace();
			}
			
		} else {
			RootController.alert("ERRO", "Os campos nome, sobrenome e endereço são obrigatorios.", AlertType.ERROR);
		}
		
	}
	
	private void setUserFields(){
		if(!passwordConfirmSignupLoginField.getText().trim().isEmpty()){
			user.setPassword(passwordSignupLoginField.getText(), passwordConfirmSignupLoginField.getText());
		}
		user.setName(nameLoginField.getText());
		user.setAdress(adressLoginField.getText());
		user.setEmail(emailLoginField.getText());
		user.setLastname(lastnameLoginField.getText());
	}
	
	private boolean fieldsAreValid(){
		
		if(!nameLoginField.getText().isEmpty() &&		!adressLoginField.getText().isEmpty() &&		!lastnameLoginField.getText().isEmpty()){
			if(!passwordConfirmSignupLoginField.getText().trim().isEmpty() || !passwordSignupLoginField.getText().trim().isEmpty()){
				return User.isValidPassword(passwordSignupLoginField.getText())		&&		passwordConfirmSignupLoginField.getText().equals(passwordSignupLoginField.getText());
			}
		}
		
		return true;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		UserDAO database = new UserDAO();
		User user = database.getUserByCpf(root.getLoggedUser().getCpf());
		
		nameLoginField.setText(user.getName());
		lastnameLoginField.setText(user.getLastname());
		emailLoginField.setText(user.getEmail());
		adressLoginField.setText(user.getAdress());
		
	}
	
}