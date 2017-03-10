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
 
import com.csc.team2.model.TreatmentDt;
import com.csc.team2.service.TreatmentDtService;
import com.csc.team2.util.CustomErrorType;

 
@RestController
@RequestMapping("/api")
public class TreatmentDtController {
 
    public static final Logger logger = LoggerFactory.getLogger(TreatmentDtController.class);
 
    @Autowired
    TreatmentDtService treatmentdtService; //Service which will do all data retrieval/manipulation work
 
    // -------------------Retrieve All TreatmentDt---------------------------------------------
 
    @RequestMapping(value = "/treatmentdt/", method = RequestMethod.GET)
    public ResponseEntity<List<TreatmentDt>> listAllTreatmentDt() {
        List<TreatmentDt> treatmentsdt = treatmentdtService.findAllTreatmentDt();
        if (treatmentsdt.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TreatmentDt>>(treatmentsdt, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single TreatmentDt------------------------------------------
 
    @RequestMapping(value = "/treatmentdt/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTreatmentDt(@PathVariable("id") int id) {
        logger.info("Fetching TreatmentDt with id {}", id);
        TreatmentDt treatmentdt = treatmentdtService.findById(id);
        if (treatmentdt == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new Error("TreatmentDt with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TreatmentDt>(treatmentdt, HttpStatus.OK);
    }
 
    // -------------------Create a TreatmentDt-------------------------------------------
 
    @RequestMapping(value = "/treatmentdt/", method = RequestMethod.POST)
    public ResponseEntity<?> createTrearmentDt(@RequestBody TreatmentDt treatmentdt, UriComponentsBuilder ucBuilder) {
        logger.info("Creating TreatmentDt : {}", treatmentdt);
 
        if (treatmentdtService.isTreatmentDtExist(treatmentdt)) {
            logger.error("Unable to create. A TreatmentDt with ID {} already exist", treatmentdt.getId());
            return new ResponseEntity(new Error("Unable to create. A TreatmentDt with ID " + 
            treatmentdt.getId() + " already exist."),HttpStatus.CONFLICT);
        }
        treatmentdtService.saveTreatmentDt(treatmentdt);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/treatmentdt/{id}").buildAndExpand(treatmentdt.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // ------------------- Update a TreatmentDt ------------------------------------------------
 
    @RequestMapping(value = "/treatmentdt/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTreatmentDt(@PathVariable("id") int id, @RequestBody TreatmentDt treatmentdt) {
        logger.info("Updating TreatmentDt with id {}", id);
 
        TreatmentDt currentTreatmentDt = treatmentdtService.findById(id);
 
        if (currentTreatmentDt == null) {
            logger.error("Unable to update. TreatmentDt with id {} not found.", id);
            return new ResponseEntity(new Error("Unable to upate. TreatmentDt with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
 
        currentTreatmentDt.setId(treatmentdt.getId());
        currentTreatmentDt.setIdTreatment(treatmentdt.getIdTreatment());
        currentTreatmentDt.setIdMedicine(treatmentdt.getIdMedicine());
        currentTreatmentDt.setDiseases(treatmentdt.getDiseases());
       
 
        treatmentdtService.updateTreatmentDt(currentTreatmentDt);
        return new ResponseEntity<TreatmentDt>(currentTreatmentDt, HttpStatus.OK);
    }
 
    // ------------------- Delete a TreatmentDt-----------------------------------------
 
    @RequestMapping(value = "/treatmentdt/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTreatmentDt(@PathVariable("id") int id) {
        logger.info("Fetching & Deleting TreatmentDt with id {}", id);
 
        TreatmentDt treatmentdt = treatmentdtService.findById(id);
        if (treatmentdt == null) {
            logger.error("Unable to delete. TreatmentDt with id {} not found.", id);
            return new ResponseEntity(new Error("Unable to delete. TreatmentDt with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        treatmentdtService.deleteTreatmentDtById(id);
        return new ResponseEntity<TreatmentDt>(HttpStatus.NO_CONTENT);
    }
 
    // ------------------- Delete All TreatmentDt-----------------------------
 
    @RequestMapping(value = "/treatmentdt/", method = RequestMethod.DELETE)
    public ResponseEntity<TreatmentDt> deleteAllTreatmentDt() {
        logger.info("Deleting All TreatmentDt");
 
        treatmentdtService.deleteAllTreatmentDt();
        return new ResponseEntity<TreatmentDt>(HttpStatus.NO_CONTENT);
    }
}