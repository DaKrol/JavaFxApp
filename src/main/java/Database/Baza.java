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
			polaczenie = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-E0K593V:1521:oracl", "damian1", "damian");
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
	public static int getNumberOfSurveys()
	{
		int nr = 0;
		try {
			a=polaczenie.createStatement();
			res=a.executeQuery("SELECT MAX(id) FROM ankieta");
			if (res.next() != false) {
			nr =  Integer.parseInt(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nr;
	}
	public static String getData(int id)
	{
		String result = "";
		try {
			a=polaczenie.createStatement();
			res = a.executeQuery("SELECT nazwa from ANKIETA where id =" + id);
			if (res.next() != false) {
			result = res.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
	public static String getQuestion(int idAnkiety,int idPytania)
	{
		String question="";
		try {
			a = polaczenie.createStatement();
			res = a.executeQuery("SELECT PYTANIE FROM PYTANIA WHERE ID_PYTANIA_ANK =" + idPytania + " and ID_ANKIETY = " +idAnkiety);
			if (res.next() != false) {
				question = res.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return question;
	}
	public static String getAnswer(int idAnkiety,int idPytania,String idOdp)
	{
		String answer="";
		try {
			a = polaczenie.createStatement();
			res = a.executeQuery("SELECT " + idOdp + " FROM PYTANIA WHERE ID_PYTANIA_ANK =" + idPytania + " and ID_ANKIETY = " +idAnkiety);
			if (res.next() != false) {
				answer = res.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
	}
	public static int getIdAnkiety(String s) {
		int idAnkiety = 0;
		try {
			a = polaczenie.createStatement();
			res = a.executeQuery("SELECT ID FROM ANKIETA WHERE NAZWA = '"+ s +"'");
			if (res.next() != false) {
				idAnkiety = Integer.parseInt(res.getString(1)) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idAnkiety;
	}
	public static int getLiczbaPytan(int idAnkiety) {
		int nr = 0;
		try {
			a = polaczenie.createStatement();
			res = a.executeQuery("SELECT MAX(ID_PYTANIA_ANK) FROM PYTANIA WHERE ID_ANKIETY = '"+ idAnkiety +"'");
			if (res.next() != false) {
				nr = Integer.parseInt(res.getString(1)) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nr;
	}
}
