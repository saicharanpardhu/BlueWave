package com.distributedpipeline.userpersistence.domain;


import javax.persistence.*;
import java.util.Set;


@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String role;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<User> users;

    public Role(long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Role( String role) {
		super();
		
		this.role = role;
		
	}

	public Role() {
		super();
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
