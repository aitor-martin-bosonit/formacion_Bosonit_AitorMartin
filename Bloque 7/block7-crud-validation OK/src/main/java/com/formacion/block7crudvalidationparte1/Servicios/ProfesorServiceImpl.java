package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Entity.Profesor;
import com.formacion.block7crudvalidationparte1.Entity.ProfesorStudent;
import com.formacion.block7crudvalidationparte1.Entity.Student;
import com.formacion.block7crudvalidationparte1.Excepciones.EntityNotFoundException;
import com.formacion.block7crudvalidationparte1.Mapper.ProfesorMapper;
import com.formacion.block7crudvalidationparte1.Repository.ProfesorRepository;
import com.formacion.block7crudvalidationparte1.Repository.ProfesorStudentRepository;
import com.formacion.block7crudvalidationparte1.Repository.StudentRepository;
import com.formacion.block7crudvalidationparte1.dto.ProfesorFullOutputDto;
import com.formacion.block7crudvalidationparte1.dto.ProfesorInputDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final StudentRepository studentRepository;
    private final ProfesorStudentRepository profesorStudentRepository;
    private final ProfesorMapper profesorMapper;
    private final PersonService personService;

    @Autowired
    public ProfesorServiceImpl(ProfesorRepository profesorRepository,
                               StudentRepository studentRepository,
                               ProfesorStudentRepository profesorStudentRepository,
                               ProfesorMapper profesorMapper, PersonService personService) {
        this.profesorRepository = profesorRepository;
        this.studentRepository = studentRepository;
        this.profesorStudentRepository = profesorStudentRepository;
        this.profesorMapper = profesorMapper;
        this.personService = personService;
    }

    @Override
    public Profesor saveProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    @Override
    public Profesor getProfesorById(Integer id) {
        return profesorRepository.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void deleteProfesor(Integer idProfesor) {
        Profesor profesor = profesorRepository.findById(idProfesor)
                .orElseThrow(() -> new EntityNotFoundException(null));

        Set<ProfesorStudent> profesorStudents = profesor.getProfesorStudents();

        for (ProfesorStudent pe : profesorStudents) {
            Student student = pe.getStudent();
            student.setProfesor(null);
            studentRepository.save(student);

            profesorStudentRepository.delete(pe);
        }

        profesorRepository.delete(profesor);
    }


    @Override
    public Profesor updateProfesor(Integer id, Profesor profesor) {
        Profesor existingProfesor = profesorRepository.findById(id).orElse(null);
        if (existingProfesor != null) {
            existingProfesor.setPerson(profesor.getPerson());
            existingProfesor.setComments(profesor.getComments());
            existingProfesor.setBranch(profesor.getBranch());
            return profesorRepository.save(existingProfesor);
        }
        return null;
    }

    @Override
    public ProfesorInputDto getProfesorDtoById(Integer id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        return profesorMapper.toDTO(profesor);
    }

    @Override
    public List<ProfesorInputDto> getProfesoresDtoByName(String name) {
        List<Profesor> profesors = Collections.singletonList(profesorRepository.findByPerson(null));
        return profesorMapper.toDTOList(profesors);
    }
    @Override
    public ProfesorFullOutputDto getProfesorFullDetailsById(Integer id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
        return profesorMapper.toFullDTO(profesor);
    }

    @Override
    public List<ProfesorFullOutputDto> getProfesorFullDetailsByName(String name) {
        List<Profesor> profesors = (List<Profesor>) profesorRepository.findByPerson(null);
        return profesorMapper.toFullDTOList(profesors);
    }
    @Override
    public ProfesorInputDto createProfesor(ProfesorInputDto profesorInputDto) {


        Integer idPerson = profesorInputDto.getIdPerson();


        Person person = personService.searchById(idPerson);


        Profesor profesor = profesorMapper.toEntity(profesorInputDto);


        profesor.setPerson(person);


        Profesor newProfesor = saveProfesor(profesor);


        return profesorMapper.toDTO(newProfesor);
    }

    @Override
    public ProfesorInputDto updateProfesor(Integer id, ProfesorInputDto profesorInputDto) {
        Integer idPerson = profesorInputDto.getIdPerson();
        Person person = personService.searchById(idPerson);
        Profesor profesor = profesorMapper.toEntity(profesorInputDto);
        profesor.setPerson(person);
        Profesor updatedProfesor = updateProfesor(id, profesor);
        return profesorMapper.toDTO(updatedProfesor);
    }
    public Profesor updateProfesorDos(Integer id, Profesor profesor) {
        Profesor existingProfesor = profesorRepository.findById(id).orElse(null);
        if (existingProfesor != null) {
            existingProfesor.setPerson(profesor.getPerson());
            existingProfesor.setComments(profesor.getComments());
            existingProfesor.setBranch(profesor.getBranch());
            return profesorRepository.save(existingProfesor);
        }
        return null;
    }

}
