/*
 * 
 */
package models;

 
/**
 * The Class Social.
 */
public class Social {
	
	/** The marital status. */
	private String maritalStatus;
	
	/** The num of children. */
	private int numOfChildren;
	
	/** The vocation. */
	private String vocation;
	
	/** The occupation. */
	private String occupation;
	
	/** The material status. */
	private String materialStatus;
	
	/** The family status. */
	private String familyStatus;

	/**
	 * Instantiates a new social.
	 *
	 * @param maritalStatus the marital status
	 * @param numOfChildren the num of children
	 * @param vocation the vocation
	 * @param occupation the occupation
	 * @param materialStatus the material status
	 * @param familyStatus the family status
	 */
	public Social(String maritalStatus, int numOfChildren, String vocation, String occupation, String materialStatus, String familyStatus) {
		this.maritalStatus = maritalStatus;
		this.numOfChildren = numOfChildren;
		this.vocation = vocation;
		this.occupation = occupation;
		this.materialStatus = materialStatus;
		this.familyStatus = familyStatus;
	}

	/**
	 * Gets the marital status.
	 *
	 * @return the marital status
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * Sets the marital status.
	 *
	 * @param maritalStatus the new marital status
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * Gets the num of children.
	 *
	 * @return the num of children
	 */
	public int getNumOfChildren() {
		return numOfChildren;
	}

	/**
	 * Sets the num of children.
	 *
	 * @param numOfChildren the new num of children
	 */
	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}

	/**
	 * Gets the vocation.
	 *
	 * @return the vocation
	 */
	public String getVocation() {
		return vocation;
	}

	/**
	 * Sets the vocation.
	 *
	 * @param vocation the new vocation
	 */
	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	/**
	 * Gets the occupation.
	 *
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * Sets the occupation.
	 *
	 * @param occupation the new occupation
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * Gets the material status.
	 *
	 * @return the material status
	 */
	public String getMaterialStatus() {
		return materialStatus;
	}

	/**
	 * Sets the material status.
	 *
	 * @param materialStatus the new material status
	 */
	public void setMaterialStatus(String materialStatus) {
		this.materialStatus = materialStatus;
	}

	/**
	 * Gets the family status.
	 *
	 * @return the family status
	 */
	public String getFamilyStatus() {
		return familyStatus;
	}

	/**
	 * Sets the family status.
	 *
	 * @param familyStatus the new family status
	 */
	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}
}
