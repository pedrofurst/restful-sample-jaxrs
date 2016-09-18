package com.sample.service;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import com.sample.model.User;

import lombok.Getter;

@Getter
public class UserDataProvider {

	private static List<User> users;

	public List<User> retrieve() {
		if (users == null)
			users = new ArrayList<User>( asList( buildUser1(), buildUser2(), buildUser3(), buildUser4() ) );
		return users;
	}

	private User buildUser1() {
		return User.builder()
				.id( 1 )
				.name( "Bill" )
				.age( 15 )
				.build();
	}

	private User buildUser2() {
		return User.builder()
				.id( 2 )
				.name( "Mark" )
				.age( 20 )
				.build();
	}

	private User buildUser3() {
		return User.builder()
				.id( 3 )
				.name( "Steve" )
				.age( 25 )
				.build();
	}

	private User buildUser4() {
		return User.builder()
				.id( 4 )
				.name( "John" )
				.age( 30 )
				.build();
	}

}
