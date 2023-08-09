package application;

import controller.dto.PersonInputDto;
import controller.dto.PersonOutputDto;
import entity.Person;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto person);
    void deletePersonId(int id);
    PersonOutputDto updatePerson(Integer idPerson,PersonInputDto person);
    List<PersonOutputDto> getAllPersons(int pageNumber, int pageSize, String output) ;
    PersonOutputDto getPerson(int id);
    List<PersonOutputDto> getPersonsName(int pageNumber, int pageSize, String name, String output) ;

    List<Person> searchPersons(
            String user,
            String name,
            String surname,
            LocalDateTime creationDate,
            String sortBy,
            int pageSize,
            int pageNumber);

}
