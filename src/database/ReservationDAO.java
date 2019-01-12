/*
 * 
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.HealthRecord;
import models.Reservation;

 
/**
 * The Class ReservationDAO.
 */
public class ReservationDAO {
	
	/** The db instance. */
	private static DBUtil dbInstance = DBUtil.getInstanca();
	
	/**
	 * Creates a new record in the reservation table, for a given list, doctor name, date, reason and a record object.
	 *
	 * @param list list of reservations
	 * @param doctorName doctor's name
	 * @param date reservation date
	 * @param reason the reason
	 * @param record record object
	 * @return true if insertion is successfu, otherwise false
	 */
	public static void createNewReservation(ArrayList<String> list, String doctorName, String date, String reason, HealthRecord record) {
		String query = "INSERT INTO reservation(doctor_name, reason, date, am9, am10, am11, am12, pm13, pm14, pm15, pm16, pm17, pm18, health_record_data_id) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, doctorName);
			statement.setString(2, reason);
			statement.setString(3, date);
			statement.setString(4, list.get(0));
			statement.setString(5, list.get(1));
			statement.setString(6, list.get(2));
			statement.setString(7, list.get(3));
			statement.setString(8, list.get(4));
			statement.setString(9, list.get(5));
			statement.setString(10, list.get(6));
			statement.setString(11, list.get(7));
			statement.setString(12, list.get(8));
			statement.setString(13, list.get(9));
			statement.setInt(14, HealthRecordDAO.getIdForJMBGAndRecNum(record.getJMBG(), record.getRecordNumber()));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Updates a specific record for a given list, username, date, reason and a record object.
	 *
	 * @param list list of reservations
	 * @param username the username
	 * @param date reservation date
	 * @param reason the reason
	 * @param record record object
	 * @return void
	 */
	public static void updateReservation(ArrayList<String> list, String username, String date, String reason, HealthRecord record) {
		String query = "UPDATE reservation SET reason = ?, date = ?, am9 = ?, am10 = ?, am11 = ?, am12 = ?, pm13 = ?,"
				+ " pm14 = ?, pm15 = ?, pm16 = ?, pm17 = ?, pm18 = ? WHERE doctor_name = ? AND date = ?" 
				+ " AND health_record_data_id = ?";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, reason);
			statement.setString(2, date);
			statement.setString(3, list.get(0));
			statement.setString(4, list.get(1));
			statement.setString(5, list.get(2));
			statement.setString(6, list.get(3));
			statement.setString(7, list.get(4));
			statement.setString(8, list.get(5));
			statement.setString(9, list.get(6));
			statement.setString(10, list.get(7));
			statement.setString(11, list.get(8));
			statement.setString(12, list.get(9));
			statement.setString(13, username);
			statement.setString(14, date);
			statement.setInt(15, HealthRecordDAO.getIdForJMBGAndRecNum(record.getJMBG(), record.getRecordNumber()));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns a list of reservation time.
	 * 
	 * @param id user's id
	 * @param username user's username
	 * @param date reservation date
	 * @return list of records if record exists, othwerwise empty list
	 * */
	public static ArrayList<String> getReservationTime(int id, String username, String date) {
		String query = "SELECT * FROM reservation WHERE health_record_data_id = ? AND doctor_name = ? AND date = ?";
		ArrayList<String> list = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setInt(1, id);
			statement.setString(2, username);
			statement.setString(3, date);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				list.add(result.getString("am9"));
				list.add(result.getString("am10"));
				list.add(result.getString("am11"));
				list.add(result.getString("am12"));
				list.add(result.getString("pm13"));
				list.add(result.getString("pm14"));
				list.add(result.getString("pm15"));
				list.add(result.getString("pm16"));
				list.add(result.getString("pm17"));
				list.add(result.getString("pm18"));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
	
	/**
	 * Returns a list of reservation time for a given doctor name, date and JMBG.
	 *
	 * @param doctorName doctor's name
	 * @param date reservation date
	 * @param jmbg the jmbg
	 * @return list of reservations if record exists, othwerwise empty list
	 */
	public static ArrayList<String> getReservationTimeForDate(String doctorName, String date, String jmbg) {
		String query = "SELECT * FROM reservation WHERE doctor_name = ? AND date = ? AND health_record_data_id NOT IN(SELECT id from health_record_data WHERE JMBG = ?)";
		ArrayList<String> list = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, doctorName);
			statement.setString(2, date);
			statement.setString(3, jmbg);
			ResultSet result = statement.executeQuery();
			if (!result.next())
				return list;
			list.add(result.getString("am9"));
			list.add(result.getString("am10"));
			list.add(result.getString("am11"));
			list.add(result.getString("am12"));
			list.add(result.getString("pm13"));
			list.add(result.getString("pm14"));
			list.add(result.getString("pm15"));
			list.add(result.getString("pm16"));
			list.add(result.getString("pm17"));
			list.add(result.getString("pm18"));
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<String>();
	}
	
	/**
	 * Checks if a record exists.
	 * 
	 * @param id id
	 * @param username user's username
	 * @param date reservation date
	 * @return true if exists, otherwise false
	 * */
	public static boolean exist(int id, String username, String date) {
		String query = "SELECT * FROM reservation WHERE health_record_data_id = ? AND doctor_name = ? AND date = ?";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setInt(1, id);
			statement.setString(2, username);
			statement.setString(3, date);
			ResultSet result = statement.executeQuery();
			if (result.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/**
	 * Returns a list of reservations.
	 * 
	 * @return list of reservations
	 * */
	public static ArrayList<Reservation> getReservations() {
		String query = "SELECT * FROM reservation";
		ArrayList<Reservation> listOfReservations = new ArrayList<>();
		
		try {
			ResultSet result = dbInstance.select(query);
			while (result.next()) {
				String doctorName = result.getString("doctor_name");
				String reason = result.getString("reason");
				String date = result.getString("date");
				int hrdId = result.getInt("health_record_data_id");
				ArrayList<String> listOfTime = new ArrayList<>();
				listOfTime = getReservationTime(hrdId, doctorName, date);
				listOfReservations.add(new Reservation(doctorName, reason, date, listOfTime, hrdId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfReservations;
	}

	/**
	 * Deletes a specific record for a given id.
	 * 
	 * @param id user's id
	 * @return void
	 * */
	public static void deleteRecord(int id) {
		String query = "DELETE FROM reservation WHERE health_record_data_id = ?";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Formats time.
	 *
	 * @param list a list of reservations
	 * @return the string
	 * @returns formatted time string
	 */
	public static String formatTime(ArrayList<String> list) {
		StringBuilder formatted = new StringBuilder("");
		if (list.get(0).equals("res")) {
			formatted.append("9:00:00 ");
		}
		if (list.get(1).equals("res")) {
			formatted.append("10:00:00 ");
		}
		if (list.get(2).equals("res")) {
			formatted.append("11:00:00 ");
		}
		if (list.get(3).equals("res")) {
			formatted.append("12:00:00 ");
		}
		if (list.get(4).equals("res")) {
			formatted.append("13:00:00 ");
		}
		if (list.get(5).equals("res")) {
			formatted.append("14:00:00 ");
		}
		if (list.get(6).equals("res")) {
			formatted.append("15:00:00 ");
		}
		if (list.get(7).equals("res")) {
			formatted.append("16:00:00 ");
		}
		if (list.get(8).equals("res")) {
			formatted.append("17:00:00 ");
		}
		if (list.get(9).equals("res")) {
			formatted.append("18:00:00 ");
		}
		return formatted.toString();
	}
	
	/**
	 * Counts rows from the reservation table.
	 * 
	 * @return rows count
	 * */
	public static String countRows() {
		String query = "SELECT COUNT(reservation_id) FROM reservation";
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
