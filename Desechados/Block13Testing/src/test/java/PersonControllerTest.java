import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.PersonService;
import com.example.Block13Testing.Block13TestingApplication;
import controller.PersonController;
import controller.dto.PersonInputDto;
import controller.dto.PersonOutputDto;
import entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(classes = { Block13TestingApplication.class })
@AutoConfigureMockMvc
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }


    @Test
    public void testGetPersonsByName_ValidName_ShouldReturnListOfPersons() throws Exception {
        // Preparar datos de prueba
        List<PersonOutputDto> personList = new ArrayList<>();
        personList.add(new PersonOutputDto());
        when(personService.getPersonsName(anyInt(), anyInt(), anyString(), anyString())).thenReturn(personList);

        // Realizar la solicitud GET y verificar el resultado
        mockMvc.perform(get("/api/persons/nombre/{nombre}", "Pepe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    public void testGetAllPersons_ValidPageNumber_ShouldReturnListOfPersons() throws Exception {
        // Preparar datos de prueba
        List<PersonOutputDto> personList = new ArrayList<>();
        personList.add(new PersonOutputDto());
        when(personService.getAllPersons(anyInt(), anyInt(), anyString())).thenReturn(personList);

        // Realizar la solicitud GET y verificar el resultado
        mockMvc.perform(get("/api/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }


    @Test
    public void testSearchPersons_ReturnsNullList() {
        when(personService.searchPersons(anyString(), anyString(), anyString(), any(), anyString(), anyInt(), anyInt()))
                .thenReturn(Collections.emptyList());

        ResponseEntity<List<Person>> response = personController.searchPersons(null, null, null, null, null, 10, 1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());

        verify(personService, times(1)).searchPersons(null, null, null, null, null, 10, 1);
    }

    


}
