package main.handlers;

import java.sql.ResultSet;
import java.sql.Statement;

import entities.Report;
import main.Constants;
import main.MainServer;

public class ReportRequestHandler {

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
	
	
}
