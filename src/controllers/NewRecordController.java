/*
 * 
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import database.ContactDAO;
import database.HealthRecordDAO;
import database.ResidenceDAO;
import database.SocialDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import models.Contact;
import models.HealthRecord;
import models.Residence;
import models.Social;
import javafx.scene.control.ToggleGroup;

 
/**
 * The Class NewRecordController.
 */
public class NewRecordController implements Initializable {
	
	/** The last name text field. */
	@FXML
	private JFXTextField lastNameTextField;

	/** The first name text field. */
	@FXML
	private JFXTextField firstNameTextField;

	/** The jmbg text field. */
	@FXML
	private JFXTextField jmbgTextField;

	/** The date of birth date picker. */
	@FXML
	private JFXDatePicker dateOfBirthDatePicker;

	/** The date of death date picker. */
	@FXML
	private JFXDatePicker dateOfDeathDatePicker;

	/** The birth place text field. */
	@FXML
	private JFXTextField birthPlaceTextField;

	/** The male RB. */
	@FXML
	private JFXRadioButton maleRB;

	/** The rbtn sex. */
	@FXML
	private ToggleGroup rbtnSex;

	/** The female RB. */
	@FXML
	private JFXRadioButton femaleRB;

	/** The record number text field. */
	@FXML
	private JFXTextField recordNumberTextField;

	/** The address text field. */
	@FXML
	private JFXTextField addressTextField;

	/** The city text field. */
	@FXML
	private JFXTextField cityTextField;

	/** The country text field. */
	@FXML
	private JFXTextField countryTextField;

	/** The citizenship text field. */
	@FXML
	private JFXTextField citizenshipTextField;

	/** The phone text field. */
	@FXML
	private JFXTextField phoneTextField;

	/** The m phone text field. */
	@FXML
	private JFXTextField mPhoneTextField;

	/** The email text field. */
	@FXML
	private JFXTextField emailTextField;

	/** The marital status C box. */
	@FXML
	private JFXComboBox<String> maritalStatusCBox;

	/** The num of children text field. */
	@FXML
	private JFXTextField numOfChildrenTextField;

	/** The vocation C box. */
	@FXML
	private JFXComboBox<String> vocationCBox;

	/** The occupation text field. */
	@FXML
	private JFXTextField occupationTextField;

	/** The material status C box. */
	@FXML
	private JFXComboBox<String> materialStatusCBox;

	/** The family status C box. */
	@FXML
	private JFXComboBox<String> familyStatusCBox;

	/** The create new record btn. */
	@FXML
	private JFXButton createNewRecordBtn;

	/** The sex. */
	private String sex;

	/** The record. */
	private HealthRecord record;
	
	/** The residence. */
	private Residence residence;
	
	/** The contact. */
	private Contact contact;
	
