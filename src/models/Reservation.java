/*
 * 
 */
package models;

import java.util.ArrayList;

import database.HealthRecordDAO;
import database.ReservationDAO;

 
/**
 * The Class Reservation.
 */
public class Reservation {
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The doctor name. */
	private String doctorName;
	
	/** The reason. */
	private String reason;
	
	/** The date. */
	private String date;
	
	/** The time. */
	private String time;
	
	/** The list of time. */
	private ArrayList<String> listOfTime;
	
	/** The hrd id. */
	private int hrdId;

	/**
	 * Instantiates a new reservation.
	 *
	 * @param doctorName the doctor name
	 * @param reason the reason
	 * @param date the date
	 * @param listOfTime the list of time
	 * @param hrdId the hrd id
	 */
	public Reservation(String doctorName, String reason, String date, ArrayList<String> listOfTime, int hrdId) {
		this.firstName = HealthRecordDAO.getFirstNameForId(hrdId);
		this.lastName = HealthRecordDAO.getSecondNameForId(hrdId);
		this.doctorName = doctorName;
		this.reason = reason;
		this.date = date;
		this.listOfTime = listOfTime;
		this.hrdId = hrdId;
		this.time = ReservationDAO.formatTime(this.listOfTime);
	}

	/**
	 * Gets the doctor name.
	 *
	 * @return the doctor name
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * Sets the doctor name.
	 *
	 * @param doctorName the new doctor name
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * Gets the reason.
	 *
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Sets the reason.
	 *
	 * @param reason the new reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the hrn id.
	 *
	 * @return the hrn id
	 */
	public int getHrnId() {
		return hrdId;
	}

	/**
	 * Sets the hrn id.
	 *
	 * @param hrnId the new hrn id
	 */
	public void setHrnId(int hrnId) {
		this.hrdId = hrnId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param secondName the new last name
	 */
	public void setLastName(String secondName) {
		this.lastName = secondName;
	}

	/**
	 * Gets the list of time.
	 *
	 * @return the list of time
	 */
	public ArrayList<String> getListOfTime() {
		return listOfTime;
	}

	/**
	 * Sets the list of time.
	 *
	 * @param listOfTime the new list of time
	 */
	public void setListOfTime(ArrayList<String> listOfTime) {
		this.listOfTime = listOfTime;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(String time) {
		this.time = time;
	}
}