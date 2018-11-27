package Server;

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
			polaczenie = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-E0K593V:1521:oracl", "damian1", "damian");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public static int executeStatment(String s)
	{
		int result = 0;
		try {
			a = polaczenie.createStatement();
			res = a.executeQuery(s);
		
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
	public static void executeQuerry(String s)
	{
		
		try {
			a = polaczenie.createStatement();
			res = a.executeQuery(s);
		} catch (Exception wyjatek) {
			
			System.out.println(wyjatek.getMessage());
		}
	}
	
	
	public static String executeStatmentString(String s) 
	{
		String temp="";
		try {
			a = polaczenie.createStatement();
			res = a.executeQuery(s);
			if (res.next() != false) {
				temp = res.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return temp;
		
	}

	
}
