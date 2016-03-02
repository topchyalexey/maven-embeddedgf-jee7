package com.company.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@XmlRootElement
@Entity
@NamedQueries({
		@NamedQuery(name = Employee.FIND_ALL_EMPLOYEES_FOR_DEPARTMENT, query = "from Employee where department = :department"),
		@NamedQuery(name = Employee.FIND_ALL, query = "from Employee") })
public class Employee {
	public static final String FIND_ALL_EMPLOYEES_FOR_DEPARTMENT = "findAllEmployeesForDepartment";
	public static final String FIND_ALL = "findAll";
	@Id
	@GenericGenerator(name = "simplecms", strategy = "increment")
	@GeneratedValue(generator = "simplecms")
	private Long id;

	private String fio;
	@ManyToOne
	private Department department;
	private String phone;
	private Integer salary;

	public String getFio() {
		return fio;
	}

	public Employee setFio(String fio) {
		this.fio = fio;
		return this;
	}

	public Department getDepartment() {
		return department;
	}

	public Employee setDepartment(Department department) {
		this.department = department;
		return this;
	}

	public String getPhone() {
		return phone;
	}

	public Employee setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public Integer getSalary() {
		return salary;
	}

	public Employee setSalary(Integer salary) {
		this.salary = salary;
		return this;
	}
}
