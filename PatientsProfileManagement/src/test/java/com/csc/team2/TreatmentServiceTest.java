package com.csc.team2;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.hibernate.type.descriptor.java.CalendarTypeDescriptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csc.team2.controller.TreatmentController;
import com.csc.team2.model.Treatment;
import com.csc.team2.service.ITreatmentService;
import com.csc.team2.service.TreatmentServiceIplm;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;

/**
 * 
 * @author t.t.d
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TreatmentServiceTest {
	private static final int UNKNOW_ID = Integer.MAX_VALUE;
	private MockMvc mockMvc;
	@Mock
	private ITreatmentService treatmentService;
	@InjectMocks
	private TreatmentController treatmentController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(treatmentController).addFilters(new CorsFilter()).build();

	}

	// --------------Test Function FindAllTreatment------------
	@Test
	public void testGetAllTreatment() throws Exception {
		List<Treatment> treatments = Arrays.asList(new Treatment(1, new Date(2017, 2, 10), "", "nhiem trung mat"),
				new Treatment(2, new Date(2017, 2, 10), "", "tim mach"),
				new Treatment(3, new Date(2017, 2, 10), "", "tram cam"));

		when(treatmentService.findAllTreatment()).thenReturn(treatments);

		mockMvc.perform(get("/treatment")).andExpect(status().isOk())
				.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				// .andExpect(jsonPath("$", hasSize(3))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].date", is("2017-02-10")))
				.andExpect(jsonPath("$[0].file", is("")))
				.andExpect(jsonPath("$[0].prescription", is("nhiem trung mat")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].date", is("2017-02-10")))
				.andExpect(jsonPath("$[1].file", is("")))
				.andExpect(jsonPath("$[1].prescription", is("tim mach")))
				.andExpect(jsonPath("$[2].id", is(3)))
				.andExpect(jsonPath("$[2].date", is("2017-02-10")))
				.andExpect(jsonPath("$[2].file", is("")))
				.andExpect(jsonPath("$[2].prescription", is("tram cam")));

		verify(treatmentService, times(1)).findAllTreatment();
		verifyNoMoreInteractions(treatmentService);

	}

	// --------------Test Function Treatment FindById------------
	@Test
	public void testGetTreatmentByIdSuccess() throws Exception {
		Treatment treatment= new Treatment(2, new Date(2017, 2, 10), "", "tim mach");

		when(treatmentService.findById(2)).thenReturn(treatment);

		mockMvc.perform(get("/treatment/{id}",2)).andExpect(status().isOk())
				.andExpect((ResultMatcher)content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.date", is("2017-02-10")))
				.andExpect(jsonPath("$.file", is("")))
				.andExpect(jsonPath("$.prescription",is("tim mach")));

		verify(treatmentService, times(1)).findById(2);
		verifyNoMoreInteractions(treatmentService);

	}

	@Test
	public void testGetTreatmentByIdFail() throws Exception {

		when(treatmentService.findById(2)).thenReturn(null);

		mockMvc.perform(get("/treatment/{id}",2)).andExpect(status().isNotFound());
				
		verify(treatmentService, times(1)).findById(2);
		verifyNoMoreInteractions(treatmentService);

	}

	// --------------Test Function Create Treatment------------
	@Test
	public void testCreateTreatmentSuccess() throws Exception {
		List<Treatment>lst= treatmentService.findAllTreatment();
		
		Treatment treatment = new Treatment(lst.size()+1,new Date(2017,03,23), "xquang.jpg", "viem phoi");

		when(treatmentService.isTreatmentExist(treatment)).thenReturn(false);
		doNothing().when(treatmentService).saveTreatment(treatment);

		mockMvc.perform(post("/treatment").contentType(MediaType.APPLICATION_JSON).content(asJsonString(treatment)))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("http://localhost:8080/treatment")));

		verify(treatmentService, times(1)).isTreatmentExist(treatment);
		verify(treatmentService, times(1)).saveTreatment(treatment);
		verifyNoMoreInteractions(treatmentService);
	}

	@Test
	public void testCreateTreatmentFail() throws Exception {
		Treatment treatment = new Treatment(2);

		when(treatmentService.isTreatmentExist(treatment)).thenReturn(true);

		mockMvc.perform(post("/treatment").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(treatment)))
				.andExpect(status().isConflict());

		verify(treatmentService, times(1)).isTreatmentExist(treatment);
		verifyNoMoreInteractions(treatmentService);
	}

	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// --------------------Test Function Update Treatment------------
	@Test
	public void testUpdateTreatmentSuccess() throws Exception {
		Treatment treatment = new Treatment(1, new Date(2017,02,10), "", "nhiem trung mat loai 1");

		when(treatmentService.findById(1)).thenReturn(treatment);
		doNothing().when(treatmentService).updateTreatment(treatment);

		mockMvc.perform(put("/treatment/{id}",1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(treatment)))
		.andExpect(status().isOk());

		verify(treatmentService, times(1)).findById(1);
		verify(treatmentService, times(1)).updateTreatment(treatment);
		verifyNoMoreInteractions(treatmentService);
	}

	@Test
	public void testUpdateTreatmentFail() throws Exception {
		Treatment treatment = new Treatment(UNKNOW_ID, new Date(2017,02,10), "", "nhiem trung mat loai 1");

		when(treatmentService.findById(UNKNOW_ID)).thenReturn(null);

		mockMvc.perform(put("/treatment/{id}",UNKNOW_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(treatment)))
				.andExpect(status().isNotFound());

		verify(treatmentService, times(1)).findById(UNKNOW_ID);
		verifyNoMoreInteractions(treatmentService);
	}
}
