package demo.repository;

import demo.model.StudentFee;
import org.springframework.data.repository.CrudRepository;

public interface StudentFeeRepository extends CrudRepository<StudentFee, Integer>
{

}
