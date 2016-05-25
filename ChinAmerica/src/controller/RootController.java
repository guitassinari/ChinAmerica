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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RootController extends Application implements Initializable {
	
	@FXML private StackPane stackPane;
	private Stage primaryStage;
	private BorderPane rootWindow;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ChinAmérica");
		openRootWindow();
	}
	
	private void openRootWindow() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/RootWindow.fxml"));
			rootWindow = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootWindow);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException ex){
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
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showLoginAndSignup();
	}

}
