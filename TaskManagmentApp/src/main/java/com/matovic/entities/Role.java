package com.matovic.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
	private String name;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(joinColumns = {
		@JoinColumn(name="user_email", referencedColumnName = "email")},
		inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name"))
	private List<User> users;
	
	public Role() {}

	public Role(String name, List<User> users) {
		this.name = name;
		this.users = users;
	}
	
	public Role(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
