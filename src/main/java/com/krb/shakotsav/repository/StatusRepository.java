package com.krb.shakotsav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krb.shakotsav.bean.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{

}
