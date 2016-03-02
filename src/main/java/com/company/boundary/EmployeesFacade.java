package com.company.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.model.Employee;

@Stateless
@Path("emps")
public class EmployeesFacade {

	@EJB
	SimpleCMSService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("all")
	public List<Employee> list() {
		return service.findAllEmployees();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Employee get(@PathParam("id") Long id) {
		return service.getEmployee(id);
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Employee add(@FormParam("departmentId") Long departmentId,
			@FormParam("fio") String fio, @FormParam("phone") String phone,
			@FormParam("salary") Integer salary) {
		return service.createEmployee(fio, service.getDepartment(departmentId),
				phone, salary);
	}

	@POST
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Employee update(@PathParam("id") Long id,
			@FormParam("departmentId") Long departmentId,
			@FormParam("fio") String fio, @FormParam("phone") String phone,
			@FormParam("salary") Integer salary) {
		Employee e = get(id);
		if (e != null) {
			if (departmentId != null) {
				e.setDepartment(service.getDepartment(departmentId));
			}
			e.setFio(fio);
			e.setPhone(phone);
			return service.updateEmployee(e);
		}
		return e;
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		service.deleteEmployee(id);
	}
}