	/** The social. */
	private Social social;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addListeners();
		loadFields();
	}

	/**
	 * Creates a new record.
	 * 
	 * @return void
	 * */
	public void createNewRecord() {
		if (!HealthRecordDAO.exist(jmbgTextField.getText(), recordNumberTextField.getText())) {
			if (isInt(recordNumberTextField.getText())) {
				if ( !jmbgTextField.getText().isEmpty()) {
					sex = maleRB.isSelected() ? "Muško" : "Žensko";
					initRecordDataObjects();
					ResidenceDAO.createNewRecord(residence);
					ContactDAO.createNewRecord(contact);
					SocialDAO.createNewRecord(social);
					HealthRecordDAO.createNewRecord(record);
					createMessageWindow("Uspeh!", "Uspešno ste kreirali karton!");
				} else {
					createMessageWindow("Greška!", "Morate uneti JMBG!");
				}
				
			} else {
				createMessageWindow("Greška!", "Morate uneti broj kartona!");
			}
		} else
			createMessageWindow("Greška!", "Korisnik već postoji!");
		resetFields();
	}
	
	/**
	 * Resets data fields.
	 * 
	 * @return void
	 * */
	public void resetFields( ) {
		addressTextField.setText("");
		birthPlaceTextField.setText("");
		citizenshipTextField.setText("");
		cityTextField.setText("");
		countryTextField.setText("");
		dateOfBirthDatePicker.setValue(null);
		dateOfDeathDatePicker.setValue(null);
		emailTextField.setText("");
		familyStatusCBox.setValue("");
		firstNameTextField.setText("");
		jmbgTextField.setText("");
		lastNameTextField.setText("");
		maritalStatusCBox.setValue("");
		materialStatusCBox.setValue("");
		mPhoneTextField.setText("");
		numOfChildrenTextField.setText("");
		occupationTextField.setText("");
		phoneTextField.setText("");
		recordNumberTextField.setText("");
		vocationCBox.setValue("");
		
		
	}

	/**
	 * Parses a given string.
	 * 
	 * @param string user's input
	 * @return int
	 * */
	private int parseStringToInt(String string) {
		int value = 0;
		try {
			value = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return 0;
		}
		return value;
	}

	/**
	 * Checks if a given string is an integer.
	 * 
	 * @param string user's input
	 * @return true if int, false otherwise
	 * */
	private boolean isInt(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Initialize record data objects.
	 * 
	 * @return void
	 * */
	private void initRecordDataObjects() {
		residence = new Residence(addressTextField.getText(), cityTextField.getText(), countryTextField.getText(),
				citizenshipTextField.getText());

		contact = new Contact(phoneTextField.getText(), mPhoneTextField.getText(), emailTextField.getText());

		social = new Social(maritalStatusCBox.getValue(), parseStringToInt(numOfChildrenTextField.getText()),
				vocationCBox.getValue(), occupationTextField.getText(), materialStatusCBox.getValue(),
				familyStatusCBox.getValue());

		record = new HealthRecord(lastNameTextField.getText(), firstNameTextField.getText(), jmbgTextField.getText(),
				dateOfBirthDatePicker, dateOfDeathDatePicker, birthPlaceTextField.getText(), sex,
				parseStringToInt(recordNumberTextField.getText()), residence, contact, social);
	}

	/**
	 * Loads fields.
	 * 
	 * @return void
	 * */
	private void loadFields() {
		loadMaritalStatus();
		loadVocation();
		loadMaterialStatus();
		loadFamilyStatus();
	}

	/**
	 * Adds listeners.
	 * 
	 * @retun void
	 * */
	private void addListeners() {
		addJMBGTextFieldListener();
		addmPhoneTextFieldListener();
		addEmailTextFieldListener();
	}
	
	/**
	 * Loads a list of marital statuses.
	 * 
	 * @return void
	 * */
	private void loadMaritalStatus() {
		maritalStatusCBox.getItems().add("Neudata/Neoženjen");
		maritalStatusCBox.getItems().add("Razvedena/Razveden");
		maritalStatusCBox.getItems().add("Udata/Oženjen");
		maritalStatusCBox.getItems().add("Udovica/Udovac");
	}

	/**
	 * Loads a list of vocations.
	 * 
	 * @return void
	 * */
	private void loadVocation() {
		vocationCBox.getItems().add("Kvalifikovan");
		vocationCBox.getItems().add("Nekvalifikovan");
		vocationCBox.getItems().add("Polukvalifikovan");
		vocationCBox.getItems().add("Srednja");
		vocationCBox.getItems().add("Visoka VII-1");
		vocationCBox.getItems().add("Visoka VII-2");
		vocationCBox.getItems().add("Visoka VIII");
		vocationCBox.getItems().add("Visokokvalifikovan");
		vocationCBox.getItems().add("Viša");
		vocationCBox.getItems().add("Viša sa specijalizacijom");
	}

	/**
	 * Loads a list of material statuses.
	 * 
	 * @return void
	 * */
	private void loadMaterialStatus() {
		materialStatusCBox.getItems().add("1. kategorija (jako siromašan)");
		materialStatusCBox.getItems().add("2. kategorija (siromašan)");
		materialStatusCBox.getItems().add("3. kategorija (srednje imućan)");
		materialStatusCBox.getItems().add("4. kategorija (imućan)");
		materialStatusCBox.getItems().add("5. kategorija (bogat)");
	}

	/**
	 * Loads a list of material statuses.
	 * 
	 * @return void
	 * */
	private void loadFamilyStatus() {
		familyStatusCBox.getItems().add("Bez roditelja");
		familyStatusCBox.getItems().add("Oba roditelja");
		familyStatusCBox.getItems().add("Roditelji razvedeni");
		familyStatusCBox.getItems().add("Samo jedan roditelj");
		familyStatusCBox.getItems().add("Usvojen");
	}

	/**
	 * Adds email listener.
	 * 
	 * @return void
	 * */
	private void addEmailTextFieldListener() {
		emailTextField.focusedProperty().addListener((v, oldValue, newValue) -> {
			if (!newValue) { // when focus lost
				if (!emailTextField.getText().matches("^(.+)@(.+)$")) {
					emailTextField.setText("");
				}
			}
		});
	}

	/**
	 * Adds phone listener.
	 * 
	 * @return void
	 * */
	private void addmPhoneTextFieldListener() {
		mPhoneTextField.focusedProperty().addListener((v, oldValue, newValue) -> {
			if (!newValue) { // when focus lost
				if (!mPhoneTextField.getText().matches("\\d{3}\\s\\d{6}|\\d{3}\\s\\d{7}")) {
					mPhoneTextField.setText("");
				}
			}
		});
	}

	/**
	 * Adds JMBG listener.
	 * 
	 * @return void
	 * */
	private void addJMBGTextFieldListener() {
		jmbgTextField.focusedProperty().addListener((v, oldValue, newValue) -> {
			if (!newValue) { // when focus lost
				if (!jmbgTextField.getText().matches("\\d{13}")) {
					jmbgTextField.setText("");
				}
			}
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
