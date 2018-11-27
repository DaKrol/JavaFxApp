package Data;

import java.io.Serializable;

public class DataInteger implements Serializable {
	private Integer number;
	public DataInteger(int number)
	{
		this.number=number;
	}
	public Integer getNumber()
	{
		return number;
	}
}
