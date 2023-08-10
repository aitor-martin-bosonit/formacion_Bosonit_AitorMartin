import controller.dto.PersonInputDto;
import controller.dto.PersonOutputDto;
import entity.Person;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonTest {

    @Test
    public void testPersonConstructorAndGetters() {
        PersonInputDto inputDto = new PersonInputDto();
        inputDto.setUsuario("john_doe");
        inputDto.setPassword("password");
        inputDto.setName("John");
        inputDto.setSurname("Doe");
        inputDto.setCompanyEmail("john.doe@company.com");
        inputDto.setPersonalEmail("john.doe@gmail.com");
        inputDto.setCity("New York");
        inputDto.setActive(true);
        inputDto.setCreatedDate(new Date());
        inputDto.setImageUrl("http://example.com/john.jpg");
        inputDto.setTerminationDate(null);


        // Configura otros campos en el PersonInputDto según sea necesario

        Person person = new Person(inputDto);

        assertNotNull(person.getIdPersona());
        assertEquals(inputDto.getUsuario(), person.getUsuario());
        assertEquals(inputDto.getPassword(), person.getPassword());
        assertEquals(inputDto.getName(), person.getName());
        assertEquals(inputDto.getSurname(), person.getSurname());
        assertEquals(inputDto.getCompanyEmail(), person.getCompanyEmail());
        assertEquals(inputDto.getPersonalEmail(), person.getPersonalEmail());
        assertEquals(inputDto.getCity(), person.getCity());
        //assertEquals(inputDto.isActive(), person.getActive());
        assertEquals(inputDto.getCreatedDate(), person.getCreatedDate());
        assertEquals(inputDto.getImageUrl(), person.getImageUrl());
        assertEquals(inputDto.getTerminationDate(), person.getTerminationDate());
    }

    @Test
    public void testPersonToPersonOutputDto() {
        // Crea una instancia de Person para la prueba
        Person person = new Person();
        person.setIdPersona(1);
        person.setUsuario("john_doe");
        person.setPassword("password");
        person.setName("John");
        person.setSurname("Doe");
        person.setCompanyEmail("john.doe@company.com");
        person.setPersonalEmail("john.doe@gmail.com");
        person.setCity("New York");
        person.setActive(true);
        person.setCreatedDate(new Date());
        person.setImageUrl("http://example.com/john.jpg");
        person.setTerminationDate(null); // Opcionalmente, configura la fecha de terminación si es necesario


        PersonOutputDto outputDto = person.personToPersonOutputDto();

        assertEquals(person.getIdPersona(), outputDto.getIdPersona());
        assertEquals(person.getUsuario(), outputDto.getUsuario());
        assertEquals(person.getPassword(), outputDto.getPassword());
        assertEquals(person.getName(), outputDto.getName());
        assertEquals(person.getSurname(), outputDto.getSurname());
        assertEquals(person.getCompanyEmail(), outputDto.getCompanyEmail());
        assertEquals(person.getPersonalEmail(), outputDto.getPersonalEmail());
        assertEquals(person.getCity(), outputDto.getCity());
        //assertEquals(person.isActive(), outputDto.getActive());
        assertEquals(person.getCreatedDate(), outputDto.getCreatedDate());
        assertEquals(person.getImageUrl(), outputDto.getImageUrl());
        assertEquals(person.getTerminationDate(), outputDto.getTerminationDate());
    }
}

