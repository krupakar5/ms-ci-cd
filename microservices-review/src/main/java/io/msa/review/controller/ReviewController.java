package io.msa.review.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.msa.review.models.Review;
import io.msa.review.services.ReviewService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("/review")
public class ReviewController {

	private static final Logger log = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	private ReviewService reviewService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Review SaveReview(@RequestBody Review review) {
		return reviewService.saveReview(review);
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public List<Review> fetchById(@PathVariable("productId") String productId) {
		return reviewService.findByProductId(productId);

	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public Iterable<Review> getAllReviews() {
		return reviewService.getAllReviews();
	}

	@RequestMapping(value = "/findbyname/{userName}", method = RequestMethod.GET)
	public List<Review> fetchByUserName(@PathVariable String userName) {
		return reviewService.fetchByUsername(userName);
	}

	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
	public void deleteByProductId(@PathVariable String productId) {
			reviewService.deleteByProductId(productId);
	}

}
