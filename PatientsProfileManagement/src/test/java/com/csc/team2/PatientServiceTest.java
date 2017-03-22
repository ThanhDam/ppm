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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csc.team2.controller.PatientController;
import com.csc.team2.model.Patient;

import com.csc.team2.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * 
 * @author t.t.d
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

	private static final int UNKNOW_ID = Integer.MAX_VALUE;
	private MockMvc mockMvc;
	@Mock
	private IPatientService patientService;
	@InjectMocks
	private PatientController patientController;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(patientController).addFilters(new CorsFilter()).build();
	}
	//---------------Test Function Get All Patient------------
	@Test
	public void testGetAllPatient() throws Exception{
		List<Patient> patients = Arrays.asList(
				new Patient(1, "Nguyen Thi Lanh", "43 Nguyen Thai Son, Q.Go Vap, Tp.HCM", "female",new Date(1978,03,12)),
				new Patient(2, "Tran Van Binh", "213 Vo Thi Sau, Q.3, Tp.HCM", "male",new Date(1984,12,12)),
				new Patient(3, "Lai Thi Hoa", "23/4 Vo Thi Sau, Q.3, Tp.HCM", "female",new Date(1984,12,12)));

		when(patientService.findAllPatients()).thenReturn(patients);

		mockMvc.perform(get("/patient")).andExpect(status().isOk())
				.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				// .andExpect(jsonPath("$", hasSize(3))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("Nguyen Thi Lanh")))
				.andExpect(jsonPath("$[0].address", is("43 Nguyen Thai Son, Q.Go Vap, Tp.HCM")))
				.andExpect(jsonPath("$[0].sex", is("female")))
				.andExpect(jsonPath("$[0].dob", is("1978-03-12")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Tran Van Binh")))
				.andExpect(jsonPath("$[1].address", is("213 Vo Thi Sau, Q.3, Tp.HCM")))
				.andExpect(jsonPath("$[1].sex", is("male")))
				.andExpect(jsonPath("$[1].dob", is("1984-12-12")))
				.andExpect(jsonPath("$[2].id", is(3)))
				.andExpect(jsonPath("$[2].name", is("Lai Thi Hoa")))
				.andExpect(jsonPath("$[2].address", is("23/4 Vo Thi Sau, Q.3, Tp.HCM")))
				.andExpect(jsonPath("$[2].sex", is("female")))
				.andExpect(jsonPath("$[2].dob", is("1984-12-12")));
				

		verify(patientService, times(1)).findAllPatients();
		verifyNoMoreInteractions(patientService);

	}
	//---------------Test Function Get Id Patient------------
	@Test
	public void testGetPatientByIdSuccess() throws Exception{
		Patient patient= new Patient();

		when(patientService.findById(2)).thenReturn(patient);

		mockMvc.perform(get("/patient/{id}",2)).andExpect(status().isOk())
				.andExpect((ResultMatcher)content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id", is(2)));
				

		verify(patientService, times(1)).findById(2);
		verifyNoMoreInteractions(patientService);
	}
	
	@Test
	public void testGetPatientByIdFail() throws Exception{
		when(patientService.findById(2)).thenReturn(null);

		mockMvc.perform(get("/patient/{id}",2)).andExpect(status().isNotFound());
				
		verify(patientService, times(1)).findById(2);
		verifyNoMoreInteractions(patientService);
	}
	//---------------Test Function Create Patient------------
	@Test
	public void testCreatePatientSuccess() throws Exception{
		List<Patient>lst= patientService.findAllPatients();
		
		Patient patient = new Patient(lst.size()+1,"Nguyen Van Hien", "123 Nguyen Trai, Q.5, Tp.HCM", "male",new Date(1976,04,27));

		when(patientService.isPatientExist(patient)).thenReturn(false);
		doNothing().when(patientService).savePatient(patient);

		mockMvc.perform(post("/patient").contentType(MediaType.APPLICATION_JSON).content(asJsonString(patient)))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("http://localhost:8080/patient")));

		verify(patientService, times(1)).isPatientExist(patient);
		verify(patientService, times(1)).savePatient(patient);
		verifyNoMoreInteractions(patientService);
	}
	
	@Test
	public void testCreatePatientFail() throws Exception {
		Patient patient = new Patient(2);

		when(patientService.isPatientExist(patient)).thenReturn(true);

		mockMvc.perform(post("/patient").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(patient)))
				.andExpect(status().isConflict());

		verify(patientService, times(1)).isPatientExist(patient);
		verifyNoMoreInteractions(patientService);
	}
	
	private static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//---------------Test Function Update Patient------------
	@Test
	public void testUpdatePatientSuccess() throws Exception{
		Patient patient = new Patient();

		when(patientService.findById(1)).thenReturn(patient);
		doNothing().when(patientService).updatePatient(patient);

		mockMvc.perform(put("/patient/{id}",1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(patient)))
		.andExpect(status().isOk());

		verify(patientService, times(1)).findById(1);
		verify(patientService, times(1)).updatePatient(patient);
		verifyNoMoreInteractions(patientService);
	}
	
	@Test
	public void testUpdatePatientFail() throws Exception{
		Patient patient = new Patient(UNKNOW_ID,"Tran Thi Hien", "213 Vo Thi Sau, Q.3, Tp.HCM", "female",new Date(1984,12,12));

		when(patientService.findById(UNKNOW_ID)).thenReturn(null);

		mockMvc.perform(put("/patient/{id}",UNKNOW_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(patient)))
				.andExpect(status().isNotFound());

		verify(patientService, times(1)).findById(UNKNOW_ID);
		verifyNoMoreInteractions(patientService);
	}
	//---------------Test Function Delete Patient------------
	@Test
	public void testDeletePatientSuccess() throws Exception{
		Patient patient = new Patient();

        when(patientService.findById(patient.getId())).thenReturn(patient);
        doNothing().when(patientService).deletePatientById(patient.getId());

        mockMvc.perform(
                delete("/patient/{id}", patient.getId()))
                .andExpect(status().isOk());

        verify(patientService, times(1)).findById(patient.getId());
        verify(patientService, times(1)).deletePatientById(patient.getId());
        verifyNoMoreInteractions(patientService);
	}
	
	@Test
	public void testDeletePatientFail() throws Exception{
		Patient patient = new Patient(UNKNOW_ID);

        when(patientService.findById(patient.getId())).thenReturn(null);

        mockMvc.perform(
                delete("/patient/{id}", patient.getId()))
                .andExpect(status().isNotFound());

        verify(patientService, times(1)).findById(patient.getId());
        verifyNoMoreInteractions(patientService);
	}

}
