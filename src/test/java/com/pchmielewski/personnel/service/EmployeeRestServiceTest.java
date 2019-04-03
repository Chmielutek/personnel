package com.pchmielewski.personnel.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pchmielewski.personnel.logic.api.PersonnelFacade;
import com.pchmielewski.personnel.logic.api.to.AddressTo;
import com.pchmielewski.personnel.logic.api.to.EmployeeTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class EmployeeRestServiceTest {

    final private static String SESSION_ID = "SESSION_ID";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private MockHttpSession mockHttpSession;

    @MockBean
    private PersonnelFacade personnelFacade;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setup() {
        mockHttpSession = new MockHttpSession(webApplicationContext.getServletContext(), SESSION_ID);
    }

    @Test
    public void shouldGetListOfEmployees() throws Exception {

        // given
        EmployeeTo employee = new EmployeeTo();
        employee.setFirstName("Jan");
        employee.setLastName("Kowalski");
        List<EmployeeTo> employees = new ArrayList<>();
        employees.add(employee);

        when(personnelFacade.getEmployeeData()).thenReturn(Optional.of(employees));

        // when
        ResultActions result =
                this.mockMvc.perform(get("/services/rest/employee/v1/employees")
                        .accept(MediaType.APPLICATION_JSON).session(mockHttpSession));

        // then
        result.andExpect(status().isOk());

    }

    @Test
    public void shouldNotGetListOfEmployees() throws Exception {

        // given
        when(personnelFacade.getEmployeeData()).thenReturn(Optional.empty());

        // when
        ResultActions result =
                this.mockMvc.perform(get("/services/rest/employee/v1/employees")
                        .accept(MediaType.APPLICATION_JSON).session(mockHttpSession));

        // then
        result.andExpect(status().isNotFound());

    }

    @Test
    public void shouldAddEmployee() throws Exception {

        // given
        EmployeeTo to = new EmployeeTo();
        to.setFirstName("Jan");
        to.setLastName("Kowalski");

        // when
        String toJson = asJsonString(to);
        ResultActions result =
                this.mockMvc.perform(post("/services/rest/employee/v1/add-employee")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson));

        // then
        result.andExpect(status().isOk());
    }

    @Test
    public void shouldNotAddEmployee() throws Exception {

        // given
        EmployeeTo to = new EmployeeTo();

        // when
        String toJson = asJsonString(to);
        ResultActions result =
                this.mockMvc.perform(post("/services/rest/employee/v1/add-employee")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson));

        // then
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void shouldAddAddressToEmployee() throws Exception {

        // given
        AddressTo to = new AddressTo();
        to.setStreetName("testStreet");
        to.setStreetNumber(1);
        to.setPostalCode("12345");
        to.setCity("testCity");
        to.setEmployeeId(1L);

        // when
        String toJson = asJsonString(to);
        ResultActions result =
                this.mockMvc.perform(post("/services/rest/employee/v1/add-address")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson));

        // then
        result.andExpect(status().isOk());
    }

    @Test
    public void shouldNotAddAddressToEmployee() throws Exception {

        // given
        AddressTo to = new AddressTo();

        // when
        String toJson = asJsonString(to);
        ResultActions result =
                this.mockMvc.perform(post("/services/rest/employee/v1/add-address")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson));

        // then
        result.andExpect(status().isBadRequest());
    }

    private String asJsonString(final Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
