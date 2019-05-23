package io.msa.apigateway.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.msa.apigateway.models.Order;
import rx.Observable;
import rx.Subscriber;

@Service
public class OrderIntegrationService {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	public Observable<Order> createOrder(final Order order, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Order> request = new HttpEntity<>(order, headers);

		return Observable.create(new Observable.OnSubscribe<Order>() {
			@Override
			public void call(Subscriber<? super Order> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Order> orderCollection = new ParameterizedTypeReference<Order>() {
						};
						observer.onNext(restTemplate.exchange("http://order-service/order/save", HttpMethod.POST,
								request, Order.class, order).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Order>> findById(final String orderId, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(orderId, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Order>>() {
			@Override
			public void call(Subscriber<? super Collection<Order>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Order>> orderCollection = new ParameterizedTypeReference<Collection<Order>>() {
						};
						observer.onNext(restTemplate.exchange("http://order-service/order/getbyorderId/{orderId}", HttpMethod.GET,
								request, orderCollection, orderId).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Order>> getAllOrders(final String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Order> request = new HttpEntity<>(headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Order>>() {
			@Override
			public void call(Subscriber<? super Collection<Order>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Order>> orderCollection = new ParameterizedTypeReference<Collection<Order>>() {
						};
						observer.onNext(restTemplate.exchange("http://order-service/order/findall", HttpMethod.GET,
								request, orderCollection).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Order>> fetchByStatus(final String status, final String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(status, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Order>>() {
			@Override
			public void call(Subscriber<? super Collection<Order>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Order>> orderCollection = new ParameterizedTypeReference<Collection<Order>>() {
						};
						observer.onNext(restTemplate.exchange("http://order-service/order/findbystatus/{status}",
								HttpMethod.GET, request, orderCollection, status).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Order>> deleteByOrderId(final String orderId, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(orderId, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Order>>() {
			@Override
			public void call(Subscriber<? super Collection<Order>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Order>> orderCollection = new ParameterizedTypeReference<Collection<Order>>() {
						};

						observer.onNext(restTemplate.exchange("http://order-service/order/delete/{orderId}",
								HttpMethod.DELETE, request, orderCollection, orderId).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}
}
