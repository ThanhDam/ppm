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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.csc.team2.model.Patient;
import com.csc.team2.service.PatientServiceImpl;


@Controller
//@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	PatientServiceImpl patientService;
	
	public static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	// -------------------Retrieve All Patient---------------------------------------------
	 
//	@RequestMapping(value={"/patient"}, method = RequestMethod.GET)
//	public ModelAndView login(){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("patient");
//		return modelAndView;
//	}
	
    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> listAllPatients() {
        List<Patient> patients = patientService.findAllPatients();
        if (patients.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
        
    }
 // -------------------Retrieve Single Patient------------------------------------------
    
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatient(@PathVariable("id") int id) {
        logger.info("Fetching Patient with id {}", id);
        Patient patient = patientService.findById(id);
        if (patient == null) {
            logger.error("Patient with id {} not found.", id);
//            return new ResponseEntity(new CustomErrorType("Patient with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }
 
    // -------------------Create a Patient-------------------------------------------
 
    @RequestMapping(value = "/patient", method = RequestMethod.POST)
    public ResponseEntity<?> createPatient(@RequestBody Patient patient, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Patient : {}", patient);
 
        if (patientService.isPatientExist(patient)) {
            logger.error("Unable to create. A Patient with name {} already exist", patient.getName());
//            return new ResponseEntity(new CustomErrorType("Unable to create. A Patient with name " + 
//            patient.getName() + " already exist."),HttpStatus.CONFLICT);
        }
        patientService.savePatient(patient);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/patient/{id}").buildAndExpand(patient.getPatienId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a Patient ------------------------------------------------
 
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePatient(@PathVariable("id") int id, @RequestBody Patient patient) {
        logger.info("Updating Patient with id {}", id);
 
        Patient currentPatient = patientService.findById(id);
 
        if (currentPatient == null) {
            logger.error("Unable to update. Patient with id {} not found.", id);
//            return new ResponseEntity(new CustomErrorType("Unable to upate. Patient with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
        }
 
        currentPatient.setName(patient.getName());
        currentPatient.setSex(patient.getSex());
        currentPatient.setAddress(patient.getAddress());
        currentPatient.setDob(patient.getDob());
 
        patientService.updatePatient(currentPatient);
        return new ResponseEntity<Patient>(currentPatient, HttpStatus.OK);
    }
 
    // ------------------- Delete a Patient-----------------------------------------
 
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePatient(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting Patient with id {}", id);
 
        Patient patientser = patientService.findById(id);
        if (patientser == null) {
            logger.error("Unable to delete. Patient with id {} not found.", id);
//            return new ResponseEntity(new CustomErrorType("Unable to delete. Patient with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
        }
        patientService.deletePatientById(id);
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Patient-----------------------------
    //detele patient-detail table before delete patient table
    @RequestMapping(value = "/patient", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> deleteAllPatients() {
        logger.info("Deleting All Patient");
 
        patientService.deleteAllPatients();
        return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
    }
}
