/*
 * 
 */
package models;

 
/**
 * The Class Contact.
 */
public class Contact {
	
	/** The phone. */
	private String phone;
	
	/** The mobile phone. */
	private String mobilePhone;
	
	/** The email. */
	private String email;

	/**
	 * Instantiates a new contact.
	 *
	 * @param phone the phone
	 * @param mobilePhone the mobile phone
	 * @param email the email
	 */
	public Contact(String phone, String mobilePhone, String email) {
		this.phone = phone;
		this.mobilePhone = mobilePhone;
		this.email = email;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the mobile phone.
	 *
	 * @return the mobile phone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * Sets the mobile phone.
	 *
	 * @param mobilePhone the new mobile phone
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
