package io.msa.apigateway.models;

import java.util.Date;

public class Review {
	private String _id;
	private String productId;
	private String userName;
	private String name;
	private String price;
	private String review;
	private int rating;
	private Date reviewDate;

	public Review() {
	}

	public Review(String _id, String productId, String userName, String name, String price, String review, int rating,
			Date reviewDate) {
		super();
		this._id = _id;
		this.productId = productId;
		this.userName = userName;
		this.name = name;
		this.price = price;
		this.review = review;
		this.rating = rating;
		this.reviewDate = reviewDate;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

}
