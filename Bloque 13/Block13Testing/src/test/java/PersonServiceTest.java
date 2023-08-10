import application.PersonServiceImpl;
import controller.dto.PersonInputDto;
import controller.dto.PersonOutputDto;
import entity.Person;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.PersonRepository;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository; // Asumiendo que tienes una interfaz PersonRepository

    @InjectMocks
    private PersonServiceImpl personService; // Asumiendo que tienes una implementación de PersonService

    @Test
    public void testAddPerson() {
        // Define el comportamiento esperado del repositorio simulado
        when(personRepository.save(any())).thenReturn(new Person());

        PersonInputDto inputDto = new PersonInputDto();
        inputDto.setUsuario("username");
        inputDto.setPassword("password");
        inputDto.setName("John");
        inputDto.setCompanyEmail("john@example.com");
        inputDto.setPersonalEmail("john.personal@example.com");
        inputDto.setCity("City");
        inputDto.setActive(true);
        inputDto.setCreatedDate(new Date()); // Debes proporcionar una fecha válida aquí
        // Configura otros campos según sea necesario

        PersonOutputDto outputDto = personService.addPerson(inputDto);

        // Verifica el comportamiento esperado
        assertNotNull(outputDto);
        // Puedes realizar más verificaciones aquí
    }

    @Test
    public void testDeletePerson() {
        // Configura el comportamiento esperado del repositorio simulado
        when(personRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Llama al método deletePersonId con un ID válido
        assertThrows(EntityNotFoundException.class, () -> personService.deletePersonId(1));

        // Verifica el comportamiento esperado
        verify(personRepository, times(1)).findById(1);
        // Puedes realizar más verificaciones aquí
    }


}
