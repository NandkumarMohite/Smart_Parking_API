package com.nbmgroup.smartParking.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbmgroup.smartParking.demo.entities.VehicleInfoEntity;
import com.nbmgroup.smartParking.demo.repository.VehicleInfoRepository;

@Service
public class SmartVehicleService implements SmartVehicleServiceInterface{
	
	@Autowired
	public VehicleInfoRepository vehicleInfoJPARepo;

	@Override
	public boolean userCheck(VehicleInfoEntity vehicleInfoEntity) {
		if(vehicleInfoEntity==null) {
			return false;
		}
		else {			
			VehicleInfoEntity vehicleInfoEntityCheckedOutput=vehicleInfoJPARepo.findUserByMobileNumberAndLatest(vehicleInfoEntity.getMobileNumber(),false);
			if(vehicleInfoEntityCheckedOutput==null) {
				return false;
			}
		return true;
		
		}
	}
	
	@Override
	public VehicleInfoEntity userFinalCheck(VehicleInfoEntity vehicleInfoEntity) {
		
					
			VehicleInfoEntity vehicleInfoEntityCheckedOutput=vehicleInfoJPARepo.findUserByMobileNumber(vehicleInfoEntity.getMobileNumber());
			
		return vehicleInfoEntityCheckedOutput;
		
		}
	
	@Override
	public boolean checkIn(VehicleInfoEntity vehicleInfoEntity) {
		
		if(vehicleInfoEntity==null) 
			return false;
		else			
			vehicleInfoJPARepo.save(vehicleInfoEntity);
		return true;
		
		
	}

	@Override
	public String checkOut(VehicleInfoEntity vehicleInfoEntity) {
	
	  vehicleInfoJPARepo.updateVehicleInfo(vehicleInfoEntity.getMobileNumber(),false,vehicleInfoEntity.getPaymentStatus(),vehicleInfoEntity.getCheckOutTime());

	  VehicleInfoEntity vehicleInfoEntityCheckedOutput=vehicleInfoJPARepo.findUserByMobileNumberAndLatest(vehicleInfoEntity.getMobileNumber(),false);
	  
	  LocalDateTime checkInTime = vehicleInfoEntityCheckedOutput.getCheckinTime();
	  LocalDateTime checkOutTime = vehicleInfoEntity.getCheckOutTime();

	  long parkingTime = Duration.between(checkInTime, checkOutTime).toSeconds();
	  
	  double AmmountToPay;
	  // per minute 0.1 and 0.4 ruppes
	  if(vehicleInfoEntityCheckedOutput.getVehicleType().equals("Bike")) {
	   AmmountToPay=parkingTime*0.01;
	  }
	  else {
	  AmmountToPay=parkingTime*0.04; 
	  }
	  
	  String output = String.valueOf(AmmountToPay);
	  
	  vehicleInfoJPARepo.updateVehicleInfoAmmountToPay(vehicleInfoEntity.getMobileNumber(),false,AmmountToPay);
		return output;
	}

	@Override
	public VehicleInfoEntity payment(VehicleInfoEntity vehicleInfoEntity) {
		
		vehicleInfoEntity.setPaymentStatus(true);
		vehicleInfoEntity.setCheckOutDone(true);
		 vehicleInfoJPARepo.updateVehicleInfoPayment(vehicleInfoEntity.getMobileNumber(),true,vehicleInfoEntity.getPaymentStatus()
				 ,vehicleInfoEntity.getPaymentType());
		 VehicleInfoEntity vehicleInfoEntityCheckedOutput=vehicleInfoJPARepo.findUserByMobileNumberAndLatest(vehicleInfoEntity.getMobileNumber(),true);
		return vehicleInfoEntityCheckedOutput;
	}

	@Override
	public Integer findAvailabilityOfPark() {
		
		Integer result= vehicleInfoJPARepo.findAvailability();
//		select count(*) from vehicleinfoentity where check_out_done=1
		
				
		return result;
	}

}
