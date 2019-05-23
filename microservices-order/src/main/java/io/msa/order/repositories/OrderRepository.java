package io.msa.order.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.msa.order.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	public List<Order> findByOrderId(String orderId);

	public List<Order> findByStatus(String status);

	public void deleteByOrderId(String orderId);
}
