import application.PersonServiceImpl;
import com.example.Block13Testing.Block13TestingApplication;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Block13TestingApplication.class)
@AutoConfigureMockMvc
public class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testDeletePersonId_ValidId_ShouldNotThrowException() {
        // Preparar datos de prueba
        int validId = 123;
        Person person = new Person();
        when(personRepository.findById(validId)).thenReturn(Optional.of(person));

        // Ejecutar el método a probar
        assertDoesNotThrow(() -> personService.deletePersonId(validId));

        // Verificar que el método del repositorio se haya llamado
        verify(personRepository, times(1)).deleteById(validId);
    }


    @Test
    public void testGetAllPersons_ValidPageNumber_ShouldReturnListOfPersons() {
        // Preparar datos de prueba
        int pageNumber = 1;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<Person> personList = new ArrayList<>();
        personList.add(new Person());
        when(personRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(personList));

        // Esta linea ejecuta el método a probar
        List<PersonOutputDto> result = personService.getAllPersons(pageNumber, pageSize, "simple");

        // A continuación vamos a verificar el resultado
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAddPerson_ValidInput_ShouldReturnPersonOutputDto() {
        PersonInputDto inputDto = new PersonInputDto();
        inputDto.setUsuario("newUser");
        inputDto.setPassword("password");
        inputDto.setName("John");
        inputDto.setCompanyEmail("john@example.com");
        inputDto.setPersonalEmail("john.personal@example.com");
        inputDto.setCity("New York");
        inputDto.setActive(true);
        inputDto.setCreatedDate(new Date());

        Person savedPerson = new Person();
        savedPerson.setIdPersona(1); // Asignar un ID válido
        when(personRepository.save(any())).thenReturn(savedPerson);

        PersonOutputDto result = personService.addPerson(inputDto);

        assertNotNull(result);
        //assertEquals("newUser", result.getUsuario());

        verify(personRepository, times(1)).save(any());
    }

    @Test
    public void testGetPersonById_ValidId_ShouldReturnPersonOutputDto() {
        // Crear una instancia ficticia de Person
        Person person = new Person();
        person.setIdPersona(1);
        person.setUsuario("username");
        person.setName("John");
        person.setCompanyEmail("john@example.com");
        person.setPersonalEmail("john.personal@example.com");
        person.setCity("New York");
        person.setActive(true);

        // Configurar el mock del repositorio para devolver la instancia ficticia de Person
        when(personRepository.findById(1)).thenReturn(Optional.of(person));

        // Llamar al método de servicio para obtener la persona por ID
        PersonOutputDto result = personService.getPerson(1);

        // Verificar que el resultado no sea nulo y que los valores coincidan
        assertNotNull(result);
        assertEquals("username", result.getUsuario());
        assertEquals("John", result.getName());
        assertEquals("john@example.com", result.getCompanyEmail());
        assertEquals("john.personal@example.com", result.getPersonalEmail());
        assertEquals("New York", result.getCity());
        assertTrue(result.getActive());

        // Verificar que el método del repositorio se llamó una vez con el ID 1L
        verify(personRepository, times(1)).findById(1);
    }

    @Test
    public void testGetPersonsName_Success() {

        String name = "John";
        int pageNumber = 0;
        int pageSize = 10;
        List<Person> mockedPersons = new ArrayList<>();
        mockedPersons.add(new Person());
        mockedPersons.add(new Person());
        when(personRepository.findByName(eq(name), any(PageRequest.class))).thenReturn(mockedPersons);

        List<PersonOutputDto> result = personService.getPersonsName(pageNumber, pageSize, name, "output");

        verify(personRepository, times(1)).findByName(eq(name), any(PageRequest.class));
        assertEquals(2, result.size());

        verify(personRepository, times(1)).findByName(eq(name), any(PageRequest.class));
        assertEquals(2, result.size());

        PersonOutputDto firstPerson = result.get(0);
        PersonOutputDto secondPerson = result.get(1);
        assertEquals(mockedPersons.get(0).getIdPersona(), firstPerson.getIdPersona());
        assertEquals(mockedPersons.get(1).getName(), secondPerson.getName());

        verify(personRepository, times(1)).findByName(eq(name), any(PageRequest.class));

        verifyNoMoreInteractions(personRepository);
    }

    @Test
    public void testAddPerson_Success() {
        // Mock data
        PersonInputDto inputDto = new PersonInputDto();
        inputDto.setUsuario("user123");
        inputDto.setPassword("password");
        inputDto.setName("John");
        inputDto.setCompanyEmail("john@example.com");
        inputDto.setPersonalEmail("john.personal@example.com");
        inputDto.setCity("New York");
        inputDto.setActive(true);
        inputDto.setCreatedDate(new Date());

        Person savedPerson = new Person(inputDto);
        savedPerson.setIdPersona(1);

        when(personRepository.save(any())).thenReturn(savedPerson);

        // Test
        PersonOutputDto result = personService.addPerson(inputDto);

        // Assertions
        assertNotNull(result);
        assertEquals(savedPerson.getIdPersona(), result.getIdPersona());
        assertEquals(inputDto.getUsuario(), result.getUsuario());
        assertEquals(inputDto.getPassword(), result.getPassword());
        assertEquals(inputDto.getName(), result.getName());
        // Add more assertions for other fields

        // Verify repository interactions
        verify(personRepository, times(1)).save(any(Person.class));
        verifyNoMoreInteractions(personRepository);
    }



}
