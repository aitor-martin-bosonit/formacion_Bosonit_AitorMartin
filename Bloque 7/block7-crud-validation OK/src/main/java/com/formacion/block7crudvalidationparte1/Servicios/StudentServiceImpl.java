package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.*;
import com.formacion.block7crudvalidationparte1.Excepciones.EntityNotFoundException;
import com.formacion.block7crudvalidationparte1.Mapper.StudentMapper;
import com.formacion.block7crudvalidationparte1.Repository.*;
import com.formacion.block7crudvalidationparte1.dto.StudentFullOutputDto;
import com.formacion.block7crudvalidationparte1.dto.StudentInputDto;
import com.formacion.block7crudvalidationparte1.dto.StudentSimpleOutputDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ProfesorRepository profesorRepository;
    private final PersonRepository personRepository;
    private final StudentMapper studentMapper;
    private final PersonService personService;
    private final ProfesorStudentRepository profesorStudentRepository;
    private final ProfesorService profesorService;

    private final AsignaturaRepository asignaturaRepository;


    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ProfesorRepository profesorRepository, PersonRepository personRepository, StudentMapper studentMapper, PersonService personService, ProfesorStudentRepository profesorStudentRepository, ProfesorService profesorService) {
        this.studentRepository = studentRepository;
        this.profesorRepository = profesorRepository;
        this.personRepository = personRepository;
        this.studentMapper = studentMapper;
        this.personService = personService;
        this.profesorStudentRepository = profesorStudentRepository;
        this.profesorService = profesorService;
        asignaturaRepository = null;
    }
    

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteStudent(Integer idStudent) throws EntityNotFoundException {
        Student student = studentRepository.findByIdStudent(idStudent)
                .orElseThrow(() -> new EntityNotFoundException("El estudiante con ID: " + idStudent + " NOT FOUND."));


        List<ProfesorStudent> profesorStudents = profesorStudentRepository.findByStudent(student);
        if (!profesorStudents.isEmpty()) {
            profesorStudentRepository.deleteByStudent(student);
        }


        List<Asignatura> asignaturas = student.getAsignaturas();
        for(Asignatura asignatura : asignaturas) {
            asignatura.getStudents().remove(student);
        }
        student.getAsignaturas().clear();
        studentRepository.save(student);


        studentRepository.delete(student);
    }


    @Override
    public StudentFullOutputDto getStudentFullDetails(Integer id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.map(student -> {
            StudentFullOutputDto studentFullDTO = new StudentFullOutputDto();
            studentFullDTO.setIdStudent(student.getIdStudent());
            studentFullDTO.setNumHoursWeek(student.getNumHoursWeek());
            studentFullDTO.setComments(student.getComments());


            Integer idPerson = student.getPerson().getIdPerson();
            Optional<Person> personOptional = personRepository.findById(idPerson);
            if (personOptional.isPresent()) {
                Person person = personOptional.get();
                studentFullDTO.setIdPerson(person.getIdPerson());
                studentFullDTO.setUsuario(person.getUsuario());
                studentFullDTO.setName(person.getName());
                studentFullDTO.setSurname(person.getSurname());
                studentFullDTO.setCompanyEmail(person.getCompanyEmail());
                studentFullDTO.setPersonalEmail(person.getPersonalEmail());
                studentFullDTO.setCity(person.getCity());
                studentFullDTO.setActive(person.isActive());
                studentFullDTO.setCreatedDate(Date.from(person.getCreatedDate()));
                studentFullDTO.setImageUrl(person.getImageUrl());
                studentFullDTO.setTerminationDate(Date.from(person.getTerminationDate()));
            }

            return studentFullDTO;
        }).orElse(null);
    }


    @Override
    public StudentInputDto agregarEstudiante(StudentInputDto studentInputDto) {

        Person person = personService.searchById(studentInputDto.getIdPerson());
        if (person == null) {
            throw new EntityNotFoundException("Persona with id " + studentInputDto.getIdPerson() + " not found");
        }


        Profesor profesor = null;
        if (studentInputDto.getIdProfesor() != null) {
            profesor = profesorRepository.findById(studentInputDto.getIdProfesor())
                    .orElseThrow(() -> new EntityNotFoundException("Profesor with id " + studentInputDto.getIdProfesor() + " not found"));
        }


        Student student = studentMapper.toEntity(studentInputDto);


        student.setPerson(person);


        if (profesor != null) {

            student.setProfesor(profesor);
            profesor.getStudents().add(student);
        }


        Student newStudent = saveStudent(student);


        if (profesor != null) {

            student.setProfesor(profesor);


            ProfesorStudent profesorStudent = new ProfesorStudent();
            profesorStudent.setProfesor(profesor);
            profesorStudent.setStudent(newStudent);
            profesorStudentRepository.save(profesorStudent);


            profesor.getProfesorStudents().add(profesorStudent);
            profesorService.saveProfesor(profesor);
        }


        return studentMapper.toDTO(newStudent);

    }

    @Override
    public StudentInputDto getStudentDTOById(Integer id) {
        return null;
    }

    @Override
    public List<StudentInputDto> getStudentsDTOByName(String name) {
        return null;
    }

    @Override
    public StudentInputDto getStudentDtoById(Integer id) {
        Student student = getStudentById(id);
        if (student != null) {
            return studentMapper.toDTO(student);
        }
        return null;
    }
    @Override
    public List<StudentInputDto> getStudentsDtoByName(String name) {

        List<Student> students = studentRepository.findByPersonName(name);


        return students.stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentFullOutputDto> getStudentFullDetailsByName(String name) {
        List<Student> students = studentRepository.findByPerson_Name(name);
        return students.stream()
                .map(student -> getStudentFullDetails(student.getIdStudent()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void asignarAsignaturasStudent(Integer idStudent, List<Integer> idsAsignaturas) {
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException(idStudent.toString()));
        List<Asignatura> asignaturas = asignaturaRepository.findAllById(idsAsignaturas);
        student.getAsignaturas().addAll(asignaturas);
        asignaturas.forEach(a -> a.getStudents().add(student));
        studentRepository.save(student);
    }

    @Transactional
    public void desasignarAsignaturasStudent(Integer idStudent, List<Integer> idsAsignaturas) {
        Student student = studentRepository.findById(idStudent).orElseThrow(() -> new EntityNotFoundException(idStudent.toString()));
        List<Asignatura> asignaturas = asignaturaRepository.findAllById(idsAsignaturas);
        student.getAsignaturas().removeAll(asignaturas);
        asignaturas.forEach(a -> a.getStudents().remove(student));
        studentRepository.save(student);
    }
    @Override
    public List<StudentFullOutputDto> getStudentFullDetails() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> getStudentFullDetails(student.getIdStudent()))
                .collect(Collectors.toList());
    }

    public List<StudentSimpleOutputDto> getAllStudentsSimpleDetails() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToSimpleDto)
                .collect(Collectors.toList());
    }

    private StudentSimpleOutputDto convertToSimpleDto(Student entity) {
        StudentSimpleOutputDto dto = new StudentSimpleOutputDto();
        dto.setIdStudent(entity.getIdStudent());
        dto.setNumHoursWeek(entity.getNumHoursWeek());
        dto.setComments(entity.getComments());
        return dto;
    }

}
