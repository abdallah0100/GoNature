package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entities.Report;
import main.Constants;
import main.MainServer;

/**
 * Manages creation, verification, and updating of reports related to park visitor statistics.
 * This includes handling reports for the number of visitors, not full park days, and managing full park instances.
 */
public class ReportRequestHandler {

	/**
	 * Checks if a numerical visitor report for a specific period and creator exists.
	 * 
	 * @param r The report containing the month, year, and creator to check for.
	 * @return True if the report exists, false otherwise.
	 */
	public static boolean numReportExist(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			String str = "SELECT COUNT(madeBy) FROM numOfVisitorsReports WHERE month='"+r.getMonth()+"' AND year='"+r.getYear()+"' AND madeBy='"+r.getMadeBy()+"'";
			ResultSet rs = st.executeQuery(str);
			if (!rs.next())
				return false;
			try {
				return Integer.parseInt(rs.getString(1)) > 0;
			}catch(Exception ex) {
				System.out.println("[ReportRequestHandler] - result was not an integer");
				ex.printStackTrace();
				return false;
			}
		}catch(Exception ex) {
			System.out.println("[ReportRequestHandler] - Error executing query");
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Verifies the existence of a 'Not Full Report' for a given period and report creator.
	 * 
	 * @param r The report to verify.
	 * @return True if such a report exists, false otherwise.
	 */
	public static boolean notFullReportExist(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			String str = "SELECT COUNT(madeBy) FROM NotFullReports WHERE month='"+r.getMonth()+"' AND year='"+r.getYear()+"' AND madeBy='"+r.getMadeBy()+"'";
			ResultSet rs = st.executeQuery(str);
			if (!rs.next())
				return false;
			try {
				return Integer.parseInt(rs.getString(1)) > 0;
			}catch(Exception ex) {
				System.out.println("[ReportRequestHandler] - result was not an integer");
				ex.printStackTrace();
				return false;
			}
		}catch(Exception ex) {
			System.out.println("[ReportRequestHandler] - Error executing query");
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Retrieves the count of reservations by type for a given report.
	 * 
	 * @param type The type of reservation to count.
	 * @param r The report detailing the park, month, and year to filter by.
	 * @return The count of reservations of the specified type.
	 */
	public static int getReservationCountByType(String type, Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return 0;
		}
		try {
			Statement st = MainServer.dbConnection.createStatement();
			
			String str = "SELECT COUNT(visitorID) FROM reservations WHERE Type='"+type+"' AND Park='"+r.getPark()+"' "
					+ "AND month(ReservationDate)='"+r.getMonth()+"' AND year(ReservationDate)='"+r.getYear()+"'";

			ResultSet rs = st.executeQuery(str);
			if (!rs.next()) {
				System.out.println("[ReportRequestHandler] - singles result set was empty");
				return 0;
			}
			try {
				int result = Integer.parseInt(rs.getString(1));
				return result;
			}catch(Exception ex) {
				System.out.println("[ReportRequestHandler] - singles result was not an integer");
				return 0;
			}
		}catch(Exception ex) {
			System.out.println("[ReportRequestHandler] - failed to execute fetch query");
			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Inserts a new numerical visitor report into the database.
	 * 
	 * @param r The report to insert.
	 * @return True if the insertion is successful, false otherwise.
	 */
	public static boolean insertNewNumReport(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try{
			Statement st = MainServer.dbConnection.createStatement();
			String str = "INSERT INTO numOfVisitorsReports (month, year, groupNumbers, individuals, Park, madeBy) "
					+ "VALUES('"+r.getMonth()+"','"+r.getYear()+"','"+r.getGroups()+"','"+r.getIndividuals()+"','"+r.getPark()+"','"+r.getMadeBy()+"')";
			return st.executeUpdate(str) > 0;
		}catch(Exception ex) {
			System.out.println("[ReportRequestHandler] - failed to insert report");
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Updates an existing numerical visitor report with new values.
	 * 
	 * @param r The report containing the new values and the identifiers to find the existing report.
	 * @return True if the update is successful, false otherwise.
	 */
	public static boolean updateNumReport(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try{
			String str = "UPDATE numOfVisitorsReports SET groupNumbers=?, individuals=?, Park=? WHERE month=? AND year=? AND madeBy=?;";
			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);
			ps.setString(1, r.getGroups() + "");
			ps.setString(2, r.getIndividuals() + "");
			ps.setString(3, r.getPark());
			
			ps.setString(4, r.getMonth());
			ps.setString(5, r.getYear());
			ps.setString(6, r.getMadeBy());
			
			return ps.executeUpdate() > 0;
		}catch(Exception ex) {
			System.out.println("[ReportRequestHandler] - failed to insert report");
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Calculates the total number of visitors for a given month, year, and report creator.
	 * 
	 * @param r The report detailing the month, year, and creator to filter by.
	 * @return The total number of visitors in the specified period.
	 */
	public static int getTotalVisitorsInMonth(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return 0;
		}
		try{
			Statement st = MainServer.dbConnection.createStatement();
			String str = "SELECT sum(NumberOfVisitors) FROM reservations WHERE month='"+r.getMonth()+"' AND year='"+r.getYear()+"', madeBy='"+r.getMadeBy()+"'";
			ResultSet rs = st.executeQuery(str);
			if (!rs.next()) {
				System.out.println("[ReportRequestHandler] - " + r.getPark() + " had no visitors in the selected date");
				return 0;
			}
			try {
				return Integer.parseInt(rs.getString(1));
			}catch(Exception ex) {
				System.out.println("[ReportRequestHandler] - number of visitor result was not an integer");
			}
			
		}catch(Exception ex) {
			System.out.println("[ReportRequestHandler] - failed to insert report");
			ex.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	/**
	 * Retrieves the count of days when the park was fully occupied in a given month and year.
	 * 
	 * @param r The report specifying the park, month, and year.
	 * @return The count of full park days.
	 */
	public static int getFullDays(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return 0;
		}
		try{
			String str = "SELECT COUNT(*) FROM full_park WHERE parkName='"+r.getPark()+"' AND year='"+r.getYear()+"' AND month='"+r.getMonth()+"'";
			Statement st = MainServer.dbConnection.createStatement();
			ResultSet rs = st.executeQuery(str);
			if (!rs.next()) {
				System.out.println("[ReportRequestHandler] - ");
				return 0;
			}
			return rs.getInt(1);
		}catch(Exception ex) {
			System.out.println("[ReportRequestHandler] - failed to execute getFullDays");
			ex.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Inserts a new 'Not Full Report' indicating days the park was not fully occupied.
	 * 
	 * @param r The report containing the data for the new 'Not Full Report'.
	 * @return True if the report is successfully inserted, false otherwise.
	 */
	public static boolean createEmptyReport(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			String str = "INSERT INTO NotFullReports (year,month,amount,Park,madeBy) VALUES(?,?,?,?,?)";
			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);
			ps.setString(1, r.getYear());
			ps.setString(2, r.getMonth());
			ps.setInt(3, r.getAmountOfFullDays());
			ps.setString(4, r.getPark());
			ps.setString(5, r.getMadeBy());
			
			return ps.executeUpdate() > 0;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Updates an existing 'Not Full Report' with a new amount of not fully occupied days.
	 * 
	 * @param r The report containing the new amount and the identifiers to find the existing report.
	 * @return True if the update is successful, false otherwise.
	 */
	public static boolean updateEmptyReport(Report r) {
		if (MainServer.dbConnection == null) {
			System.out.println(Constants.DB_CONNECTION_ERROR);
			return false;
		}
		try {
			String str = "UPDATE NotFullReports SET amount=? WHERE Park=? AND year=? AND month=?";
			PreparedStatement ps = MainServer.dbConnection.prepareStatement(str);
			ps.setInt(1, r.getAmountOfFullDays());
			ps.setString(2, r.getPark());
			ps.setString(3, r.getYear());
			ps.setString(4, r.getMonth());
			
			return ps.executeUpdate() > 0;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
