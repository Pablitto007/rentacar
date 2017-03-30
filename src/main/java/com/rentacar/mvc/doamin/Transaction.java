package com.rentacar.mvc.doamin;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Paweł Pigłowski (Pablitto077)
 *
 */
public class Transaction {

	private Integer transactionId;
	private Integer carId;
	private Integer customerId;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date start;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date end;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", carId=" + carId + ", customerId=" + customerId
				+ ", start=" + start + ", end=" + end + "]";
	}
	
}
