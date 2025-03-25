package dk.kea.studentdtoexception.api;

import dk.kea.studentdtoexception.dto.StudentRequestDTO;
import dk.kea.studentdtoexception.dto.StudentResponseDTO;
import dk.kea.studentdtoexception.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    // Constructor injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> studentResponseDTOS = studentService.getAllStudents();
        return ResponseEntity.ok(studentResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        StudentResponseDTO studentResponseDTO = studentService.getStudentById(id);
        return ResponseEntity.ok(studentResponseDTO);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(
            @RequestBody StudentRequestDTO studentRequestDTO
    ) {
        StudentResponseDTO newStudent = studentService.createStudent(studentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDTO studentRequestDTO
    ) {
        StudentResponseDTO updatedStudent = studentService.updateStudent(id, studentRequestDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
