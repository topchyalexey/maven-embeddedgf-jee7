package com.company.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.company.model.Department;
import com.company.model.Employee;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SimpleCMSService {

	@PersistenceContext(unitName = "pu")
	private EntityManager em;

	public Department createDeparment(String name, String room, String chief) {
		return em.merge(new Department().setName(name).setRoom(room)
				.setChief(chief));
	}

	public Employee createEmployee(String fio, Department department,
			String phone, Integer salary) {
		return em.merge(new Employee().setFio(fio).setDepartment(department)
				.setPhone(phone).setSalary(salary));

	}

	@SuppressWarnings("unchecked")
	public List<Department> findAllDepartments() {
		return em.createNamedQuery(Department.ALL_DEPARTMENTS).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployeesForDepartment(Department dep) {
		Query query = em
				.createNamedQuery(Employee.FIND_ALL_EMPLOYEES_FOR_DEPARTMENT);
		query.setParameter("department", dep);
		return query.getResultList();
	}

	public Department getDepartment(Long id) {
		return em.find(Department.class, id);
	}

	public Department updateDepartment(Department d) {
		return em.merge(d);
	}

	public void deleteDepartment(Long id) {
		em.remove(getDepartment(id));
	}

	public void deleteEmployee(Long id) {
		em.remove(getEmployee(id));
	}

	public Employee updateEmployee(Employee e) {
		return em.merge(e);
	}

	public Employee getEmployee(Long id) {
		return em.find(Employee.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		return em.createNamedQuery(Employee.FIND_ALL).getResultList();
	}
}