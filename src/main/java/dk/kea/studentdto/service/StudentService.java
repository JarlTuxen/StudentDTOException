package dk.kea.studentdto.service;

import dk.kea.studentdto.dto.StudentRequestDTO;
import dk.kea.studentdto.dto.StudentResponseDTO;
import dk.kea.studentdto.mapper.StudentMapper;
import dk.kea.studentdto.model.Student;
import dk.kea.studentdto.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        /*
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOs = new ArrayList<>();

        // Using a for-loop to convert each Student to a StudentResponseDTO
        for (Student student : students) {
            StudentResponseDTO dto = new StudentResponseDTO(student.getId(), student.getName(), student.getBornDate(), student.getBornTime());
            studentResponseDTOs.add(dto);
        }

        return studentResponseDTOs;
        */
        /*
        // Using stream and map with lambda
        return studentRepository.findAll().stream()
                .map(student -> new StudentResponseDTO(student.getId(), student.getName(), student.getBornDate(), student.getBornTime()))
                .collect(Collectors.toList());*/
        return studentRepository.findAll().stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long id) {
        /*
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
         */
        /*
        Optional<Student> optionalStudent = studentRepository.findById(id);

        // Throw RuntimeException if student is not found
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with id " + id);
        }

        Student student = optionalStudent.get();

        return new StudentResponseDTO(student.getId(), student.getName(), student.getBornDate(), student.getBornTime());
         */
        /*
        // Using stream and map with lambda
        return studentRepository.findById(id)
                .map(student -> new StudentResponseDTO(student.getId(), student.getName(), student.getBornDate(), student.getBornTime()))
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

         */
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        return studentMapper.toDTO(student);
    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        /*
        Student student = new Student();
        student.setName(studentRequestDTO.name());
        student.setPassword(studentRequestDTO.password());
        student.setBornDate(studentRequestDTO.bornDate());
        student.setBornTime(studentRequestDTO.bornTime());
         */
        /*
        // Use Builder to create student
        Student student = Student.builder()
                .name(studentRequestDTO.name())
                .password(studentRequestDTO.password())
                .bornDate(studentRequestDTO.bornDate())
                .bornTime(studentRequestDTO.bornTime())
                .build();
        */
        /*
        Student student = new Student(
                studentRequestDTO.name(),
                studentRequestDTO.password(),
                studentRequestDTO.bornDate(),
                studentRequestDTO.bornTime()
        );
        student = studentRepository.save(student);

        return new StudentResponseDTO(student.getId(), student.getName(), student.getBornDate(), student.getBornTime());
         */
        Student newStudent = studentMapper.toEntity(studentRequestDTO);
        Student savedStudent = studentRepository.save(newStudent);

        return studentMapper.toDTO(savedStudent);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        studentMapper.updateEntityFromDTO(student, studentRequestDTO);
        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toDTO(updatedStudent);
        /*
        Optional<Student> optionalStudent = studentRepository.findById(id);
        // Throw RuntimeException if student is not found
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with id " + id);
        }

        Student student = optionalStudent.get();

        student.setName(studentRequestDTO.name());
        student.setPassword(studentRequestDTO.password());
        student.setBornDate(studentRequestDTO.bornDate());
        student.setBornTime(studentRequestDTO.bornTime());

        student = studentRepository.save(student);
        return new StudentResponseDTO(student.getId(), student.getName(), student.getBornDate(), student.getBornTime());
         */

    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }
}
