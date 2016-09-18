package com.sample.resource;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sample.exception.UserNotFoundException;
import com.sample.exception.UserNotSaveException;
import com.sample.model.User;
import com.sample.service.UserService;

@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("users")
public class UserResource {

	private UserService service = new UserService();

	@GET
	public List<User> getUsers() throws UserNotFoundException {
		return service.getAllUsers();
	}

	@GET
	@Path("{id}")
	public User getUserById(@PathParam("id") int id) throws UserNotFoundException {
		return service.getUser( id );
	}

	@POST
	public User save(User user) throws UserNotSaveException {
		return service.save( user );
	}

	@PUT
	public User update(User user) {
		return service.update( user );
	}

	@DELETE
	@Path("{id}")
	public void remove(@PathParam("id") int id) {
		service.remove( id );
	}

}
