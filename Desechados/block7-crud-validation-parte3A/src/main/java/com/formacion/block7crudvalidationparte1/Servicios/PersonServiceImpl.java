package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Entity.Profesor;
import com.formacion.block7crudvalidationparte1.Entity.Student;
import com.formacion.block7crudvalidationparte1.Excepciones.EntityNotFoundException;
import com.formacion.block7crudvalidationparte1.Excepciones.UnprocessableEntityException;
import com.formacion.block7crudvalidationparte1.Repository.PersonRepository;
import com.formacion.block7crudvalidationparte1.Repository.ProfesorRepository;
import com.formacion.block7crudvalidationparte1.Repository.ProfesorStudentRepository;
import com.formacion.block7crudvalidationparte1.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl {

    private final PersonRepository personRepository;
    private final ProfesorRepository profesorRepository;

    private final StudentRepository studentRepository;

    private final ProfesorStudentRepository profesorStudentRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, ProfesorRepository profesorRepository,
                              StudentRepository studentRepository,
                              ProfesorStudentRepository profesorStudentRepository) {
        this.personRepository = personRepository;
        this.profesorRepository = profesorRepository;
        this.studentRepository = studentRepository;
        this.profesorStudentRepository = profesorStudentRepository;
    }

    @Override
    public Person agregarPersona(Person person) {
        // Validar los campos requeridos y lanzar UnprocessableEntityException en caso de que no cumplan los requisitos
        if (person.getUsuario() == null || person.getName() == null || person.getCity() == null) {
            throw new UnprocessableEntityException("Todos los campos (usuario, name, city) deben estar presentes y no pueden estar vacíos.");
        }

        return personRepository.save(person);
    }

    @Override
    public Person searchById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public Person searchByUser(String usuario) {
        return personRepository.findByUsuario(usuario)
                .orElseThrow(() -> new EntityByNameNotFoundException(usuario));
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }



    @Override
    @Transactional
    public void deletePerson(Integer id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona with id " + id + " not found"));

        Student student = studentRepository.findByPerson(person);
        if (student != null) {
            profesorStudentRepository.deleteByStudent(student);
            studentRepository.delete(student);
        }

        Profesor profesor = profesorRepository.findByPerson(person);
        if (profesor != null) {

            List<Student> students = profesor.getStudents();
            for (Student relatedStudent : students) {
                studentRepository.delete(relatedStudent);
            }

            profesorRepository.delete(profesor);
        }

        personRepository.delete(person);
    }


    @Override
    public Person modifyPerson(int id, Person person) {

        if (person.getUsuario() == null || person.getUsuario().isEmpty() ||
                person.getName() == null || person.getName().isEmpty() ||
                person.getCity() == null || person.getCity().isEmpty()) {
            throw new UnprocessableEntityException("Todos los campos (usuario, name, city) deben estar presentes y no pueden estar vacíos.");
        }


        Person nowPerson = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));


        nowPerson.setUsuario(person.getUsuario());
        nowPerson.setPassword(person.getPassword());
        nowPerson.setName(person.getName());
        nowPerson.setSurname(person.getSurname());
        nowPerson.setCompanyEmail(person.getCompanyEmail());
        nowPerson.setPersonalEmail(person.getPersonalEmail());
        nowPerson.setCity(person.getCity());
        nowPerson.setActive(person.isActive());
        nowPerson.setCreatedDate(person.getCreatedDate());
        nowPerson.setImageUrl(person.getImageUrl());
        nowPerson.setTerminationDate(person.getTerminationDate());


        return personRepository.save(nowPerson);

    }
}
