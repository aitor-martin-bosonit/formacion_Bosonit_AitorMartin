import application.PersonServiceImpl;
import controller.dto.PersonOutputDto;
import entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class PersonRepositoryTest {

    @InjectMocks
    private PersonServiceImpl personService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPersonsName() {

        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Person> fakePersons = new ArrayList<>();
        when(personRepository.findByName(eq("John"), eq(pageRequest))).thenReturn(fakePersons);

        List<PersonOutputDto> result = personService.getPersonsName(0, 10, "John", "simple");

        assertNotNull(result);
        assertEquals(fakePersons.size(), result.size());

        for (int i = 0; i < fakePersons.size(); i++) {
            assertEquals(fakePersons.get(i).getName(), result.get(i).getName());
        }
    }
}
