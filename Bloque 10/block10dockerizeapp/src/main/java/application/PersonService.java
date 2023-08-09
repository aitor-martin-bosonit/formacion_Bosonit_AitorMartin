package application;

import controller.dto.PersonInputDto;
import controller.dto.PersonOutputDto;

import java.util.List;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto person);
    void deletePersonId(int id);
    PersonOutputDto updatePerson(Integer idPerson,PersonInputDto person);
    List<PersonOutputDto> getAllPersons(int pageNumber, int pageSize, String output) ;
    PersonOutputDto getPerson(int id);
    List<PersonOutputDto> getPersonsName(int pageNumber, int pageSize, String name, String output) ;

}
