package com.example.securingweb;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "db_user")
public class DbUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String uname;

	private String upassword;

	public DbUser(String uname, String upassword) {
		this.uname = uname;
		this.upassword = upassword;
	}

	public DbUser() {

	}

	public String getName() {
		return uname;
	}

	public void setName(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return upassword;
	}

	public void setPassword(String upassword) {
		this.upassword = upassword;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, uname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DbUser other = (DbUser) obj;
		return id == other.id && Objects.equals(uname, other.uname);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", upassword=" + upassword + "]";
	}

}
