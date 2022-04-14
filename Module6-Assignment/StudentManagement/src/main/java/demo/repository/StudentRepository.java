package demo.repository;

import org.springframework.data.repository.CrudRepository;

import demo.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>
{

}
