package com.sample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private int id;
	private String name;
	private int age;

	@Override
	public boolean equals(Object obj) {
		return obj.equals( this.id );
	}

}
