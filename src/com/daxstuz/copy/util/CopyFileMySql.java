package com.daxstuz.copy.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Statement;

public class CopyFileMySql {

	public static void copyToFile(Connection connection, String filePath, String tableOrQuery)
			throws SQLException, IOException {
		String sql = tableOrQuery+" INTO OUTFILE "+filePath;
		
		Statement statement=connection.createStatement();
		statement.execute(sql);
		
		statement.close();
		connection.close();
		
	}

	public static void copyFromFile(Connection connection, String filePath, String tableName)
			throws SQLException, IOException {
		String sql="load data infile "+filePath+" replace into table "+tableName;
		Statement statement=connection.createStatement();
		statement.execute(sql);
		
		statement.close();
		connection.close();
	}

}
