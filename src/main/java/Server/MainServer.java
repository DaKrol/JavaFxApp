package Server;

public class MainServer {

	public static void main(String [] args)
	{
		JDBC.connectToDB();
	    Server.Start();
	    JDBC.closeConnection();
	}
	
}
