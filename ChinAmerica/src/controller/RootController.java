package controller;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.User;
import model.UserType;

public class RootController extends Application implements Initializable {

	@FXML private StackPane stackPane;
	@FXML private HBox buttonsMenu;
	@FXML private Button profileButton;
	@FXML private Button signoutButton;
	@FXML private Button openedOrders;
	private Stage primaryStage;
	private BorderPane rootWindow;

	private static User loggedUser;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ChinAmérica");
		openRootWindow();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showLoginAndSignup();
	}

	private void openRootWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/RootWindow.fxml"));
			rootWindow = (BorderPane) loader.load();

			Scene scene = new Scene(rootWindow);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void showLoginAndSignup() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/LoginAndSignup.fxml"));
			Pane loginAndSignup = (Pane) loader.load();
			stackPane.getChildren().add(loginAndSignup);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void setLoggedUser(User newLoggedUser) {
		loggedUser = newLoggedUser;
		updateButtonsMenu();
	}
	
	private static void updateButtonsMenu(){
		removeAllCustomUserButtons();
		
		if(loggedUser != null){
			showLoggedUserButtons();
			if(loggedUser.getUserType().equals(UserType.CLIENT)){
				showClientButtons();
			}
			
			if(loggedUser.getUserType().equals(UserType.MANAGER)){
				showManagerButtons();
			}
		}

		
	}
	
	private static void showLoggedUserButtons(){
		profileButton = new Button();
	}
	
	private static void showClientButtons(){
		
	}
	
	private static void showManagerButtons(){
		
	}
	
	private static void removeAllCustomUserButtons(){
		
	}

}
