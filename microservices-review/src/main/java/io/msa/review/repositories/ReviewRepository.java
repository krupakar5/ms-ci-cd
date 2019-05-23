package io.msa.review.repositories;

import java.util.List;

//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.msa.review.models.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {

	public List<Review> findByProductId(String productId);

	public List<Review> findByUserName(String userName);

	public void deleteByProductId(String productId);

}
