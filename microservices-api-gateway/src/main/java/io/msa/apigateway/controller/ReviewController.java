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

import io.msa.apigateway.models.Review;
import io.msa.apigateway.services.ReviewsIntegrationService;
import io.swagger.annotations.Api;
import rx.Observable;
import rx.Observer;

@CrossOrigin
@RestController
@RequestMapping("/review")
@Api(value = "REVIEW", description = "API Gateway ReviewController")
public class ReviewController {

	@Autowired
	ReviewsIntegrationService reviewsIntegrationService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public DeferredResult<Review> SaveReview(@RequestBody Review review, HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Review> lstReview = reviewsIntegrationService.SaveReview(review, token);
		return toDeferredResultt(lstReview);
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public DeferredResult<Collection<Review>> findByProductId(@PathVariable String productId,
			HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Review>> lstReview = reviewsIntegrationService.findByProductId(productId, token);
		return toDeferredResult(lstReview);
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public DeferredResult<Collection<Review>> getAllReview(HttpServletRequest request, final String token) {
		String tokens = request.getHeader("authorization");
		Observable<Collection<Review>> lstReview = reviewsIntegrationService.getAllReviews(tokens);
		return toDeferredResult(lstReview);
	}

	@RequestMapping(value = "/findbyname/{userName}", method = RequestMethod.GET)
	public DeferredResult<Collection<Review>> findByStatus(@PathVariable String userName, HttpServletRequest request) {
		String tokens = request.getHeader("authorization");
		Observable<Collection<Review>> lstReview = reviewsIntegrationService.fetchByUserName(userName, tokens);
		return toDeferredResult(lstReview);
	}

	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
	public DeferredResult<Collection<Review>> deleteByReviewId(@PathVariable String productId,
			HttpServletRequest request) {
		String token = request.getHeader("authorization");
		Observable<Collection<Review>> lstOrder = reviewsIntegrationService.deleteByProductId(productId, token);
		return toDeferredResult(lstOrder);

	}

	public DeferredResult<Collection<Review>> toDeferredResult(Observable<Collection<Review>> lstReview) {
		DeferredResult<Collection<Review>> result = new DeferredResult<>();
		lstReview.subscribe(new Observer<Collection<Review>>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Collection<Review> lstReview) {
				result.setResult(lstReview);
			}
		});
		return result;
	}

	public DeferredResult<Review> toDeferredResultt(Observable<Review> lstReview) {
		DeferredResult<Review> result = new DeferredResult<>();
		lstReview.subscribe(new Observer<Review>() {
			@Override
			public void onCompleted() {
			}

			@Override
			public void onError(Throwable throwable) {
			}

			@Override
			public void onNext(Review lstReview) {
				result.setResult(lstReview);
			}
		});
		return result;
	}
}
