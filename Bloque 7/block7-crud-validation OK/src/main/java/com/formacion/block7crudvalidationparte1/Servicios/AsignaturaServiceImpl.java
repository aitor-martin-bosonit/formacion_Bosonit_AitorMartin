package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Asignatura;
import com.formacion.block7crudvalidationparte1.Entity.Student;
import com.formacion.block7crudvalidationparte1.Excepciones.EntityNotFoundException;
import com.formacion.block7crudvalidationparte1.Mapper.AsignaturaMapper;
import com.formacion.block7crudvalidationparte1.Repository.AsignaturaRepository;
import com.formacion.block7crudvalidationparte1.Repository.StudentRepository;
import com.formacion.block7crudvalidationparte1.dto.AsignaturaInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {



    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private AsignaturaMapper asignaturaMapper;


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfesorService profesorService;


    @Override
    public List<Asignatura> getAsignaturasByStudentId(Integer idStudent) {
        return asignaturaRepository.findAllByStudents_IdStudent(idStudent);
    }

    @Override
    public AsignaturaInputDto createAsignatura(AsignaturaInputDto asignaturaInputDto) {


        Asignatura newAsignatura = asignaturaMapper.toEntity(asignaturaInputDto);
        Asignatura savedAsignatura = asignaturaRepository.save(newAsignatura);
        return asignaturaMapper.toDTO(savedAsignatura);

    }


    @Override
    public ResponseEntity<?> deleteAsignatura(Integer idAsignatura) {
        Optional<Asignatura> asignatura = asignaturaRepository.findById(idAsignatura);

        if (asignatura.isPresent()) {

            List<Student> students = asignatura.orElseThrow().getStudents();


            for (Student student : students) {
                student.getAsignaturas().remove(asignatura.get());
                studentRepository.save(student);
            }


            asignaturaRepository.delete(asignatura.get());
            return new ResponseEntity<>("Asignatura eliminada.", HttpStatus.OK);
        } else {
            throw new RuntimeException("Asignatura NOT FOUND id: " + idAsignatura);
        }
    }


    @Override
    public List<AsignaturaInputDto> getAllAsignaturas() {
        List<Asignatura> asignaturas = asignaturaRepository.findAll();
        return asignaturaMapper.toDTOList(asignaturas);
    }

    @Override
    public List<Student> getStudentByAsignaturaId(Integer idAsignatura) {
        Asignatura asignatura = asignaturaRepository.findById(idAsignatura)
                .orElseThrow(() -> new EntityNotFoundException("Asignatura NOT FOUND id: " + idAsignatura));
        return asignatura.getStudents();
    }

}
