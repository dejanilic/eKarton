/*
 * 
 */
package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import database.HealthRecordDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.HealthRecord;

 
/**
 * The Class FilesPageController.
 */
public class FilesPageController implements Initializable{
	
	/** The table view. */
	@FXML
    private TableView<HealthRecord> tableView;
	
	/** The first name col. */
	private TableColumn<HealthRecord, String> firstNameCol;
	
	/** The last name col. */
	private TableColumn<HealthRecord, String> lastNameCol;
	
	/** The JMBG col. */
	private TableColumn<HealthRecord, String> JMBGCol;
	
	/** The Record number col. */
	private TableColumn<HealthRecord, Integer> RecordNumberCol;
	
	/** The list of records. */
	private ArrayList<HealthRecord> listOfRecords;
	
	/** The table list of records. */
	private ObservableList<HealthRecord> tableListOfRecords;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initColumns();
		if (HomePageController.getInput().equals("")) {
			listOfRecords = HealthRecordDAO.getBasicData();
		} else {
			String[] parsed = HomePageController.getInput().split(" ");
			if (parsed.length != 2)
				return;
			listOfRecords = HealthRecordDAO.getBasicDataForName(parsed[0], parsed[1]);
		}
		tableListOfRecords = FXCollections.observableArrayList(listOfRecords);
		tableView.setItems(tableListOfRecords);
		tableView.getColumns().addAll(firstNameCol, lastNameCol, JMBGCol, RecordNumberCol);
	}
	
	/**
	 * Initializes a list of record's table.
	 * 
	 * @return void
	 * */
	private void initColumns() {
		firstNameCol = new TableColumn<>("Ime");
		firstNameCol.setMinWidth(200);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		lastNameCol = new TableColumn<>("Prezime");
		lastNameCol.setMinWidth(200);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		JMBGCol = new TableColumn<>("JMBG");
		JMBGCol.setMinWidth(200);
		JMBGCol.setCellValueFactory(new PropertyValueFactory<>("JMBG"));
		
		RecordNumberCol = new TableColumn<>("Broj kartona");
		RecordNumberCol.setMinWidth(200);
		RecordNumberCol.setCellValueFactory(new PropertyValueFactory<>("recordNumber"));
	}
}
