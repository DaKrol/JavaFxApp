package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Data.DataInteger;
import Data.DataIntegers;
import Data.User;
import Data.Users;

public class JDBC {
	private static Statement st;
	private static Connection con;
	private static ResultSet res;

	public static void connectToDB() {
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-E0K593V:1521:oracl", "damian1", "damian");
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void closeConnection() {
		System.out.print("\nZamykanie polaczenia z bazą:");
		try {
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Bląd przy zamykaniu polączenia z bazą! " + e.getMessage() + ": " + e.getErrorCode());
			System.exit(4);
		}
		System.out.print(" zamknięcie OK");
	}

	public static Users getUser() {

		System.out.println("Downloading data from db");
		User data = null;
		Users datas = new Users();

		try {
			res = st.executeQuery("Select * from users");

			if (res != null) {
				while (res.next()) {
					Object id = res.getObject(1);
					Object login = res.getObject(2);
					Object haslo = res.getObject(3);
					data = new User(Integer.parseInt(id.toString()), login.toString(), haslo.toString());
					datas.add(data);
				}
			}

		} catch (Exception wyjatek) {

			System.out.println(wyjatek.getMessage());
		}

		return datas;
	}
/*
	public static Integer getInteger(String statment) {
		System.out.println("Downloading data from db");
		Integer x = null;
		try {
			res = st.executeQuery(statment);

			if (res.next() != false) {
				x =  Integer.parseInt(res.getString(1));
				}
		} catch (Exception wyjatek) {

			System.out.println(wyjatek.getMessage());
		}
		if(x==null)
		{
			return new Integer(-1);
		}
		else return x;
	}*/
	public static String getString(String statment) {
		System.out.println("Downloading data from db");
		String x = "-1";
		try {
			res = st.executeQuery(statment);

			if (res.next() != false) {
				x = res.getString(1);
			}
		} catch (Exception wyjatek) {

			System.out.println(wyjatek.getMessage());
		}
		
		return x;
	}

}
