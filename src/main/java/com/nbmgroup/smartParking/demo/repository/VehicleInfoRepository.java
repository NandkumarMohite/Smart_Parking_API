package com.nbmgroup.smartParking.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nbmgroup.smartParking.demo.entities.VehicleInfoEntity;

import jakarta.transaction.Transactional;

@Repository
public interface VehicleInfoRepository extends JpaRepository<VehicleInfoEntity, Integer>{
	
	@Query(value = "SELECT * FROM vehicleinfoentity u WHERE u.mobile_number = :mobilenumber and u.check_out_done = :check_out_done LIMIT 1", nativeQuery = true)
	VehicleInfoEntity findUserByMobileNumberAndLatest(@Param("mobilenumber") String mobilenumber,
														@Param("check_out_done") Boolean check_out_done);
	
	@Modifying
	@Transactional
	@Query(value ="Update vehicleinfoentity u Set u.check_out_time= :check_out_time , u.payment_status= :payment_status WHERE u.mobile_number = :mobilenumber and u.check_out_done = :check_out_done LIMIT 1", nativeQuery = true)
	void updateVehicleInfo(@Param("mobilenumber") String mobileNumber,
										@Param("check_out_done") boolean check_out_done,
										@Param("payment_status") boolean payment_status,
										@Param("check_out_time") LocalDateTime checkOutTime);

	
	
	@Modifying
	@Transactional
	@Query(value ="Update vehicleinfoentity u Set u.to_pay_money= :to_pay_money WHERE u.mobile_number = :mobilenumber and u.check_out_done = :check_out_done LIMIT 1", nativeQuery = true)
	void updateVehicleInfoAmmountToPay(@Param("mobilenumber") String mobileNumber,
			@Param("check_out_done") boolean check_out_done,
			@Param("to_pay_money") double to_pay_money);

	
	@Modifying
	@Transactional
	@Query(value ="Update vehicleinfoentity u Set  u.payment_status= :payment_status ,u.payment_type= :payment_type, u.check_out_done= :check_out_done WHERE u.mobile_number = :mobilenumber ORDER BY check_out_time desc LIMIT 1", nativeQuery = true)
	void updateVehicleInfoPayment(
			@Param("mobilenumber") String mobileNumber,
			@Param("check_out_done") boolean check_out_done,
			@Param("payment_status") boolean payment_status,
			@Param("payment_type") String payment_type);

	@Query(value = "SELECT * FROM vehicleinfoentity u WHERE u.mobile_number = :mobilenumber ORDER BY checkin_time desc LIMIT 1", nativeQuery = true)
	VehicleInfoEntity findUserByMobileNumber(@Param("mobilenumber") String mobileNumber);

	@Query(value = "SELECT count(*) FROM vehicleinfoentity u WHERE u.check_out_done = False ", nativeQuery = true)
	Integer findAvailability(); 
}

