package dk.kea.studentdtoexception.config;

import dk.kea.studentdtoexception.model.Student;
import dk.kea.studentdtoexception.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(String... args) {
        //ChatGPT testdata
        studentRepository.save(new Student("Bruce", "TheAppBruce!",LocalDate.of(2010, 11, 12), LocalTime.of(10, 11, 12)));
        studentRepository.save(new Student("Alice", "1234",LocalDate.of(2008, 5, 22), LocalTime.of(8, 30, 45)));
        studentRepository.save(new Student("John", "", LocalDate.of(2012, 7, 9), LocalTime.of(15, 20, 30)));
        studentRepository.save(new Student("Diana", "password", LocalDate.of(2009, 1, 19), LocalTime.of(11, 45, 0)));
        studentRepository.save(new Student("Clark", "admin", LocalDate.of(2011, 3, 14), LocalTime.of(9, 15, 10)));
        studentRepository.save(new Student("Emma", "123456", LocalDate.of(2013, 6, 4), LocalTime.of(14, 5, 25)));
        studentRepository.save(new Student("Oliver", "letmein", LocalDate.of(2007, 8, 27), LocalTime.of(16, 50, 40)));
        studentRepository.save(new Student("Sophia", "qwerty", LocalDate.of(2014, 10, 2), LocalTime.of(7, 20, 55)));
        studentRepository.save(new Student("Liam", "superman", LocalDate.of(2010, 12, 31), LocalTime.of(13, 35, 15)));
        studentRepository.save(new Student("Ava", "trustno1", LocalDate.of(2006, 9, 18), LocalTime.of(17, 25, 35)));
    }
}
