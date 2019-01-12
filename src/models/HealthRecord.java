/*
 * 
 */
package models;

import com.jfoenix.controls.JFXDatePicker;

 
/**
 * The Class HealthRecord.
 */
public class HealthRecord {
	
	/** The last name. */
	private String lastName;
	
	/** The first name. */
	private String firstName;
	
	/** The jmbg. */
	private String JMBG;
	
	/** The birth place. */
	private String birthPlace;
	
	/** The sex. */
	private String sex;
	
	/** The record number. */
	private int recordNumber;
	
	/** The birth date. */
	private JFXDatePicker birthDate;
	
	/** The death date. */
	private JFXDatePicker deathDate;
	
	/** The residence. */
	private Residence residence;
	
	/** The contact. */
	private Contact contact;
	
	/** The social. */
	private Social social;

	/**
	 * Instantiates a new health record.
	 *
	 * @param lastName the last name
	 * @param firstName the first name
	 * @param JMBG the jmbg
	 * @param recordNumber the record number
	 */
	public HealthRecord(String lastName, String firstName, String JMBG, int recordNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.JMBG = JMBG;
		this.recordNumber = recordNumber;
	}

	/**
	 * Instantiates a new health record.
	 *
	 * @param lastName the last name
	 * @param firstName the first name
	 * @param jMBG the j MBG
	 * @param birthDate the birth date
	 * @param deathDate the death date
	 * @param birthPlace the birth place
	 * @param sex the sex
	 * @param recordNumber the record number
	 * @param residence the residence
	 * @param contact the contact
	 * @param social the social
	 */
	public HealthRecord(String lastName, String firstName, String jMBG, JFXDatePicker birthDate,
			JFXDatePicker deathDate, String birthPlace, String sex, int recordNumber, Residence residence,
			Contact contact, Social social) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.JMBG = jMBG;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
		this.birthPlace = birthPlace;
		this.sex = sex;
		this.recordNumber = recordNumber;
		this.residence = residence;
		this.contact = contact;
		this.social = social;
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
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * Gets the jmbg.
	 *
	 * @return the jmbg
	 */
	public String getJMBG() {
		return JMBG;
	}

	/**
	 * Sets the jmbg.
	 *
	 * @param jMBG the new jmbg
	 */
	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	/**
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public JFXDatePicker getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(JFXDatePicker birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the death date.
	 *
	 * @return the death date
	 */
	public JFXDatePicker getDeathDate() {
		return deathDate;
	}

	/**
	 * Sets the death date.
	 *
	 * @param deathDate the new death date
	 */
	public void setDeathDate(JFXDatePicker deathDate) {
		this.deathDate = deathDate;
	}

	/**
	 * Gets the birth place.
	 *
	 * @return the birth place
	 */
	public String getBirthPlace() {
		return birthPlace;
	}

	/**
	 * Sets the birth place.
	 *
	 * @param birthPlace the new birth place
	 */
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the sex.
	 *
	 * @param sex the new sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Gets the record number.
	 *
	 * @return the record number
	 */
	public int getRecordNumber() {
		return recordNumber;
	}

	/**
	 * Sets the record number.
	 *
	 * @param recordNumber the new record number
	 */
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}

	/**
	 * Gets the residence.
	 *
	 * @return the residence
	 */
	public Residence getResidence() {
		return residence;
	}

	/**
	 * Sets the residence.
	 *
	 * @param residence the new residence
	 */
	public void setResidence(Residence residence) {
		this.residence = residence;
	}

	/**
	 * Gets the contact.
	 *
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * Sets the contact.
	 *
	 * @param contact the new contact
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * Gets the social.
	 *
	 * @return the social
	 */
	public Social getSocial() {
		return social;
	}

	/**
	 * Sets the social.
	 *
	 * @param social the new social
	 */
	public void setSocial(Social social) {
		this.social = social;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder(firstName + " " + lastName + " " + JMBG + " " + recordNumber).toString();
	}
}