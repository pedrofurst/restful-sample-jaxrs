package com.sample.service;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sample.exception.UserException;
import com.sample.exception.UserNotFoundException;
import com.sample.exception.UserNotSaveException;
import com.sample.model.User;

public class UserServiceTest {

	private UserService service;

	@Rule
	public ExpectedException thrown = none();

	@Before
	public void setup() {
		service = new UserService( mockUsers() );
	}

	private List<User> mockUsers() {
		return new ArrayList<User>( asList( new User( 1, "Mark", 25 ), new User( 2, "Bill", 30 ) ) );
	}

	@Test
	public void getAllUsers() {
		List<User> users = service.getAllUsers();
		assertThat( users.size(), equalTo( 2 ) );
	}

	@Test
	public void getUser() throws UserException {
		User user = service.getUser( 1 );
		assertThat( user.getId(), equalTo( 1 ) );
		assertThat( user.getName(), equalTo( "Mark" ) );
		assertThat( user.getAge(), equalTo( 25 ) );
	}

	@Test
	public void getUserWhenNotFound() throws UserException {
		thrown.expect( UserNotFoundException.class );
		thrown.expectMessage( "User not found! :/" );
		service.getUser( 3 );
	}

	@Test
	public void save() throws UserException {
		User userSaved = service.save( new User( 99, "Robert", 40 ) );
		assertThat( userSaved.getId(), equalTo( 3 ) );
		assertThat( userSaved.getName(), equalTo( "Robert" ) );
		assertThat( userSaved.getAge(), equalTo( 40 ) );
	}

	@Test
	public void saveWhenUserIsNull() throws UserException {
		thrown.expect( UserNotSaveException.class );
		thrown.expectMessage( "Couldn't save this user" );
		service.save( null );
	}

	@Test
	public void update() throws UserException {
		User user = service.getUser( 1 );
		assertThat( user.getName(), equalTo( "Mark" ) );
		assertThat( user.getAge(), equalTo( 25 ) );
		user.setName( "Robert" );
		user.setAge( 30 );
		service.update( user );
		assertThat( user.getName(), equalTo( "Robert" ) );
		assertThat( user.getAge(), equalTo( 30 ) );
	}

	@Test
	public void remove() throws UserNotFoundException {
		List<User> users = service.getAllUsers();
		assertThat( users.size(), equalTo( 2 ) );
		service.remove( 2 );
		assertThat( users.size(), equalTo( 1 ) );
	}

	@Test
	public void removeWhenIdNotExist() throws UserNotFoundException {
		List<User> users = service.getAllUsers();
		assertThat( users.size(), equalTo( 2 ) );
		service.remove( 3 );
		assertThat( users.size(), equalTo( 2 ) );
	}
}
