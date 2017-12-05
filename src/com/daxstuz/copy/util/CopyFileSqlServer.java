package com.daxstuz.copy.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CopyFileSqlServer {

	public static void copyToFile(Connection connection, String filePath, String tableOrQuery)
			throws SQLException, IOException {
		String sql = "bcp " +tableOrQuery+ " queryout "+filePath+" -c -T";
		Statement statement=connection.createStatement();
		statement.execute(sql);
		
		statement.close();
		connection.close();
		
	}

	public static void copyFromFile(Connection connection, String filePath, String tableName)
			throws SQLException, IOException {
		String sql="bcp "+tableName+" in "+filePath+" -c -T";
		Statement statement=connection.createStatement();
		statement.execute(sql);
		
		statement.close();
		connection.close();
	}
}
