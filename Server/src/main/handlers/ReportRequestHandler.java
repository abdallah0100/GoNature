package main.handlers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entities.Report;
import main.Constants;
import main.MainServer;

public class ReportRequestHandler {

	public static boolean reportExist(Report r) {
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
	
	public static boolean insertNewReport(Report r) {
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
	
	public static boolean updateReport(Report r) {
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
	
	
}
