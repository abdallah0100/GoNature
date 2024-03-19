package entities;

import java.io.Serializable;

public class UsageReport implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String month;
    private String year;
    private String amount;
    private String park;
    private String madeBy;
    
    public UsageReport(String month, String year, String amount, String park, String madeBy) {
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.park = park;
        this.madeBy = madeBy;
    }

    // Getters and setters

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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
}
