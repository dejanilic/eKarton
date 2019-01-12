/*
 * 
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.HealthRecordDAO;
import database.ReservationDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Reservation;

 
/**
 * The Class ReservedController.
 */
public class ReservedController implements Initializable{
	
	/** The table view. */
	@FXML
	private TableView<Reservation> tableView;
	
	/** The first name col. */
	private TableColumn<Reservation, String> firstNameCol;
	
	/** The last name col. */
	private TableColumn<Reservation, String> lastNameCol;
	
	/** The doctor name col. */
	private TableColumn<Reservation, String> doctorNameCol;
	
	/** The reason col. */
	private TableColumn<Reservation, String> reasonCol;
	
	/** The date col. */
	private TableColumn<Reservation, String> dateCol;
	
	/** The time col. */
	private TableColumn<Reservation, String> timeCol;
	
	/** The list of reservations. */
	private ArrayList<Reservation> listOfReservations;
	
	/** The table list of reservations. */
	private ObservableList<Reservation> tableListOfReservations;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initColumns();
		listOfReservations = ReservationDAO.getReservations();
		tableListOfReservations = FXCollections.observableArrayList(listOfReservations);
		tableView.setItems(tableListOfReservations);
		tableView.getColumns().addAll(firstNameCol, lastNameCol, doctorNameCol, reasonCol, dateCol, timeCol);
	}
	
	/**
	 * Initializes a list of reservations table.
	 * 
	 * @return void
	 * */
	private void initColumns() {
		firstNameCol = new TableColumn<>("Ime");
		firstNameCol.setPrefWidth(100);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		lastNameCol = new TableColumn<>("Prezime");
		lastNameCol.setPrefWidth(100);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		doctorNameCol = new TableColumn<>("Ime doktora");
		doctorNameCol.setPrefWidth(100);
		doctorNameCol.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
		
		reasonCol = new TableColumn<>("Razlog");
		reasonCol.setPrefWidth(100);
		reasonCol.setCellValueFactory(new PropertyValueFactory<>("reason"));
		
		dateCol = new TableColumn<>("Datum");
		dateCol.setPrefWidth(100);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		timeCol = new TableColumn<>("Vreme");
		timeCol.setPrefWidth(100);
		timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
	}
	
	
}
