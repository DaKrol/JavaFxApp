package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Baza {
	private static Connection polaczenie;
	private static Statement a;
	private static ResultSet res;
	static {
		try {
			polaczenie = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracl", "hr", "hr");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static int checkLoginPass(String s, String s2) {
		int result = 0;
		try {
			a = polaczenie.createStatement();
			res = a.executeQuery("SELECT * FROM Users where login like '" + s + "' and pass like '" + s2 + "'");

			if (res.next() != false) {
				result = Integer.parseInt(res.getString(1));
			} else {
				result = 0;
			}
		} catch (Exception wyjatek) {
			System.out.println(wyjatek.getMessage());
		}

		return result;
	}

	public static void addRow(String s1, String s2) {
		int num = 0;
		try {
			a = polaczenie.createStatement();
			res = a.executeQuery("SELECT MAX(USER_ID) FROM USERS");
			if (res.next() != false) {
				num = Integer.parseInt(res.getString(1)) + 1;
			}

			res = null;
			res = a.executeQuery("INSERT INTO USERS VALUES (" + num + ",'" + s1 + "' , '" + s2 + "')");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
