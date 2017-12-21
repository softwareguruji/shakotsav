package com.krb.shakotsav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krb.shakotsav.bean.Item;

@Repository("itemRepository")
public interface ItemRepository extends JpaRepository<Item, Long>{

	List<Item> findByItemName(String itemName);
}
