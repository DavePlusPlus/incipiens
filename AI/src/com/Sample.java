package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Sample extends Application {
	
	public static void main(String[] args) throws ClassNotFoundException {
		launch(args);
		
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
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Stage Title");
		StackPane pane = new StackPane();
		stage.setScene(new Scene(pane, 400, 300));
		stage.show();
	}
}