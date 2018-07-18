package com.gag.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Random;

public class User {

	private static final String[] ALPHABET = {	"A", "a", "B", "b", "C", "c", "D", "d", "E", "e", "F", "f", "G", "g",
												"H", "h", "I", "i", "J", "j", "K", "k", "L", "l", "M", "m", "N", "n", 
												"O", "o", "P", "p", "Q", "q", "R", "r", "S", "s", "T", "t", "U", "u", 
												"V", "v", "W", "w", "X", "x", "Y", "y", "Z", "z"};
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private int genderId;
	private int countryId;
	private String biography;
	private String photo;
	private String activationCode;
	private List<Post> posts;
	
	public User(String firstName, String lastName, String username, String password, String email, int gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.genderId = gender;
		this.activationCode = this.getRandomCode();
		this.posts=new ArrayList<>();
	}

	public User(int id, String firstName, String lastName, String photo, String username, String password, String email,
				String biography, int gender, int country, String activationCode) {
		this(firstName, lastName, username, password, email, gender);
		this.id = id;
		this.photo = photo;
		this.biography = biography;
		this.countryId = country;
		this.activationCode = activationCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public int getGenderId() {
		return genderId;
	}
	
	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}
	
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public void deletePost(Post p) {
		posts.remove(p);
	}
	
	public List<Post> getPosts(){
		return Collections.unmodifiableList(posts);
	}
	
	private String getRandomCode() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 10; i++) {
			if (i%2 == 0) {
				sb.append(new Random().nextInt(10));
			} else {
				sb.append(ALPHABET[new Random().nextInt(ALPHABET.length)]);
			}
		}
		return sb.toString();
	}
}