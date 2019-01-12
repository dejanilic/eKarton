/*
 * 
 */
package models;

 
/**
 * The Class Residence.
 */
public class Residence {
	
	/** The address. */
	private String address;
	
	/** The city. */
	private String city;
	
	/** The country. */
	private String country;
	
	/** The citizenship. */
	private String citizenship;

	/**
	 * Instantiates a new residence.
	 *
	 * @param address the address
	 * @param city the city
	 * @param country the country
	 * @param citizenship the citizenship
	 */
	public Residence(String address, String city, String country, String citizenship) {
		this.address = address;
		this.city = city;
		this.country = country;
		this.citizenship = citizenship;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the citizenship.
	 *
	 * @return the citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * Sets the citizenship.
	 *
	 * @param citizenship the new citizenship
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
}