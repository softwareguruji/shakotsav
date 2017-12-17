package com.krb.shakotsav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krb.shakotsav.bean.User;
import java.lang.String;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByUserName(String username);
	
}
