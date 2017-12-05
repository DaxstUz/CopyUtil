package com.daxstuz.copy.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

public class CopyFilePg {

	public static void copyToFile(Connection connection, String filePath, String tableOrQuery)
			throws SQLException, IOException {
		FileOutputStream fileOutputStream = null;
		try {
			CopyManager copyManager = new CopyManager((BaseConnection) connection);
			fileOutputStream = new FileOutputStream(filePath);
			copyManager.copyOut("COPY " + tableOrQuery + " TO STDOUT ", fileOutputStream);
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void copyFromFile(Connection connection, String filePath, String tableName)
			throws SQLException, IOException {
		FileInputStream fileInputStream = null;
		try {
			CopyManager copyManager = new CopyManager((BaseConnection) connection);
			fileInputStream = new FileInputStream(filePath);
			copyManager.copyIn("COPY " + tableName + " FROM STDIN ", fileInputStream);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
