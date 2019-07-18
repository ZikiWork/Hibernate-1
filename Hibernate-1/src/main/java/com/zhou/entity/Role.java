package com.zhou.entity;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private Integer role_id;// id
	private String role_name;// ½ÇÉ«Ãû³Æ
	private String role_describe;// ÃèÊö
	private Set<User> users = new HashSet<>();

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_describe() {
		return role_describe;
	}

	public void setRole_describe(String role_describe) {
		this.role_describe = role_describe;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
