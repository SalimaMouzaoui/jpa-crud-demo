package dz.selma.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dz.selma.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	// define field for entitymanager
	private EntityManager entityManager;

	// se up constructor injection
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {

		// create a query
		Query query = entityManager.createQuery("from Employee");

		// execute query and get result list
		List<Employee> employees = query.getResultList();

		// return the results

		return employees;
	}

	@Override
	public Employee findById(int id) {
		
		// get the employee
		Employee employee = entityManager.find(Employee.class, id);
		
		// return employe
		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		// save or update employee
		Employee dbEmployee = entityManager.merge(employee);
		
		// update with id from db
		employee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int id) {
		
		Query query = entityManager.createQuery("delete from Employee e where e.id = :idEmployee");
		
		query.setParameter("idEmployee", id);
		
		query.executeUpdate();
	}

}
