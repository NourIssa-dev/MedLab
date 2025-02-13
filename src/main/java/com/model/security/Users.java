package com.model.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

//@NamedQueries({
//        @NamedQuery(name = Users.SELECT_USER, query = " select users from  Users users where  users.username  =:username"),
//
//        @NamedQuery(name = Users.USER_CHANGE_PASS, query = " update  Users  users  set users.userPassword =:newaPassword  where  users.username =:username and users.userPassword =:oldPassword   ")
//})
@Entity
@Table

public class Users implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private String user_id;
    private String user_name;
    private String user_password;
    private String user_role;


    public Users() {}


	public Users(String user_id, String user_name, String user_password, String user_role) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_role = user_role;
	}


	public String getUser_role() {
		return user_role;
	}


	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}


	public String getUser_password() {
		return user_password;
	}


	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



}
