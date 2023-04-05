package co.develhope.crudTestExercise.Controllers;

import co.develhope.crudTestExercise.Entities.Student;
import co.develhope.crudTestExercise.Repositories.StudentRepository;
import co.develhope.crudTestExercise.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;


    @PostMapping("")
    public @ResponseBody Student create(@RequestBody Student student){
        return studentRepository.save(student);

    }

    @GetMapping("/")
    public @ResponseBody List<Student> getList(){
        return studentRepository.findAll();

    }

    @GetMapping("/{id}")
    public @ResponseBody Student getSingle(@PathVariable long id){
        Optional<Student> user = studentRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public  @ResponseBody Student update(@PathVariable long id, @RequestBody @NonNull Student student){
        student.setId(id);
        return studentRepository.save(student);

    }

    @PutMapping("/{id}/working")
    public @ResponseBody Student setUserWorking(@PathVariable long id, @RequestParam("working") boolean isWorking){
        return studentService.setStudentWorkingStatus(id, isWorking);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        studentRepository.deleteById(id);

    }
}
