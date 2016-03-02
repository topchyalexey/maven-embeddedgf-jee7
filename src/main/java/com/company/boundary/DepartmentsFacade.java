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

import com.company.model.Department;

@Stateless
@Path("deps")
public class DepartmentsFacade {

	@EJB
	SimpleCMSService service;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("all")
	public List<Department> list() {
		return service.findAllDepartments();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Department get(@PathParam("id") Long id) {
		return service.getDepartment(id);
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Department add(@FormParam("name") String name,
			@FormParam("room") String room, @FormParam("chief") String chief) {
		return service.createDeparment(name, room, chief);
	}

	@POST
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Department update(@PathParam("id") Long id,
			@FormParam("name") String name, @FormParam("room") String room,
			@FormParam("chief") String chief) {
		Department d = get(id);
		if (d != null) {
			d.setName(name);
			d.setChief(chief);
			d.setRoom(room);
			return service.updateDepartment(d);
		}
		return d;
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		service.deleteDepartment(id);
	}
}
