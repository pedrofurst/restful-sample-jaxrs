package com.sample.service;

import static java.lang.Integer.compare;

import java.util.List;

import com.sample.exception.UserNotFoundException;
import com.sample.exception.UserNotSaveException;
import com.sample.model.User;

public class UserService {

	static List<User> users;

	public UserService(List<User> users) {
		UserService.users = users;
	}

	public UserService() {
		if (users == null)
			users = new UserDataProvider().retrieve();
	}

	public List<User> getAllUsers() {
		return users;
	}

	public User getUser(int id) throws UserNotFoundException {
		return users.stream().filter( user -> user.getId() == id )
				.findFirst()
				.orElseThrow( () -> new UserNotFoundException() );
	}

	public User save(User user) throws UserNotSaveException {
		validateUser( user );
		user.setId( getLastId() + 1 );
		users.add( user );
		return user;
	}

	public User update(User user) {
		users.set( users.indexOf( user ), user );
		return user;
	}

	public void remove(int id) {
		users.remove( User.builder().id( id ).build() );
	}

	private void validateUser(User user) throws UserNotSaveException {
		if (user == null)
			throw new UserNotSaveException();
	}

	private Integer getLastId() {
		return users.stream()
				.max( (u1, u2) -> compare( u1.getId(), u2.getId() ) )
				.map( User::getId )
				.get();
	}

}
