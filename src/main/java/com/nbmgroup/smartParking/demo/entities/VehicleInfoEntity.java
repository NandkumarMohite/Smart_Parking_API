package com.nbmgroup.smartParking.demo.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="vehicleinfoentity")
public class VehicleInfoEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer SrNo;
	private Boolean CheckOutDone;
	private String MobileNumber;
	private String VehicleNumber;
	private String VehicleType;
	private LocalDateTime CheckinTime;
	private LocalDateTime CheckOutTime;
	
	private LocalDate Date;
	private String PaymentType;
	private String ToPayMoney;
	private Boolean PaymentStatus;
	

	public void setSrNo(Integer srNo) {
		SrNo = srNo;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getVehicleNumber() {
		return VehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		VehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return VehicleType;
	}

	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}

	public LocalDateTime getCheckinTime() {
		return CheckinTime;
	}

	public void setCheckinTime(LocalDateTime checkinTime) {
		CheckinTime = checkinTime;
	}

	public LocalDateTime getCheckOutTime() {
		return CheckOutTime;
	}

	public void setCheckOutTime(LocalDateTime checkOutTime) {
		CheckOutTime = checkOutTime;
	}

	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

	public String getPaymentType() {
		return PaymentType;
	}

	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}

	public String getToPayMoney() {
		return ToPayMoney;
	}

	public void setToPayMoney(String toPayMoney) {
		ToPayMoney = toPayMoney;
	}

	public Boolean getPaymentStatus() {
		return PaymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		PaymentStatus = paymentStatus;
	}

	public VehicleInfoEntity( String mobileNumber, String vehicleNumber, String vehicleType,
			LocalDateTime checkinTime, LocalDateTime checkOutTime, LocalDate date, String paymentType, String toPayMoney,
			Boolean paymentStatus,Boolean checkOutDone) {
	
		CheckOutDone=checkOutDone;
		MobileNumber = mobileNumber;
		VehicleNumber = vehicleNumber;
		VehicleType = vehicleType;
		CheckinTime = checkinTime;
		CheckOutTime = checkOutTime;
		Date = date;
		PaymentType = paymentType;
		ToPayMoney = toPayMoney;
		PaymentStatus = paymentStatus;
	}

	public VehicleInfoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean getCheckOutDone() {
		return CheckOutDone;
	}

	public void setCheckOutDone(Boolean checkOutDoen) {
		CheckOutDone = checkOutDoen;
	}	
	

	

}
