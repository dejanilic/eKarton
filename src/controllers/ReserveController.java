/*
 * 
 */
package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;

import database.HealthRecordDAO;
import database.ReservationDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.HealthRecord;

 
/**
 * The Class ReserveController.
 */
public class ReserveController implements Initializable{
	
	/** The list of records C box. */
	@FXML
    private JFXComboBox<HealthRecord> listOfRecordsCBox;

    /** The list of doctors C box. */
    @FXML
    private JFXComboBox<String> listOfDoctorsCBox;

    /** The list of reasons C box. */
    @FXML
    private JFXComboBox<String> listOfReasonsCBox;

    /** The date date picker. */
    @FXML
    private JFXDatePicker dateDatePicker;

    /** The reserve btn. */
    @FXML
    private JFXButton reserveBtn;

    /** The Chk btn 9. */
    @FXML
    private JFXCheckBox ChkBtn9;

    /** The Chk btn 10. */
    @FXML
    private JFXCheckBox ChkBtn10;

    /** The Chk btn 11. */
    @FXML
    private JFXCheckBox ChkBtn11;

    /** The Chk btn 12. */
    @FXML
    private JFXCheckBox ChkBtn12;

    /** The Chk btn 13. */
    @FXML
    private JFXCheckBox ChkBtn13;

    /** The Chk btn 14. */
    @FXML
    private JFXCheckBox ChkBtn14;

    /** The Chk btn 15. */
    @FXML
    private JFXCheckBox ChkBtn15;

    /** The Chk btn 16. */
    @FXML
    private JFXCheckBox ChkBtn16;

    /** The Chk btn 17. */
    @FXML
    private JFXCheckBox ChkBtn17;

    /** The Chk btn 18. */
    @FXML
    private JFXCheckBox ChkBtn18;
    
    /** The username. */
    String username;

    /** The list of chks. */
    ArrayList<JFXCheckBox> listOfChks;
    
    /** The list of records. */
    ArrayList<HealthRecord> listOfRecords;
    
	/** The selected record. */
	HealthRecord selectedRecord;
	
	/** The full record. */
	HealthRecord fullRecord;
    
