/*
 * 
 */
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Residence;

 
/**
 * The Class ResidenceDAO.
 */
public class ResidenceDAO {
	
	/** The db instance. */
	private static DBUtil dbInstance = DBUtil.getInstanca();

	/**
	 * Returns a last record's id from the residence table.
	 * 
	 * @return last record id
	 * */
	public static int getLastRecordId() {
		String query = "SELECT * FROM residence ORDER BY residence_id DESC LIMIT 1";
		int lastRecord = 0;
		try {
			ResultSet result = dbInstance.select(query);
			if (result.next())
				lastRecord = result.getInt("residence_id");
			else
				lastRecord = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lastRecord;
	}
	
	/**
	 * Creates a new record in the residence table, for a given residence object.
	 * 
	 * @param residence residence object
	 * @return true if insertion is successfu, otherwise false
	 * */
	public static boolean createNewRecord(Residence residence) {
		String query = "INSERT INTO residence(address, city, country, citizenship)"
				+ "VALUES(?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, residence.getAddress());
			statement.setString(2, residence.getCity());
			statement.setString(3, residence.getCountry());
			statement.setString(4, residence.getCitizenship());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * Updates a specific record for a given residence object, JMBG and record number.
	 * 
	 * @param residence residence object
	 * @param jmbg user's JMBG
	 * @param recordNum user's record number
	 * @return true if update is successful, otherwise false
	 * */
	public static boolean updateRecord(Residence residence, String jmbg, String recordNum) {
		String query = "UPDATE residence SET address = ?, city = ?, country = ?, citizenship = ? "
				+ "WHERE residence_id IN(SELECT residence_id FROM health_record_data WHERE JMBG = " + jmbg + " AND "
						+ "record_number = " + recordNum + ")";
		PreparedStatement statement;
		try {
			statement = dbInstance.getConnection().prepareStatement(query);
			statement.setString(1, residence.getAddress());
			statement.setString(2, residence.getCity());
			statement.setString(3, residence.getCountry());
			statement.setString(4, residence.getCitizenship());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a residence object for a given JMBG and record number.
	 * 
	 * @param JMBG user's JMBG
	 * @param recordNumber user's record number
	 * @return residence object
	 * */
	public static Residence getResidence(String JMBG, int recordNumber) {
		String query = "SELECT * FROM residence WHERE residence_id IN(SELECT residence_id FROM health_record_data WHERE JMBG = "
				+ JMBG + " AND record_number = " + recordNumber + ")";	
		Residence residence = null;
		try {
			ResultSet result = dbInstance.select(query);
			if (result.next()) {
				String address = result.getString("address");
				String city = result.getString("city");
				String country = result.getString("country");
				String citizenship = result.getString("citizenship");
				residence = new Residence(address, city, country, citizenship);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return residence;
	}
	
	/**
	 * Deletes a specific record for a given jmbg and record number.
	 * 
	 * @param jmbg user's JMBG
	 * @param recordNumber user's record number
	 * @return void
	 * */
	public static void deleteRecord(String jmbg, String recordNumber) {
		String query = "DELETE FROM residence WHERE residence_id IN (SELECT residence_id FROM health_record_data WHERE JMBG = " + jmbg + " AND "
				+ " record_number = " + recordNumber + ")";
		try {
			dbInstance.iudQuerry(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
