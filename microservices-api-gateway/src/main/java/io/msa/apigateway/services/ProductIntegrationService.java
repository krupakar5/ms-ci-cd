package io.msa.apigateway.services;

import java.util.ArrayList;
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

import io.msa.apigateway.models.Content;
import io.msa.apigateway.models.Product;
import rx.Observable;
import rx.Subscriber;

@Service
public class ProductIntegrationService {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	public Observable<Product> saveProduct(final Product product, final String token) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Product> request = new HttpEntity<>(product, headers);

		return Observable.create(new Observable.OnSubscribe<Product>() {
			@Override
			public void call(Subscriber<? super Product> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Product> productCollection = new ParameterizedTypeReference<Product>() {
						};
						observer.onNext(restTemplate.exchange("http://product-service/product/add", HttpMethod.POST,
								request, Product.class, product).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Product>> findByProductId(final Long id, final String token) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Long> request = new HttpEntity<>(id, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Product>>() {
			@Override
			public void call(Subscriber<? super Collection<Product>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Product>> productCollection = new ParameterizedTypeReference<Collection<Product>>() {
						};
						observer.onNext(restTemplate.exchange("http://product-service/product/{id}", HttpMethod.GET,
								request, productCollection, id).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Product>> getAllProducts(final String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Content> request = new HttpEntity<>(headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Product>>() {
			@Override
			public void call(Subscriber<? super Collection<Product>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Product>> productCollection = new ParameterizedTypeReference<Collection<Product>>() {
						};
						observer.onNext(restTemplate.exchange("http://product-service/product/findall", HttpMethod.GET,
								request, productCollection).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Product>> fetchByProductName(final String productName, final String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(productName, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Product>>() {
			@Override
			public void call(Subscriber<? super Collection<Product>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Product>> productCollection = new ParameterizedTypeReference<Collection<Product>>() {
						};
						observer.onNext(
								restTemplate.exchange("http://product-service/product/findbyProName/{productName}",
										HttpMethod.GET, request, productCollection, productName).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Product>> deleteByProductId(final Long id, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Long> request = new HttpEntity<>(id, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Product>>() {
			@Override
			public void call(Subscriber<? super Collection<Product>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Product>> productCollection = new ParameterizedTypeReference<Collection<Product>>() {
						};

						observer.onNext(restTemplate.exchange("http://product-service/product/delete/{id}",
								HttpMethod.DELETE, request, productCollection, id).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	@SuppressWarnings("unused")
	private Collection<Content> stubContent(Content content, final String token) {
		Collection<Content> content2 = new ArrayList<Content>();
		return content2;
	}

	@SuppressWarnings("unused")
	private Collection<Product> stubContent(Long id, final String token) {
		Collection<Product> product = new ArrayList<Product>();
		return product;
	}

	@SuppressWarnings("unused")
	private Collection<Product> stubContent(String productName, final String token) {
		Collection<Product> product = new ArrayList<Product>();
		return product;
	}

	@SuppressWarnings("unused")
	private Collection<Product> stubContent() {
		Collection<Product> product = new ArrayList<Product>();
		return product;
	}
}