    /** The record. */
    HealthRecord record;
    
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dateDatePicker.setValue(LocalDate.now());
		listOfRecords = HealthRecordDAO.getBasicData();
		listOfRecords.forEach(record -> listOfRecordsCBox.getItems().add(record));
		listOfRecordsCBox.setValue(listOfRecords.get(0));
		username = LoginPageController.getUserName();
		listOfDoctorsCBox.getItems().add(username);
		listOfDoctorsCBox.setValue(username);
		initListOfChks();
		loadReasons();
		addRecordBoxListener();
		addDateListener();
	}
	
	/**
	 * Reserves an appointment.
	 * 
	 * @return void
	 * */
	public void reserve() {
		initRecord();
		if (listOfRecordsCBox.getValue() != null && dateDatePicker.getValue() != null) {
			if (ReservationDAO.exist(HealthRecordDAO.getIdForJMBGAndRecNum(fullRecord.getJMBG(), fullRecord.getRecordNumber()), username, dateDatePicker.getValue().toString())) {
				ReservationDAO.updateReservation(getAppointmentList(), username, dateDatePicker.getValue().toString(),
					listOfReasonsCBox.getValue(), HealthRecordDAO.getRecord(fullRecord.getJMBG(), fullRecord.getRecordNumber()));
				createMessageWindow("Uspeh!", "Uspešno ste zakazali termin!");
				return;
			}
			ReservationDAO.createNewReservation(getAppointmentList(), username, dateDatePicker.getValue().toString(),
					listOfReasonsCBox.getValue(), HealthRecordDAO.getRecord(fullRecord.getJMBG(), fullRecord.getRecordNumber()));
			createMessageWindow("Uspeh!", "Uspešno ste zakazali termin!");
		} else {
			createMessageWindow("Greška!", "Greška pri zakazivanju termina!");
		}
	}
	
	/**
	 * Adds date listener.
	 * 
	 * @return void
	 * */
	public void addDateListener() {
		dateDatePicker.valueProperty().addListener((ob, oldV, newV) -> {
			if (listOfRecordsCBox.getValue() == null)
				return;
			resetChks();
			if (newV != null) {
				enableAll();
				disableReserved();
				String date = dateDatePicker.getValue() == null ? "" : dateDatePicker.getValue().toString();
				resetChks();
				record = HealthRecordDAO.getRecord(listOfRecordsCBox.getValue().getJMBG(), listOfRecordsCBox.getValue().getRecordNumber());
				setAppointmentFields(ReservationDAO.getReservationTime(
						HealthRecordDAO.getIdForJMBGAndRecNum(record.getJMBG(), record.getRecordNumber()), username,
						date));
			}
		});
	}
	
	/**
	 * Initializes a record.
	 * 
	 * @return void
	 * */
	public void initRecord() {
		selectedRecord = listOfRecordsCBox.getValue();
		if (selectedRecord != null) {
			fullRecord = HealthRecordDAO.getRecord(selectedRecord.getJMBG(), selectedRecord.getRecordNumber());
			System.out.println("Nije null");
		}
	}
	
	/**
	 * Adds record listener.
	 * 
	 * @return void
	 * */
	private void addRecordBoxListener() {
		listOfRecordsCBox.valueProperty().addListener((ob, oldVal, newVal) -> {
			if (newVal != null) {
				enableAll();
				disableReserved();
				String date = dateDatePicker.getValue() == null ? "" : dateDatePicker.getValue().toString();
				resetChks();
				record = HealthRecordDAO.getRecord(listOfRecordsCBox.getValue().getJMBG(),
						listOfRecordsCBox.getValue().getRecordNumber());
				setAppointmentFields(ReservationDAO.getReservationTime(
						HealthRecordDAO.getIdForJMBGAndRecNum(record.getJMBG(), record.getRecordNumber()), username,
						date));
			}
		});
	}
	
	/**
	 * Returns a list of appointments.
	 * 
	 * @return list of appointments
	 * */
	private ArrayList<String> getAppointmentList() {
		ArrayList<String> list = new ArrayList<>();
		listOfChks.forEach(elem -> {
			if (elem.isSelected())
				list.add("res");
			else
				list.add("free");
		});
		return list;
	}
	
	/**
	 * Enable all.
	 */
	private void enableAll() {
		listOfChks.forEach(elem -> elem.setDisable(false));
	}
	
	/**
	 * Disable reserved.
	 */
	private void disableReserved() {
		ArrayList<String> reserved = ReservationDAO.getReservationTimeForDate(listOfDoctorsCBox.getValue(),
				dateDatePicker.getValue().toString(),
				listOfRecordsCBox.getValue().getJMBG());
		int counter = 0;
		for(String elem: reserved) {
			listOfChks.get(counter).setDisable("res".equals(elem));
			++counter;
		}
	}
	
	/**
	 * Inits the list of chks.
	 */
	private void initListOfChks() {
		listOfChks = new ArrayList<>();
		listOfChks.add(ChkBtn9);
		listOfChks.add(ChkBtn10);
		listOfChks.add(ChkBtn11);
		listOfChks.add(ChkBtn12);
		listOfChks.add(ChkBtn13);
		listOfChks.add(ChkBtn14);
		listOfChks.add(ChkBtn15);
		listOfChks.add(ChkBtn16);
		listOfChks.add(ChkBtn17);
		listOfChks.add(ChkBtn18);
	}

	/**
	 * Sets an appointment list.
	 * 
	 * @param list list of appointments
	 * @return void
	 * */
	private void setAppointmentFields(ArrayList<String> list) {
		int counter = 0;
		for(String elem: list) {
			if (elem.equals("res"))
				listOfChks.get(counter).setSelected(true);
			else
				listOfChks.get(counter).setSelected(false);
			counter++;
		}
	}
	
	/**
	 * Loads appointments reasons.
	 * 
	 * @return void
	 * */
	private void loadReasons() {
		listOfReasonsCBox.getItems().add("Poseta");
		listOfReasonsCBox.getItems().add("Ultrazvuk");
		listOfReasonsCBox.getItems().add("Recepti");
		listOfReasonsCBox.getItems().add("Kontrola");
		listOfReasonsCBox.getItems().add("HITNO");
		listOfReasonsCBox.getItems().add("Vanredno");
		listOfReasonsCBox.getItems().add("Uput-IZIS");
		listOfReasonsCBox.setValue("Poseta");
	}
	
	/**
	 * Reset chks.
	 */
	private void resetChks() {
		listOfChks.forEach(elem -> {
			elem.setSelected(false);
		});
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
