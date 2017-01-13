package com.ap.model;

public class User {

	private Long id;
	private String stringId;
	private String firstName;
	private String lastName;
	private String screen_name;
	private String email;

	public String getStringId() {
		return stringId;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public User(long id, String stringId, String firstName, String lastName, String screen_name, String email) {
		this.id = id;
		this.stringId = stringId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.screen_name = screen_name;
		this.email = email;
	}

	public User(String stringId, String firstName, String lastName) {
		this.stringId = stringId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public void setStringId(String stringId) {
		this.stringId = stringId;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public User(long id, String firstName, String lastName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User() {
	}

}
