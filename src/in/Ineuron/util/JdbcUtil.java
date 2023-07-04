package in.Ineuron.util;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class JdbcUtil {

	private JdbcUtil() {
	}

	static {
		// Step1: loading and register the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws SQLException, IOException {
		String loc = "D:\\WEB_Project\\JdbcCrudeApp\\src\\in\\Ineuron\\property\\app.properites";
		HikariConfig config = new HikariConfig(loc);
		DataSource dataSource = new HikariDataSource(config);
		return dataSource.getConnection();
		
	}

}
