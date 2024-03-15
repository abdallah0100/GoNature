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
	
	public Report(String park, String month, String year, String madeBy) {
		this.setPark(park);
		this.setMonth(month);
		this.setYear(year);
		this.madeBy = madeBy;
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
	
	
	
}
