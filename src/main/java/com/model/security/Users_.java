package com.model.security;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2024-03-09T10:56:47.983+0200")
@StaticMetamodel(Users.class)
public class Users_ {
	public static volatile SingularAttribute<Users, String> user_id;
	public static volatile SingularAttribute<Users, String> user_name;
	public static volatile SingularAttribute<Users, String> user_password;
	public static volatile SingularAttribute<Users, String> user_role;
}
