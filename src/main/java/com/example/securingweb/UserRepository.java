package com.example.securingweb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<DbUser, Long> {

	DbUser findByUname(String name);

}
