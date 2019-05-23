package io.msa.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.msa.order.models.Order;
import io.msa.order.services.OrderService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Order createOrder(@RequestBody Order order) {
		return orderService.saveOrder(order);
	}

	@RequestMapping(value = "/getbyorderId/{orderId}", method = RequestMethod.GET)
	public List<Order> findByOrderId(@PathVariable String orderId) {
		return orderService.fetchById(orderId);
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public Iterable<Order> getAllOrders() {
		return orderService.findAll();

	}

	@RequestMapping(value = "/findbystatus/{status}", method = RequestMethod.GET)
	public List<Order> fetchByStatus(@PathVariable String status) {
		return orderService.fetchByStatus(status);
	}

	@RequestMapping(value = "/delete/{orderId}", method = RequestMethod.DELETE)
	public void deleteByOrderId(@PathVariable String orderId) {
		orderService.deleteById(orderId);

	}

}
