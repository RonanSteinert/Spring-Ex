package co.develhope.email01.services;

import co.develhope.email01.entities.Student;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    /** Metto studenti finti dato che la repository viene esposta su GitHub **/
    static List<Student> students = Arrays.asList(
            new Student("1","Ciao","Pino","pino123@gmail.com"),
            new Student("2","Mario","Rossi","lory1991@develhope.com"),
            new Student("3","Giulio","Verdi","lory1992@develhope.com"),
            new Student("4","Giovanna","Gialli","giovanna@develhope.com")
    );

    public Student getStudentById(String studentId) {
        Optional<Student> studentFromDb = students.stream().filter(student -> student.getId().equals(studentId)).findAny();
        if(studentFromDb.isPresent()){
            return studentFromDb.get();
        } else {
            return null;
        }
    }
}
