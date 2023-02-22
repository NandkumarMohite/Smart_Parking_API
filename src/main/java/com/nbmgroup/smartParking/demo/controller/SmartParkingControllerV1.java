package com.nbmgroup.smartParking.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nbmgroup.smartParking.demo.entities.VehicleInfoEntity;
import com.nbmgroup.smartParking.demo.paths.OutPutJsonString;
import com.nbmgroup.smartParking.demo.service.SmartVehicleServiceInterface;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SmartParkingControllerV1 {
	
	@Autowired
	public SmartVehicleServiceInterface smartVehicleServiceInterface;
	

	public OutPutJsonString outPutJsonString = new OutPutJsonString() ;
	
	@PostMapping(value = "/CheckIn")
	public String checkINController(@RequestBody VehicleInfoEntity vehicleInfoEntity) {
		
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		
		vehicleInfoEntity.setDate(localDate);
		vehicleInfoEntity.setCheckinTime(localDateTime);
		
		boolean usercheck =smartVehicleServiceInterface.userCheck(vehicleInfoEntity);
		if(usercheck==true) {
		return outPutJsonString.getUserCheckOutput();
		}
		else {
			vehicleInfoEntity.setCheckOutDone(false);
		boolean result = smartVehicleServiceInterface.checkIn(vehicleInfoEntity);
		
		
		if(result==true) {
			return outPutJsonString.getCheckInSuccessfull();
		}
		else
			return outPutJsonString.getErrorMassage();
		}
	}
	
	
	@PostMapping(value = "/CheckOut")
	public String checkOutController(@RequestBody VehicleInfoEntity vehicleInfoEntity) {
		
		LocalDateTime localDateTime = LocalDateTime.now();

		vehicleInfoEntity.setCheckOutTime(localDateTime);
		boolean usercheck =smartVehicleServiceInterface.userCheck(vehicleInfoEntity);
		if(usercheck==true) {
		vehicleInfoEntity.setPaymentStatus(false);
		String result = smartVehicleServiceInterface.checkOut(vehicleInfoEntity);
		if(result!=null) {
			
			return outPutJsonString.getCheckOutInitiated(result);
		}
		else
			return outPutJsonString.getErrorMassage();
		}
		return outPutJsonString.getErrorMassage();
	}
	
	
	@PostMapping(value = "/payment")
	public String paymentController(@RequestBody VehicleInfoEntity vehicleInfoEntity) {
		
		boolean usercheck =smartVehicleServiceInterface.userCheck(vehicleInfoEntity);
		if(usercheck==true) {
		
		VehicleInfoEntity result = smartVehicleServiceInterface.payment(vehicleInfoEntity);
		
		if(result!=null) {
			
			return outPutJsonString.getPaymentDoneMessage(vehicleInfoEntity.getToPayMoney());
		}		
		}
		return outPutJsonString.getErrorMassage();
	}
	
	@PostMapping(value = "/checkOutDone")
	public VehicleInfoEntity CheckOutDoneController(@RequestBody VehicleInfoEntity vehicleInfoEntity) {
		
		
		
		VehicleInfoEntity result = smartVehicleServiceInterface.userFinalCheck(vehicleInfoEntity);
		
				
		
		
		return result;
	}
	
	
	@PostMapping(value = "/findvehicle")
	public VehicleInfoEntity findvehicleController(@RequestBody VehicleInfoEntity vehicleInfoEntity) {
		
		
		
		VehicleInfoEntity result = smartVehicleServiceInterface.userFinalCheck(vehicleInfoEntity);
		
				
		
		
		return result;
	}
	
	
  @GetMapping(value="/availability")
  public String findAvailabilityOfParking() {
		
		
		
		Integer result = smartVehicleServiceInterface.findAvailabilityOfPark();
		
				if(result>30) {
					return outPutJsonString.getParkingIsFull();
				}
		
		
		return outPutJsonString.getParkingIsAvailable();
	}
  
	
}
