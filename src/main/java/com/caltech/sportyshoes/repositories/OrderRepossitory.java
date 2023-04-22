package com.caltech.sportyshoes.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.caltech.sportyshoes.pojo.Order;

public interface OrderRepossitory extends JpaRepository<Order, Integer> {
	// this interface has all the crud operations with predefined methods in it
	// in this interface if we need our own query we can frame it by using JPQL
	// @Query

	String query1 = "select o from Order o where o.orderedDate =?1 "
			+ "and o.product.brand.id =?2";

	@Query(query1)
	public List<Order> getOrders(Date orderedDate, int brandID);

}
