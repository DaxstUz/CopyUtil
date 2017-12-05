package com.daxstuz.copy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgSqlUtil {

	public static Connection connectPgJdbc(String ip,String port,String dbname,String userName,String userPsd) {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
			return null;
		}

		System.out.println("PostgreSQL JDBC Driver Registered!");
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://"+ip+":"+port+"/"+dbname+"?charSet=UTF-8", userName, userPsd);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
			return null;
		}
		return connection;
	}
}
