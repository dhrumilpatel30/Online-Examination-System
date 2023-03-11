package com.proj.java.onlineexaminationsystem.repository;

import com.proj.java.onlineexaminationsystem.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class StudentDAO {

	private EntityManager entityManager;

	@Autowired
	public StudentDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	public Student getStudent(final int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Student student = currentSession.get(Student.class, id);
		currentSession.close();
		return student;
	}
//	public boolean validate(int student_id,String date_of_birth){
//		Session currentSession = entityManager.unwrap(Session.class);
//		TypedQuery<Student> theQuery = currentSession.createQuery("from Student where person_id = ? and date_of_birth = ?", Student.class);
//		theQuery.
//		List<Student> students = theQuery.getResultList();
//		return false;
//	}
	public List<Student> getStudents() {
		Session currentSession = entityManager.unwrap(Session.class);

		TypedQuery<Student> theQuery = currentSession.createQuery("from Student", Student.class);
		List<Student> students = theQuery.getResultList();
		currentSession.close();
		return students;
	}
	@Transactional
	public void addStudent(final Student student) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.persist(student);
		currentSession.close();
	}

	@Transactional
	public void updateStudent(final Student student) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.merge(student);
		currentSession.close();
	}

	@Transactional
	public void deleteStudent(final int id) {
		Session currentSession = entityManager.unwrap(Session.class);
		Student student = currentSession.get(Student.class, id);
		currentSession.remove(student);
		currentSession.close();

	}
}

