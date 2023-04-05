package co.develhope.crudTestExercise.Services;

import co.develhope.crudTestExercise.Entities.Student;
import co.develhope.crudTestExercise.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student setStudentWorkingStatus(Long userId, Boolean isWorking){
        Optional<Student> user = studentRepository.findById(userId);
        if(!user.isPresent()) return null;
        user.get().setIsWorking(isWorking);
        return studentRepository.save(user.get());

    }
}
