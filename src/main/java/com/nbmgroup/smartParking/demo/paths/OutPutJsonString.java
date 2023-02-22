package com.nbmgroup.smartParking.demo.paths;

public class OutPutJsonString {
	
	
	String output="{\"Output\":";
	
	String Hold="\"The User Have a vehicle that not yet Checked Out Please Hold he Check-In\"}";
	
	String Success="\"Check-In SuccessFull \"}";
	
	String ParkingFull="\"Parking Is Full\"}";

	String ParkingAvailable="\"Parking is Available\"}";
	
	private String error="Something went Wrong Try Again";
	
	private String UserCheckOutput=output+Hold;
	
	private String CheckOutInitiated=output;
	
	private String CheckInSuccessfull=output+Success;
	
	private String ParkingIsFull=output+ParkingFull;
	
	private String ParkingIsAvailable=output+ParkingAvailable;
	
	public String getUserCheckOutput() {
		return UserCheckOutput;
	}


	public String getCheckInSuccessfull() {
		return CheckInSuccessfull;
	}
	

	public String getCheckOutInitiated(String result) {
		
		return CheckOutInitiated+"\"Amount to pay "+result+"\"}";
	}


	public String getErrorMassage() {
		return error;
	}


	public String getPaymentDoneMessage(String result) {
		return output+"\"payment Ruppes "+result+" successfull\"}";
	}


	public String getParkingIsFull() {
		return ParkingIsFull;
	}


	public String getParkingIsAvailable() {
		return ParkingIsAvailable;
	}

	
	

	

	

}
