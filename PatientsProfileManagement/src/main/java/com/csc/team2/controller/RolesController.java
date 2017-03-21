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

import com.csc.team2.model.Roles;
import com.csc.team2.model.TypeOfMedicine;
import com.csc.team2.service.RolesServiceImpl;
import com.csc.team2.service.TypeMedicineServiceImpl;

@Controller
public class RolesController {
	
	@Autowired
	RolesServiceImpl rolesService;
	
	public static final Logger logger = LoggerFactory.getLogger(RolesController.class);
	
	//--------------------Select All Roles-----------------------------------------
	
	@RequestMapping(value="/roles", method = RequestMethod.GET)
	public ResponseEntity<List<Roles>> listRoles(){
		List<Roles> Roles = rolesService.findAllRoles();
		if(Roles.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Roles>>(Roles, HttpStatus.OK);
	}
	
	//--------------------Select One Role-----------------------------------------
	@RequestMapping(value="/roles/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getRole(@PathVariable("id") int id){
		logger.info("Fetching Roles with id {}", id);
		Roles role = rolesService.findById(id);
		if(role==null){
			 logger.error("role with id {} not found.", id);
			 return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Roles>(role, HttpStatus.OK);
	}
	
}
