package dk.kea.studentdtoexception.service;

import dk.kea.studentdtoexception.dto.StudentRequestDTO;
import dk.kea.studentdtoexception.dto.StudentResponseDTO;
import dk.kea.studentdtoexception.exception.StudentNotFoundException;
import dk.kea.studentdtoexception.mapper.StudentMapper;
import dk.kea.studentdtoexception.model.Student;
import dk.kea.studentdtoexception.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    // Constructor injection
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        return studentMapper.toDTO(student);
    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student newStudent = studentMapper.toEntity(studentRequestDTO);
        Student savedStudent = studentRepository.save(newStudent);

        return studentMapper.toDTO(savedStudent);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        studentMapper.updateEntityFromDTO(student, studentRequestDTO);
        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toDTO(updatedStudent);
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }
}
