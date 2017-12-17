package com.krb.shakotsav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krb.shakotsav.bean.Role;
import java.lang.String;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByRole(String role);
}
