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
import com.csc.team2.model.TypeOfMedicine;
import com.csc.team2.service.TypeMedicineServiceImpl;

@Controller
public class TypeMedicineController {
	
	@Autowired
	TypeMedicineServiceImpl typeMedicineService;
	
	public static final Logger logger = LoggerFactory.getLogger(TypeMedicineController.class);
	
	//--------------------Select All TypeMedicine-----------------------------------------
	
	@RequestMapping(value="/typemedicine", method = RequestMethod.GET)
	public ResponseEntity<List<TypeOfMedicine>> listMedicine(){
		List<TypeOfMedicine> typeMedicines = typeMedicineService.findAllTypeMedicines();
		if(typeMedicines.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TypeOfMedicine>>(typeMedicines, HttpStatus.OK);
	}
	
	//--------------------Select One TypeMedicine-----------------------------------------
	@RequestMapping(value="/typemedicine/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMedicine(@PathVariable("id") int id){
		logger.info("Fetching Medicine with id {}", id);
		TypeOfMedicine typeMedicine = typeMedicineService.findById(id);
		if(typeMedicine==null){
			 logger.error("typeMedicine with id {} not found.", id);
			 return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TypeOfMedicine>(typeMedicine, HttpStatus.OK);
	}
	//-------------------Create Type Medicine----------------------------------------------
	
		@RequestMapping(value="/typemedicine", method= RequestMethod.POST)
		public ResponseEntity<?> createTypeMedicine(@RequestBody TypeOfMedicine typemedicine,UriComponentsBuilder ucBuilder){
			logger.info("create New Type Medicine : {}", typemedicine);
			if(typeMedicineService.isTypeMedicineExist(typemedicine)){
				logger.error("Unable to create. A Medicine with name {} already exist", typemedicine.getTypename());
				return new ResponseEntity(HttpStatus.CONFLICT);
			}
			typeMedicineService.saveTypeMedicine(typemedicine);
			
			HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/typemedicine/{id}").buildAndExpand(typemedicine.getId()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		
		//------------------Update Type Medicine-----------------------------------------------
		
		@RequestMapping(value="/typemedicine/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateMedicine(@PathVariable("id") int id,@RequestBody TypeOfMedicine typemedicine){
			logger.info("update type medicine with id {}", id);
			
			TypeOfMedicine currentTypeMedicine = typeMedicineService.findById(id);
			if(currentTypeMedicine==null){
				logger.info("unable to update medicine with id {}, not found", id);
			}
			currentTypeMedicine.setTypename(typemedicine.getTypename());
			typeMedicineService.saveTypeMedicine(currentTypeMedicine);
			
			return new ResponseEntity<TypeOfMedicine>(currentTypeMedicine,HttpStatus.OK);
		}
		
		//------------------Delete a Type Medicinhe-----------------------------------------
		@RequestMapping(value="/typemedicine/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteTypeMedicine(@PathVariable("id") int id){
			logger.info("Fetch & delete type medicine with id {}", id);
			TypeOfMedicine typemedicine = typeMedicineService.findById(id);
			if(typemedicine==null){
				logger.info("unable to delete type medicine with id {}, not found",id);
			}
			typeMedicineService.deleteTypeMedicineById(id);
			return new ResponseEntity<TypeOfMedicine>(HttpStatus.NO_CONTENT);
		}
		
		//------------------Delete All Type Medicine---------------------------------------
		@RequestMapping(value="/typemedicine", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteAllTypeMedicine(){
			logger.info("Deleting All type Medicine");
			 
			typeMedicineService.deleteAllTypeMedicines();
	        return new ResponseEntity<TypeOfMedicine>(HttpStatus.NO_CONTENT);

		}
}
