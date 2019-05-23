package io.msa.apigateway.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.msa.apigateway.models.Order;
import io.msa.apigateway.services.OrderIntegrationService;
import io.swagger.annotations.Api;
import rx.Observable;
import rx.Observer;

@CrossOrigin
@RestController
@RequestMapping("/order")
@Api(value = "ORDER", description = "API Gateway OrderController")
public class OrderController {

	@Autowired
	OrderIntegrationService orderIntegrationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public DeferredResult<Order> createOrder(@RequestBody Order order, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Order> lstOrder = orderIntegrationService.createOrder(order, token);
		return toDeferredResultt(lstOrder);
	}

	@RequestMapping(value = "/getbyorderId/{orderId}", method = RequestMethod.GET)
	public DeferredResult<Collection<Order>> findById(@PathVariable String orderId, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Order>> lstOrder = orderIntegrationService.findById(orderId, token);
		return toDeferredResult(lstOrder);
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public DeferredResult<Collection<Order>> getAllOrders(HttpServletRequest request, final String token) {
		String tokens = request.getHeader("authorization");
		Observable<Collection<Order>> lstOrder = orderIntegrationService.getAllOrders(tokens);
		return toDeferredResult(lstOrder);
	}

	@RequestMapping(value = "/findbystatus/{status}", method = RequestMethod.GET)
	public DeferredResult<Collection<Order>> findByStatus(@PathVariable String status, HttpServletRequest request) {
		String tokens = request.getHeader("authorization");
		Observable<Collection<Order>> lstOrder = orderIntegrationService.fetchByStatus(status, tokens);
		return toDeferredResult(lstOrder);
	}

	@RequestMapping(value = "/delete/{orderId}", method = RequestMethod.DELETE)
	public DeferredResult<Collection<Order>> deleteByOrderId(@PathVariable String orderId, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Order>> lstOrder = orderIntegrationService.deleteByOrderId(orderId, token);
		return toDeferredResult(lstOrder);

	}

	public DeferredResult<Collection<Order>> toDeferredResult(Observable<Collection<Order>> lstOrder) {
		DeferredResult<Collection<Order>> result = new DeferredResult<>();
		lstOrder.subscribe(new Observer<Collection<Order>>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Collection<Order> lstOrder) {
				result.setResult(lstOrder);
			}
		});
		return result;
	}

	public DeferredResult<Order> toDeferredResultt(Observable<Order> lstOrder) {
		DeferredResult<Order> result = new DeferredResult<>();
		lstOrder.subscribe(new Observer<Order>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Order lstOrder) {
				result.setResult(lstOrder);
			}
		});
		return result;
	}
}
