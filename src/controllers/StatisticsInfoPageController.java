/*
 * 
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import database.HealthRecordDAO;
import database.ReservationDAO;
import database.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

 
/**
 * The Class StatisticsInfoPageController.
 */
public class StatisticsInfoPageController implements Initializable {
	
	/** The num of records. */
	@FXML
	private Label numOfRecords;

	/** The num of reserved. */
	@FXML
	private Label numOfReserved;

	/** The num of male. */
	@FXML
	private Label numOfMale;

	/** The num of female. */
	@FXML
	private Label numOfFemale;

	/** The num of doctors. */
	@FXML
	private Label numOfDoctors;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		numOfRecords.setText(HealthRecordDAO.countRows());
		numOfReserved.setText(ReservationDAO.countRows());
		numOfMale.setText(HealthRecordDAO.countMaleRows());
		numOfFemale.setText(HealthRecordDAO.countFemaleRows());
		numOfDoctors.setText(UserDAO.countRows());
	}

}
