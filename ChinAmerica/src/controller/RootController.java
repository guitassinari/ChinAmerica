package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Product;
import model.User;
import model.UserType;

public class RootController extends Application implements Initializable {

	@FXML
	private StackPane stackPane;
	@FXML
	private HBox buttonsMenu;
	@FXML
	private Button openLoginSignupButton;
	@FXML
	private Button openProductEditionButton;
	@FXML
	private Button profileButton;
	@FXML
	private Button signoutButton;
	@FXML
	private Button openedOrders;
	
	private Stage primaryStage;
	private BorderPane rootWindow;

	private Product productParam;
	private User loggedUser;

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
		updateButtons();
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

	@FXML
	public void showLoginAndSignup() {
		clearStackPane();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/LoginAndSignup.fxml"));
			LoginAndSignupController loginAndSignupController = new LoginAndSignupController();
			loginAndSignupController.setRoot(this);
			loader.setController(loginAndSignupController);
			Pane loginAndSignup = (Pane) loader.load();
			stackPane.getChildren().add(loginAndSignup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void showSignup() {
		clearStackPane();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/Signup.fxml"));
			SignupController SignupController = new SignupController();
			SignupController.setRoot(this);
			loader.setController(SignupController);
			Pane Signup = (Pane) loader.load();
			stackPane.getChildren().add(Signup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void showProductEdition() {
		clearStackPane();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/Product.fxml"));
			ProductController productController = new ProductController();
			productController.setRoot(this);
			productController.setProduct(productParam);
			loader.setController(productController);
			Pane productEdition = (Pane) loader.load();
			stackPane.getChildren().add(productEdition);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearStackPane() {
		stackPane.getChildren().clear();
	}

	private void updateButtons() {
		// TODO: Remover todos os botões do menu
		if (loggedUser != null) {
			disableGeneralButtons(false);
			if (loggedUser.getUserType().equals(UserType.CLIENT)) {
				disableClientButtons(false);
			}

			if (loggedUser.getUserType().equals(UserType.MANAGER)) {
				disableManagerButtons(false);
			}
		} else {
			disableClientButtons(true);
			disableManagerButtons(true);
			disableGeneralButtons(true);
		}
	}
	
	private void disableGeneralButtons(boolean value){
		openProductEditionButton.setDisable(value);
		profileButton.setDisable(value);
		signoutButton.setDisable(value);
	}

	private void disableClientButtons(boolean value) {
		
	}
	
	private void disableManagerButtons(boolean value){
		openedOrders.setDisable(value);
	}



	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User newLoggedUser) {
		loggedUser = newLoggedUser;
		updateButtons();
	}

	public static void alert(String alertTitle, String alertText, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(alertTitle);
		alert.setHeaderText(null);
		alert.setContentText(alertText);
		alert.showAndWait();
	}

	public Product getProductParam() {
		return productParam;
	}

	public void setProductParam(Product productParam) {
		this.productParam = productParam;
	}

}
