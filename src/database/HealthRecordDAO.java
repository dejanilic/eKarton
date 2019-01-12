/*
 * 
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.jfoenix.controls.JFXDatePicker;

import models.HealthRecord;

 
/**
 * The Class HealthRecordDAO.
 */
public class HealthRecordDAO {
	
	/** The db instance. */
	private static DBUtil dbInstance = DBUtil.getInstanca();
	
	/**
	 * Creates a new record in the health_record_data table, for a given contact object.
	 * 
	 * @param record record object
	 * @return true if insertion is successfu, otherwise false
	 * */
	public static boolean createNewRecord(HealthRecord record) {
		String query = "INSERT INTO health_record_data(last_name, first_name, JMBG, date_of_birth, birth_place, date_of_death, sex, record_number, contact_id, residence_id, social_data_id)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, record.getLastName());
			statement.setString(2, record.getFirstName());
			statement.setString(3, record.getJMBG());
			if (record.getBirthDate().getValue() == null) {
				statement.setString(4, "0");
			} else {
				statement.setString(4, record.getBirthDate().getValue().toString());
			}
			statement.setString(5, record.getBirthPlace());
			if (record.getDeathDate().getValue() == null) {
				statement.setString(6, "0");
			} else {
				statement.setString(6, record.getDeathDate().getValue().toString());
			}
			statement.setString(7, record.getSex());
			statement.setInt(8, record.getRecordNumber());
			statement.setInt(9, ContactDAO.getLastRecordId());
			statement.setInt(10, ResidenceDAO.getLastRecordId());
			statement.setInt(11, SocialDAO.getLastRecordId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if a record exsts, for a given JMBG and record number.
	 * 
	 * @param JMBG user's JMBG
	 * @param recordNum user's record number
	 * @return true if exists, otherwise false
	 * */
	public static boolean exist(String JMBG, String recordNum) {
		boolean isJmbgFound = false;
		boolean isRecordNumFound = false;
		try {
			String query = "SELECT * FROM health_record_data WHERE JMBG = ?";
			PreparedStatement statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, JMBG);
			ResultSet result = statement.executeQuery();
			if (result.next())
				isJmbgFound = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		try {
			String query = "SELECT * FROM health_record_data WHERE record_number = ?";
			PreparedStatement statement = dbInstance.getConnection().prepareStatement(query);
			statement.setInt(1, parseStringToInt(recordNum));
			ResultSet result = statement.executeQuery();
			if (result.next())
				isRecordNumFound = true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return isJmbgFound || isRecordNumFound;
	}

	/**
	 * Returns a list of basic data.
	 * 
	 * @return list of records
	 * */
	public static ArrayList<HealthRecord> getBasicData() {
		String query = "SELECT * FROM health_record_data";
		ArrayList<HealthRecord> listOfRecords = new ArrayList<>();
		try {
			ResultSet result = dbInstance.select(query);
			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String JMBG = result.getString("JMBG");
				int recordNumber = result.getInt("record_number");
				listOfRecords.add(new HealthRecord(lastName, firstName, JMBG, recordNumber));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfRecords;
	}
	
	/**
	 * Returns a list of basic data for a given first and second name.
	 * 
	 * @param firstname user's first name
	 * @param lastname user's last name
	 * @return list of records
	 * */
	public static ArrayList<HealthRecord> getBasicDataForName(String firstname, String lastname) {
		String query = "SELECT * FROM health_record_data WHERE first_name = ? AND last_name = ?";
		ArrayList<HealthRecord> listOfRecords = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String JMBG = result.getString("JMBG");
				int recordNumber = result.getInt("record_number");
				listOfRecords.add(new HealthRecord(lastName, firstName, JMBG, recordNumber));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfRecords;
	}
	
	/**
	 * Returns a sigle record for a given JMBG and record number.
	 * 
	 * @param JMBG user's JMBG
	 * @param recordNumber user's record number
	 * @return health record
	 * */
	public static HealthRecord getRecord(String JMBG, int recordNumber) {
		String query = "SELECT * FROM health_record_data WHERE JMBG = ? AND record_number = ?";
		HealthRecord record = null;
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, JMBG);
			statement.setInt(2, recordNumber);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				String lastName = result.getString("last_name");
				String firstName = result.getString("first_name");
				String jmbg = result.getString("JMBG");
				String birthDate = result.getString("date_of_birth");
				String deathDate = result.getString("date_of_death");
				String birthPlace = result.getString("birth_place");
				String sex = result.getString("sex");
				record = new HealthRecord(lastName, firstName, jmbg, new JFXDatePicker(convertMilisToDate(birthDate)),
						new JFXDatePicker(convertMilisToDate(deathDate)), birthPlace, sex, recordNumber,
						ResidenceDAO.getResidence(JMBG, recordNumber), ContactDAO.getContact(JMBG, recordNumber),
						SocialDAO.getSocial(JMBG, recordNumber));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return record;
	}
	
	/**
	 * Parses string to int.
	 * 
	 * @param string string input
	 * @return int
	 * */
	private static int parseStringToInt(String string) {
		int value = 0;
		try {
			value = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return 0;
		}
		return value;
	}
	
	/**
	 * Converst miliseconds to date object for a given date string.
	 * 
	 * @param dateString date input
	 * @return LocalDate object
	 * */
	public static LocalDate convertMilisToDate(String dateString) {
		if (dateString.equals("0"))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = null;
		try {
			java.util.Date date = sdf.parse(dateString);
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			sdf.setCalendar(calendar);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);		
		LocalDate local = LocalDate.of(year, month, day);
		return local;
	}

	/**
	 * Updates a specific record for a given contact object, JMBG and record number.
	 *
	 * @param record record object
	 * @param jmbg user's JMBG
	 * @param recordNumber the record number
	 * @return void
	 */
	public static void updateRecord(HealthRecord record, String jmbg, String recordNumber) {
		String query = "UPDATE health_record_data SET last_name = ?, first_name = ?, JMBG = ?, date_of_birth = ?, birth_place = ?, date_of_death = ?, sex = ?, "
				+ "record_number = ? WHERE JMBG = ? AND record_number = ?";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, record.getLastName());
			statement.setString(2, record.getFirstName());
			statement.setString(3, record.getJMBG());
			if (record.getBirthDate().getValue() == null) {
				statement.setString(4, "0");
			} else {
				statement.setString(4, record.getBirthDate().getValue().toString());
			}
			statement.setString(5, record.getBirthPlace());
			if (record.getDeathDate().getValue() == null) {
				statement.setString(6, "0");
			} else {
				statement.setString(6, record.getDeathDate().getValue().toString());
			}
			statement.setString(7, record.getSex());
			statement.setInt(8, record.getRecordNumber());
			statement.setString(9, jmbg);
			statement.setString(10, recordNumber);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a specific record for a given JMBG and record number.
	 * 
	 * @param jmbg user's JMBG
	 * @param recordNumber user's record number
	 * @return void
	 * */
	public static boolean deleteRecord(String jmbg, String recordNumber) {
		String query = "DELETE FROM health_record_data WHERE JMBG = ? AND "
				+ "record_number = ?";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, jmbg);
			statement.setString(2, recordNumber);
			dbInstance.automatskaTransakcija(false);
			ReservationDAO.deleteRecord(HealthRecordDAO.getIdForJMBGAndRecNum(jmbg, parseStringToInt(recordNumber)));
			ContactDAO.deleteRecord(jmbg, recordNumber);
			ResidenceDAO.deleteRecord(jmbg, recordNumber);
			SocialDAO.deleteRecord(jmbg, recordNumber);
			statement.executeUpdate();
			dbInstance.getConnection().commit();
			System.out.println("USPEH");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Returns an id for a given JMBG and record number.
	 * 
	 * @param jmbg user's JMBG
	 * @param recordNum user's record number
	 * @return id if exists, otherwise 0
	 * */
	public static int getIdForJMBGAndRecNum(String jmbg, int recordNum) {
		String query = "SELECT * FROM health_record_data WHERE JMBG = ? AND record_number = ?";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, jmbg);
			statement.setInt(2, recordNum);
			ResultSet result = statement.executeQuery();
			if (result.next())
				return result.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * Returns first name for a given id.
	 * 
	 * @param hrnId id
	 * @return first name 
	 * */
	public static String getFirstNameForId(int hrnId) {
		String query = "SELECT * FROM health_record_data WHERE id = ?";
		String name = "";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setInt(1, hrnId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				name = result.getString("first_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * Returns second name for a given id.
	 * 
	 * @param hrnId id
	 * @return last name
	 * */
	public static String getSecondNameForId(int hrnId) {
		String query = "SELECT * FROM health_record_data WHERE id = ?";
		String name = "";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setInt(1, hrnId);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				name = result.getString("last_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	/**
	 * Counts all rows from the health_record_data table.
	 * 
	 * @return number of rows
	 * */
	public static String countRows() {
		String query = "SELECT COUNT(id) FROM health_record_data";
		int count = 0;
		try {
			for (ResultSet result = dbInstance.select(query); result.next();)
				count = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
		return count + "";
	}
	
	/**
	 * Counts all male rows from the health_record_data table.
	 * 
	 * @return number of male rows
	 * */
	public static String countMaleRows() {
		String query = "SELECT COUNT(id) FROM health_record_data WHERE sex = 'Muško'";
		int count = 0;
		try {
			for (ResultSet result = dbInstance.select(query); result.next();)
				count = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
		return count + "";
	}
	
	/**
	 * Counts all female rows from the health_record_data table.
	 * 
	 * @return number of female rows
	 * */
	public static String countFemaleRows() {
		String query = "SELECT COUNT(id) FROM health_record_data WHERE sex = 'Žensko'";
		int count = 0;
		try {
			for (ResultSet result = dbInstance.select(query); result.next();)
				count = result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return "0";
		}
		return count + "";
	}
}
