package org.cryptical.cbot.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.dv8tion.jda.core.entities.User;

public class Database {
	
	private static Connection con;
	
	public static boolean connect() {
		try {
			if (!isConnected()) {
				con = DriverManager.getConnection("jdbc:mysql://" + Constants.host + ":" + Constants.port + "/" + Constants.database, Constants.user, Constants.password);
				return true;
			}
		} catch (SQLException e) {
			System.out.println("[CrypBot] Unable to connect to the database with error: \n");
			e.printStackTrace();
			return false;
		} return false;
	}
	
	public static void disconnect() {
		if (isConnected()) {
			try {
				con.close();
				System.out.println("[CrypBot] Disconnecting from the MySQL database \n");
			} catch (SQLException e) {
				System.out.println("[CrypBot] Unable to disconnect from the database with error: \n");
				e.printStackTrace();
			}
		}
	}

	public static boolean isConnected() {
		return !(con == null);
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public static void createTable() {
		try {
			PreparedStatement ps = getConnection().prepareCall("CREATE TABLE IF NOT EXISTS " + Constants.userTable + " (id INT(255) NOT NULL AUTO_INCREMENT, PRIMARY KEY (id), UUID varchar(255), MENTIONS(9999))");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void put(String table, User user) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("INSERT INTO " + table + "(UUID, MENTIONS) VALUES (?,?)");
			ps.setString(1, user.getId());
			ps.setString(2, "");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void update(String table, User user, String string, String value) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("UPDATE " + table + " SET " + string + "=" + value + " WHERE UUID=" + user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String table, User user, String get) {
		try {
			PreparedStatement ps = getConnection().prepareStatement("SELECT " + get + " FROM " + table + " WHERE UUID="+user.getId());
			ResultSet rs = ps.executeQuery();
			
			String string = "";
			if (rs.next()) {
				string = rs.getString(get);
			}
			return string;
		} catch (SQLException e) {
			e.printStackTrace();
		} return null;
	}
	
	public static boolean contains(String table, User user) {
		String s = get(Constants.userTable, user, "mentions");
		if (s.equals("") || s == null) return false;
		return true;
	}	
	
	
}
