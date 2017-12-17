package com.krb.shakotsav.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krb.shakotsav.bean.TableItemMap;

@Repository
public interface TableItemMapRepository extends JpaRepository<TableItemMap, Long>{

}
