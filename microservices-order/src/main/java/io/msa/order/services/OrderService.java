package io.msa.order.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.msa.order.models.Order;
import io.msa.order.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> fetchById(String orderId) {
		return orderRepository.findByOrderId(orderId);
	}

	public Iterable<Order> findAll() {
		return orderRepository.findAll();
	}

	public List<Order> fetchByStatus(String status) {
		return orderRepository.findByStatus(status);
	}

	public void deleteById(String orderId) {
		orderRepository.deleteByOrderId(orderId);
	}
}
