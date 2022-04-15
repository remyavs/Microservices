package demo.controller;

import java.util.List;

import demo.model.StudentFee;
import demo.service.StudentFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/fee")
public class StudentFeeController
{
	@Autowired
	StudentFeeService studentFeeService;

	//http://localhost:8083/liststudentfee
	@GetMapping("/liststudentfee")
	private List<StudentFee> getAllStudent()
	{
		return studentFeeService.getAllStudentFee();
	}

	//http://localhost:8083/getstudentfee/id
	@GetMapping("/getstudentfee/{id}")
	private StudentFee getStudent(@PathVariable("id") int id)
	{
		return studentFeeService.getStudentFeeById(id);
	}

	//http://localhost:8083/deletestudent
	@DeleteMapping("/deletestudent/{id}")
	private void deleteStudent(@PathVariable("id") int id)
	{
		studentFeeService.deleteStudent(id);
	}

	//http://localhost:8083/createstudentfee
	@PostMapping("/createstudentfee")
	private int createStudent(@RequestBody StudentFee student)
	{
		studentFeeService.createStudentFee(student);
		return student.getId();
	}
	//http://localhost:8083/updatestudentfee
	@PostMapping("/updatestudentfee")
	private int updateStudent(@RequestBody StudentFee student)
	{
		studentFeeService.updateStudentFee(student);
		return student.getId();
	}
}
