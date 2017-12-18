package com.krb.shakotsav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krb.shakotsav.bean.Table;
import java.lang.String;
import java.util.List;

@Repository("tableRepository")
public interface TableRepository extends JpaRepository<Table, Long>{

	List<Table> findByTableName(String tablename);
	
}
