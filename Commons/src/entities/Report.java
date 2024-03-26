package entities;

import java.io.Serializable;

/**
 * Represents a report within a park management system. This class stores information related
 * to various types of reports that can be generated, including monthly reports for park activities
 * and change requests for park parameters. It includes details such as the report period, the park
 * it pertains to, the person who created the report, and specific data captured in the report.
 */
public class Report implements Serializable{

	private static final long serialVersionUID = 1L;
	private String month; // The month the report covers.
	private String year; // The year the report covers.
	private String park; // The name of the park the report is about.
	private String madeBy; // The identifier of the person that created the report.
	private int groups; // The number of organized groups that visited the park in the report period.
	private int individual; // The number of private visitors to the park in the report period.
	private String creationStatus; // A variable to determine the status of creating the report.
	private String reportType; // The type of report.
	private boolean reportExist; // Flag indicating whether the report already exists.
	private String variableToChange; // In case of a change request report, the variable to be changed.
	private int newValue; // The new value proposed for the variable in a change request.
	private int amountOfFullDays;
	
	/**
	 * Constructor for creating a general report with essential details
	 * @param park The park the report is related to.
	 * @param month The month the report covers.
	 * @param year The year the report covers.
	 * @param madeBy The creator of the report.
	 * @param type The type of report.
	 */
	public Report(String park, String month, String year, String madeBy, String type) {
		this.setPark(park);
		this.setMonth(month);
		this.setYear(year);
		this.madeBy = madeBy;
		this.reportExist = false;
		this.reportType = type;
	}
	
	/**
	 * Constructor for creating a report specifically for change requests.
	 * @param park The park the change request is related to.
	 * @param madeBy The creator of the report.
	 * @param variableToChange The variable in the park settings that is requested to be changed.
	 * @param newValue The proposed new value for the variable.
	 */
	public Report(String park, String madeBy, String variableToChange, int newValue) {
		this.setPark(park);
		this.madeBy = madeBy;
		this.newValue = newValue;
		this.variableToChange=variableToChange;
	}

	/**
	 * @return The month the report covers.
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * Sets the month the report covers.
	 * @param month
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return The year the report covers.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Sets the year the report covers.
	 * @param year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * @return The name of the park the report is about.
	 */
	public String getPark() {
		return park;
	}

	/**
	 * Sets the name of the park the report is about.
	 * @param park
	 */
	public void setPark(String park) {
		this.park = park;
	}

	/**
	 * @return The identifier of the person that created the report.
	 */
	public String getMadeBy() {
		return madeBy;
	}

	/**
	 * Sets the identifier of the person that created the report.
	 * @param madeBy
	 */
	public void setMadeBy(String madeBy) {
		this.madeBy = madeBy;
	}

	/**
	 * @return The number of organized groups that visited the park in the report period.
	 */
	public int getGroups() {
		return groups;
	}

	/**
	 * Sets the number of organized groups that visited the park in the report period.
	 * @param groups
	 */
	public void setGroups(int groups) {
		this.groups = groups;
	}

	/**
	 * @return The number of private visitors to the park in the report period.
	 */
	public int getIndividuals() {
		return individual;
	}

	/**
	 * Sets the number of private visitors to the park in the report period.
	 * @param individuals
	 */
	public void setIndividuals(int individuals) {
		this.individual = individuals;
	}

	/**
	 * @return The status of creating the report.
	 */
	public String getCreationStatus() {
		return creationStatus;
	}

	/**
	 * Sets the status of creating the report.
	 * @param creationStatus
	 */
	public void setCreationStatus(String creationStatus) {
		this.creationStatus = creationStatus;
	}

	/**
	 * @return True if report exists false otherwise.
	 */
	public boolean isReportExist() {
		return reportExist;
	}

	/**
	 * Sets true if report exists false otherwise.
	 * @param reportExist
	 */
	public void setReportExist(boolean reportExist) {
		this.reportExist = reportExist;
	}

	/**
	 * @return The type of report.
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @return In case of a change request report, the variable to be changed.
	 */
	public String getVariableToChange() {
		return variableToChange;
	}

	/**
	 * Sets the in case of a change request report, the variable to be changed.
	 * @param variableToChange
	 */
	public void setVariableToChange(String variableToChange) {
		this.variableToChange = variableToChange;
	}

	/**
	 * @return The new value proposed for the variable in a change request.
	 */
	public int getNewValue() {
		return newValue;
	}

	/**
	 * Sets the new value proposed for the variable in a change request.
	 * @param newValue
	 */
	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}

	public int getAmountOfFullDays() {
		return amountOfFullDays;
	}

	public void setAmountOfFullDays(int amountOfFullDays) {
		this.amountOfFullDays = amountOfFullDays;
	}
}
