package com.csc.team2.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.csc.team2.model.Medicine;
import com.csc.team2.service.MedicineServiceImpl;

@Controller
public class MedicineController {
	
	@Autowired
	MedicineServiceImpl medicineService;
	
	public static final Logger logger = LoggerFactory.getLogger(MedicineController.class);
	
	//--------------------Select All Medicine-----------------------------------------
	
	@RequestMapping(value="/medicine", method = RequestMethod.GET)
	public ResponseEntity<List<Medicine>> listMedicine(){
		List<Medicine> medicines = medicineService.findAllMedicines();
		if(medicines.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Medicine>>(medicines, HttpStatus.OK);
	}
	
	//--------------------Select Medicine Type-----------------------------------------
	
		@RequestMapping(value="/medicinebytype/{id}", method = RequestMethod.GET)
		public ResponseEntity<List<Medicine>> listMedicine(@PathVariable("id") int id){
			List<Medicine> medicines = medicineService.findByType(id);
			if(medicines.isEmpty()){
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Medicine>>(medicines, HttpStatus.OK);
		}
	
	//--------------------Select One Medicine-----------------------------------------
	
	@RequestMapping(value="/medicine/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMedicine(@PathVariable("id") int id){
		logger.info("Fetching Medicine with id {}", id);
		Medicine medicine = medicineService.findById(id);
		if(medicine==null){
			 logger.error("Patient with id {} not found.", id);
			 return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Medicine>(medicine, HttpStatus.OK);
	}
	
	//-------------------Create Medicine----------------------------------------------
	
	@RequestMapping(value="/medicine", method= RequestMethod.POST)
	public ResponseEntity<?> createMedicine(@RequestBody Medicine medicine,UriComponentsBuilder ucBuilder){
		logger.info("create New Medicine : {}", medicine);
		if(medicineService.isMedicineExist(medicine)){
			logger.error("Unable to create. A Medicine with name {} already exist", medicine.getName());
			return new ResponseEntity(HttpStatus.CONFLICT);
		}
		medicineService.saveMedicine(medicine);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Medicine/{id}").buildAndExpand(medicine.getMedicineId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	//------------------Update Medicine-----------------------------------------------
	
	@RequestMapping(value="/medicine/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateMedicine(@PathVariable("id") int id,@RequestBody Medicine medicine){
		logger.info("update medicine with id {}", id);
		
		Medicine currentMedicine = medicineService.findById(id);
		if(currentMedicine==null){
			logger.info("unable to update medicine with id {}, not found", id);
		}
		currentMedicine.setName(medicine.getName());
		currentMedicine.setMfg(medicine.getMfg());
		currentMedicine.setProducer(medicine.getProducer());
		currentMedicine.setTypeId(medicine.getTypeId());
		currentMedicine.setDosage(medicine.getDosage());
		medicineService.saveMedicine(currentMedicine);
		
		return new ResponseEntity<Medicine>(currentMedicine,HttpStatus.OK);
	}
	
	//------------------Delete a Medicinhe-----------------------------------------
	@RequestMapping(value="/medicine/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMedicine(@PathVariable("id") int id){
		logger.info("Fetch & delete medicine with id {}", id);
		Medicine medicine = medicineService.findById(id);
		if(medicine==null){
			logger.info("unable to delete medicine with id {}, not found",id);
		}
		medicineService.deleteMedicineById(id);
		return new ResponseEntity<Medicine>(HttpStatus.NO_CONTENT);
	}
	
	//------------------Delete All Medicine---------------------------------------
	@RequestMapping(value="/medicine", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllMedicine(){
		logger.info("Deleting All Medicine");
		 
		medicineService.deleteAllMedicines();
        return new ResponseEntity<Medicine>(HttpStatus.NO_CONTENT);

	}
}
