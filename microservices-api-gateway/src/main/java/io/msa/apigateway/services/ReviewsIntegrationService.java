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
import io.msa.apigateway.models.Review;

import rx.Observable;
import rx.Subscriber;

@Service
public class ReviewsIntegrationService {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	public Observable<Review> SaveReview(final Review review, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Review> request = new HttpEntity<>(review, headers);

		return Observable.create(new Observable.OnSubscribe<Review>() {
			@Override
			public void call(Subscriber<? super Review> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Review> reviewCollection = new ParameterizedTypeReference<Review>() {
						};
						observer.onNext(restTemplate.exchange("http://review-service/review/add", HttpMethod.POST,
								request, Review.class, review).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Review>> findByProductId(final String productId, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(productId, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Review>>() {
			@Override
			public void call(Subscriber<? super Collection<Review>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Review>> reviewCollection = new ParameterizedTypeReference<Collection<Review>>() {
						};
						observer.onNext(restTemplate.exchange("http://review-service/review/{productId}",
								HttpMethod.GET, request, reviewCollection, productId).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Review>> getAllReviews(final String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Order> request = new HttpEntity<>(headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Review>>() {
			@Override
			public void call(Subscriber<? super Collection<Review>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Review>> reviewCollection = new ParameterizedTypeReference<Collection<Review>>() {
						};
						observer.onNext(restTemplate.exchange("http://review-service/review/findall", HttpMethod.GET,
								request, reviewCollection).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Review>> fetchByUserName(final String userName, final String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(userName, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Review>>() {
			@Override
			public void call(Subscriber<? super Collection<Review>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Review>> reviewCollection = new ParameterizedTypeReference<Collection<Review>>() {
						};
						observer.onNext(restTemplate.exchange("http://review-service/review/findbyname/{userName}",
								HttpMethod.GET, request, reviewCollection, userName).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Review>> deleteByProductId(final String productId, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(productId, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Review>>() {
			@Override
			public void call(Subscriber<? super Collection<Review>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Review>> reviewCollection = new ParameterizedTypeReference<Collection<Review>>() {
						};

						observer.onNext(restTemplate.exchange("http://review-service/review/delete/{productId}",
								HttpMethod.DELETE, request, reviewCollection, productId).getBody());
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
