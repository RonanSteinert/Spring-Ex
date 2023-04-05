package co.develhope.crudTestExercise.Repositories;

import co.develhope.crudTestExercise.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
