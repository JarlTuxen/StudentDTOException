package dk.kea.studentdtoexception.repository;

import dk.kea.studentdtoexception.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
