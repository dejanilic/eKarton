/*
 * 
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import database.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Class ChangePasswordController.
 */
public class ChangePasswordController {
	
	/** The new password text field. */
	@FXML
	private JFXTextField newPasswordTextField;

	/** The Repeat new password text field. */
	@FXML
	private JFXTextField RepeatNewPasswordTextField;

	/** The change password btn. */
	@FXML
	private JFXButton changePasswordBtn;

	/** The username. */
	String username = HomePageController.getUsername();

	/**
	 * Changes an user's password.
	 * 
	 * @return void
	 * 
	 * */
	public void changePassword() {
		if (!newPasswordTextField.getText().equals("") && !RepeatNewPasswordTextField.getText().equals("")) {
			UserDAO.changePassword(username, newPasswordTextField.getText());
			createMessageWindow("Uspeh!", "Lozinka uspešno promenjena!");
		} else {
			createMessageWindow("Greška!", "Oba polja moraju biti popunjena!");
		}
	}
	
	/**
	 * Creates an alert window for a given status and message.
	 * 
	 * @param status user's status
	 * @param message user's message
	 * 
	 * */
	private void createMessageWindow(String status, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("eKarton - Poruka v1.5.1");
		alert.setHeaderText(status);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
