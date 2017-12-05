package com.daxstuz.copy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleSqlUtil {

	public static Connection connectPgJdbc(String ip,String port,String dbname,String userName,String userPsd) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@"+ip+":"+port+":"+dbname, userName, userPsd);
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
