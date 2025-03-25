package dk.kea.studentdtoexception.mapper;

import dk.kea.studentdtoexception.dto.StudentRequestDTO;
import dk.kea.studentdtoexception.dto.StudentResponseDTO;
import dk.kea.studentdtoexception.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    // Konverter en Student entity til en StudentResponseDTO
    public StudentResponseDTO toDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getBornDate(),
                student.getBornTime()
        );
    }

    // Konverter StudentRequestDTO til Student entity til create
    public Student toEntity(StudentRequestDTO studentRequestDTO) {
        return new Student(
                studentRequestDTO.name(),
                studentRequestDTO.password(),
                studentRequestDTO.bornDate(),
                studentRequestDTO.bornTime()
        );
    }

    // Updater en eksisterende Student entity med data fra StudentRequestDTO
    public void updateEntityFromDTO(Student student, StudentRequestDTO studentRequestDTO) {
        student.setName(studentRequestDTO.name());
        student.setPassword(studentRequestDTO.password());
        student.setBornDate(studentRequestDTO.bornDate());
        student.setBornTime(studentRequestDTO.bornTime());
    }
}