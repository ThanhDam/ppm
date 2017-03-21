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

import com.csc.team2.model.History;
import com.csc.team2.model.Medicine;
import com.csc.team2.service.HistoryServiceImpl;

@Controller
public class HistoryController {

	public static final Logger logger = LoggerFactory.getLogger(HistoryController.class);

	@Autowired
	HistoryServiceImpl historyService;

	// ======================== Get All History========================

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public ResponseEntity<List<History>> listHistory() {
		List<History> historys = historyService.findAllHistorys();
		if (historys.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<History>>(historys, HttpStatus.OK);
	}
	
	//=========================Find History By Treatment Id===========
	
	@RequestMapping(value="/history/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getHistorryBytreatmentId(@PathVariable("id") int id){
		logger.info("Fetching Medicine with id {}", id);
		List<History> history = historyService.findBytreatmentId(id);
		if(history==null){
			 logger.error("Patient with id {} not found.", id);
			 return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<History>>(history, HttpStatus.OK);
	}
	
	// ======================== Create History ========================

	@RequestMapping(value = "/history", method = RequestMethod.POST)
	public ResponseEntity<?> creatHistory(@RequestBody History history, UriComponentsBuilder ucBuilder) {
		logger.info("create New Medicine : {}", history);
		historyService.saveHistory(history);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/history/{id}").buildAndExpand(history.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

}
