package io.msa.review.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.msa.review.models.Review;
import io.msa.review.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public Review saveReview(Review review) {
		return reviewRepository.save(review);

	}

	public List<Review> findByProductId(String productId) {
		return reviewRepository.findByProductId(productId);
	}

	public Iterable<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	public List<Review> fetchByUsername(String userName) {
		return reviewRepository.findByUserName(userName);
	}

	public void deleteByProductId(String productId) {
		reviewRepository.deleteByProductId(productId);
	}
}
