import controller.dto.PersonOutputDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PersonOutputDtoTest {

    @Test
    public void testCopyConstructor() {
        Integer idPersona = 1;
        String usuario = "john_doe";
        String password = "securePassword";
        String name = "John";
        String surname = "Doe";
        String companyEmail = "john.doe@company.com";
        String personalEmail = "john.doe@example.com";
        String city = "New York";
        Boolean active = true;
        String imageUrl = "https://example.com/john.jpg";

        PersonOutputDto originalDto = new PersonOutputDto();
        originalDto.setIdPersona(idPersona);
        originalDto.setUsuario(usuario);
        originalDto.setPassword(password);
        originalDto.setName(name);
        originalDto.setSurname(surname);
        originalDto.setCompanyEmail(companyEmail);
        originalDto.setPersonalEmail(personalEmail);
        originalDto.setCity(city);
        originalDto.setActive(active);
        originalDto.setImageUrl(imageUrl);

        PersonOutputDto personOutputDto = new PersonOutputDto(originalDto);

        assertEquals(idPersona, personOutputDto.getIdPersona());
        assertEquals(usuario, personOutputDto.getUsuario());
        assertEquals(password, personOutputDto.getPassword());
        assertEquals(name, personOutputDto.getName());
        assertEquals(surname, personOutputDto.getSurname());
        assertEquals(companyEmail, personOutputDto.getCompanyEmail());
        assertEquals(personalEmail, personOutputDto.getPersonalEmail());
        assertEquals(city, personOutputDto.getCity());
        assertEquals(active, personOutputDto.getActive());
        assertEquals(imageUrl, personOutputDto.getImageUrl());
    }

    @Test
    public void testCopyConstructorWithMockito() {
        PersonOutputDto originalDto = Mockito.mock(PersonOutputDto.class);
        when(originalDto.getIdPersona()).thenReturn(1);
        when(originalDto.getUsuario()).thenReturn("john_doe");
        when(originalDto.getPassword()).thenReturn("securePassword");
        when(originalDto.getName()).thenReturn("John");
        when(originalDto.getSurname()).thenReturn("Doe");
        when(originalDto.getCompanyEmail()).thenReturn("john.doe@company.com");
        when(originalDto.getPersonalEmail()).thenReturn("john.doe@example.com");
        when(originalDto.getCity()).thenReturn("New York");
        when(originalDto.getActive()).thenReturn(true);
        when(originalDto.getImageUrl()).thenReturn("https://example.com/john.jpg");

        PersonOutputDto personOutputDto = new PersonOutputDto(originalDto);

        assertEquals(1, personOutputDto.getIdPersona());
        assertEquals("john_doe", personOutputDto.getUsuario());
        assertEquals("securePassword", personOutputDto.getPassword());
        assertEquals("John", personOutputDto.getName());
        assertEquals("Doe", personOutputDto.getSurname());
        assertEquals("john.doe@company.com", personOutputDto.getCompanyEmail());
        assertEquals("john.doe@example.com", personOutputDto.getPersonalEmail());
        assertEquals("New York", personOutputDto.getCity());
        assertEquals(true, personOutputDto.getActive());
        assertEquals("https://example.com/john.jpg", personOutputDto.getImageUrl());
    }

    // Add more test methods as needed for other functionality

}
