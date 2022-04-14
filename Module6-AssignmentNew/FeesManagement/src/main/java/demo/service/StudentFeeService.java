package demo.service;

import java.util.ArrayList;
import java.util.List;

import demo.model.StudentFee;
import demo.repository.StudentFeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentFeeService
{
	@Autowired
	StudentFeeRepository studentFeeRepository;
	
	public List<StudentFee> getAllStudentFee()
	{
		List<StudentFee> students = new ArrayList<StudentFee>();
		studentFeeRepository.findAll().forEach(student -> students.add(student));
		return students;
	}
	
	public StudentFee getStudentFeeById(int id)
	{
		return studentFeeRepository.findById(id).get();
	}
	
	public void createStudentFee(StudentFee studentFee)
	{
		studentFee.setRemainingFees(studentFee.getTotalFees() - studentFee.getFeesPaid());

		studentFeeRepository.save(studentFee);
	}

	public void deleteStudent(int id)
	{
		studentFeeRepository.deleteById(id);
	}

	public void updateStudentFee(StudentFee studentFee) { studentFeeRepository.save(studentFee); }
	
}
