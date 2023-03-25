package application;

public class Flight implements Comparable<Flight>{
	private int flightNumber;
	private String airlineName;
	private String source; 
	private String destination;
	private int capacity;
	private LinkedList<Passenger> passList = new LinkedList<Passenger>();

	public Flight() {

	}

	public Flight(int flightNumber, String airlineName, String source, String destination, int capacity) {
		super();
		this.flightNumber = flightNumber;
		this.airlineName = airlineName;
		this.source = source;
		this.destination = destination;
		this.capacity = capacity;
	}

	public Flight(int flightNumber, String airlineName, String source, String destination, int capacity,
			LinkedList<Passenger> passList) {
		super();
		this.flightNumber = flightNumber;
		this.airlineName = airlineName;
		this.source = source;
		this.destination = destination;
		this.capacity = capacity;
		this.passList = passList;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public LinkedList<Passenger> getPassList() {
		return passList;
	}

	public void setPassList(LinkedList<Passenger> passList) {
		this.passList = passList;
	}

	@Override
	public String toString() {
		return "Flight [Flight Number = " + flightNumber + ", Airline Name = " + airlineName + ", Source = " + source
				+ ", Destination = " + destination + ", Capacity = " + capacity + ", Passenger List = "  + passList.traverseListReceve() + " ] ";
	}

	@Override
	public int compareTo(Flight o) {
		if(flightNumber > o.flightNumber)
			return 1;
		else if(flightNumber < o.flightNumber)
			return -1;
		else
			return 0;
	}
	
}
