/*
 * 	Szymon Terlecki, 01.12.2016r.
 */

public class Room implements Comparable<Room>
{
	private String symbol;
	private int number;
	private String description;
	
	Room(String symbol, int number, String description)
	{
		this.symbol = symbol;
		this.number = number;
		this.description = description;
	}

	public String getSymbol()
	{
		return symbol;
	}
	
	public int getNumber()
	{
		return number;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public int compareTo(Room r) 
	{
		if(symbol.codePointAt(0)> r.symbol.codePointAt(0))
			return 1;
		else if(symbol.codePointAt(0) == r.symbol.codePointAt(0))
		{
			if(symbol.length() == r.symbol.length() && symbol.length() == 4)
			{
					if(Integer.parseInt(((Integer)symbol.codePointAt(2)).toString() + ((Integer)symbol.codePointAt(3)).toString()) > 
					(Integer.parseInt(((Integer)r.symbol.codePointAt(2)).toString() + ((Integer)r.symbol.codePointAt(3)).toString())))
						return 1;
					else if(Integer.parseInt(((Integer)symbol.codePointAt(2)).toString() + ((Integer)symbol.codePointAt(3)).toString()) ==
							(Integer.parseInt(((Integer)r.symbol.codePointAt(2)).toString() + ((Integer)r.symbol.codePointAt(3)).toString())))
					{
						if(number > r.number)
							return 1;
						else if(number == r.number)
							return 0;
						else 
							return -1;
					}
					else
						return -1;
			}
			else if(symbol.length() == r.symbol.length() && symbol.length() == 3)
				{
					if(symbol.codePointAt(2) > r.symbol.codePointAt(2))
						return 1;
					else if(symbol.codePointAt(2) == r.symbol.codePointAt(2))
						{
							if(number > r.number)
								return 1;
							else if(number == r.number)
								return 0;
							else 
								return -1;
						}
					else return -1;
				}
				else if(symbol.length() > r.symbol.length())
					return 1;
				else 
					return -1;
		}
		else 
			return -1;
	}
	
	
	//Obie wersje dzialaja poprawnie
	/*
	@Override
	public int compareTo(Room r) {
		int comparison = symbol.compareTo(r.symbol);
		
		if (comparison == 0){
			if(number > r.number){
				return 1;
			} 
			
			else if (number == r.number){
				return 0;
			}
			return -1;
		} else{
			return comparison;
		}
	}
	*/
	
	public String toString()
	{
		return symbol + "/" + number + " : " + description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}
	
	//Obie wersje dzialaja poprawnie
	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (number != other.number)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	*/
	
	public boolean equals(Object o)
	{
		if(symbol.equals(((Room) o).symbol) && number == ((Room)o).number)
			return true;
		return false;
	}
	
	}