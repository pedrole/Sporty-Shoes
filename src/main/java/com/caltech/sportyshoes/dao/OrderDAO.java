package com.caltech.sportyshoes.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caltech.sportyshoes.pojo.Order;
import com.caltech.sportyshoes.repositories.OrderRepossitory;

@Service
public class OrderDAO {
	@Autowired
	OrderRepossitory repo;
	
	
	public Order insert(Order order) {
		return repo.save(order);
	}
	
	public List<Order> getOrders(Date orderedDate, int brandID) {
		List<Order> orders = repo.getOrders(orderedDate,brandID);
		return orders;
	}
	

}
