package com.company.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQuery(name = Department.ALL_DEPARTMENTS, query = "from Department")
public class Department {

	public static final String ALL_DEPARTMENTS = "allDepartments";

	@Id
	@GenericGenerator(name = "simplecms", strategy = "increment")
	@GeneratedValue(generator = "simplecms")
	// @XmlTransient
	private Long id;

	private String name;
	private String room;
	private String chief;
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Employee> emps;

	public String getName() {
		return name;
	}

	public Department setName(String name) {
		this.name = name;
		return this;
	}

	public String getRoom() {
		return room;
	}

	public Department setRoom(String room) {
		this.room = room;
		return this;
	}

	public String getChief() {
		return chief;
	}

	public Department setChief(String chief) {
		this.chief = chief;
		return this;
	}
}
