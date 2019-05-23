package io.msa.apigateway.models;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	private Long id;
	private String productName;
	private String decription;
	private String quantity;
	private BigDecimal currency;
	private String version;
	private String productImage;
	private Date dateAvailable;
	private boolean status;
	private String priceType;
	private String relationshipType;
	private Date reviewDate;
	private Date manufactureDate;

	public Product() {
	}

	public Product(Long id, String productName, String decription, String quantity, BigDecimal currency, String version,
			String productImage, Date dateAvailable, boolean status, String priceType, String relationshipType,
			Date reviewDate, Date manufactureDate) {
		super();
		this.id = id;
		this.productName = productName;
		this.decription = decription;
		this.quantity = quantity;
		this.currency = currency;
		this.version = version;
		this.productImage = productImage;
		this.dateAvailable = dateAvailable;
		this.status = status;
		this.priceType = priceType;
		this.relationshipType = relationshipType;
		this.reviewDate = reviewDate;
		this.manufactureDate = manufactureDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getCurrency() {
		return currency;
	}

	public void setCurrency(BigDecimal currency) {
		this.currency = currency;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Date getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(Date dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

}