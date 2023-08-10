import controller.dto.PersonInputDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonInputDtoTest {

    @Test
    public void testConstructorAndGetters() {
        String usuario = "john_doe";
        String password = "securePassword";
        String name = "John";
        String surname = "Doe";
        String companyEmail = "john.doe@company.com";
        String personalEmail = "john.doe@example.com";
        String city = "New York";
        Boolean active = true;
        String imageUrl = "https://example.com/john.jpg";

        PersonInputDto personInputDto = new PersonInputDto(
                usuario, password, name, surname, companyEmail, personalEmail,
                city, active, null, imageUrl, null
        );

        assertNotNull(personInputDto);
        assertEquals(usuario, personInputDto.getUsuario());
        assertEquals(password, personInputDto.getPassword());
        assertEquals(name, personInputDto.getName());
        assertEquals(surname, personInputDto.getSurname());
        assertEquals(companyEmail, personInputDto.getCompanyEmail());
        assertEquals(personalEmail, personInputDto.getPersonalEmail());
        assertEquals(city, personInputDto.getCity());
        assertEquals(active, personInputDto.getActive());
        assertEquals(imageUrl, personInputDto.getImageUrl());
    }

    // Add more test methods as needed for other functionality

}
