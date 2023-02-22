package com.nbmgroup.smartParking.demo.service;

import com.nbmgroup.smartParking.demo.entities.VehicleInfoEntity;

public interface SmartVehicleServiceInterface {

	boolean userCheck(VehicleInfoEntity vehicleInfoEntity);

	boolean checkIn(VehicleInfoEntity vehicleInfoEntity);

	String checkOut(VehicleInfoEntity vehicleInfoEntity);

	VehicleInfoEntity payment(VehicleInfoEntity vehicleInfoEntity);

	VehicleInfoEntity userFinalCheck(VehicleInfoEntity vehicleInfoEntity);

	Integer findAvailabilityOfPark();

}
