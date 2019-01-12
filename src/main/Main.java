/*
 * 
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Class Main.
 */
public class Main extends Application {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/resources/view/loginPage.fxml"));
		window.setTitle("eKarton - Logovanje v1.5.1");
		Scene scene = new Scene(root, 800, 449);
		scene.getStylesheets().add("/resources/css/style.css");
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}
}