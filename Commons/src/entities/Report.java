package entities;

import java.io.Serializable;

public class Report implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String month;
	private String year;
	private String park;
	
	private String madeBy;
	
	private int groups;
	private int individual;
	
	private String creationStatus;
	private String reportType;
	private boolean reportExist;
	
	
	private String variableToChange;
	private int newValue;
	
	public Report(String park, String month, String year, String madeBy, String type) {
		this.setPark(park);
		this.setMonth(month);
		this.setYear(year);
		this.madeBy = madeBy;
		this.reportExist = false;
		this.reportType = type;
	}
	
	//change request data
	public Report(String park, String madeBy, String variableToChange, int newValue) {
		this.setPark(park);
		this.madeBy = madeBy;
		this.newValue = newValue;
		this.variableToChange=variableToChange;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getMadeBy() {
		return madeBy;
	}

	public void setMadeBy(String madeBy) {
		this.madeBy = madeBy;
	}

	public int getGroups() {
		return groups;
	}

	public void setGroups(int groups) {
		this.groups = groups;
	}

	public int getIndividuals() {
		return individual;
	}

	public void setIndividuals(int individuals) {
		this.individual = individuals;
	}

	public String getCreationStatus() {
		return creationStatus;
	}

	public void setCreationStatus(String creationStatus) {
		this.creationStatus = creationStatus;
	}

	public boolean isReportExist() {
		return reportExist;
	}

	public void setReportExist(boolean reportExist) {
		this.reportExist = reportExist;
	}

	public String getReportType() {
		return reportType;
	}

	public String getVariableToChange() {
		return variableToChange;
	}

	public void setVariableToChange(String variableToChange) {
		this.variableToChange = variableToChange;
	}

	public int getNewValue() {
		return newValue;
	}

	public void setNewValue(int newValue) {
		this.newValue = newValue;
	}
	
	
	
}
