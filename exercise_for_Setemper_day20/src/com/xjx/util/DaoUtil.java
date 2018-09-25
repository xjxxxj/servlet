package com.xjx.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.sun.xml.internal.ws.Closeable;

public class DaoUtil {
	
	private static DataSource dataSource ;

	static{
		dataSource = new ComboPooledDataSource("exercise") ;
	}
	
	public static DataSource getDataSource() {
		if(dataSource == null) dataSource = new ComboPooledDataSource("exercise") ;
		return dataSource ;
	}
	
	public static Connection getConnection() {
		Connection connection = null;
		dataSource = getDataSource() ;
		try {
			connection = dataSource.getConnection() ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection ;
	}
	public static void close(Object obj) {
		if(obj != null && obj instanceof Closeable) {
			((Closeable)obj).close();
		}	
	}
}
