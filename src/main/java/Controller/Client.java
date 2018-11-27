package Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

import Data.DataInteger;
import Data.DataIntegers;
import Data.Users;

public class Client {

	Users users;
	Socket mySocket;
	Integer x;
	String temp;
	public void Connect() throws IOException {
		int port = 3603;
		mySocket = new Socket("127.0.0.1", port);
	}
	/*public Integer getInteger(String statment)
	{
		sendObject(new String(statment));

		while (true) {
			Object object = getObject();
			if (object instanceof Integer) {
				x = (Integer) object;
				break;
			}
		}
		return x;
	}*/
	public String executeQuerry(String statment)
	{
		  class Task implements Callable {
		        String statment;
		        Task(String s) { statment = s; }
				@Override
				public String call() throws Exception {
					return getString(statment);
				}
		    }
		   // Thread t = new Thread(new Task(statment));
		  Task t = new Task(statment);
		  String temp = null;
		  try {
			temp = t.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;	
	}
	public String getString(String statment)
	{
		sendObject(new String(statment));

		while (true) {
			Object object = getObject();
			if (object instanceof String) {
				temp = (String) object;
				break;
			}
		}
		return temp;
	}
	public Users getUsers() {
		sendObject(new Users());

		while (true) {
			Object object = getObject();
			if (object instanceof Users) {
				users = (Users) object;
				break;
			}
		}
		return users;
	}
	public void sendObject(Object object) {
		try {
			ObjectOutputStream out = null;
			out = new ObjectOutputStream(mySocket.getOutputStream());
			mySocket.setTcpNoDelay(true);
			out.writeObject(object);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Object getObject() {
		Object object = null;

		try {
			ObjectInputStream in = new ObjectInputStream(mySocket.getInputStream());
			object = in.readObject();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}

}
