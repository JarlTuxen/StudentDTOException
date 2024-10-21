package dk.kea.studentdto.repository;

import dk.kea.studentdto.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
