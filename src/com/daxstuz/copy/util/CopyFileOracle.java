package com.daxstuz.copy.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CopyFileOracle {

//	exp system/manager@TEST file=d：\daochu.dmp tables=（table1，table2）

	public static void copyToFile(Connection connection, String sql)
			throws SQLException, IOException {
		Statement statement=connection.createStatement();
		statement.execute(sql);
		
		statement.close();
		connection.close();
		
	}

	public static void copyFromFile(Connection connection, String sql)
			throws SQLException, IOException {
		Statement statement=connection.createStatement();
		statement.execute(sql);
		statement.close();
		connection.close();
	}

}
