/*
 * 
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.User;

 
/**
 * The Class LoginPageController.
 */
public class LoginPageController {
	
	/** The register btn. */
	@FXML
	private JFXButton registerBtn;

	/** The login btn. */
	@FXML
	private JFXButton loginBtn;

	/** The username txt fld. */
	@FXML
	private JFXTextField usernameTxtFld;

	/** The password txt fld. */
	@FXML
	private JFXPasswordField passwordTxtFld;

	/** The username lbl. */
	@FXML
	private Label usernameLbl;

	/** The password lbl. */
	@FXML
	private Label passwordLbl;

	/** The message lbl. */
	@FXML
	private Label messageLbl;

	/** The window. */
	@FXML
	private AnchorPane window;

	/** The password. */
	private static String username, password;

	/**
	 * Logs user.
	 *
	 * @return void
	 * @throws Exception the exception
	 */
	public void login() throws Exception {
		checkInputData();
		if (isUserValid()) {
			Stage window = new Stage();
			Scene scene;
			Parent root = FXMLLoader.load(getClass().getResource("/resources/view/homePage.fxml"));
			window.setTitle("eKarton - Početna v1.5.1");
			scene = new Scene(root, 850, 520);
			scene.getStylesheets().add("/resources/css/style.css");
			window.setMinWidth(860);
			window.setMinHeight(560);
			window.setScene(scene);
			window.show();
			exit();
		} else {
			messageLbl.setTextFill(Color.web("#cc0000"));
			messageLbl.setText("Korisnik ne postoji!");
			usernameTxtFld.clear();
			passwordTxtFld.clear();
		}
		addUsernameTxtFldListener();
		addPasswordTxtFldListener();
	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public static String getUserName() {
		return username;
	}

	/**
	 * Closes the current window.
	 * 
	 * @return void
	 * */
	public void exit() {
		Stage stage = (Stage) loginBtn.getScene().getWindow();
		stage.close();
	}

	/**
	 * Checks if user exists in the database.
	 * 
	 * @return true if exists, false otherwise.
	 * */
	private boolean isUserValid() {
		if (!usernameTxtFld.getText().equals("") && !passwordTxtFld.getText().equals("")) {
			username = usernameTxtFld.getText();
			password = passwordTxtFld.getText();
			if (UserDAO.exist(new User(username, password)))
				return true;
		}
		return false;
	}

	/**
	 * Adds username listener.
	 * 
	 * @return void
	 * */
	private void addUsernameTxtFldListener() {
		usernameTxtFld.focusedProperty().addListener((v, oldV, newV) -> {
			if (!newV && usernameTxtFld.getText().equals("")) {
				usernameLbl.setText("korisničko ime ne sme biti prazno!");
			}
		});
	}

	/**
	 * Adds password listener.
	 * 
	 * @return void
	 * */
	private void addPasswordTxtFldListener() {
		passwordTxtFld.focusedProperty().addListener((v, oldV, newV) -> {
			if (!newV && passwordTxtFld.getText().equals("")) {
				passwordLbl.setText("lozinka ne sme biti prazna!");
			}
		});
	}

	/**
	 * Checks if the username and password fields contains input.
	 *
	 * @return void
	 */
	private void checkInputData() {
		if (usernameTxtFld.getText().equals(""))
			usernameLbl.setText("korisničko ime ne sme biti prazno!");
		if (passwordTxtFld.getText().equals(""))
			passwordLbl.setText("lozinka ne sme biti prazna!");
	}

	/**
	 * Reset username lbl.
	 */
	public void resetUsernameLbl() {
		usernameLbl.setText("");
	}

	/**
	 * Reset password lbl.
	 */
	public void resetPasswordLbl() {
		passwordLbl.setText("");
	}
}
