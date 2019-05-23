
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
import org.springframework.web.multipart.MultipartFile;

import io.msa.apigateway.models.Content;

import rx.Observable;
import rx.Subscriber;

@Service
public class ContentIntegrationService {

	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	public Observable<Content> saveContent(final Content content, final String token) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Content> request = new HttpEntity<>(content, headers);

		return Observable.create(new Observable.OnSubscribe<Content>() {
			@Override
			public void call(Subscriber<? super Content> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Content> contentCollection = new ParameterizedTypeReference<Content>() {
						};
						observer.onNext(restTemplate.exchange("http://content-service/content/add", HttpMethod.POST,
								request, Content.class, content).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);

				}

			}

		});
	}

	public Observable<Collection<Content>> findByContentId(final Long ctnId, final String token) {

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Long> request = new HttpEntity<>(ctnId, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Content>>() {
			@Override
			public void call(Subscriber<? super Collection<Content>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Content>> contentCollection = new ParameterizedTypeReference<Collection<Content>>() {
						};
						observer.onNext(restTemplate.exchange("http://content-service/content/getctnbyid/{ctnId}",
								HttpMethod.GET, request, contentCollection, ctnId).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Content>> getAllContents(final String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Content> request = new HttpEntity<>(headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Content>>() {
			@Override
			public void call(Subscriber<? super Collection<Content>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Content>> contentCollection = new ParameterizedTypeReference<Collection<Content>>() {
						};
						observer.onNext(restTemplate.exchange("http://content-service/content/findall", HttpMethod.GET,
								request, contentCollection).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	public Observable<Collection<Content>> fetchByName(final String name, final String token) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(name, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Content>>() {
			@Override
			public void call(Subscriber<? super Collection<Content>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Content>> contentCollection = new ParameterizedTypeReference<Collection<Content>>() {
						};
						observer.onNext(restTemplate.exchange("http://content-service/content/findbyname/{name}",
								HttpMethod.GET, request, contentCollection, name).getBody());
						observer.onCompleted();
					}
				} catch (Throwable e) {
					e.printStackTrace();
					observer.onError(e);
				}

			}

		});
	}

	
	public Observable<Collection<Content>> deleteByContentId(final Long ctnId, final String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Long> request = new HttpEntity<>(ctnId, headers);

		return Observable.create(new Observable.OnSubscribe<Collection<Content>>() {
			@Override
			public void call(Subscriber<? super Collection<Content>> observer) {
				try {

					if (!observer.isUnsubscribed()) {
						ParameterizedTypeReference<Collection<Content>> contentCollection = new ParameterizedTypeReference<Collection<Content>>() {
						};

						observer.onNext(restTemplate.exchange("http://content-service/content/delete/{ctnId}",
								HttpMethod.DELETE, request, contentCollection, ctnId).getBody());
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
	private Collection<Content> stubContent(Integer id, final String token) {
		Collection<Content> content = new ArrayList<Content>();
		return content;
	}

	@SuppressWarnings("unused")
	private Collection<Content> stubContent(String name, final String token) {
		Collection<Content> content = new ArrayList<Content>();
		return content;
	}

	@SuppressWarnings("unused")
	private Collection<Content> stubContent() {
		Collection<Content> content = new ArrayList<Content>();
		return content;
	}

}
