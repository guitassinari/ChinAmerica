package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RootController extends Application {

	private Stage primaryStage;
	private BorderPane rootWindow;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;

		this.primaryStage.setTitle("ChinAmérica");

		openRootWindow();
		showLoginAndSignup();
	}

	private void openRootWindow() {
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/RootWindow.fxml"));
			this.rootWindow = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootWindow);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void showLoginAndSignup() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RootController.class.getResource("../view/LoginAndSignup.fxml"));
            Pane loginAndSignup = (Pane) loader.load();
            StackPane stack = (StackPane) rootWindow.getCenter();
            
            stack.getChildren().add(loginAndSignup);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public BorderPane getRootWindow() {
		return rootWindow;
	}

	public void setRootWindow(BorderPane rootWindow) {
		this.rootWindow = rootWindow;
	}

}
