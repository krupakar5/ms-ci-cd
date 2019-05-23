package io.msa.order.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

@Document(collection = "order")
public class Order {
	@Id
	private String id;
	private String orderId;
	private String status;// Order,process,delivered,refund,canceled
	private String currencyValue;
	private String totalType;// Shipping,Handling,Tax,Product,Subtotal,Total,Credit,Refund
	private String orderType;// Order and Booking
	@CreatedDate
	private Date lastModified = new Date();
	@CreatedDate
	private Date datePurchased = new Date();
	@CreatedDate
	private Date orderDateFinished = new Date();

	public Order() {
	}

	public Order(String id, String orderId, String status, String currencyValue, String totalType, String orderType,
			Date lastModified, Date datePurchased, Date orderDateFinished) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.status = status;
		this.currencyValue = currencyValue;
		this.totalType = totalType;
		this.orderType = orderType;
		this.lastModified = lastModified;
		this.datePurchased = datePurchased;
		this.orderDateFinished = orderDateFinished;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrencyValue() {
		return currencyValue;
	}

	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}

	public String getTotalType() {
		return totalType;
	}

	public void setTotalType(String totalType) {
		this.totalType = totalType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Date getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}

	public Date getOrderDateFinished() {
		return orderDateFinished;
	}

	public void setOrderDateFinished(Date orderDateFinished) {
		this.orderDateFinished = orderDateFinished;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", status=" + status + ", currencyValue=" + currencyValue
				+ ", totalType=" + totalType + ", orderType=" + orderType + ", lastModified=" + lastModified
				+ ", datePurchased=" + datePurchased + ", orderDateFinished=" + orderDateFinished + "]";
	}

}
