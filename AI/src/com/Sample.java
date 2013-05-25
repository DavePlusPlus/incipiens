package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sample {
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:Data/dbFile.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("drop table if exists table1");
			statement.executeUpdate("create table table1 (text string)");
			statement.executeUpdate("insert into table1 values('Hello World.')");
			ResultSet rs = statement.executeQuery("select text from table1");
			while(rs.next()) {
				System.out.println(rs.getString("text"));
			}
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		finally {
			try {
				if(connection != null)
					connection.close();
			}
			catch(SQLException e) {
				System.err.println(e);
			}
		}
	}
}