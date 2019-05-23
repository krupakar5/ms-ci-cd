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

import io.msa.apigateway.models.*;
import rx.Observable;
import rx.Subscriber;

@Service
public class ContextIntegrationService {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	public Observable<Context> createContext(final Context context, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Context> request = new HttpEntity<>(context, headers);

		return Observable.create(new Observable.OnSubscribe<Context>() {
			@Override
			public void call(Subscriber<? super Context> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Context> contextCollection = new ParameterizedTypeReference<Context>() {
						};
						observer.onNext(restTemplate.exchange("http://context-service/context/add", HttpMethod.POST,
								request, Context.class, context).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Context>> findByUserId(final Integer userId, final String token) {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Integer> request = new HttpEntity<>(userId, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Context>>() {
			@Override
			public void call(Subscriber<? super Collection<Context>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Context>> contextCollection = new ParameterizedTypeReference<Collection<Context>>() {
						};
						observer.onNext(restTemplate.exchange("http://context-service/context/getctxbyuid/{userId}",
								HttpMethod.GET, request, contextCollection, userId).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Context> findCtxByIp(final Context ctx, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Context> request = new HttpEntity<>(ctx, headers);

		return Observable.create(new Observable.OnSubscribe<Context>() {
			@Override
			public void call(Subscriber<? super Context> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Context> contextCollection = new ParameterizedTypeReference<Context>() {
						};
						observer.onNext(restTemplate.exchange("http://context-service/context/getctxbyip",
								HttpMethod.POST, request, Context.class, ctx).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Context>> deleteByUserId(final Integer userId, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Integer> request = new HttpEntity<>(userId, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Context>>() {
			@Override
			public void call(Subscriber<? super Collection<Context>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Context>> contextCollection = new ParameterizedTypeReference<Collection<Context>>() {
						};
						observer.onNext(restTemplate.exchange("http://context-service/context/deletebyuid/{userId}",
								HttpMethod.DELETE, request, contextCollection, userId).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);

				}

			}

		});
	}

	public Observable<Collection<Context>> deleteById(final String id, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(id, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Context>>() {
			@Override
			public void call(Subscriber<? super Collection<Context>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Context>> contextCollection = new ParameterizedTypeReference<Collection<Context>>() {
						};
						observer.onNext(restTemplate.exchange("http://context-service/context/deletebyid/{id}",
								HttpMethod.DELETE, request, contextCollection, id).getBody());
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
	private Collection<Context> stubContent(Context context, final String token) {
		Collection<Context> context1 = new ArrayList<Context>();
		return context1;
	}

	@SuppressWarnings("unused")
	private Context stubContext(final int userId, final String token) {
		Context context = new Context();
		context.setUserId(userId);
		context.setLatitude("latitude");
		context.setLongitude("longitude");
		context.setDeviceId("deviceId");
		context.setDeviceInfo("deviceInfo");
		return context;
	}

}
