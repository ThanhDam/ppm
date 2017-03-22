package com.csc.team2.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.csc.team2.model.Allergic;
import com.csc.team2.model.File;
import com.csc.team2.model.Medicine;
import com.csc.team2.model.Patient;
import com.csc.team2.model.Treatment;
import com.csc.team2.service.AllergicServiceImpl;

@RestController
public class AllergicController {
	
	@Autowired
	AllergicServiceImpl allergicService;
	
	public static final Logger logger = LoggerFactory.getLogger(AllergicController.class);
	
////========= Get All Allergic========================================
	@RequestMapping(value="/allergic", method = RequestMethod.GET)
	public ResponseEntity<List<Allergic>> listAllergic(){
		List<Allergic> allergis = allergicService.findAllAllergics();
		if(allergis.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Allergic>>(allergis, HttpStatus.OK);
	}
	
////==========	Get One Allergic======================================
	@RequestMapping(value="/allergic/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMedicine(@PathVariable("id") int id){
		logger.info("Fetching allergic with id {}", id);
		Allergic allergic = allergicService.findById(id);
		if(allergic==null){
			 logger.error("Patient with id {} not found.", id);
			 return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Allergic>(allergic, HttpStatus.OK);
	}
	
////========= Create List Allergic====================================
	@RequestMapping(value = "/allergicList", method = RequestMethod.POST)
	public @ResponseBody
	ResponseEntity<?> createAllergic(@RequestParam("allergics") String allergicList,
			@RequestParam("patientId") String patientId){
		
;
		//Treatment treatment = getTreatmentById(Integer.parseInt(treatmentId));
		//File file = new File();
			//file.setTreatmentId(treatment);
			//file.setImage(mfile.getBytes());
			//allergicService.saveAllergicList(allergicList);
			logger.info("You successfully uploaded file");
		
		
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		
	}
	
	
	
	@RequestMapping(value="/allergic/List", method= RequestMethod.POST)
	public ResponseEntity<?> createAllergics(@RequestBody List<Allergic> allergicList,@RequestBody Patient patientId, UriComponentsBuilder ucBuilder){
		logger.info("create New allergic : {}", allergicList);
//		allergicService.saveAllergic(allergicList);
		
		HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
////========= Create Allergic=========================================
	@RequestMapping(value="/addAllergic", method= RequestMethod.POST)
	public ResponseEntity<?> createAllergics(@RequestBody Allergic allergic,UriComponentsBuilder ucBuilder){
		logger.info("create New allergic : {}", allergic);
		allergicService.saveAllergic(allergic);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/allergic/{id}").buildAndExpand(allergic.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	
////=========delete Allergic==========================================
	@RequestMapping(value="/allergic/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAllergic(@PathVariable("id") int id){
		logger.info("Fetch & delete allergic with id {}", id);
		Allergic allergic = allergicService.findById(id);
		if(allergic==null){
			logger.info("unable to delete allergic with id {}, not found",id);
		}
		allergicService.deleteAllergicById(id);
		return new ResponseEntity<Medicine>(HttpStatus.NO_CONTENT);
	}

}
