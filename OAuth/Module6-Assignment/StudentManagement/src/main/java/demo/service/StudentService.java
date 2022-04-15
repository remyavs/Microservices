package demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.model.Student;
import demo.repository.StudentRepository;

@Service
public class StudentService
{
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAllStudent()
	{
		List<Student> students = new ArrayList<Student>();
		studentRepository.findAll().forEach(student -> students.add(student));
		return students;
	}
	
	public Student getStudentById(int id)
	{
		return studentRepository.findById(id).get();
	}
	
	public void createStudent(Student student)
	{
		studentRepository.save(student);
	}

	public void deleteStudent(int id)
	{
		studentRepository.deleteById(id);
	}

	public void updateStudent(Student student) { studentRepository.save(student); }
	
}
