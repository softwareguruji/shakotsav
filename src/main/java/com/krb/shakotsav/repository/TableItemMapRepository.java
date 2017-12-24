package com.krb.shakotsav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krb.shakotsav.bean.TableItemMap;
import com.krb.shakotsav.bean.TableItemPK;

@Repository
public interface TableItemMapRepository extends JpaRepository<TableItemMap, Long>{
	List<TableItemMap> findByTableItem(TableItemPK tableitem);
}
