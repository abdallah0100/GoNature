package entities;

import javafx.beans.property.SimpleStringProperty;

public class UsageReport {
	
	private SimpleStringProperty month;
	private SimpleStringProperty year;
	private SimpleStringProperty amount;
	private SimpleStringProperty park;
	private SimpleStringProperty madeBy;
	
	public UsageReport(String month, String year,String amount, String park, String madeBy) {
		this.month = new SimpleStringProperty(month);
		this.year = new SimpleStringProperty(year);
		this.amount = new SimpleStringProperty(amount);
		this.park = new SimpleStringProperty(park);
		this.madeBy = new SimpleStringProperty(madeBy);
	}

	public String getMonth() {
		return month.get();
	}

	public void setMonth(String month) {
		this.month = new SimpleStringProperty(month);
	}

	public String getYear() {
		return year.get();
	}

	public void setYear(String year) {
		this.year =new SimpleStringProperty(year);
	}

	public String getAmount() {
		return amount.get();
	}

	public void setAmount(String amount) {
		this.amount = new SimpleStringProperty(amount);
	}

	public String getPark() {
		return park.get();
	}

	public void setPark(String park) {
		this.park = new SimpleStringProperty(park);
	}

	public String getMadeBy() {
		return madeBy.get();
	}

	public void setMadeBy(String madeBy) {
		this.madeBy = new SimpleStringProperty(madeBy);
	}
	

}
