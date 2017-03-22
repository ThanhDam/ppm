package com.csc.team2.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
 
import com.csc.team2.model.Treatment;
import com.csc.team2.service.ITreatmentService;
import com.csc.team2.util.CustomErrorType;

 
@RestController
public class TreatmentController {
 
    public static final Logger logger = LoggerFactory.getLogger(TreatmentController.class);
 
    @Autowired
    ITreatmentService treatmentService; //Service which will do all data retrieval/manipulation work
 
    // -------------------Retrieve All Treatment---------------------------------------------
 
    @RequestMapping(value = "/treatment", method = RequestMethod.GET)
    public ResponseEntity<List<Treatment>> listAllTreatment() {
        List<Treatment> treatment = treatmentService.findAllTreatment();
        if (treatment.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Treatment>>(treatment, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single Treatment------------------------------------------
 
    @RequestMapping(value = "/treatment/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTreatment(@PathVariable("id") int id) {
        logger.info("Fetching Treatment with id {}", id);
        Treatment treatment = treatmentService.findById(id);
        if (treatment == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new Error("Treatment with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Treatment>(treatment, HttpStatus.OK);
    }
 
    // -------------------Create a Treatment-------------------------------------------
 
    @RequestMapping(value = "/treatment", method = RequestMethod.POST)
    public ResponseEntity<?> createTreatment(@RequestBody Treatment treatment, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Treatment : {}", treatment);
 
//        if (treatmentService.isTreatmentExist(treatment)) {
//            logger.error("Unable to create. A Treatment with ID {} already exist", treatment.getId());
//            return new ResponseEntity(new Error("Unable to create. A Treatment with ID " + 
//            treatment.getId() + " already exist."),HttpStatus.CONFLICT);
//        }
        treatmentService.saveTreatment(treatment);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/treatment/{id}").buildAndExpand(treatment.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a Treatment ------------------------------------------------
 
    @RequestMapping(value = "/treatment/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTreatment(@PathVariable("id") int id, @RequestBody Treatment treatment) {
        logger.info("Updating Treatment with id {}", id);
 
        Treatment currentTreatment = treatmentService.findById(id);
 
        /*if (currentTreatment == null) {
            logger.error("Unable to update. Treatment with id {} not found.", id);
            return new ResponseEntity(new Error("Unable to upate. Treatment with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }*/
 
        currentTreatment.setId(treatment.getId());
        currentTreatment.setPatientId(treatment.getPatientId());
        currentTreatment.setDoctorId(treatment.getDoctorId());
        currentTreatment.setDate(treatment.getDate());
        currentTreatment.setFile(treatment.getFile());
        currentTreatment.setPrescription(treatment.getPrescription());
 
        treatmentService.updateTreatment(currentTreatment);
        return new ResponseEntity<Treatment>(currentTreatment, HttpStatus.OK);
    }
 
    // ------------------- Delete a Treatment-----------------------------------------
 
    @RequestMapping(value = "/treatment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTreatment(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting Treatment with id {}", id);
 
        Treatment treatment = treatmentService.findById(id);
        if (treatment == null) {
            logger.error("Unable to delete. Treatment with id {} not found.", id);
            return new ResponseEntity(new Error("Unable to delete. Treatment with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        treatmentService.deleteTreatmentById(id);
        return new ResponseEntity<Treatment>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All Treatment-----------------------------
 
    @RequestMapping(value = "/treatment", method = RequestMethod.DELETE)
    public ResponseEntity<Treatment> deleteAllTreatment() {
        logger.info("Deleting All Treatment");
 
        treatmentService.deleteAllTreatment();
        return new ResponseEntity<Treatment>(HttpStatus.NO_CONTENT);
    }
}