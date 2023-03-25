package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Passenger implements Comparable<Passenger>{
	private int flightNumber;
	private int ticketNumber;
	private String fullName;
	private String passportNumber;
	private String nationality;
	private Date birthdate = new Date();

	public Passenger(int flightNumber, int ticketNumber, String fullName, String passportNumber, String nationality,Date date) {
		super();
		this.flightNumber = flightNumber;
		this.ticketNumber = ticketNumber;
		this.fullName = fullName;
		this.passportNumber = passportNumber;
		this.nationality = nationality;
		this.birthdate = date;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBirthdate() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String dateFormatted = fmt.format(birthdate);
		return dateFormatted;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Passenger[Flight Number = " + flightNumber + ", Ticket Number = " + ticketNumber + ", Full Name = " + fullName
				+ ", Passport Number = " + passportNumber + ", Nationality = " + nationality + ", Birthdate = " + getBirthdate() + "]";
	}

	@Override
	public int compareTo(Passenger o) {
		if(fullName.compareTo(o.fullName) > 0 )
			return 1;
		else if (fullName.compareTo(o.fullName) < 0)
			return -1;
		else 
			return 0;
	}

}
