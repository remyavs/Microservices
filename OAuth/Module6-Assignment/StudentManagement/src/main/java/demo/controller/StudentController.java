package demo.controller;

import java.util.List;

import demo.model.StudentFee;
import demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import demo.model.Student;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/student")
public class StudentController
{
	@Autowired
	StudentService studentService;
	//@Bean
	//public RestTemplate getRestTemplate()
	//{
	//	return new RestTemplate();
	//}

	@Autowired
	private RestTemplate restTemplate;

	//static final String STUDENTFEE_URL="http://localhost:8083/";
	static final String STUDENTFEE_URL="http://fee-service/";
	//http://localhost:8080/fetchstudentfee/id

	@GetMapping("/fetchstudentfee/{id}")
	public String fetchStudentFee(@PathVariable("id") int id)
	{
		StudentFee studentFee =	restTemplate.exchange(STUDENTFEE_URL+"getstudentfee/"+id, HttpMethod.GET, null, StudentFee.class).getBody();
		System.out.println("Student fee from fee management project : " + studentFee);
		return restTemplate.exchange(STUDENTFEE_URL+"getstudentfee/"+id, HttpMethod.GET, null, String.class).getBody();
	}

	//http://localhost:8080/fetchAllStudentFee

	@GetMapping("/fetchAllStudentFee")
	public String fetchAllStudentFee()
	{
		return restTemplate.exchange(STUDENTFEE_URL+"liststudentfee",HttpMethod.GET,null,String.class).getBody();
	}

	//http://localhost:8080/addStudentFee
	@PostMapping("/addStudentFee")
	public String addStudentFee(@RequestBody StudentFee studentFee)
	{
		return restTemplate.postForObject(STUDENTFEE_URL+"createstudentfee", studentFee, String.class);
	}

	//http://localhost:8080/liststudent
	@GetMapping("/liststudent")
	private List<Student> getAllStudent()
	{
		return studentService.getAllStudent();
	}

	//http://localhost:8080/getstudent/id
	@GetMapping("/getstudent/{id}")
	private Student getStudent(@PathVariable("id") int id)
	{
		return studentService.getStudentById(id);
	}

	//http://localhost:8080/deletestudent/id
	@DeleteMapping("/deletestudent/{id}")
	private void deleteStudent(@PathVariable("id") int id)
	{
		studentService.deleteStudent(id);
	}

	//http://localhost:8080/createstudent
	@PostMapping("/createstudent")
	private int createStudent(@RequestBody Student student)
	{
		studentService.createStudent(student);
		return student.getId();
	}

	//http://localhost:8080/updatestudent
	@PostMapping("/updatestudent")
	private int updateStudent(@RequestBody Student student)
	{
		studentService.updateStudent(student);
		return student.getId();
	}
}
